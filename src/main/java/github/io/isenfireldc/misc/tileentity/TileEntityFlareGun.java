package github.io.isenfireldc.misc.tileentity;

import github.io.isenfireldc.misc.entity.EntityFlare;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class TileEntityFlareGun {
	
	public TileEntityFlareGun() {
		
	};
	
	public boolean fire(World world, EntityPlayer player) {
		EntityFlare flare = new EntityFlare(world, player);
		flare.setAim(player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 0.2F);
		if (!world.isRemote) {
			world.spawnEntity(flare);
		};
		
		return true;
	}

}
