package github.io.isenfireldc.misc.type;

import github.io.isenfireldc.misc.type.arrows.TypeExplosive;
import github.io.isenfireldc.misc.type.arrows.TypeVanilla;

public enum ArrowTypes {
	
	VANILLA(0, new TypeVanilla()),
	EXPLOSIVE(1, new TypeExplosive());
	
	public int id;
	public ArrowType type;
	private ArrowTypes(int id, ArrowType type) {
		this.id = id;
		this.type = type;
	};
	
	public static ArrowTypes getType(int id) {
		switch(id) {
		case 0:
			return VANILLA;
		case 1:
			return EXPLOSIVE;
		default:
			return VANILLA;
		}
	};
	
	public static ArrowType getFullType(ArrowTypes type) {
		switch(type) {
		case VANILLA:
			return VANILLA.type;
		case EXPLOSIVE:
			return EXPLOSIVE.type;
		default:
			return VANILLA.type;
		}
	}

}
