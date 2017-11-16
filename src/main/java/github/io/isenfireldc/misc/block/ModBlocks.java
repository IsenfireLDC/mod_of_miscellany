package github.io.isenfireldc.misc.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModBlocks {
	
	public static BlockLitAir litAir;
	
	public static void init() {
		
		litAir = register(new BlockLitAir());
		
	}
	
	private static <T extends Block> T register(T block, ItemBlock itemBlock) {
		if (itemBlock != null) {
			GameRegistry.register(itemBlock);
		};
		GameRegistry.register(block);
		
/*		if (block instanceof ItemModelProvider) {
			((ItemModelProvider)block).registerItemModel(itemBlock);
		}*/
		
		return block;
	}
	
	private static <T extends Block> T register(T block) {
		ItemBlock itemBlock = new ItemBlock(block);
		itemBlock.setRegistryName(block.getRegistryName());
		return register(block, itemBlock);
	}

}
