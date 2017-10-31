package github.io.isenfireldc.misc.item;

import github.io.isenfireldc.misc.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModItems {
	
	public static ItemArrowSpawner test5;
	
	public static ItemArrowSpawner inst10;
	public static ItemArrowSpawner slow10;
	
	public static ItemArrowSpawner inst20;
	public static ItemArrowSpawner slow20;
	
	public static ItemFlareGun flare_gun;
	
	public static void init() {
		test5 = register(new ItemArrowSpawner("test5", 5, false));
		
		inst10 = register(new ItemArrowSpawner("inst10", 10, true));
		slow10 = register(new ItemArrowSpawner("slow10", 10, false));
		
		inst20 = register(new ItemArrowSpawner("inst20", 20, true));
		slow20 = register(new ItemArrowSpawner("slow20", 20, false));
		
		flare_gun = register(new ItemFlareGun("flare_gun"));
	}
	
	private static <T extends Item> T register(T item) {
		GameRegistry.register(item);
		
		if (item instanceof ItemBase) {
			((ItemBase)item).registerItemModel();
		}
		
		return item;
	}
	
	/*public static void registerRenders(Item item) {
		registerRender(item);
	}
	
	private static void registerRender(Item item) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Reference.MODID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
	}*/

}
