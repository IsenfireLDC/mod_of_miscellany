package github.io.isenfireldc.misc.tileentity;

import net.minecraft.tileentity.TileEntity;

public class TileEntityBase extends TileEntity {
	
	protected String name;
	
	public TileEntityBase(String name) {
		super();
		
		this.name = name;
	};
	
	@Override
	public String toString() {
		return name;
	}

}
