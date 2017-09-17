package poker.framework;

//import static poker.framework.Card.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

	private List<Card> cards;
	
	/**
	 * Initialize a deck of 52 cards.
	 */
	public void init() {
		cards = new ArrayList<Card>();
		for(Card card : Card.values()) {
			cards.add(card);
		}
	}
	
	/**
	 * Shuffle/randomize the order of the deck.
	 */
	public void shuffle() {
		Collections.shuffle(cards);
	}
	
	/**
	 * Removes a card from the deck.
	 * @return
	 */
	public Card draw() {
		
		if(cards.size() > 0) {
			return cards.remove(0);
		}
		
		return null;
	}
}
