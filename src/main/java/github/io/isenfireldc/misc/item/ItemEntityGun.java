package github.io.isenfireldc.misc.item;

import github.io.isenfireldc.misc.entity.AbstractEntityProjectile;
import github.io.isenfireldc.misc.entity.EntityBridgeCreator;
import github.io.isenfireldc.misc.entity.EntityFlare;
import github.io.isenfireldc.misc.tileentity.TileEntityEntityGun;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemEntityGun extends ItemBase {
	
	protected int meta;
	
	private TileEntityEntityGun entityGun = new TileEntityEntityGun();
	
	public ItemEntityGun(String name, int meta) {
		super(name);
		
		this.meta = meta;
	};
	
	@Override
	public int getItemEnchantability() {
		return 1;
	};
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		entityGun.fire(worldIn, playerIn, meta);
		
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
	};

}
