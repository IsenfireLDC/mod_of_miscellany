package github.io.isenfireldc.misc.entity;

import github.io.isenfireldc.misc.item.ModItems;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityFlare extends AbstractEntityProjectile {
	
	//TODO Use the AbstractEntityProjectile.Boundedness enum
	//TODO Try to create a block at the current entity position
	
	private Item entityItem = ModItems.slow10;
	
    private int burnTime = 1000;
    
    private double gravity = 0.8D;
    private boolean velocityLimit = true;	//mainly for debugging
    private double maxVelocityUp = 1/0D;
    private double maxVelocityDown = -0.2D;

	public EntityFlare(World worldIn) {
		super(worldIn);
	};
	
	public EntityFlare(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
	};
	
	public EntityFlare(World worldIn, EntityLivingBase shooter) {
		super(worldIn, shooter);
	};

	@Override
	protected ItemStack getEntityStack() {
		return new ItemStack(entityItem);
	};
	
	@Override
	protected double getVerticalVelocity(double velocity) {
    	if (velocityLimit) {
    		velocity = boundedVelocity(velocity);
    	}
    	velocity -= 0.05000000074505806D * gravity;
    	return velocity;
    };
    
    //TODO Use the AbstractEntityProjectile.Boundedness enum
    private double boundedVelocity(double velocity) {
		double diff = Math.abs((velocity > maxVelocityUp ? maxVelocityUp : maxVelocityDown) - velocity);
		if (velocity > maxVelocityUp) {
			velocity -= diff / 2;
		} else if (velocity < maxVelocityDown) {
			if (diff > 0.1D) {
				velocity += diff / 2;
			} else {
				velocity = maxVelocityDown + (0.05000000074505806D * gravity);
			}
		}
		return velocity;
    };
    
    @Override
    public void onUpdate() {
    	super.onUpdate();
    	
    	if (burnTime == 0) {
    		this.entityDropItem(this.getEntityStack(), 0.1F);
    		this.setDead();
    	} else {
    		burnTime--;
    	}
    };

}
