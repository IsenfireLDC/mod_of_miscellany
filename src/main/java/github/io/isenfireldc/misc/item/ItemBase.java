package github.io.isenfireldc.misc.item;

import github.io.isenfireldc.misc.MiscellanyMod;
import github.io.isenfireldc.misc.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemBase extends Item implements ItemModelProvider {
	
	protected static final String defaultTexture = "placeholder_item";
	
	protected String name;
	protected String texture;
	protected String subfolder = "general";
	
	public ItemBase(String name) {
		this.name = checkName(name);
		setUnlocalizedName(this.name);
		setRegistryName(this.name);
		setCreativeTab(Reference.TAB);
	};
	
	@Override
	public ItemBase setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	};
	
	public void setSubfolder(String subfolder) {
		this.subfolder = subfolder;
	};
	
	@Override
	public void registerItemModel(Item item, int meta) {
		MiscellanyMod.proxy.registerItemRenderer(item, meta, texture, subfolder);
	};
	
	public void registerItemModel(Item item) {
		registerItemModel(item, 0);
	};
	
	private String checkName(String name) {
		String check = name.substring(name.length() - 1);
		if (check.equals("#")) {
			texture = defaultTexture;
			return name.substring(0, name.length() - 1);
		};
		texture = name;
		return name;
	};

}
