package github.io.isenfireldc.misc.proxy;

import github.io.isenfireldc.misc.Reference;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

public class ClientProxy extends CommonProxy {
	
	@Override
	public void registerItemRenderer(Item item, int meta, String id, String subfolder) {
		if (subfolder == "") {
			ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(Reference.MODID + ":" + id, "inventory"));
		} else {
			ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(Reference.MODID + ":" + subfolder + "/" + id, "inventory"));
		}
	}
	
	public void registerBakery() {
		ModelBakery.registerItemVariants(Reference.Holder.arrowspawner, new ResourceLocation(Reference.MODID, "inst10"), new ResourceLocation(Reference.MODID, "inst20"), new ResourceLocation(Reference.MODID, "slow10"), new ResourceLocation(Reference.MODID, "slow20"));
	};

}
