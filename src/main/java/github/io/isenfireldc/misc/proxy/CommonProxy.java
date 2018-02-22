package github.io.isenfireldc.misc.proxy;

import github.io.isenfireldc.misc.ConfigHandler;
import net.minecraft.item.Item;

public class CommonProxy {
	
	public void registerItemRenderer(Item item, int meta, String id, String subfolder) {};
	
    public void loadConfiguration() {
    	ConfigHandler.init();
    	
    	if(ConfigHandler.CONFIGURATION.hasChanged())
		{
			ConfigHandler.CONFIGURATION.save();
		}
    }

}
