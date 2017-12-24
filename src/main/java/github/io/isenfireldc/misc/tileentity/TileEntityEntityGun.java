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
	
	public <T extends AbstractEntityProjectile> T fire(World world, EntityPlayer player, T entity) {
		this.world = world;
		this.player = player;
		entity = setEntity(entity);
		entity.setAim(player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 0.2F);
		if (!world.isRemote) {
			world.spawnEntity(entity);
		};
		
		return entity;
	};
	
	private <T extends AbstractEntityProjectile> T setEntity(T entity) {
		if (entity instanceof EntityFlare) {
			return (T) new EntityFlare(world, player);
		} else if (entity instanceof EntityBridgeCreator) {
			return (T) new EntityBridgeCreator(world);
		};
		
		return null;
	}

}
