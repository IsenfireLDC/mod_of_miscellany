package github.io.isenfireldc.misc.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import github.io.isenfireldc.misc.tileentity.TileEntityArrowSpawner;

public class ItemArrowSpawner extends ItemBase {
	
	private boolean instant;
	private int arrows;
	
	private TileEntityArrowSpawner spawner;
	
	public ItemArrowSpawner(String name, int arrows, boolean instant) {
		super(name);
		
		this.instant = instant;
		this.arrows = arrows;
		
		this.setMaxStackSize(4);
		this.setMaxDamage(4);
		
		spawner = new TileEntityArrowSpawner(arrows);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		if (instant) {
			spawner.spawn(worldIn, playerIn, itemstack);
		};
		return new ActionResult<ItemStack>(instant ? EnumActionResult.SUCCESS : EnumActionResult.FAIL, itemstack);
	};
	
	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
		EntityPlayer entityplayer = (EntityPlayer)entityLiving;
		spawner.spawn(worldIn, entityplayer, stack);
	}
	
	@Override
	public boolean isBeaconPayment(ItemStack stack) {
		return true;
	}

}
