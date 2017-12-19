package github.io.isenfireldc.misc.entity;

import java.util.ArrayList;

import github.io.isenfireldc.misc.block.BlockLitAir;
import github.io.isenfireldc.misc.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityFlare extends AbstractEntityProjectile {
	
	//TODO Use the AbstractEntityProjectile.Boundedness enum
	//TODO Try to create a block at the current entity position
	
	private Item entityItem = ModItems.slow10;
	
	private ArrayList<BlockLitAir> lighting = new ArrayList<BlockLitAir>();
	
    private int burnTime = 1000;
    
    private double gravity = 0.8D;
    private boolean velocityLimit = true;	//mainly for debugging
    private double maxVelocityUp = 1/0D;
    private double maxVelocityDown = -0.2D;
    
    private BlockPos prevPos;
    private BlockPos pos;
    private IBlockState prevState;

	public EntityFlare(World worldIn) {
		super(worldIn);
		
		this.pos = this.getPosition();
	};
	
	public EntityFlare(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
		
		this.pos = this.getPosition();
	};
	
	public EntityFlare(World worldIn, EntityLivingBase shooter) {
		super(worldIn, shooter);
		
		this.pos = this.getPosition();
	};

	@Override
	protected ItemStack getEntityStack() {
		return new ItemStack(entityItem);
	};
	
	@Override
	protected double getVerticalVelocity(double velocity) {
		boolean flag = false;
    	if (velocityLimit) {
    		double diff = Math.abs((velocity > maxVelocityUp ? maxVelocityUp : maxVelocityDown) - velocity);
    		if (velocity > maxVelocityUp) {
    			velocity -= diff / 2;
    		} else if (velocity < maxVelocityDown) {
    			if (diff > 0.1D) {
    				velocity += diff / 2;
    			} else {
    				flag = true;
    			}
    		}
    	}
    	if (!flag) {
    		velocity -= 0.05000000074505806D * gravity;
    	} else {
    		flag = false;
    	};
    	return velocity;
    };
    
    //TODO Use the AbstractEntityProjectile.Boundedness enum
/*    private double boundedVelocity(double velocity) {
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
    };*/
    
    @Override
    public void onUpdate() {
    	super.onUpdate();
    	
    	this.setBlock(this.getEntityWorld(), this.getPosition());
    	
    	if (burnTime == 0) {
    		this.entityDropItem(this.getEntityStack(), 0.1F);
    		this.setDead();
    	} else {
    		burnTime--;
    	};
    };
    
    /**
     * Updates all lighting blocks and creates and destroys them as necessary
     * 
     * @param world Current world
     * @param pos Position of the flare
     */
    private void setBlock(World world, BlockPos pos) {
    	IBlockState currentState = world.getBlockState(pos);
    	
    	BlockLitAir light = new BlockLitAir(pos);
    	world.setBlockState(pos, light.getDefaultState());
    	
    	if (prevState != null) {
    		world.setBlockState(prevPos, prevState);
    	}
    	prevPos = pos;
    	prevState = currentState;
    	
    	/*boolean flag = false;
    	boolean refresh = false;
    	BlockLitAir light;
    	for (BlockLitAir block : lighting) {
    		if (block.getPosition() == pos) {
    			flag = true;
    			refresh = true;
    			light = block;
    		};
    		
    		if (block.update(refresh, world)) {
    			lighting.remove(block);
    		};
    		refresh = false;
    	};
    	
    	if (!flag) {
    		light = new BlockLitAir(pos);
    		world.setBlockState(pos, light.getDefaultState());
    		lighting.add(light);
    	} else {
    		flag = false;
    	};*/
    };
    
    //For potential use later
    private boolean hasMoved() {
    	this.pos = this.getPosition();
    	
    	if (pos != prevPos) {
    		prevPos = pos;
    		return true;
    	}
    	
    	return false;
    };

}
