package poker.framework;

public class Hand {

	private final Card first;
	private final Card second;
	
	public Hand(Card first, Card second) {
	
		this.first = first;
		this.second = second;
	}

	public Card getFirst() {
		return first;
	}

	public Card getSecond() {
		return second;
	}

	@Override
	public String toString() {
		return first.getShortName() + second.getShortName();
	}
}


