package github.io.isenfireldc.misc.item;

import github.io.isenfireldc.misc.entity.EntityFlare;
import github.io.isenfireldc.misc.tileentity.TileEntityFlareGun;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemFlareGun extends ItemBase {
	
	private TileEntityFlareGun flareGun = new TileEntityFlareGun();
	
	public ItemFlareGun(String name) {
		super(name);
	};
	
	@Override
	public int getItemEnchantability() {
		return 1;
	};
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		flareGun.fire(worldIn, playerIn);
		
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
	};

}
