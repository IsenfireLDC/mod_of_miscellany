package github.io.isenfireldc.misc.recipes;

import github.io.isenfireldc.misc.item.ModItems;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModRecipes {
	
	public static void init() {
		
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.pizza), " C ", "SSS", "BBB", 'C', Items.MILK_BUCKET, 'S', Items.BEEF, 'B', Items.BREAD);
		
	};

}
