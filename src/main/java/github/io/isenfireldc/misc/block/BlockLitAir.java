package github.io.isenfireldc.misc.block;

import javax.annotation.Nullable;

import github.io.isenfireldc.misc.MiscellanyMod;
import github.io.isenfireldc.misc.tileentity.TileEntityLitAir;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

//TODO Fix collision
public class BlockLitAir extends BlockTileEntity<TileEntityLitAir> {
	
	private BlockPos pos;
	
	public int lightValue = 15;
	
	private IBlockState state = this.getDefaultState();
	
	private TileEntityLitAir tileEntity;

	public BlockLitAir(BlockPos pos) {
		super("lit_air");
		this.pos = pos;
		
//		this.setDefaultState();//TODO Remove?
	};
	
	public BlockLitAir() {
		super("lit_air");
	};
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.INVISIBLE;
	};
	
	//Borrowed from BlockAir
    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        return NULL_AABB;
    }
    
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    public boolean canCollideCheck(IBlockState state, boolean hitIfLiquid)
    {
        return false;
    }
    
    public boolean isReplaceable(IBlockAccess worldIn, BlockPos pos)
    {
        return true;
    }

    public boolean isFullCube(IBlockState state)
    {
        return false;
    } 
	
	@Override
	public int getLightValue(IBlockState state) {
		return tileEntity.lightValue;
	};
	
	/**
	 * Updates the block
	 * 
	 * @param refresh Whether to refresh the block's life
	 * @param world The world that the block is in
	 * @return Returns true if the block is 'dead'
	 */
	/*public boolean update(/*boolean refresh, World world//int ticksRemaining) {
		/*if (ticksRemaining == 0 && !refresh) {
			world.setBlockToAir(pos);
			return true;
		}
		if (refresh) {
			ticksRemaining = 1000;
		} else {
			this.ticksRemaining--;
		};//
		if (ticksRemaining % animTicks == 0) {
			lightValue -= 2 - (int)Math.round(Math.random() * 3);
			this.setLightLevel(lightValue);
		}
		MiscellanyMod.debug.debug(this + " (BlockLitAir):" + animTicks);
		return false;
	};*/
	
	public BlockPos getPosition() {
		return pos;
	};

	@Override
	public Class<TileEntityLitAir> getTileEntityClass() {
		return TileEntityLitAir.class;
	};

	@Override
	public TileEntityLitAir createTileEntity(World world, IBlockState state) {
		this.tileEntity = new TileEntityLitAir();
		return this.tileEntity;
	};
}
