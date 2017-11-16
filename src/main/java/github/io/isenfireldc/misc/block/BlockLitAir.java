package github.io.isenfireldc.misc.block;

import java.util.Collection;

import com.google.common.base.Optional;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

//TODO Fix collision
//TODO Make die after flare is dead or otherwise fix system
public class BlockLitAir extends BlockBase {
	
	private int ticksRemaining = 1000;
	
	private BlockPos pos;

	public BlockLitAir(BlockPos pos) {
		super("litAir", Material.AIR);
		this.pos = pos;
		
		this.setDefaultState(getDefaultState());
	};
	
	public BlockLitAir() {
		super("litAir", Material.AIR);
	};
	
	@Override
	public int getLightValue(IBlockState state) {
		return 30;
	};
	
	/**
	 * Updates the block
	 * 
	 * @param refresh Whether to refresh the block's life
	 * @param world The world that the block is in
	 * @return Returns true if the block is 'dead'
	 */
	public boolean update(boolean refresh, World world) {
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
	};
	
	public BlockPos getPosition() {
		return pos;
	};
	
	

}
