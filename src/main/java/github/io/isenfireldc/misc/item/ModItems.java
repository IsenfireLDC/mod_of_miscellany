package github.io.isenfireldc.misc.item;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

public class ModItems {
	
	private static IForgeRegistry<Item> reg;
	
	public static void init() {
		
		register(new ItemArrowSpawner("inst10", 10, true));
		register(new ItemArrowSpawner("slow10", 10, false));
		
		register(new ItemArrowSpawner("inst20", 20, true));
		register(new ItemArrowSpawner("slow20", 20, false));
		
		register(new ItemEntityGun("flare_gun", 0));
		register(new ItemEntityGun("bridge_builder", 1));
		
		register(new ItemFlare("flare"));
		
		register(new ItemPizza("pizza"));
		
		register(new ItemBridgeBuilder("builder_ammo"));
		
		register(new ItemSpecialArrow());
		
		register(new ItemBazooka("bazooka"));
		
		register(new ItemInstLauncher("inst_launcher"));
	};
	
	@SubscribeEvent
	public void registerItems(RegistryEvent.Register<Item> event) {
		reg = event.getRegistry();
	};
	
	private static <T extends Item> T register(T item) {
		reg.register(item);
		
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
