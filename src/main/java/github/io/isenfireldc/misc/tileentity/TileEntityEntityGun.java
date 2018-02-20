package github.io.isenfireldc.misc.tileentity;

import github.io.isenfireldc.misc.entity.AbstractEntityProjectile;
import github.io.isenfireldc.misc.entity.EntityBridgeBuilder;
import github.io.isenfireldc.misc.entity.EntityFlare;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntityEntityGun extends TileEntity {
	
	private World world;
	private EntityPlayer player;
	
	public TileEntityEntityGun() {
		
	};
	
	public void fire(World world, EntityPlayer player, int meta) {
		this.world = world;
		this.player = player;
		AbstractEntityProjectile entity = setEntity(meta);
		if (!world.isRemote) {
			entity.setAim(player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 0.2F);
			world.spawnEntity(entity);
		};
	};
	
	private AbstractEntityProjectile setEntity(int meta) {
		if (meta == 0) {
			return new EntityFlare(world, player);
		} else if (meta == 1) {
			return new EntityBridgeBuilder(world, player);
		};
		
		return null;
	}

}
