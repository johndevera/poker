package poker.framework;

public enum Suit {

	SPADE(4),
	HEART(3),
	CLUB(2),
	DIAMOND(1);
	
	private int value;	
	
	private Suit(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}
