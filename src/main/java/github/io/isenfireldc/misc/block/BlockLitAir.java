package github.io.isenfireldc.misc.block;

import java.util.Collection;

import com.google.common.base.Optional;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

//TODO Fix collision
public class BlockLitAir extends BlockCollisionlessBase {
	
	//private int ticksRemaining = 1000;
	
	private BlockPos pos;

	public BlockLitAir(BlockPos pos) {
		super("lit_air");
		this.pos = pos;
		
		this.setDefaultState(getDefaultState());
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
		return 15;
	};
	
	/**
	 * Updates the block
	 * 
	 * @param refresh Whether to refresh the block's life
	 * @param world The world that the block is in
	 * @return Returns true if the block is 'dead'
	 */
	/*public boolean update(boolean refresh, World world) {
		if (ticksRemaining == 0 && !refresh) {
			world.setBlockToAir(pos);
			return true;
		}
		if (refresh) {
			ticksRemaining = 1000;
		} else {
			this.ticksRemaining--;
		};
		return false;
	};*/
	
	public BlockPos getPosition() {
		return pos;
	};
}
