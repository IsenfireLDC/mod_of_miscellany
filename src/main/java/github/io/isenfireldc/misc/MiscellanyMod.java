package github.io.isenfireldc.misc;

import github.io.isenfireldc.misc.block.ModBlocks;
import github.io.isenfireldc.misc.entity.ModEntities;
import github.io.isenfireldc.misc.item.ModItems;
import github.io.isenfireldc.misc.proxy.CommonProxy;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION, acceptedMinecraftVersions = Reference.ACCEPTED_VERSIONS)
public class MiscellanyMod {
	
	public static Configuration config;
	
	public static Property builder_max_distance;
	
	@Instance(Reference.MODID)
	public static MiscellanyMod instance;
	
	@SidedProxy(serverSide = Reference.SERVER, clientSide = Reference.CLIENT)
	public static CommonProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		System.out.println(Reference.NAME + " is loading...");
		/*
		config = new Configuration(e.getSuggestedConfigurationFile());
		config.load();*/
		
		proxy.loadConfiguration();
		
		ModItems.init();
		ModBlocks.init();
		ModEntities.init();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent e) {
		
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {
		
	}
}
