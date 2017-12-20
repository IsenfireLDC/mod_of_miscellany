package github.io.isenfireldc.misc.block;

import github.io.isenfireldc.misc.MiscellanyMod;
import github.io.isenfireldc.misc.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockCollisionlessBase extends BlockAir {
	
	protected String name;
	protected String subfolder;
	
	public BlockCollisionlessBase(String name) {
		this.name = name;
		
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Reference.TAB);
	};
	
	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return NULL_AABB;
	};
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	};
	
	@Override
	public boolean canCollideCheck(IBlockState state, boolean hitIfLiquid) {
		return false;
	};
	
	@Override
	public boolean isReplaceable(IBlockAccess worldIn, BlockPos pos) {
		return false;
	};
	
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	};
	
	@Override
	public Block setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	};
	
	public void setSubfolder(String subfolder) {
		this.subfolder = subfolder;
	};
	
	public void registerItemModel(Item item) {
		MiscellanyMod.proxy.registerItemRenderer(item, 0, name, subfolder);
	};

}
