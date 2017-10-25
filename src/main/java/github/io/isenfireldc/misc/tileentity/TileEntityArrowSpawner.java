package github.io.isenfireldc.misc.tileentity;

import java.util.Random;

import javax.annotation.Nullable;

import github.io.isenfireldc.misc.item.ItemArrowSpawner;
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
	private boolean aimable = true;
	private boolean instant;
	private float charge;
	
	private float spread = 10.0F;
	
	protected Random rand = new Random();
	
	public TileEntityArrowSpawner(int arrowsSpawned) {
		this.arrowsSpawned = arrowsSpawned;
	};
	
/*	public TileEntityArrowSpawner(int arrowsSpawned, boolean aimable) {
		this.arrowsSpawned = arrowsSpawned;
		this.aimable = aimable;
	};*/
	
	public TileEntityArrowSpawner(int arrowsSpawned, boolean instant) {
		this.arrowsSpawned = arrowsSpawned;
		this.instant = instant;
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		//compound.setInteger("arrowsSpawned", arrowsSpawned);
		return super.writeToNBT(compound);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		//arrowsSpawned = compound.getInteger("arrowsSpawned");
		super.readFromNBT(compound);
	}
	
	public void spawn(World worldIn, EntityPlayer entityplayer, ItemStack stack, ItemArrowSpawner spawner, @Nullable int timeLeft) {
		EntityArrow[] arrows = new EntityArrow[arrowsSpawned];
		//sound:
		worldIn.playSound((EntityPlayer)null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (rand.nextFloat() * 0.4F + 1.2F) + 1.0F * 0.5F);
		
		//get velocity for instant spawners
		int a = spawner.getMaxItemUseDuration(stack) - timeLeft;
		charge = instant ? 1.0F : getArrowVelocity(a);
		
		for (int i = 0; i < arrows.length; i++) {
			//shooting:
			ItemArrow itemarrow = (ItemArrow)Items.ARROW;
			EntityArrow entityarrow = itemarrow.createArrow(worldIn, new ItemStack(Items.ARROW), entityplayer);
			entityarrow.setAim(entityplayer, conePitch(entityplayer.rotationPitch, spread), coneYaw(entityplayer.rotationPitch, entityplayer.rotationYaw, spread), 0.0F, charge * 3.0F, 0.0F);
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
		if (!worldIn.isRemote) {
			for (EntityArrow entityarrow : arrows) {
				worldIn.spawnEntity(entityarrow);
			};
		};
		
		//damage the arrow spawner
		stack.damageItem(1, entityplayer);
		
	}
	
	private float conePitch(float rotation, float spread) {
		rotation -= (spread / 2.0F);
		return rotation + rand.nextFloat() * spread;
	}
	
	private float coneYaw(float pitch, float yaw, float spread) {
		spread = (spread / 2) * (float)Math.pow((double)((Math.abs(Math.abs(pitch) - 90)) / 180), -1D);
		if (spread > 360) {
			spread = 360;
		}
		yaw -= spread / 2.0F;
		return yaw + rand.nextFloat() * spread;
	}
	
	private float getArrowVelocity(int charge) {
		float f = (float)charge / 20.0F;
		f = (f * f + f * 2.0F) / 3.0F;
		f = 2 * f;
		
		if (f > 2.0F) {
			f = 2.0F;
		}
		
		return f;
	}

}
