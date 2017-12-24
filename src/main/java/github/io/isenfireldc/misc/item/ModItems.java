package github.io.isenfireldc.misc.item;

import github.io.isenfireldc.misc.Reference;
import github.io.isenfireldc.misc.entity.AbstractEntityProjectile;
import github.io.isenfireldc.misc.entity.EntityFlare;
import github.io.isenfireldc.misc.entity.ModEntities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
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
	
	public static void init() {
		
		inst10 = register(new ItemArrowSpawner("inst10", 10, true));
		slow10 = register(new ItemArrowSpawner("slow10", 10, false));
		
		inst20 = register(new ItemArrowSpawner("inst20", 20, true));
		slow20 = register(new ItemArrowSpawner("slow20", 20, false));
		
		flare_gun = register(new ItemEntityGun("flare_gun", ModEntities.flare));
		bridge_builder = register(new ItemEntityGun("bridge_builder", ModEntities.bridge_builder));
		
		item_flare = register(new ItemFlare("item_flare"));
	}
	
	private static <T extends Item> T register(T item) {
		GameRegistry.register(item);
		
		if (item instanceof ItemBase) {
			((ItemBase)item).registerItemModel();
		}
		
		return item;
	};

}
