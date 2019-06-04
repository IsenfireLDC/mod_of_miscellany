package github.io.isenfireldc.misc.type;

public enum ArrowTypes {
	
	VANILLA(0),
	EXPLOSIVE(1);
	
	public int id;
	private ArrowTypes(int id) {
		this.id = id;
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
	}

}
