package github.io.isenfireldc.misc.tileentity;

import java.util.Random;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class TileEntityArrowSpawner extends TileEntity {
	
	private int arrowsSpawned;
	
	protected Random rand = new Random();
	
	public TileEntityArrowSpawner(int arrowsSpawned) {
		this.arrowsSpawned = arrowsSpawned;
	};
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setInteger("arrowsSpawned", arrowsSpawned);
		return super.writeToNBT(compound);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		arrowsSpawned = compound.getInteger("arrowsSpawned");
		super.readFromNBT(compound);
	}
	
	public void spawn(World worldIn, EntityPlayer entityplayer, ItemStack stack) {
		EntityArrow[] arrows = new EntityArrow[arrowsSpawned];
		
		for (int i = 0; i < arrowsSpawned; i++) {
			//sound:
			worldIn.playSound((EntityPlayer)null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (rand.nextFloat() * 0.4F + 1.2F) + 1.0F * 0.5F);
		
			//shooting:
			ItemArrow itemarrow = (ItemArrow)Items.ARROW;
			EntityArrow entityarrow = itemarrow.createArrow(worldIn, new ItemStack(Items.ARROW), entityplayer);
			entityarrow.setAim(entityplayer, rand.nextFloat() * 360.0F, rand.nextFloat() * 360.0F, 0.0F, 1.0F * 3.0F, 1.0F);
			entityarrow.setIsCritical(true);
		
			//enchantments:
			int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);
		
			if (j > 0) {
				entityarrow.setDamage(entityarrow.getDamage() + (double)j * 0.5D + 0.5D);
			};
		
			int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, stack);
		
			if (k > 0) {
				entityarrow.setKnockbackStrength(k);
			};
		
			if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, stack) > 0) {
				entityarrow.setFire(200);
			};
		
			//set pickup status of arrows
			entityarrow.pickupStatus = EntityArrow.PickupStatus.CREATIVE_ONLY;
			
			arrows[i] = entityarrow;
		};
		
		//spawn arrows
		for (EntityArrow entityarrow : arrows) {
			worldIn.spawnEntity(entityarrow);
		};
		
		//damage the arrow spawner
		stack.damageItem(1, entityplayer);
	}

}
