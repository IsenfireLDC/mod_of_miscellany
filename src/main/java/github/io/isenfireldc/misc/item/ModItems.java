package github.io.isenfireldc.misc.item;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModItems {
	
	public static ItemArrowSpawner test5;
	
	public static ItemArrowSpawner inst10;
	public static ItemArrowSpawner slow10;
	
	public static ItemArrowSpawner inst20;
	public static ItemArrowSpawner slow20;
	
	public static void init() {
		test5 = register(new ItemArrowSpawner("test5", 5, false));
		
		inst10 = register(new ItemArrowSpawner("inst10", 10, true));
		slow10 = register(new ItemArrowSpawner("slow10", 10, false));
		
		inst20 = register(new ItemArrowSpawner("inst20", 20, true));
		slow20 = register(new ItemArrowSpawner("slow20", 20, false));
	}
	
	private static <T extends Item> T register(T item) {
		GameRegistry.register(item);
		
		return item;
	}

}
