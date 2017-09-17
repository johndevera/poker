package poker.learning;

import poker.framework.Card;
import poker.framework.Rank;
import poker.framework.Suit;

public class CardBits extends InfoBits {

	private Card card;
	
	public CardBits(Card card) {
		super(17);
		this.card = card;
		init();
	}
	
	private void init() {
		
		int suitBit = -1;
		int rankBit = -1;
		
		Suit suit = card.getSuit();
		Rank rank = card.getRank();
		
		if(suit == Suit.DIAMOND)
			suitBit = 13;
		else if (suit == Suit.CLUB)
			suitBit = 14;
		else if (suit == Suit.HEART)
			suitBit = 15;
		else if (suit == Suit.SPADE)
			suitBit = 16;
		else
			suitBit = -1; // THIS IS AN EXCEPTION.  IT SHOULD NEVER HAPPEN!!
		
		rankBit = (rank == Rank.ACE) ? 0 : rank.getValue() - 1;

		update(true, suitBit);
		update(true, rankBit);
	}
}
