package github.io.isenfireldc.misc.item;

import java.util.Random;

import github.io.isenfireldc.misc.MiscellanyMod;
import github.io.isenfireldc.misc.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

public class ItemArrowSpawner extends ItemBase {
	
	private final static float spread = 10.0F;
	
	protected Random rand = new Random();
	
	@ObjectHolder(Reference.MODID)
	static class Holder {
		public static final Item inst10 = null;
		public static final Item inst20 = null;
		public static final Item slow10 = null;
		public static final Item slow20 = null;
		
		public static Item[] arrowspawners = {
				inst10,
				inst20,
				slow10,
				slow20
		};
	}
	
	public ItemArrowSpawner(String name, int arrows, boolean instant) {
		super(name);
		
		//this.instant = instant;
		//this.arrows = arrows;
		
		this.setHasSubtypes(true);
		this.setMaxStackSize(4);
		
		
		//spawner = new TileEntityArrowSpawner(arrows, instant);
		
		this.setSubfolder("arrowspawners");
	};
	
	@Override
	public int getItemEnchantability() {
		return 1;
	};
	
	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.BOW;
	};
	
	@Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 72000;
    };
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		int meta = itemstack.getMetadata();
		Types type = Types.getType(meta);
		if (type.instant) {
			spawn(worldIn, playerIn, itemstack, -1);
		} else {
			playerIn.setActiveHand(handIn);
		};
		return new ActionResult<ItemStack>(type.instant ? EnumActionResult.SUCCESS : EnumActionResult.FAIL, itemstack);
	};
	
	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
		EntityPlayer entityplayer = (EntityPlayer)entityLiving;
		spawn(worldIn, entityplayer, stack, timeLeft);
	};
	
	@Override
	public boolean isBeaconPayment(ItemStack stack) {
		return true;
	};
	
	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		for(int i = 0; i < 4; ++i) {
			items.add(new ItemStack(Holder.arrowspawners[i], 1, i));
		}
	};
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return this.getUnlocalizedName() + "." + Types.getType(stack.getMetadata()).name;
	};
	
	//TODO: Don't know if this is useful or not
	public enum Types {
		INST10(10, true, "inst10"),
		INST20(20, true, "inst20"),
		SLOW10(10, false, "slow10"),
		SLOW20(20, false, "slow20");
		
		public int arrows;
		public boolean instant;
		public String name;
		
		private Types(int arrows, boolean instant, String name) {
			this.arrows = arrows;
			this.instant = instant;
			this.name = name;
		};
		
		public static int getMeta(Types type) {
			switch(type) {
			case INST10:
				return 0;
			case INST20:
				return 1;
			case SLOW10:
				return 2;
			case SLOW20:
				return 3;
			default:
				return 0;
			}
		};
		
		public static Types getType(int meta) {
			switch(meta) {
			case 0:
				return INST10;
			case 1:
				return INST20;
			case 2:
				return SLOW10;
			case 3:
				return SLOW20;
			default:
				return INST10;
			}
		}
	};
	
	public void spawn(World worldIn, EntityPlayer entityplayer, ItemStack stack, int timeLeft) {
		int meta = stack.getMetadata();
		Types type = Types.getType(meta);
		EntityArrow[] arrows = new EntityArrow[type.arrows];
		ItemArrowSpawner spawner = (ItemArrowSpawner)stack.getItem();
		//sound:
		worldIn.playSound((EntityPlayer)null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (rand.nextFloat() * 0.4F + 1.2F) + 1.0F * 0.5F);
		
		//get velocity for instant spawners
		float charge = type.instant ? 1.0F : getArrowVelocity(spawner.getMaxItemUseDuration(stack) - timeLeft);
		
		for (int i = 0; i < arrows.length; i++) {
			//shooting:
			ItemArrow itemarrow = (ItemArrow)Items.ARROW;
			EntityArrow entityarrow = itemarrow.createArrow(worldIn, new ItemStack(Items.ARROW), entityplayer);
			entityarrow.shoot(entityplayer, conePitch(entityplayer.rotationPitch, spread), coneYaw(entityplayer.rotationPitch, entityplayer.rotationYaw, spread), 0.0F, charge * 3.0F, 0.0F);
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
		
	};
	
	private float conePitch(float rotation, float spread) {
		rotation -= (spread / 2.0F);
		return rotation + rand.nextFloat() * spread;
	};
	
	private float coneYaw(float pitch, float yaw, float spread) {
		spread = (spread / 2) * (float)Math.pow((double)((Math.abs(Math.abs(pitch) - 90)) / 180), -1D);
		if (spread > 360) {
			spread = 360;
		}
		yaw -= spread / 2.0F;
		return yaw + rand.nextFloat() * spread;
	};
	
	private float getArrowVelocity(int charge) {
		float f = (float)charge / 20.0F;
		f = (f * f + f * 2.0F) / 3.0F;
		f = 2 * f;
		
		if (f > 2.0F) {
			f = 2.0F;
		}
		
		return f;
	};

}
