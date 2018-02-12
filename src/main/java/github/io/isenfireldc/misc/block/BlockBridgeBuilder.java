package github.io.isenfireldc.misc.block;

import java.util.Random;

import javax.annotation.Nullable;

import github.io.isenfireldc.misc.entity.EntityBridgeCreator;
import github.io.isenfireldc.misc.tileentity.TileEntityBridgeBuilder;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockBridgeBuilder extends BlockTileEntity<TileEntityBridgeBuilder> {
	
	protected final static String name = "builder#";
	
	private BlockPos start;
	private BlockPos end;
	private int direction;
	private int[][] slope;
	private int step = 0;
	
	private static final int SECTION_SIZE = 4;
	
	private boolean update = true;
	
	private static World world;
	
	private EntityBridgeCreator entityBuilder;
	
	private TileEntityBridgeBuilder builder;
	
	public BlockBridgeBuilder() {
		super(name);
	};
	
	public void build(BlockPos start, BlockPos end, int direction, World world, EntityBridgeCreator entityBuilder) {
		this.start = start;
		this.end = end;
		this.direction = direction;
		this.world = world;
		this.entityBuilder = entityBuilder;
		
		int[] sec = getSections();
		System.out.println(this + ": " + sec);
		this.slope = buildArray(sec[0], sec[1]);
		System.out.println(this + ": " + this.slope);
	};
	
	private int[] getSections() {
		int distance = entityBuilder.setDirection(start, end);
		System.out.println(this + ": " + entityBuilder.setDirection(start, end));
		int height = end.getY() - start.getY();
		
		return new int[] {distance, height};
	};
	
	/*
	 * Debugging purposes only
	 */
	public void setArrayTo(int[][] array) {
		slope = array;
	};
	
	private int[][] buildArray(int distance, int height) {
		double slope = (double)height / distance;
		boolean running = true;
		
		int c_dist = 1;
		int c_height;
		int a_height = 0;
		
		boolean incomplete = false;
		int size = (int) Math.ceil((double)distance / SECTION_SIZE);
		
		int leftovers = distance % SECTION_SIZE;
		
		int[][] result = new int[size][SECTION_SIZE];
		
		int count = 0;
		
		while (running) {
			int[] section;
			if (size - count == 1 && leftovers != 0) {
				section = new int[leftovers];
			} else {
				section = new int[SECTION_SIZE];
			};
			
			for (int i = 0; i < SECTION_SIZE; i++) {
				c_height = (int)Math.round(slope * c_dist);
				
				if (c_height - a_height >= 1) {
					section[i] = 1;
					a_height++;
				} else if (c_height - a_height <= -1) {
					section[i] = -1;
					a_height--;
				} else {
					section[i] = 0;
				};
				
				if (c_dist >= distance) {
					running = false;
					break;
				};
				
				c_dist++;
			}
			
			result[count] = section;
			count++;
			if (count >= size) {
			    running = false;
			    break;
			 };
		}
		
		return result;
	};

	@Override
	public Class<TileEntityBridgeBuilder> getTileEntityClass() {
		return TileEntityBridgeBuilder.class;
	};
	
	@Nullable
	@Override
	public TileEntityBridgeBuilder createTileEntity(World world, IBlockState state) {
		this.builder = new TileEntityBridgeBuilder(world, start, direction, slope);
		return this.builder;
	};

}
