package github.io.isenfireldc.misc.entity;

import java.util.ArrayList;

import github.io.isenfireldc.misc.block.BlockLitAir;
import github.io.isenfireldc.misc.block.ModBlocks;
import github.io.isenfireldc.misc.item.ModItems;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityFlare extends AbstractEntityProjectile {
	
	//TODO Use the AbstractEntityProjectile.Boundedness enum
	//TODO Try to create a block at the current entity position
	
	private Item entityItem = ModItems.slow10;
	
	//private ArrayList<BlockLitAir> lighting = new ArrayList<BlockLitAir>();
	
    private int burnTime = 1000;
    private static final int defaultLitTime = 40;
    
    private static final double MOVEMENT = 0.75D;
    
    private BlockPos prevPos = null;
    private BlockPos pos;
//    private IBlockState prevState;

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
		velocity -= super.grav * MOVEMENT;
		return velocity;
	};
	
	@Override
	protected double[] sideVelocity(double velocityX, double velocityZ) {
		velocityX *= MOVEMENT;
		velocityZ *= MOVEMENT;
		
		return new double[] {velocityX, velocityZ};
	};
    
    @Override
    public void onUpdate() {
    	super.onUpdate();
    	
    	if (!world.isRemote) {
    		this.setBlock(this.getEntityWorld(), this.getPosition());
    		if (burnTime % 20 == 0) {
    			System.out.println(this + " was ticked: " + burnTime);
    		}
    	};
    	
    	if (pos == prevPos) {
    		//TODO Restart block countdown
    	};
    	
    	if (burnTime == 0) {
    		this.entityDropItem(this.getEntityStack(), 0.1F);
//    		light = null;
//    		this.getEntityWorld().setBlockState(this.getPosition(), prevState);
    		this.setDead();
    	} else {
    		burnTime--;
    	};
    };
    
    /**
     * Updates all lighting blocks and creates and destroys them as necessary
     * ---Being Reworked---
     * 
     * @param world Current world
     * @param pos Position of the flare
     */
    private void setBlock(World world, BlockPos pos) {
//    	BlockLitAir light = new BlockLitAir(pos);
    	IBlockState state = world.getBlockState(pos);
    	BlockLitAir light = ModBlocks.lit_air;
    	world.setBlockState(pos, light.getDefaultState());
    	light.getTileEntity(world, pos).ticksRemaining = defaultLitTime;
    	light.getTileEntity(world, pos).prevState = state;
    	
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
    /*private boolean hasMoved() {
    	this.pos = this.getPosition();
    	
    	if (pos != prevPos) {
    		prevPos = pos;
    		return true;
    	}
    	
    	return false;
    }*/

	@Override
	public void onEntityHit(RayTraceResult raytraceResultIn, Entity entity) {};

}
