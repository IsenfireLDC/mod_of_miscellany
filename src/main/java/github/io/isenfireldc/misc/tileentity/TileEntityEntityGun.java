package github.io.isenfireldc.misc.tileentity;

import github.io.isenfireldc.misc.entity.AbstractEntityProjectile;
import github.io.isenfireldc.misc.entity.EntityFlare;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class TileEntityEntityGun {
	
	public TileEntityEntityGun() {
		
	};
	
	public boolean fire(World world, EntityPlayer player, AbstractEntityProjectile entity) {
		entity = setEntity(entity);
		entity.setAim(player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 0.2F);
		if (!world.isRemote) {
			world.spawnEntity(entity);
		};
		
		return true;
	};
	
	private <T extends AbstractEntityProjectile> T setEntity(T entity) {
		return null;
	}

}
