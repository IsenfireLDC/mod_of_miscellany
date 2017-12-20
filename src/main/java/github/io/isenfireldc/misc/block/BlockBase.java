package github.io.isenfireldc.misc.block;

import github.io.isenfireldc.misc.MiscellanyMod;
import github.io.isenfireldc.misc.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class BlockBase extends Block {
	
	protected static final Material defaultMaterial = Material.GROUND;
	
	protected String name;
	protected String subfolder;
	
	public BlockBase(String name) {
		this(name, defaultMaterial);
	};
	
	public BlockBase(String name, Material material) {
		super(material);
		this.name = name;
		
		setUnlocalizedName(name);
		setRegistryName(name);
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
		MiscellanyMod.proxy.registerItemRenderer(item, 0, name, subfolder);
	};

}
