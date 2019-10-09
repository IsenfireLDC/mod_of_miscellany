package github.io.isenfireldc.misc.item;

import net.minecraft.item.Item;

public interface ItemModelProvider {
	
	void registerItemModel(Item item, int meta);

}
