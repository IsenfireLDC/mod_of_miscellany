package github.io.isenfireldc.misc.item;

import github.io.isenfireldc.misc.MiscellanyMod;
import github.io.isenfireldc.misc.Reference;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraftforge.oredict.OreDictionary;

public class ItemPizza extends ItemEdible implements ItemModelProvider {
	
	public ItemPizza(String name) {
		super(10, 4F, true, name);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Reference.TAB);
	};
	
}
