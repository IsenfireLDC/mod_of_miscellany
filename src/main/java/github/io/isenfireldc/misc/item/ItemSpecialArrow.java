package github.io.isenfireldc.misc.item;

import java.util.ArrayList;
import java.util.List;

import github.io.isenfireldc.misc.entity.EntitySpecialArrow;
import github.io.isenfireldc.misc.type.ArrowTypes;
import github.io.isenfireldc.misc.type.ItemType;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemSpecialArrow extends ItemBase {
	
	public List<ArrowTypes> types = new ArrayList<ArrowTypes>();
	
	public ItemSpecialArrow() {
		super("special_arrow#");
	};
	
    public static EntitySpecialArrow createArrow(World worldIn, ItemStack stack, EntityLivingBase shooter)
    {
        EntitySpecialArrow entityspecialarrow = new EntitySpecialArrow(worldIn, shooter);
        entityspecialarrow.setArrowEffect(stack);
//        entityspecialarrow.setType(type);
        return entityspecialarrow;
    };
    
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item itemIn, CreativeTabs tab, NonNullList<ItemStack> subItems) {
    	for(int i = 0; i < types.size(); i++) {
    		subItems.add(SpecialItemStackBase.addTypeToItemStack(new ItemStack(itemIn), ArrowTypes.getFullType(types.get(i))));
    	}
    }

    public boolean isInfinite(ItemStack stack, ItemStack bow, net.minecraft.entity.player.EntityPlayer player)
    {
        int enchant = net.minecraft.enchantment.EnchantmentHelper.getEnchantmentLevel(net.minecraft.init.Enchantments.INFINITY, bow);
        return enchant <= 0 ? false : this.getClass() == ItemSpecialArrow.class;
    };

}
