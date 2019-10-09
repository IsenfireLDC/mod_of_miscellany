package github.io.isenfireldc.misc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import github.io.isenfireldc.misc.block.ModBlocks;
import github.io.isenfireldc.misc.config.Config;
import github.io.isenfireldc.misc.entity.ModEntities;
import github.io.isenfireldc.misc.item.ModItems;
import github.io.isenfireldc.misc.proxy.CommonProxy;
import github.io.isenfireldc.misc.recipes.ModRecipes;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * A mod of miscellaneous creations
 * 
 * @author IsenfireLDC
 * @version 0.1.6.2
 *
 */
@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION, acceptedMinecraftVersions = Reference.ACCEPTED_VERSIONS, guiFactory = Reference.GUI_FACTORY)
public class MiscellanyMod {
	
	public static ModItems items = new ModItems();
	public static ModBlocks blocks = new ModBlocks();
	
	public static Logger log = LogManager.getLogger(Reference.MODID + ":Info");
	public static Logger debug = LogManager.getLogger(Reference.MODID + ":Debug");
	
	@Instance(Reference.MODID)
	public static MiscellanyMod instance;
	
	@SidedProxy(serverSide = Reference.SERVER, clientSide = Reference.CLIENT)
	public static CommonProxy proxy;
	
	//register the registration classes with the event bus
	public static void registerEventBus() {
		MinecraftForge.EVENT_BUS.register(items);
		MinecraftForge.EVENT_BUS.register(blocks);
	};
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		log.info(Reference.NAME + " is loading...");
		
		Config.load(e);
		
		registerEventBus();
		ModEntities.init();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent e) {
		proxy.registerBakery();
		ModRecipes.init();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {
//		MinecraftForge.EVENT_BUS.register(new ConfigSync());
	}
	
	@EventHandler
	public void onConfigChanged(OnConfigChangedEvent e) {
		Config.update(e);
	};
}
