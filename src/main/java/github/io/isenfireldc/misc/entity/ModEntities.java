package github.io.isenfireldc.misc.entity;

import github.io.isenfireldc.misc.MiscellanyMod;
import github.io.isenfireldc.misc.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class ModEntities {
	
	private static int id = -1;
	
	public static EntityFlare flare;
	
	public static EntityBridgeBuilder bridge_builder;
	
	public static void init() {
		
		register(EntityFlare.class, "flare");
		
		register(EntityBridgeBuilder.class, "bridge_builder");
		
	};
	
	private static void register(Class entity, String name) {
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID + ":" + name), entity, name, ++id, MiscellanyMod.instance, 128, 1, true);
	};

}