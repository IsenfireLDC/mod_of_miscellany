package github.io.isenfireldc.misc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import github.io.isenfireldc.misc.block.ModBlocks;
import github.io.isenfireldc.misc.entity.ModEntities;
import github.io.isenfireldc.misc.gui.Config;
import github.io.isenfireldc.misc.item.ModItems;
import github.io.isenfireldc.misc.proxy.CommonProxy;
import github.io.isenfireldc.misc.recipes.ModRecipes;
import jline.internal.Log;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION, acceptedMinecraftVersions = Reference.ACCEPTED_VERSIONS, guiFactory = Reference.GUI_FACTORY)
public class MiscellanyMod {
	
	public static Logger log = LogManager.getLogger(Reference.MODID + ":Info");
	
	@Instance(Reference.MODID)
	public static MiscellanyMod instance;
	
	@SidedProxy(serverSide = Reference.SERVER, clientSide = Reference.CLIENT)
	public static CommonProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		log.info(Reference.NAME + " is loading...");
		
		Config.load(e);
		
		ModItems.init();
		ModBlocks.init();
		ModEntities.init();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent e) {
		ModRecipes.init();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {
		
	}
}
