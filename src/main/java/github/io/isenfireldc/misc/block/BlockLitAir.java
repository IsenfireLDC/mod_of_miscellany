package github.io.isenfireldc.misc.block;

import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.BlockPos;

//TODO Fix collision
public class BlockLitAir extends BlockCollisionlessBase {
	
	private int ticksRemaining = 1000;
	
	private BlockPos pos;
	
	private int lightValue = 15;
	
	private IBlockState state = this.getDefaultState();

	public BlockLitAir(BlockPos pos) {
		super("lit_air");
		this.pos = pos;
		
		this.setDefaultState(getDefaultState());//TODO Remove?
	};
	
	public BlockLitAir() {
		super("litAir");
	};
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.INVISIBLE;
	}
	
	@Override
	public int getLightValue(IBlockState state) {
		return lightValue;
	};
	
	/**
	 * Updates the block
	 * 
	 * @param refresh Whether to refresh the block's life
	 * @param world The world that the block is in
	 * @return Returns true if the block is 'dead'
	 */
	public boolean update(/*boolean refresh, World world*/) {
		/*if (ticksRemaining == 0 && !refresh) {
			world.setBlockToAir(pos);
			return true;
		}
		if (refresh) {
			ticksRemaining = 1000;
		} else {
			this.ticksRemaining--;
		};*/
		if (ticksRemaining == 0) {
			ticksRemaining = 20;
			lightValue = 12 + (int)Math.round(Math.random() * 3);
			this.setLightLevel(lightValue);
		}
		System.out.println(this + " (BlockLitAir):" + ticksRemaining);
		ticksRemaining--;
		return false;
	};
	
	public BlockPos getPosition() {
		return pos;
	};
}
