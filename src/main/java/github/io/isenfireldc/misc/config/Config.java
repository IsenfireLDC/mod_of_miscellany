package github.io.isenfireldc.misc.config;

import java.io.File;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.collect.Lists;

import github.io.isenfireldc.misc.Reference;
import jline.internal.Log;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Config {
	
	public static final File CONFIGURATION_FILE = new File(Loader.instance().getConfigDir(), Reference.NAME.replace(" ", "") + ".cfg");
	public static final Configuration CONFIGURATION = new Configuration(CONFIGURATION_FILE, "main");
	
	public static Config instance = new Config();
	public static Logger log = LogManager.getLogger(Reference.MODID + ":" + "Config");
	
	public static ConfigCategory General;
	
	public static int maxBridgeDistance = 100;
	
	public Config() {
	};
	
	public static void load(FMLPreInitializationEvent event) {
		log.info("Running load with input " + event);
		MinecraftForge.EVENT_BUS.register(instance);
		
		syncConfig();
	};
	
	@SubscribeEvent
	public static void update(ConfigChangedEvent.OnConfigChangedEvent e) {
		log.info("Running update with input " + e);
		if (e.getModID().equals(Reference.NAME)) {
			syncConfig();
		}
	};
	
	
	
	public static boolean syncConfig() {
		log.info("Running syncConfig");
		CONFIGURATION.load();
		Property prop;
		
		{
			String cat = "general";
			List<String> propOrder = Lists.newArrayList();
			General = CONFIGURATION.getCategory(cat);
			
			prop = CONFIGURATION.get(cat, "maxBridgeDistance", maxBridgeDistance);
			prop.setComment("Maximum distance of a bridge created by the bridge builder entity");
			maxBridgeDistance = prop.getInt();
			propOrder.add(prop.getName());
			
			General.setPropertyOrder(propOrder);
		}
		
		boolean changed = false;
		if (CONFIGURATION.hasChanged()) {
			log.info("Config Changed");
			CONFIGURATION.save();
			changed = true;
		}
		
		return changed;
	}

}
