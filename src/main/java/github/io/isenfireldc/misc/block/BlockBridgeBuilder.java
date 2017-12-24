package github.io.isenfireldc.misc.block;

public class BlockBridgeBuilder extends BlockCollisionlessBase {
	
	protected final static String defaultName = "builder";
	
	private int direction;
	
	public BlockBridgeBuilder() {
		super(defaultName);
	};
	
	public BlockBridgeBuilder(int direction) {
		this();
		
		this.direction = direction;
	}

}
