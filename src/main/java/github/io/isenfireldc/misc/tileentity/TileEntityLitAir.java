package github.io.isenfireldc.misc.tileentity;

import java.util.Random;

import github.io.isenfireldc.misc.MiscellanyMod;
import github.io.isenfireldc.misc.block.BlockLitAir;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;

public class TileEntityLitAir extends TileEntityBase implements ITickable {
	
	public static final String name = "tile_entity_bridge_builder";
	
	public IBlockState prevState = null;
	
	public BlockLitAir block;
	
	public int ticksRemaining = 200;
	
	public int lightValue = 15;
	public int animTicks = 20;
	
	public Random rand = new Random();

	public TileEntityLitAir() {
		super(name);
	};

	@Override
	public void update() {
		if (ticksRemaining % animTicks == 0) {
			lightValue -= 2 - (rand.nextDouble() * 3);
			
			if (lightValue <= 0) {
				if(prevState != null) {
					world.setBlockState(pos, prevState);
				} else {
					world.setBlockToAir(pos);
				}
				MiscellanyMod.debug.debug("TELitAir: Block at pos " + pos + " deleted.");
				world.removeTileEntity(pos);
				return;
			};
			
			world.checkLight(pos);
//			world.notifyBlockUpdate(pos, ModBlocks.lit_air.getDefaultState(), ModBlocks.lit_air.getDefaultState(), 0);
			MiscellanyMod.debug.info(this + " (TELitAir): Light level updated to " + lightValue);
		};
		
		if (ticksRemaining <= 0) {
			lightValue -= 2;
		} else {
			ticksRemaining--;
		}
	};
	
	
	//TODO Add save/load for TileEntityLitAir
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		return super.writeToNBT(compound);
	};
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
	};

}
