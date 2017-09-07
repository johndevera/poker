package poker.framework;

public enum Position {

	SMALL_BLIND(0),
	BIG_BLIND(1);
	
	
	
	private int posIndex;
	
	Position(int posIndex) {
		this.posIndex = posIndex;
	}
	
	public int getIndex() {
		return posIndex;
	}
}
