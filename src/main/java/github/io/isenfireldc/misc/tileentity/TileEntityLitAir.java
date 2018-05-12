package github.io.isenfireldc.misc.tileentity;

import java.util.Random;

import github.io.isenfireldc.misc.MiscellanyMod;
import github.io.isenfireldc.misc.block.BlockLitAir;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ITickable;

/**
 * A placeholder-type block that is used as a light source for EntityFlare
 * 
 * @author IsenfireLDC
 * @since 0.1.4
 * @version 2.0
 *
 */
public class TileEntityLitAir extends TileEntityBase implements ITickable {
	
	public static final String name = "tile_entity_lit_air";
	
	public IBlockState prevState = null;
	
	public int ticksRemaining = 200;
	
	public int lightValue = 15;
	public int animTicks = 20;
	
	public Random rand = new Random();

	public TileEntityLitAir() {
		super(name);
	};

	/**
	 * Updates light level of connected {@link BlockLitAir} block and
	 * deletes it when it goes out
	 */
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
		this.markDirty();
	};
	
	//TODO Add save/load for TileEntityLitAir
	/**
	 * Code example for saving blocks found in {@link net.minecraft.inventory.ItemStackHelper}
	 */
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		//blockstate
		int meta = prevState.getBlock().getMetaFromState(prevState);
		compound.setInteger("meta", meta);
		ItemStack itemstack = new ItemStack(Item.getItemFromBlock(prevState.getBlock()));
		NBTTagCompound compIn = new NBTTagCompound();
		itemstack.writeToNBT(compIn);
		compound.setTag("block", compIn);
		//ints
		compound.setInteger("ticks", ticksRemaining);
		compound.setInteger("light" ,lightValue);
		compound.setInteger("anim", animTicks); //currently not used
		
		return super.writeToNBT(compound);
	};
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		//blockstate
		NBTTagCompound tagComp = compound.getCompoundTag("block");
		ItemStack out = new ItemStack(tagComp);
		int intOut = compound.getInteger("meta");
		prevState = Block.getBlockFromItem(out.getItem()).getStateFromMeta(intOut);
		//ints
		ticksRemaining = compound.getInteger("ticks");
		lightValue = compound.getInteger("light");
		animTicks = compound.getInteger("anim");
		
		super.readFromNBT(compound);
	};

}
