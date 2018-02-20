package github.io.isenfireldc.misc.block;

import github.io.isenfireldc.misc.MiscellanyMod;
import github.io.isenfireldc.misc.Reference;
import github.io.isenfireldc.misc.item.ItemModelProvider;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class BlockBase extends Block {
	
	protected static final Material defaultMaterial = Material.ROCK;
	protected static final String defaultTexture = "PlaceholderBlock";
	
	protected String name;
	protected String texture;
	protected String subfolder;
	
	public BlockBase(String name) {
		this(name, defaultMaterial);
	};
	
	public BlockBase(String name, Material material) {
		super(material);
		this.name = checkName(name);
		
		setUnlocalizedName(this.name);
		setRegistryName(this.name);
		setCreativeTab(Reference.TAB);
	};
	
	@Override
	public Block setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	};
	
	public void setSubfolder(String subfolder) {
		this.subfolder = subfolder;
	};
	
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
	}

	/*@Override
	public void registerItemModel() {
		MiscellanyMod.proxy.registerItemRenderer(this, 0, texture, subfolder);
	};*/

}
