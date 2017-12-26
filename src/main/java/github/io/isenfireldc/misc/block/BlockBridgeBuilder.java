package github.io.isenfireldc.misc.block;

import java.util.Random;

import github.io.isenfireldc.misc.entity.EntityBridgeCreator;
import github.io.isenfireldc.misc.tileentity.TileEntityBridgeBuilder;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockBridgeBuilder extends BlockCollisionlessBase {
	
	protected final static String name = "builder";
	
	private static BlockPos start;
	private static BlockPos end;
	private static int direction;
	private int[][] slope;
	private int step = 0;
	
	private boolean update = true;
	
	private static World world;
	
	private TileEntityBridgeBuilder builder;
	
	public BlockBridgeBuilder() {
		super(name);
	};
	
	public BlockBridgeBuilder(BlockPos start, BlockPos end, int direction, World world) {
		this();
		
		this.start = start;
		this.end = end;
		this.direction = direction;
		this.world = world;
	};
	
	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		this.build();
		this.builder = new TileEntityBridgeBuilder(world, start, direction, slope);
	};
	
	@Override
	public boolean requiresUpdates() {
		return update;
	};
	
	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		update = builder.buildStep(step);
		step++;
	};
	
	public void build() {
		int[] sec = getSections();
		
		this.slope = buildArray(sec[0], sec[1]);
	};
	
	private int[] getSections() {
		int distance = EntityBridgeCreator.setDirection(start, end);
		int height = end.getY() - start.getY();
		
		return new int[] {distance, height};
	};
	
	private int[][] buildArray(int distance, int height) {
		double slope;
		boolean running = true;
		int i = 0;
		
		int size = (int) Math.ceil((double)distance / 4);
		
		int[][] result = new int[size][4];
		
		while (running) {
			int[] section = new int[4];
			int j = 0;
			
			for (int k = 0; k < 4; k++) {
				
				slope = height / distance;
				int a = (int) Math.round(slope);
				section[j] = a;
				
				distance--;
				if (distance <= 0) {
					running = false;
					break;
				};
				j++;
				if (a == 1) {
					height--;
				};
			}
			
			result[i] = section;
			i++;
		}
		
		return result;
	};
	
	/*private void buildSection(int distance, int height, int slope) {
		Section s = Section.AAAA;
		switch (slope) {
		case 0:
			s=Section.AAAA;
			break;
		case 1:
			s=Section.AAAB;
			break;
		case 2:
			s=Section.ABAB;
			break;
		case 3:
			s=Section.ABBB;
			break;
		case 4:
			s=Section.BBBB;
			break;
		};
		
		build(s.slope);
		
	};*/
	
	/*protected enum Section {
		AAAA(new int[] {0, 0, 0, 0}),
		AAAB(new int[] {0, 0, 0, 1}),
		ABAB(new int[] {0, 1, 0, 1}),
		ABBB(new int[] {0, 1, 1, 1}),
		BBBB(new int[] {1, 1, 1, 1});
		
		private int[] slope;
		
		private Section(int[] slope) {
			this.slope = slope;
		};
	};*/

}
