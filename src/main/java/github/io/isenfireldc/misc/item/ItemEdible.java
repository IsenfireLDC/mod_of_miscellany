package github.io.isenfireldc.misc.item;

import github.io.isenfireldc.misc.MiscellanyMod;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;

public class ItemEdible extends ItemFood implements ItemModelProvider {
	
	protected static final String defaultTexture = "placeholder_food";
	
	protected String name;
	protected String texture;
	protected String subfolder = "food";
	
	public ItemEdible(int amount, float saturation, boolean isWolfFood, String name) {
		super(amount, saturation, isWolfFood);
		this.name = checkName(name);
	};
	
	/*
	 * Only call this once per class, but that shouldn't be a problem 
	*/
	public void setSubfolder(String subfolder) {
		this.subfolder += "/" + subfolder;
	};

	@Override
	public void registerItemModel(Item item) {
		MiscellanyMod.proxy.registerItemRenderer(item, 0, texture, subfolder);
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
