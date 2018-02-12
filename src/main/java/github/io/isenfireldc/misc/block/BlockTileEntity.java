package github.io.isenfireldc.misc.block;

import javax.annotation.Nullable;

import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class BlockTileEntity<TE extends TileEntity> extends BlockBase {

	public BlockTileEntity(String name) {
		super(name);
	};
	
	public abstract Class<TE> getTileEntityClass();
	
	public TE getTileEntity(IBlockAccess world, BlockPos pos) {
		return (TE)world.getTileEntity(pos);
	};
	
	@Override
	public boolean hasTileEntity(IBlockState blockstate) {
		return true;
	};
	
	@Nullable
	@Override
	public abstract TE createTileEntity(World world, IBlockState state);

}
