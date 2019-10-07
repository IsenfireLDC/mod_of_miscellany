package github.io.isenfireldc.misc.item;

import java.util.ArrayList;

import github.io.isenfireldc.misc.type.ItemType;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionType;
import net.minecraft.util.ResourceLocation;

public class SpecialItemStackBase {
	ItemStack stack;
	ArrayList<ItemType> types;
	//TODO: Figure out appropriate scope
	
	public SpecialItemStackBase(ItemStack stack, ArrayList<ItemType> types) {
		this.stack = stack;
		this.types = types;
	};
	
	public ItemStack getStack() {
		return this.stack;
	};
	
	public ArrayList<ItemType> getTypes() {
		return types;
	};
	
    public static ItemStack addTypeToItemStack(ItemStack itemIn, ItemType type) {
    	ResourceLocation resourcelocation = new ResourceLocation(type.getName());
    	
        if (type == ItemType.NONE)
        {
            if (itemIn.hasTagCompound())
            {
                NBTTagCompound nbttagcompound = itemIn.getTagCompound();
                nbttagcompound.removeTag("misc:type");

                if (nbttagcompound.hasNoTags())
                {
                    itemIn.setTagCompound((NBTTagCompound)null);
                }
            }
        }
        else
        {
            NBTTagCompound nbttagcompound1 = itemIn.hasTagCompound() ? itemIn.getTagCompound() : new NBTTagCompound();
            nbttagcompound1.setString("misc:type", resourcelocation.toString());
            itemIn.setTagCompound(nbttagcompound1);
        }

        return itemIn;
    }
}
