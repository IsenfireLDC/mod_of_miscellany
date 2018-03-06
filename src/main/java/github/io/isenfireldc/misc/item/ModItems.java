package github.io.isenfireldc.misc.item;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModItems {
	
	public static ItemArrowSpawner inst10;
	public static ItemArrowSpawner slow10;
	
	public static ItemArrowSpawner inst20;
	public static ItemArrowSpawner slow20;
	
	public static ItemEntityGun flare_gun;
	public static ItemEntityGun bridge_builder;
	
	public static ItemFlare item_flare;
	
	public static ItemBridgeBuilderCreator bridge_builder_debugger;
	
	public static ItemPizza pizza;
	
	public static void init() {
		
		inst10 = register(new ItemArrowSpawner("inst10", 10, true));
		slow10 = register(new ItemArrowSpawner("slow10", 10, false));
		
		inst20 = register(new ItemArrowSpawner("inst20", 20, true));
		slow20 = register(new ItemArrowSpawner("slow20", 20, false));
		
		flare_gun = register(new ItemEntityGun("flare_gun", 0));
		bridge_builder = register(new ItemEntityGun("bridge_builder", 1));
		
		item_flare = register(new ItemFlare("item_flare"));
		bridge_builder_debugger = register(new ItemBridgeBuilderCreator("bridge_builder_debugger"));
		
		pizza = register(new ItemPizza("pizza"));
	}
	
	private static <T extends Item> T register(T item) {
		GameRegistry.register(item);
		
		if (item instanceof ItemModelProvider) {
			//((ItemBase)item).registerItemModel(item);
			//TODO Need a better method for doing this
			if (item instanceof ItemEdible) {
				((ItemEdible)item).registerItemModel(item);
			} else {
				((ItemBase)item).registerItemModel(item);
			}
		}
		
		return item;
	};

}
