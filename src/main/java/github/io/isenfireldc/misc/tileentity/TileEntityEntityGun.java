package github.io.isenfireldc.misc.tileentity;

import github.io.isenfireldc.misc.entity.AbstractEntityProjectile;
import github.io.isenfireldc.misc.entity.EntityBridgeCreator;
import github.io.isenfireldc.misc.entity.EntityFlare;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class TileEntityEntityGun {
	
	private World world;
	private EntityPlayer player;
	
	public TileEntityEntityGun() {
		
	};
	
	public Class<? extends AbstractEntityProjectile> fire(World world, EntityPlayer player, Class<? extends AbstractEntityProjectile> entity) {
		this.world = world;
		this.player = player;
		setEntity(entity).setAim(player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 0.2F);
		if (!world.isRemote) {
			world.spawnEntity(setEntity(entity));
		};
		
		return entity;
	};
	
	private <T extends AbstractEntityProjectile> T setEntity(Class<? extends AbstractEntityProjectile> entity) {
		if (entity == EntityFlare.class) {
			return (T) new EntityFlare(world, player);
		} else if (entity == EntityBridgeCreator.class) {
			return (T) new EntityBridgeCreator(world);
		};
		
		return null;
	}

}
