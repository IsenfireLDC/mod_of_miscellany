package github.io.isenfireldc.misc.tileentity;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModTileEntities {
	
	public static void init() {
		
	};
	
	private static <T extends TileEntity> T register(T tileentity) {
		GameRegistry.registerTileEntity(tileentity.getClass(), tileentity.toString());
		
		return tileentity;
	};

}
