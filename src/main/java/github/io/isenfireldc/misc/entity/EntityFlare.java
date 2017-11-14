package github.io.isenfireldc.misc.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityFlare extends AbstractEntityProjectile {
	
	private Item entityItem;
	
    private int burnTime = 1000;
    
    private double gravity = 0.8D;
    private boolean velocityLimit = true;
    private double maxVelocityUp = 0D;
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

}
