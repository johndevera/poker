package poker.framework;

public enum Street {

	PRE_FLOP(0),
	FLOP(1),
	TURN(2),
	RIVER(3);
	
	private int value;
	
	private Street(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
	public Street nextStreet() {
		if(this == PRE_FLOP) return FLOP;
		if(this == FLOP) return TURN;
		if(this == TURN) return RIVER;
		return null;
	}
	
	public static Street random() {
				
		int randomStreet = (int) (4 * Math.random());
		
		if(randomStreet == 0)
			return PRE_FLOP;
		else if(randomStreet == 1)
			return FLOP;
		else if(randomStreet == 2)
			return TURN;
		else
			return RIVER;
	}
}
