package github.io.isenfireldc.misc.entity;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityBase extends Entity {
	
	protected String name;

	public EntityBase(World worldIn) {
		super(worldIn);
		
	};

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
