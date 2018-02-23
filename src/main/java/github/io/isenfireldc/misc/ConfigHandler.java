package github.io.isenfireldc.misc;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.Loader;

public class ConfigHandler {
	
	public static final File CONFIGURATION_FILE = new File(Loader.instance().getConfigDir(), Reference.NAME.replace(" ", "") + ".cfg");
	public static final Configuration CONFIGURATION = new Configuration(CONFIGURATION_FILE);
	
	//public static Property MAX_BRIDGE_DISTANCE;
	public static int MAX_BRIDGE_DISTANCE = 100;
	
	public static void init() {
		
		//CONFIGURATION.load();
		
		MAX_BRIDGE_DISTANCE = CONFIGURATION.get(Configuration.CATEGORY_GENERAL, "Max Bridge Distance", 100).getInt(100);
		
		//, "Maximum distance of a bridge created by bridge builder entity"
		
		//setValues();
		//MAX_BRIDGE_DISTANCE = CONFIGURATION.getInt("builder_max_distance", Configuration.CATEGORY_GENERAL, 100, 15, 10000, "Maximum distance of a bridge created by the bridge builder");
	};
	
	/*private static void setValues() {
		ConfigValues.MAX_BRIDGE_DISTANCE = MAX_BRIDGE_DISTANCE.getInt();
	}*/

}
