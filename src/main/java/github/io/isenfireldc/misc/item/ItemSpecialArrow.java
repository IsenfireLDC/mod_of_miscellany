package github.io.isenfireldc.misc.item;

import java.util.ArrayList;
import java.util.List;

import github.io.isenfireldc.misc.entity.EntitySpecialArrow;
import github.io.isenfireldc.misc.type.ArrowType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemSpecialArrow extends ItemBase {
	
	public List<ArrowType> types = new ArrayList<ArrowType>();
	
	public ItemSpecialArrow() {
		super("special_arrow#");
	};
	
    public EntitySpecialArrow createArrow(World worldIn, ItemStack stack, EntityLivingBase shooter)
    {
        EntitySpecialArrow entityspecialarrow = new EntitySpecialArrow(worldIn, shooter);
        entityspecialarrow.setArrowEffect(stack);
//        entityspecialarrow.setType(type);
        return entityspecialarrow;
    }

    public boolean isInfinite(ItemStack stack, ItemStack bow, net.minecraft.entity.player.EntityPlayer player)
    {
        int enchant = net.minecraft.enchantment.EnchantmentHelper.getEnchantmentLevel(net.minecraft.init.Enchantments.INFINITY, bow);
        return enchant <= 0 ? false : this.getClass() == ItemSpecialArrow.class;
    }

}
