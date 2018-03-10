package github.io.isenfireldc.misc.gui;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.collect.Lists;

import github.io.isenfireldc.misc.ConfigHandler;
import github.io.isenfireldc.misc.MiscellanyMod;
import github.io.isenfireldc.misc.Reference;
import jline.internal.Log;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent.PostConfigChangedEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.eventhandler.Event.Result;

public class ConfigGui extends GuiConfig {
	
	public static Logger log = MiscellanyMod.log;
	
	//private GuiTextField bridgeRange;
	
	public ConfigGui(GuiScreen parent) {
        super(parent, getConfigElements(),
                Reference.MODID, 
                false, 
                false, 
                "Make Miscellany Mod Even More Miscellaneous");
        log.info("Creating ConfigGui with input " + parent);
        titleLine2 = ConfigHandler.CONFIGURATION_FILE.getAbsolutePath();
	};
	
	private static List<IConfigElement> getConfigElements() {
		log.info("Running getConfigElements");
		List<IConfigElement> list = Lists.newArrayList();
		
		list.add(new ConfigElement(Config.General));
		
		return list;
	};
    
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
    	log.info("Running drawScreen");
    	title = "Make Miscellany Mod Even More Miscellaneous";
    	super.drawScreen(mouseX, mouseY, partialTicks);
    }
    
    /*@Override
    protected void actionPerformed(GuiButton button)
    {
        if (button.id == 2000)
        {
        	// DEBUG
        	System.out.println("Pressed DONE button");
            boolean flag = true;
            try
            {
                if ((configID != null || parentScreen == null || !(parentScreen instanceof GuiConfig)) 
                        && (entryList.hasChangedEntry(true)))
                {
                	// DEBUG
                	System.out.println("Saving config elements");
                    boolean requiresMcRestart = entryList.saveConfigElements();

                    if (Loader.isModLoaded(modID))
                    {
                        ConfigChangedEvent event = new OnConfigChangedEvent(modID, configID, isWorldRunning, requiresMcRestart);
                        FMLCommonHandler.instance().bus().post(event);
                        if (!event.getResult().equals(Result.DENY))
                            FMLCommonHandler.instance().bus().post(new PostConfigChangedEvent(modID, configID, isWorldRunning, requiresMcRestart));
                        
                        /*if (requiresMcRestart)
                        {
                            flag = false;
                            mc.displayGuiScreen(new GuiMessageDialog(parentScreen, "fml.configgui.gameRestartTitle", 
                                    new ChatComponentText(I18n.format("fml.configgui.gameRestartRequired")), "fml.configgui.confirmRestartMessage"));
                        }*/
    	/*
                        
                        if (parentScreen instanceof GuiConfig)
                            ((GuiConfig) parentScreen).needsRefresh = true;
                    }
                }
            }
            catch (Throwable e)
            {
                e.printStackTrace();
            }
            
            if (flag)
                mc.displayGuiScreen(parentScreen);
        }
    }*/

}
