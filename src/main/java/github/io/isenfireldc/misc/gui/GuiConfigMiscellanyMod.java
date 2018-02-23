package github.io.isenfireldc.misc.gui;

import github.io.isenfireldc.misc.ConfigHandler;
import github.io.isenfireldc.misc.Reference;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.GuiConfig;

public class GuiConfigMiscellanyMod extends GuiConfig {
	
	public GuiConfigMiscellanyMod(GuiScreen parent) {
        super(parent, new ConfigElement(
        		ConfigHandler.CONFIGURATION.getCategory(Configuration.CATEGORY_GENERAL))
        		.getChildElements(),
                Reference.MODID, 
                false, 
                false, 
                "Add Miscellany Any Way You Want");
        titleLine2 = ConfigHandler.CONFIGURATION_FILE.getAbsolutePath();
	};
	
	@Override
	public void initGui() {
		super.initGui();
	};
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
	};
	
	@Override
	protected void actionPerformed(GuiButton button) {
		super.actionPerformed(button);
	};

}
