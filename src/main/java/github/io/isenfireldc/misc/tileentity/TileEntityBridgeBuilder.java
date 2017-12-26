package github.io.isenfireldc.misc.tileentity;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileEntityBridgeBuilder {
	
	private World world;
	private BlockPos start;
	private int direction;
	private int[][] slope;
	
	private final static Block block = Blocks.STONE;
	private final static Block stair = Blocks.STONE_STAIRS;
	
	public TileEntityBridgeBuilder(World world, BlockPos start, int direction, int[][] slope) {
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
			return false;
		}
		
	};
	
	private BlockPos increment(BlockPos pos, int direction) {
		switch (direction) {
		case 0:
			pos.add(0, 0, 1);
			break;
		case 2:
			pos.add(1, 0, 0);
			break;
		case 3:
			pos.add(0, 1, -1);
			break;
		case 4:
			pos.add(-1, 0, 0);
			break;
		};
		
		return pos;
		
	};
	
	public void build(int[] slope) {
		for (int i : slope) {
			if (i == 0) {
				world.setBlockState(start, block.getDefaultState());
			} else if (i == 1) {
				world.setBlockState(start, stair.getDefaultState());	//TODO Use correct state for direction of stair
				start.add(0, 1, 0);
			}
			start = increment(start, direction);
		}
	};

}
