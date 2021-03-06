package github.io.isenfireldc.misc.tileentity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileEntityBridgeBuilder extends TileEntityBase implements ITickable {
	
	private static final String name = "tile_entity_bridge_builder";
	
	private World world;
	private BlockPos start;
	private int direction;
	private int[][] slope;
	
	private int step = -1;
	private boolean building = true;
	
	private final static Block block = Blocks.COBBLESTONE;
	private final static Block stair = Blocks.STONE_STAIRS;
	
	public TileEntityBridgeBuilder() {
		super(name);
	}
	
	public TileEntityBridgeBuilder(World world, BlockPos start, int direction, int[][] slope) {
		this();
		
		this.world = world;
		this.start = start;
		this.direction = direction;
		this.slope = slope;
	};
	
	public boolean buildStep(int step) {
		try {
			build(slope[step]);
			return true;
		} catch (Exception e) {
			System.err.println(this + " buildStep: " + e);
			return false;
		}
		
	};
	
	private BlockPos increment(BlockPos pos, int direction) {
		switch (direction) {
		case 0:
			return pos.add(0, 0, 1);
		case 1:
			return pos.add(1, 0, 0);
		case 2:
			return pos.add(0, 0, -1);
		case 3:
			return pos.add(-1, 0, 0);
		};
		
		return pos;
	};
	
	public void build(int[] slope) {
		for (int i : slope) {
			if (i == 0) {
				world.setBlockState(start, block.getDefaultState());
			} else if (i == 1) {
				start = start.add(0, 1, 0);
				world.setBlockState(start, getOrientation((BlockStairs) stair, 1));
			} else if (i == -1) {
				world.setBlockState(start, getOrientation((BlockStairs) stair, -1));
				start = start.add(0, -1, 0);
			};
			
			start = increment(start, direction);
		}
	};
	
	/*
	 * Returns the orientation of the stair, based on direction
	 * 
	 * @return IBlockState of correctly oriented stair
	 */
	private IBlockState getOrientation(BlockStairs stair, int dirY) {
		//Values given by stair.getDefaultState()
		EnumFacing facing = EnumFacing.NORTH;
		BlockStairs.EnumHalf half = BlockStairs.EnumHalf.BOTTOM;
		BlockStairs.EnumShape shape = BlockStairs.EnumShape.STRAIGHT;
		
		//south: +z, east: +x, north: -z, west: -x
		//0 for +z, 1 for +x, 2 for -z, 3 for -x
		if (dirY > 0) { //going up
			switch (this.direction) {
				case 0:
					facing = EnumFacing.SOUTH;
					break;
				case 1:
					facing = EnumFacing.EAST;
					break;
				case 2:
					facing = EnumFacing.NORTH;
					break;
				case 3:
					facing = EnumFacing.WEST;
					break;
			}
		} else if (dirY < 0) { //going down
			switch (this.direction) {
				case 0:
					facing = EnumFacing.NORTH;
					break;
				case 1:
					facing = EnumFacing.WEST;
					break;
				case 2:
					facing = EnumFacing.SOUTH;
					break;
				case 3:
					facing = EnumFacing.EAST;
					break;
			}
		}
		return stair.getBlockState().getBaseState().withProperty(stair.FACING, facing).withProperty(stair.HALF, half).withProperty(stair.SHAPE, shape);
	};
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setIntArray("BlockPos", new int[] {start.getX(), start.getY(), start.getZ()});
		compound.setInteger("Direction", direction);
		
		int count = 0;
		for (int[] arr : slope) {
			String arrName = "Slope" + count;
			compound.setIntArray("Slope" + count, arr);
			count++;
		};
		compound.setInteger("Count", count);
		return super.writeToNBT(compound);
	};
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		int[] startArr = compound.getIntArray("BlockPos");
		this.start = new BlockPos(startArr[0], startArr[1], startArr[2]);
		
		this.direction = compound.getInteger("Direction");
		
		int count = compound.getInteger("Count");
		int[][] tempSlope = new int[count][];
		for (int i = 0; i <= count; i++) {
			tempSlope[i] = compound.getIntArray("Slope" + i);
		};
		this.slope = tempSlope;
		super.readFromNBT(compound);
	}

	@Override
	public void update() {
		if (building) {
			building = buildStep(++step);
		};
	};

}
