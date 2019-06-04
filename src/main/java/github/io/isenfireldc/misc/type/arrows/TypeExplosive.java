package github.io.isenfireldc.misc.type.arrows;

import github.io.isenfireldc.misc.MiscellanyMod;
import github.io.isenfireldc.misc.entity.EntitySpecialArrow;
import github.io.isenfireldc.misc.type.ArrowType;
import github.io.isenfireldc.misc.type.ArrowTypes;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.RayTraceResult;

public class TypeExplosive extends ArrowType {
	
	private float strength = 1;

	public TypeExplosive() {
		super(ArrowTypes.EXPLOSIVE);
	};

	@Override
	public void onEntityHit(EntityLivingBase living, EntitySpecialArrow a) {
        if(!a.world.isRemote) {
        	float f = 4.0F;
        	MiscellanyMod.debug.debug("Exploding with multiplier of " + strength);
        	a.world.createExplosion(a, a.posX, a.posY + (double)(a.height / 16.0F), a.posZ, strength * 4.0F, true);
        	a.setDead();
        }
	};

	@Override
	public void onBlockHit(RayTraceResult raytraceResultIn, EntitySpecialArrow a) {
        if(!a.world.isRemote) {
        	float f = 4.0F;
        	MiscellanyMod.debug.debug("Exploding with multiplier of " + strength);
        	a.world.createExplosion(a, a.posX, a.posY + (double)(a.height / 16.0F), a.posZ, strength * 4.0F, true);
        	a.setDead();
        }
	};
	
	public void setStrength(float strength) {
		this.strength = strength;
	}

}
