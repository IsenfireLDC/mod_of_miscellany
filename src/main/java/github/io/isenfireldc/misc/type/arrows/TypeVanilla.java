package github.io.isenfireldc.misc.type.arrows;

import github.io.isenfireldc.misc.entity.EntitySpecialArrow;
import github.io.isenfireldc.misc.type.ArrowType;
import github.io.isenfireldc.misc.type.ArrowTypes;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;

public class TypeVanilla extends ArrowType {

	public TypeVanilla() {
		super(ArrowTypes.VANILLA);
	};
	
	@Override
	public void onEntityHit(EntityLivingBase living, EntitySpecialArrow a) {
        float f = MathHelper.sqrt(a.motionX * a.motionX + a.motionY * a.motionY + a.motionZ * a.motionZ);
        int i = MathHelper.ceil((double)f * a.damage);
        
        DamageSource damagesource;

        if (a.shootingEntity == null)
        {
            damagesource = new EntityDamageSourceIndirect("arrow", a, a);
        }
        else
        {
            damagesource = new EntityDamageSourceIndirect("arrow", a, a.shootingEntity);
        }
        
        living.attackEntityFrom(damagesource, (float)i);
	};

	@Override
	public void onBlockHit(RayTraceResult raytraceResultIn, EntitySpecialArrow a) {
        BlockPos blockpos = raytraceResultIn.getBlockPos();
        a.xTile = blockpos.getX();
        a.yTile = blockpos.getY();
        a.zTile = blockpos.getZ();
        IBlockState iblockstate = a.world.getBlockState(blockpos);
        a.inTile = iblockstate.getBlock();
        a.inData = a.inTile.getMetaFromState(iblockstate);
        a.motionX = (double)((float)(raytraceResultIn.hitVec.xCoord - a.posX));
        a.motionY = (double)((float)(raytraceResultIn.hitVec.yCoord - a.posY));
        a.motionZ = (double)((float)(raytraceResultIn.hitVec.zCoord - a.posZ));
        float f2 = MathHelper.sqrt(a.motionX * a.motionX + a.motionY * a.motionY + a.motionZ * a.motionZ);
        a.posX -= a.motionX / (double)f2 * 0.05000000074505806D;
        a.posY -= a.motionY / (double)f2 * 0.05000000074505806D;
        a.posZ -= a.motionZ / (double)f2 * 0.05000000074505806D;
        a.playSound(SoundEvents.ENTITY_ARROW_HIT, 1.0F, 1.2F / (a.rand.nextFloat() * 0.2F + 0.9F));
        a.inGround = true;
        a.projectileShake = 7;

        if (iblockstate.getMaterial() != Material.AIR)
        {
            a.inTile.onEntityCollidedWithBlock(a.world, blockpos, iblockstate, a);
        }
	};
	
	public String toString() {
		return "Vanilla Arrow";
	};
	

}
