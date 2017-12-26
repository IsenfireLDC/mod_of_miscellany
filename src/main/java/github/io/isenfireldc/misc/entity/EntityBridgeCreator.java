package github.io.isenfireldc.misc.entity;

import github.io.isenfireldc.misc.block.BlockBridgeBuilder;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityBridgeCreator extends AbstractEntityProjectile {
	//TODO Only works in cardinal directions at present, corners are too complicated
	
	BlockPos pos;
	/** Direction of motion: -1 for nothing, 0 for +z, 1 for +x, 2 for -z, 3 for -x */
	static int direction;
	boolean directionChecked = false;
	
	protected Item entityItem;
	
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
		return new ItemStack(entityItem);
	};

	@Override
	protected double getVerticalVelocity(double velocity) { //TODO Add a different system
		velocity -= super.grav / 10;
		return velocity;
	};
	
	@Override
	protected double[] sideVelocity(double velocityX, double velocityZ) {
		if (velocityX > velocityZ) {
			velocityZ = 0;
		} else {
			velocityX = 0;
			direction = 0;
		};
		
		if (!directionChecked) {
			setDirection(pos, this.getPosition());
			directionChecked = true;
		}
		
		return new double[] {velocityX, velocityZ};
	};
	
	public static int setDirection(BlockPos start, BlockPos end) {
		if (start.getZ() - end.getZ() < 0) {
			direction = 0;
			return -1 * (start.getZ() - end.getZ());
		} else if (start.getX() - end.getX() < 0) {
			direction = 1;
			return -1 * (start.getX() - end.getX());
		} else if (start.getZ() - end.getZ() > 0) {
			direction = 2;
			return start.getZ() - end.getZ();
		} else if (start.getX() - end.getX() > 0) {
			direction = 3;
			return start.getX() - end.getX();
		} else {
			direction = -1;
			return 0;
		}
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		
		BlockPos currentPos = this.getPosition();
		
		if ((direction == 0 || direction == 2) && (Math.abs(pos.getZ() - currentPos.getZ()) >= 100) || (direction == 1 || direction == 3) && (Math.abs(pos.getZ() - currentPos.getZ()) >= 100)) {
			BlockBridgeBuilder builder = new BlockBridgeBuilder(pos, currentPos, direction, world);
			this.world.setBlockState(currentPos, builder.getDefaultState());
		};
		
	};
	
}
