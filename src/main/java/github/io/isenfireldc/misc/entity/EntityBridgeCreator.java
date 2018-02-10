package github.io.isenfireldc.misc.entity;

import github.io.isenfireldc.misc.block.BlockBridgeBuilder;
import github.io.isenfireldc.misc.block.ModBlocks;
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
	
	int count = 0;
	
	protected Item entityItem;
	
	double initialX;
	double initialZ;
	
	public EntityBridgeCreator(World worldIn) {
		super(worldIn);
		
		this.pos = this.getPosition();
		this.initialX = this.posX;
		this.initialZ = this.posZ;
	}
	
	public EntityBridgeCreator(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
		
		this.pos = this.getPosition().add(0, -1, 0);
		this.initialX = this.posX;
		this.initialZ = this.posZ;
	};
	
	public EntityBridgeCreator(World worldIn, EntityLivingBase shooter) {
		super(worldIn, shooter);
		
		this.initialX = shooter.posX;
		this.initialZ = shooter.posZ;
		this.pos = shooter.getPosition();
	};

	@Override
	protected ItemStack getEntityStack() {
		return new ItemStack(entityItem);
	};

	@Override
	protected double getVerticalVelocity(double velocity) { //TODO Add a different system (-45 degrees to 45 degrees pitch only)
		//velocity -= super.grav / 10;
		return velocity;
	};
	
	@Override
	protected double[] sideVelocity(double velocityX, double velocityZ) {
		System.out.println(this + ": " + "Vx=" + velocityX + ", Vz=" + velocityZ);
		if (Math.abs(velocityX) > Math.abs(velocityZ)) {
			velocityZ = 0;
		} else {
			velocityX = 0;
		};
		
		if (!directionChecked) {
			setDirection(pos, this.getPosition());
			directionChecked = true;
			System.out.println(direction);
		}
		
		return new double[] {velocityX, velocityZ};
	};
	
	public int setDirection(BlockPos start, BlockPos end) {
		System.out.println("setDirection zi: " + this.initialZ + " zf: " + this.posZ);
		System.out.println("setDirection xi: " + this.initialX + " xf: " + this.posX);
		if (this.initialZ - this.posZ < 0) {
			direction = 0;
			return Math.abs(start.getZ() - end.getZ());
		} else if (this.initialX - this.posX < 0) {
			direction = 1;
			return Math.abs(start.getX() - end.getX());
		} else if (this.initialZ - this.posZ > 0) {
			direction = 2;
			return Math.abs(start.getZ() - end.getZ());
		} else if (this.initialX - this.posX > 0) {
			direction = 3;
			return Math.abs(start.getX() - end.getX());
		} else {
			direction = -1;
			return 0;
		}
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		
		//System.out.println(this + ": " + "Update: " + ++count);
		
		BlockPos currentPos = this.getPosition();
/*		System.out.println("z: " + Math.abs(pos.getZ() - currentPos.getZ()));
		System.out.println("x: " + Math.abs(pos.getX() - currentPos.getX()));
		System.out.println("block: " + world.getBlockState(currentPos));*/
		int zDiff = Math.abs(pos.getZ() - currentPos.getZ());
		int xDiff = Math.abs(pos.getX() - currentPos.getX());
		
		if (zDiff >= 100 || xDiff >= 100) {
			int print = zDiff > xDiff ? zDiff : xDiff;
			System.out.println("Creating BlockBridgeBuilder: " + print);
			try {
				if (!world.isRemote) {
					BlockBridgeBuilder builder = ModBlocks.bridge_builder;
					builder.build(pos.down(), currentPos, direction, world, this);
					this.world.setBlockState(currentPos, builder.getDefaultState());
				};
				this.setDead();
			} catch (Exception e){
				System.err.println(this + ": " + "Attempted to place builder: " + e);
			}
		};
		
	};
	
}
