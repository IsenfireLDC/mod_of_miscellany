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
	
	public static ItemFlare flare;
	
	public static ItemPizza pizza;
	
	public static ItemBridgeBuilder builder;
	
	public static ItemSpecialArrow special_arrow;
	
	public static ItemBazooka bazooka;
	
	public static void init() {
		
		inst10 = register(new ItemArrowSpawner("inst10", 10, true));
		slow10 = register(new ItemArrowSpawner("slow10", 10, false));
		
		inst20 = register(new ItemArrowSpawner("inst20", 20, true));
		slow20 = register(new ItemArrowSpawner("slow20", 20, false));
		
		flare_gun = register(new ItemEntityGun("flare_gun", 0));
		bridge_builder = register(new ItemEntityGun("bridge_builder", 1));
		
		flare = register(new ItemFlare("flare"));
		
		pizza = register(new ItemPizza("pizza"));
		
		builder = register(new ItemBridgeBuilder("builder_ammo"));
		
		special_arrow = register(new ItemSpecialArrow());
		
		bazooka = register(new ItemBazooka("bazooka"));
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
