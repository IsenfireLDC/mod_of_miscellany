package github.io.isenfireldc.misc.item;

import javax.annotation.Nullable;

import github.io.isenfireldc.misc.entity.EntitySpecialArrow;
import github.io.isenfireldc.misc.type.ArrowTypes;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemInstLauncher extends ItemBase {
	
	public ItemInstLauncher(String name) {
		super(name);
		
		this.maxStackSize = 1;
		this.setMaxDamage(2000);
		this.setCreativeTab(CreativeTabs.COMBAT);
		//TODO: I have this here, but I don't know what it does
		this.addPropertyOverride(new ResourceLocation("reload"), new IItemPropertyGetter() {
			
			@SideOnly(Side.CLIENT)
			public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {
				return entityIn == null ? 0.0F : (entityIn.getActiveItemStack().getItem() != ModItems.inst_launcher ? 0.0F : (float)(stack.getMaxItemUseDuration() - entityIn.getItemInUseCount()) / 20.0F);
			}
		});
		
		this.setSubfolder("launchers");
	};
	
	private ItemStack findAmmo(EntityPlayer player) {
		System.out.println("Finding ammo");
		if (this.isAmmo(player.getHeldItem(EnumHand.OFF_HAND))) {
			return player.getHeldItem(EnumHand.OFF_HAND);
		} else if (this.isAmmo(player.getHeldItem(EnumHand.MAIN_HAND))) {
			return player.getHeldItem(EnumHand.MAIN_HAND);
		} else {
			for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
				ItemStack itemstack = player.inventory.getStackInSlot(i);
				
				if (this.isAmmo(itemstack)) {
					return itemstack;
				}
			}
			return player.isCreative() ? new ItemStack(ModItems.special_arrow) : ItemStack.EMPTY;
		}
	};
	
	protected boolean isAmmo(ItemStack stack) {
		return stack.getItem() instanceof ItemSpecialArrow;
	};
	
	public boolean fire(World worldIn, EntityPlayer playerIn, ItemStack launcher) {
		ItemStack stack = this.findAmmo(playerIn);
		if (stack == ItemStack.EMPTY) {
			System.out.println("No ammo found");
			return false;
		};
		
		if (!worldIn.isRemote) {
			ItemSpecialArrow arrow = ((ItemSpecialArrow)stack.getItem());
			//if(!arrow.types.contains(ArrowTypes.EXPLOSIVE)) arrow.types.add(ArrowTypes.EXPLOSIVE);
			EntitySpecialArrow entityspecialarrow = ItemSpecialArrow.createArrow(worldIn, stack, (EntityLivingBase)playerIn);
			entityspecialarrow.addEffect(ArrowTypes.EXPLOSIVE);
			entityspecialarrow.setAim(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 3.0F, 1.0F);
			entityspecialarrow.setIsCritical(true);
			
			int i = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, launcher);
			if (i > 0) entityspecialarrow.setDamage(entityspecialarrow.getDamage() + (double)i * 0.75D + 0.5D);
			if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, launcher) > 0)
				entityspecialarrow.setFire(200);
			launcher.damageItem(1, playerIn);
			playerIn.addStat(StatList.getObjectUseStats(this));
			
			System.out.println("Firing ammo " + entityspecialarrow);
			
			worldIn.spawnEntity(entityspecialarrow);
			return true;
		};
		return false;
	};
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		this.fire(worldIn, playerIn, itemstack);
		return new ActionResult<ItemStack> (EnumActionResult.SUCCESS, itemstack);
	};

}
