package github.io.isenfireldc.misc.entity;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityBase extends Entity {
	
	public Random rand = new Random();

	public EntityBase(World worldIn) {
		super(worldIn);
	};
	
/*	public EntityBase(World world, String name) {
		super(world);
		this.name = name;
	};*/

	@Override
	protected void entityInit() {
		
	};

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {
		
	};

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {
		
	};

}
