package poker;

public enum PocketHand {

	SUPER_PREMIUM_HAND(11),
	PREMIUM_HAND(10),
	MIDDLE_PAIR(9),
	BOTTOM_PAIR(8),
	SUITED_CONNECTOR(7),
	SUITED_ONE_GAPPER(6),
	PREMIUM_SUITED(5),
	SUITED(4),
	CONNECTED(3),
	ONE_GAPPER(2),
	ABOVE_AVERAGE(1),
	JUNK(0);
	
	private int value;
	
	PocketHand(int value) {
		this.value = value;
	}	
	
	public int getValue() {
		return value;
	}
}
