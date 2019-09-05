package github.io.isenfireldc.misc.type;

import java.util.List;

import github.io.isenfireldc.misc.entity.EntitySpecialArrow;
import github.io.isenfireldc.misc.type.arrows.TypeVanilla;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.math.RayTraceResult;

public abstract class ArrowType {
	public final static ArrowType VANILLA = new TypeVanilla();
	//TODO: These should probably be private, but I don't want to add the getter/setter methods
	public ArrowTypes type;
	public int amplifier;
	public int duration;
	public boolean lasting;
	public boolean particles;
	
	public ArrowType(ArrowTypes type) {
		this(type, 1, 0);
	};
	
	public ArrowType(ArrowTypes type, int amplifier, int duration) {
		this(type, amplifier, duration, false, false);
	};
	
	public ArrowType(ArrowTypes type, int amplifier, int duration, boolean lasting, boolean particles) {
		this.type = type;
		this.amplifier = amplifier;
		this.duration = duration;
		this.lasting = lasting;
		this.particles = particles;
	};
	
	public abstract void onEntityHit(EntityLivingBase living, EntitySpecialArrow a);
	public abstract void onBlockHit(RayTraceResult raytraceResultIn, EntitySpecialArrow a);
	public abstract String toString();
	
	public static NBTTagCompound writeEffectList(NBTTagCompound compound, List<ArrowTypes> effects) {
        NBTTagList nbttaglist = new NBTTagList();

        for (ArrowTypes type : effects) {
        	ArrowType arrowtype = ArrowTypes.getFullType(type);
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setByte("Id", (byte)arrowtype.type.id);
            nbt.setByte("Amplifier", (byte)arrowtype.amplifier);
            nbt.setInteger("Duration", arrowtype.duration);
            nbt.setBoolean("Lasting", arrowtype.lasting);
            nbt.setBoolean("Particles", arrowtype.particles);
            nbttaglist.appendTag(nbt);
        }

        compound.setTag("Effects", nbttaglist);
		return compound;
	};
	
	public static List<ArrowTypes> readEffectList(NBTTagCompound compound, List<ArrowTypes> effects) {
        for (NBTBase comp : compound.getTagList("Effects", 9))
        {
            NBTTagCompound nbt = (NBTTagCompound)comp;
            ArrowType arrowtype = getTypeFromId((int)nbt.getByte("Id"));
            arrowtype.amplifier = (int)nbt.getByte("Amplifier");
            arrowtype.duration = nbt.getInteger("Duration");
            arrowtype.lasting = nbt.getBoolean("Lasting");
            arrowtype.particles = nbt.getBoolean("Particles");
            effects.add(arrowtype.type);
        }
		return effects;
	};
	
	public static ArrowType getTypeFromId(int id) {
		switch(id) {
		case 0:
			return new TypeVanilla();
		default:
			return new TypeVanilla();
		}
	}
}
