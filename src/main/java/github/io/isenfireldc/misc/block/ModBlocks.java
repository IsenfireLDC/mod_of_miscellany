package github.io.isenfireldc.misc.block;

import github.io.isenfireldc.misc.item.ItemModelProvider;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

public class ModBlocks {
	
	private static IForgeRegistry<Block> reg;
	
	public static void init() {
		
		register(new BlockLitAir());
		
		register(new BlockBridgeBuilder());
		
	};
	
	@SubscribeEvent
	public void registerBlocks(RegistryEvent.Register<Block> event) {
		reg = event.getRegistry();
	};
	
	private static <T extends Block> T register(T block, ItemBlock itemBlock) {
		reg.register(block);
		
		//TODO Create a better version of this
		if (block instanceof ItemModelProvider) {
			((ItemModelProvider)block).registerItemModel(itemBlock);
		}
		if (block instanceof BlockTileEntity) {
			GameRegistry.registerTileEntity(((BlockTileEntity<?>)block).getTileEntityClass(), block.getRegistryName().toString());
		}
		
		return block;
	}
	
	private static <T extends Block> T register(T block) {
		ItemBlock itemBlock = new ItemBlock(block);
		itemBlock.setRegistryName(block.getRegistryName());
		return register(block, itemBlock);
	}

}
