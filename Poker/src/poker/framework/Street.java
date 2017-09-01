package poker.framework;

public enum Street {

	PRE_FLOP,
	FLOP,
	TURN,
	RIVER;
	
	public Street nextStreet() {
		if(this == PRE_FLOP) return FLOP;
		if(this == FLOP) return TURN;
		if(this == TURN) return RIVER;
		return null;
	}
}
