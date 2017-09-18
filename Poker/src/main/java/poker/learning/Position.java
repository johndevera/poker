package poker.learning;

public enum Position {

	DEALER(9),
	CUTOFF(8),
	HIJACK(7),
	UTG_4(6),
	UTG_3(5),
	UTG_2(4),
	UTG_1(3),
	UTG(2),
	BIG_BLIND(1),
	SMALL_BLIND(0);
	
	private int value;
	
	private Position(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}	
	
	public static Position fromValue(int value) {
		for(Position position : Position.values()) {
			if(position.getValue() == value) {
				return position;
			}
		}
		return null;
	}
	
	public static Position random() {
		
		int total = Position.values().length;
		
		int randomPosition = (int) (total * Math.random());
		
		return fromValue(randomPosition);
	}
}
