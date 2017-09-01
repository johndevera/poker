package poker.framework;

//import static poker.framework.Card.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

	private List<Card> cards;
	
	public void init() {
	
		cards = new ArrayList<Card>();
		
		for(Card card : Card.values()) {
			cards.add(card);
		}
		/*
		cards.add(TWO_OF_DIAMONDS);
		cards.add(THREE_OF_DIAMONDS);
		cards.add(FOUR_OF_DIAMONDS);
		cards.add(FIVE_OF_DIAMONDS);
		cards.add(SIX_OF_DIAMONDS);
		cards.add(SEVEN_OF_DIAMONDS);
		cards.add(EIGHT_OF_DIAMONDS);
		cards.add(NINE_OF_DIAMONDS);
		cards.add(TEN_OF_DIAMONDS);
		cards.add(JACK_OF_DIAMONDS);
		cards.add(QUEEN_OF_DIAMONDS);
		cards.add(KING_OF_DIAMONDS);
		cards.add(ACE_OF_DIAMONDS);
		
		cards.add(TWO_OF_CLUBS);
		cards.add(THREE_OF_CLUBS);
		cards.add(FOUR_OF_CLUBS);
		cards.add(FIVE_OF_CLUBS);
		cards.add(SIX_OF_CLUBS);
		cards.add(SEVEN_OF_CLUBS);
		cards.add(EIGHT_OF_CLUBS);
		cards.add(NINE_OF_CLUBS);
		cards.add(TEN_OF_CLUBS);
		cards.add(JACK_OF_CLUBS);
		cards.add(QUEEN_OF_CLUBS);
		cards.add(KING_OF_CLUBS);
		cards.add(ACE_OF_CLUBS);
		
		cards.add(TWO_OF_HEARTS);
		cards.add(THREE_OF_HEARTS);
		cards.add(FOUR_OF_HEARTS);
		cards.add(FIVE_OF_HEARTS);
		cards.add(SIX_OF_HEARTS);
		cards.add(SEVEN_OF_HEARTS);
		cards.add(EIGHT_OF_HEARTS);
		cards.add(NINE_OF_HEARTS);
		cards.add(TEN_OF_HEARTS);
		cards.add(JACK_OF_HEARTS);
		cards.add(QUEEN_OF_HEARTS);
		cards.add(KING_OF_HEARTS);
		cards.add(ACE_OF_HEARTS);
		
		cards.add(TWO_OF_SPADES);
		cards.add(THREE_OF_SPADES);
		cards.add(FOUR_OF_SPADES);
		cards.add(FIVE_OF_SPADES);
		cards.add(SIX_OF_SPADES);
		cards.add(SEVEN_OF_SPADES);
		cards.add(EIGHT_OF_SPADES);
		cards.add(NINE_OF_SPADES);
		cards.add(TEN_OF_SPADES);
		cards.add(JACK_OF_SPADES);
		cards.add(QUEEN_OF_SPADES);
		cards.add(KING_OF_SPADES);
		cards.add(ACE_OF_SPADES);
		*/
	}
	
	public void shuffle() {
		Collections.shuffle(cards);
	}
	
	public Card draw() {
		
		if(cards.size() > 0) {
			return cards.remove(0);
		}
		
		return null;
	}
}
