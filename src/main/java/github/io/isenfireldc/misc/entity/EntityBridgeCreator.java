package github.io.isenfireldc.misc.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityBridgeCreator extends AbstractEntityProjectile {
	
	BlockPos pos;
	
	public EntityBridgeCreator(World worldIn) {
		super(worldIn);
		
		this.pos = this.getPosition();
	}
	
	public EntityBridgeCreator(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
		
		this.pos = this.getPosition();
	};
	
	public EntityBridgeCreator(World worldIn, EntityLivingBase shooter) {
		super(worldIn, shooter);
		
		this.pos = this.getPosition();
	};

	@Override
	protected ItemStack getEntityStack() {
		// TODO Auto-generated method stub
		return null;
	};

	@Override
	protected double getVerticalVelocity(double velocity) {
		// TODO Auto-generated method stub
		return 0;
	};
	
	
	
}
