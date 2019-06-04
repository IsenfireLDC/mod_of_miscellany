package github.io.isenfireldc.misc.entity;

import java.util.ArrayList;
import java.util.List;

import github.io.isenfireldc.misc.item.ItemSpecialArrow;
import github.io.isenfireldc.misc.item.ModItems;
import github.io.isenfireldc.misc.type.ArrowType;
import github.io.isenfireldc.misc.type.ArrowTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityArrow.PickupStatus;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntitySpecialArrow extends AbstractEntityProjectile {

    private static final DataParameter<Integer> COLOR = EntityDataManager.<Integer>createKey(EntityTippedArrow.class, DataSerializers.VARINT);
    private List<ArrowType> effects = new ArrayList<ArrowType>();
    private boolean field_191509_at;
    private int type;
    private boolean isCritical;
    private int knockback;
	public PickupStatus pickupStatus;

    public EntitySpecialArrow(World worldIn)
    {
        super(worldIn);
    }

    public EntitySpecialArrow(World worldIn, double x, double y, double z)
    {
        super(worldIn, x, y, z);
    }

    public EntitySpecialArrow(World worldIn, EntityLivingBase shooter)
    {
        super(worldIn, shooter);
    }

    public void setArrowEffect(ItemStack stack)
    {
        if (stack.getItem() == ModItems.special_arrow)
        {
            this.effects.addAll(((ItemSpecialArrow)stack.getItem()).types);
        }
        
        else if (stack.getItem() == Items.ARROW)
        {
            this.effects.add(ArrowType.VANILLA);
        }
    }

    public static int func_191508_b(ItemStack p_191508_0_)
    {
        NBTTagCompound nbttagcompound = p_191508_0_.getTagCompound();
        return nbttagcompound != null && nbttagcompound.hasKey("CustomPotionColor", 99) ? nbttagcompound.getInteger("CustomPotionColor") : -1;
    }

    private void refreshColor()
    {
        this.field_191509_at = false;
    }

    public void addEffect(ArrowType effect) {
    	this.effects.add(effect);
    };

    protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(COLOR, Integer.valueOf(-1));
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        super.onUpdate();

        if (this.world.isRemote)
        {
            if (this.inGround)
            {
                if (this.timeInGround % 5 == 0)
                {
                    this.spawnPotionParticles(1);
                }
            }
            else
            {
                this.spawnPotionParticles(2);
            }
        }
        else if (this.inGround && this.timeInGround != 0 && !this.effects.isEmpty() && this.timeInGround >= 600)
        {
            this.world.setEntityState(this, (byte)0);
            this.effects.clear();
            this.effects.add(ArrowType.VANILLA);
            this.dataManager.set(COLOR, Integer.valueOf(-1));
        }
    }

    private void spawnPotionParticles(int particleCount)
    {
        int i = this.getColor();

        if (i != -1 && particleCount > 0)
        {
            double d0 = (double)(i >> 16 & 255) / 255.0D;
            double d1 = (double)(i >> 8 & 255) / 255.0D;
            double d2 = (double)(i >> 0 & 255) / 255.0D;

            for (int j = 0; j < particleCount; ++j)
            {
                this.world.spawnParticle(EnumParticleTypes.SPELL_MOB, this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height, this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width, d0, d1, d2, new int[0]);
            }
        }
    }

    public int getColor()
    {
        return ((Integer)this.dataManager.get(COLOR)).intValue();
    }

    private void func_191507_d(int p_191507_1_)
    {
        this.field_191509_at = true;
        this.dataManager.set(COLOR, Integer.valueOf(p_191507_1_));
    }

    public static void registerFixesSpecialArrow(DataFixer fixer)
    {
        EntityArrow.registerFixesArrow(fixer, "SpecialArrow");
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);

        if (this.field_191509_at)
        {
            compound.setInteger("Color", this.getColor());
        }

        if (!this.effects.isEmpty()) {
        	compound = ArrowType.writeEffectList(compound, effects);
        }
        
        compound.setBoolean("critical", isCritical);
        compound.setInteger("knockback", knockback);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
        
        this.effects = ArrowType.readEffectList(compound, this.effects);

        if (compound.hasKey("Color", 99))
        {
            this.func_191507_d(compound.getInteger("Color"));
        }
        else
        {
            this.refreshColor();
        }
        this.isCritical = compound.getBoolean("critical");
        this.knockback = compound.getInteger("knockback");
    }

    protected void onEntityHit(EntityLivingBase living) {
        for (ArrowType arrowtype : this.effects)
            arrowtype.onEntityHit(living, this);
    };
    
    protected void onBlockHit(RayTraceResult raytraceResultIn) {
    	for (ArrowType arrowtype : this.effects)
    		arrowtype.onBlockHit(raytraceResultIn, this);
    };

    protected ItemStack getArrowStack()
    {
        if (this.effects.isEmpty() || this.effects.get(0).type == ArrowTypes.VANILLA)
        {
            return new ItemStack(Items.ARROW);
        }
        else
        {
            ItemStack itemstack = new ItemStack(ModItems.special_arrow);
            NBTTagCompound nbt = itemstack.getTagCompound();
            ArrowType.writeEffectList(nbt, this.effects);

            if (this.field_191509_at)
            {
                NBTTagCompound nbttagcompound = itemstack.getTagCompound();

                if (nbttagcompound == null)
                {
                    nbttagcompound = new NBTTagCompound();
                    itemstack.setTagCompound(nbttagcompound);
                }

                nbttagcompound.setInteger("CustomPotionColor", this.getColor());
            }

            return itemstack;
        }
    }

    @SideOnly(Side.CLIENT)
    public void handleStatusUpdate(byte id)
    {
        if (id == 0)
        {
            int i = this.getColor();

            if (i != -1)
            {
                double d0 = (double)(i >> 16 & 255) / 255.0D;
                double d1 = (double)(i >> 8 & 255) / 255.0D;
                double d2 = (double)(i >> 0 & 255) / 255.0D;

                for (int j = 0; j < 20; ++j)
                {
                    this.world.spawnParticle(EnumParticleTypes.SPELL_MOB, this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height, this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width, d0, d1, d2, new int[0]);
                }
            }
        }
        else
        {
            super.handleStatusUpdate(id);
        }
    }

	@Override
	protected void onEntityHit(RayTraceResult raytraceResultIn, Entity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected ItemStack getEntityStack() {
		return new ItemStack(ModItems.special_arrow);
	};

	@Override
	protected double[] sideVelocity(double velocityX, double velocityZ) {
		return new double[] {velocityX, velocityZ};
	}

	@Override
	protected double getVerticalVelocity(double velocity) {
		return velocity - super.grav;
	};

	public void setIsCritical(boolean b) {
		// TODO Auto-generated method stub
		this.isCritical = b;
	}

	public double getDamage() {
		return this.damage;
	}

	public void setDamage(double d) {
		this.damage = d;
	}

	public void setKnockbackStrength(int k) {
		this.knockback = k;
	};
	
	public List<ArrowType> getEffects() {
		return this.effects;
	};

}
