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
	
	private static BlockPos start;
	private static BlockPos end;
	private static int direction;
	private int[][] slope;
	private int step = 0;
	
	private static final int SECTION_SIZE = 4;
	
	private boolean update = true;
	
	private static World world;
	
	private TileEntityBridgeBuilder builder;
	
	public BlockBridgeBuilder() {
		super(name);
		System.out.println(this + ": " + "ConstructorA");
	};
	
	public BlockBridgeBuilder(BlockPos start, BlockPos end, int direction, World world) {
		this();
		
		this.start = start;
		this.end = end;
		this.direction = direction;
		this.world = world;
		
		//this.build();
	};
	
/*	@Override
	public boolean requiresUpdates() {
		return update;
	};
	
	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		update = builder.buildStep(step);
		step++;
	};*/
	
	public void build() {
		int[] sec = getSections();
		System.out.println(this + ": " + sec);
		this.slope = buildArray(sec[0], sec[1]);
		System.out.println(this + ": " + this.slope);
	};
	
	private int[] getSections() {
		int distance = EntityBridgeCreator.setDirection(start, end);
		System.out.println(this + ": " + EntityBridgeCreator.setDirection(start, end));
		int height = end.getY() - start.getY();
		
		return new int[] {distance, height};
	};
	
	public void setArrayTo(int[][] array) {
		slope = array;
	};
	
	private int[][] buildArray(int distance, int height) {  //TODO Needs a rework: Incorporate the average slope into the algorithm
		double slope;
		boolean running = true;
		int i = 0;
		
		int size = (int) Math.ceil((double)distance / SECTION_SIZE);
		
		int[][] result = new int[size][SECTION_SIZE];
		
		while (running) {
			int[] section = new int[SECTION_SIZE];
			int j = 0;
			
			for (int k = 0; k < SECTION_SIZE; k++) {
				
				slope = (double)height / distance;
				int a = (int) Math.round(slope * 2);
				if (a > 1) {
					a = 1;
				};
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

	@Override
	public Class<TileEntityBridgeBuilder> getEntityClass() {
		return TileEntityBridgeBuilder.class;
	};
	
	@Nullable
	@Override
	public TileEntityBridgeBuilder createTileEntity(World world, IBlockState state) {
		this.builder = new TileEntityBridgeBuilder(world, start, direction, slope);
		return this.builder;
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
