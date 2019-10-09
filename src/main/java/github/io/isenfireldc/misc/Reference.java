package github.io.isenfireldc.misc;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

public class Reference {
	
	public static final String MODID = "misc";
	public static final String NAME = "Mod of Miscellany";
	public static final String VERSION = "0.1.4.17";
	public static final String ACCEPTED_VERSIONS = "[1.12.2]";
	
	public static final String SERVER = "github.io.isenfireldc.misc.proxy.CommonProxy";
	public static final String CLIENT = "github.io.isenfireldc.misc.proxy.ClientProxy";
	
	public static final String GUI_FACTORY = "github.io.isenfireldc.misc.gui.ConfigGuiFactory";
	
	public static final CreativeTabs TAB = CreativeTabs.MISC;
	
	@ObjectHolder("misc")
	public static class Holder {
		//ModItems
		public static final Item arrowspawner = null;
		public static final Item flare_gun = null;
		public static final Item brigde_builder = null;
		public static final Item flare = null;
		public static final Item pizza = null;
		public static final Item builder_ammo = null;
		public static final Item special_arrow = null;
		public static final Item bazooka = null;
		public static final Item inst_launcher = null;
		
		//ModBlocks
		//ModEntities
	}

}
