package github.io.isenfireldc.misc.item;

import github.io.isenfireldc.misc.MiscellanyMod;
import github.io.isenfireldc.misc.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemBase extends Item {
	
	protected String name;
	protected String subfolder = "";
	
	public ItemBase(String name) {
		this.name = name;
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Reference.TAB);
	};
	
	@Override
	public ItemBase setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}
	
	public void setSubfolder(String subfolder) {
		this.subfolder = subfolder;
	}
	
	public void registerItemModel() {
		MiscellanyMod.proxy.registerItemRenderer(this, 0, name, subfolder);
	}

}
