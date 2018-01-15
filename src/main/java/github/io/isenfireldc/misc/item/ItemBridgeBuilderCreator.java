package github.io.isenfireldc.misc.item;

import github.io.isenfireldc.misc.block.BlockBridgeBuilder;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemBridgeBuilderCreator extends ItemBase {

	public ItemBridgeBuilderCreator(String name) {
		super(name);
	};
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		
		BlockPos pos = playerIn.getPosition();
		BlockPos currentPos = playerIn.getPosition().add(100, 25, 0);
		int direction = 1;
		World world = playerIn.getEntityWorld();
		
		if (!world.isRemote) {
			BlockBridgeBuilder builder = null;
			try {
				builder = new BlockBridgeBuilder(pos, currentPos, direction, world);
				builder.build();
				System.out.println("Successfully created builder");
			} catch (Exception e) {
				System.err.println("Failed to create BridgeBuilder: " + e);
			}
			try {
				world.setBlockState(currentPos, builder.getDefaultState());
				System.out.println("Successfully set builder in world");
			} catch (Exception e) {
				if (builder != null) {
					System.err.println("Failed to set builder Block: " + e);
				}
			}
		}
		
		return new ActionResult<ItemStack>(EnumActionResult.PASS, itemstack);
	}

}
