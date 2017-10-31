package github.io.isenfireldc.misc.entity;

import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class ModEntities {
	
	public static EntityFlare flare;
	
	public static void init() {
		
	};
	
	private static <T extends Entity> T register(T entity, String name) {
		EntityRegistry.registerModEntity(registryName, entityClass, entityName, id, mod, trackingRange, updateFrequency, sendsVelocityUpdates);
		
		return entity;
	};

}
