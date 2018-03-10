package github.io.isenfireldc.misc.gui;

import java.util.Set;

import org.apache.logging.log4j.Logger;

import github.io.isenfireldc.misc.MiscellanyMod;
import jline.internal.Log;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.IModGuiFactory;

public class ConfigGuiFactory implements IModGuiFactory {
	
	public static Logger log = MiscellanyMod.log;

	@Override
	public void initialize(Minecraft minecraftInstance) {
		log.info("Running initialize");
	}

	@Override
	public boolean hasConfigGui() {
		log.info("Running hasConfigGui");
		return true; //TODO Not sure
	}

	@Override
	public GuiScreen createConfigGui(GuiScreen parentScreen) {
		log.info("Running createConfigGui with input " + parentScreen);
		return parentScreen; //TODO Not sure
	}

	@Override
	public Class<? extends GuiScreen> mainConfigGuiClass() {
		log.info("Running mainConfigGuiClass");
		return ConfigGui.class;
	}

	@Override
	public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {
		log.info("Running runtimeGuiCategories");
		return null;
	}

	@Override
	public RuntimeOptionGuiHandler getHandlerFor(RuntimeOptionCategoryElement element) {
		log.info("Running getHandlerFor");
		return null;
	}
	
}
