//import static org.junit.Assert.*;

import java.util.HashMap;

import poker.framework.Card;
import poker.framework.Deck;
//import poker.framework.Hand;
import poker.framework.HandEvaluator;
import poker.strategy.FiveCardHand;
import poker.strategy.PocketHand;

public class HandEvaluatorUnitTest {

	/*
	@Test
	public void testRandom() {
		Deck deck = new Deck();
		deck.init();
		deck.shuffle();
		Card [] fiveCards = new Card[5];
		
		
		for(int i = 0; i < 5; i++) {
			fiveCards[i] = deck.draw();	
			System.out.print(fiveCards[i].getShortName() + ", ");
		}

		FiveCardHand fiveCardHand = HandEvaluator.evaluate(fiveCards);
		
		System.out.println(" ... HAND = " + fiveCardHand);
	}
	
	@Test
	public void testRandomXTimes() {
		for(int i = 0; i < 100; i++) {
			testRandom();
		}
	}
	*/
	/*
	@Test
	public void testRandomXGetStats() {
		int num_hands = 1000;
		int num_cards = 5;
		HashMap<FiveCardHand, Integer> fiveCardHandCounts = new HashMap<>();
		for(int i = 0; i < num_hands; i++) {
			
			Deck deck = new Deck();
			deck.init();
			deck.shuffle();
			Card [] fiveCards = new Card[num_cards];
			
			for(int j = 0; j < num_cards; j++) {
				fiveCards[j] = deck.draw();	
			}
			
			FiveCardHand fiveCardHand = HandEvaluator.evaluate(fiveCards);
			
			Integer count = fiveCardHandCounts.get(fiveCardHand);
			if(count == null) {
				fiveCardHandCounts.put(fiveCardHand, 1);
			}
			else {
				count++;
				fiveCardHandCounts.put(fiveCardHand, count);
			}
			
		}
		
		for(FiveCardHand fiveCardHand : fiveCardHandCounts.keySet()) {
			System.out.println(fiveCardHand + ", " + fiveCardHandCounts.get(fiveCardHand));
		}
	}
	*/
	private FiveCardHand evaluateCards(int num_cards, Card[] cards) {
		if  (num_cards == 5) {
			return HandEvaluator.evaluate(cards);
		}
		if  (num_cards == 6) {
			return HandEvaluator.evaluateSix(cards);
		}
		if  (num_cards == 7) {
			return HandEvaluator.evaluateSeven(cards);
		}
		
		/*
		 if {num_cards == 5}return evaluateDraw(cards);
		 if {num_cards == 6}return evaluateDrawSix(cards);
		 if {num_cards == 7}return evaluateDrawSeven(cards):
		 */
		else return null;
	}
	 //Test 6 cards
	//@Test
	public void testRandomXGetStats() {
		int num_hands = 10000;
		int num_cards = 7;
		HashMap<FiveCardHand, Integer> fiveCardHandCounts = new HashMap<>();
		for(int i = 0; i < num_hands; i++) {
			
			Deck deck = new Deck();
			deck.init();
			deck.shuffle();
			Card [] currentCards = new Card[num_cards];
			
			for(int j = 0; j < num_cards; j++) {
				currentCards[j] = deck.draw();	
			}

			FiveCardHand fiveCardHand = evaluateCards(num_cards, currentCards);

			
			Integer count = fiveCardHandCounts.get(fiveCardHand);
			if(count == null) {
				fiveCardHandCounts.put(fiveCardHand, 1);
				//System.out.println(count);
			}
			else {
				//System.out.println(count);
				count++;
				fiveCardHandCounts.put(fiveCardHand, count);
			}
			
			
		}
		
		for(FiveCardHand fiveCardHand : fiveCardHandCounts.keySet()) {
			System.out.println(fiveCardHand + ", " + fiveCardHandCounts.get(fiveCardHand));
		}
		
		//for (int i = 0; i < 3; i++) {
		//	DrawingHands.
		//}
		
	}
	 
	  	
	//@Test
	public void testPocketHand() {
		
		int num_hands = 22;
		for(int i = 0; i < num_hands; i++) {
			
			Deck deck = new Deck();
			deck.init();
			deck.shuffle();
			Card [] pocketCards = new Card[2];
			
			pocketCards[0] = deck.draw();//Card.TWO_OF_SPADES;//deck.draw();
			pocketCards[1] = deck.draw();//Card.FOUR_OF_SPADES;//deck.draw();
			
			PocketHand pocketHand = HandEvaluator.evaluatePocket(pocketCards);
			
			System.out.println(pocketCards[0].getShortName() + ", " + 
							   pocketCards[1].getShortName() + ": " + pocketHand);
		}
	}
	
	/*
	@Test
	public void testHighCard() {
		
		Deck deck = new Deck();
		deck.init();
		deck.shuffle();
		Card [] fiveCards = new Card[5];

		fiveCards[0] = Card.NINE_OF_SPADES;
		fiveCards[1] = Card.JACK_OF_CLUBS;
		fiveCards[2] = Card.TEN_OF_DIAMONDS;
		fiveCards[3] = Card.KING_OF_CLUBS;
		fiveCards[4] = Card.FOUR_OF_DIAMONDS;
		for(int i = 0; i < 5; i++) {
			System.out.print(fiveCards[i].getShortName() + ", ");
		}
		
		FiveCardHand fiveCardHand = HandEvaluator.evaluate(fiveCards);
		
		System.out.println(" ... HAND = " + fiveCardHand);
	}
	
	@Test
	public void testOnePair() {
		
		Deck deck = new Deck();
		deck.init();
		deck.shuffle();
		Card [] fiveCards = new Card[5];

		fiveCards[0] = Card.THREE_OF_CLUBS;
		fiveCards[1] = Card.JACK_OF_CLUBS;
		fiveCards[2] = Card.TEN_OF_DIAMONDS;
		fiveCards[3] = Card.EIGHT_OF_DIAMONDS;
		fiveCards[4] = Card.EIGHT_OF_SPADES;
		for(int i = 0; i < 5; i++) {
			System.out.print(fiveCards[i].getShortName() + ", ");
		}
		
		FiveCardHand fiveCardHand = HandEvaluator.evaluate(fiveCards);
		
		System.out.println(" ... HAND = " + fiveCardHand);
	}
	
	@Test
	public void testTwoPair() {
		
		Deck deck = new Deck();
		deck.init();
		deck.shuffle();
		Card [] fiveCards = new Card[5];

		fiveCards[0] = Card.FIVE_OF_CLUBS;
		fiveCards[1] = Card.SEVEN_OF_CLUBS;
		fiveCards[2] = Card.ACE_OF_SPADES;
		fiveCards[3] = Card.ACE_OF_HEARTS;
		fiveCards[4] = Card.FIVE_OF_DIAMONDS;
		for(int i = 0; i < 5; i++) {
			System.out.print(fiveCards[i].getShortName() + ", ");
		}
		
		FiveCardHand fiveCardHand = HandEvaluator.evaluate(fiveCards);
		
		System.out.println(" ... HAND = " + fiveCardHand);
	}
	
	@Test
	public void testThreeOfAKind() {
		
		Deck deck = new Deck();
		deck.init();
		deck.shuffle();
		Card [] fiveCards = new Card[5];

		fiveCards[0] = Card.EIGHT_OF_HEARTS;
		fiveCards[1] = Card.JACK_OF_CLUBS;
		fiveCards[2] = Card.TEN_OF_DIAMONDS;
		fiveCards[3] = Card.EIGHT_OF_DIAMONDS;
		fiveCards[4] = Card.EIGHT_OF_SPADES;
		for(int i = 0; i < 5; i++) {
			System.out.print(fiveCards[i].getShortName() + ", ");
		}
		
		FiveCardHand fiveCardHand = HandEvaluator.evaluate(fiveCards);
		
		System.out.println(" ... HAND = " + fiveCardHand);
	}
	
	@Test
	public void testStraight() {
		
		Deck deck = new Deck();
		deck.init();
		deck.shuffle();
		Card [] fiveCards = new Card[5];

		fiveCards[0] = Card.SEVEN_OF_CLUBS;
		fiveCards[1] = Card.NINE_OF_CLUBS;
		fiveCards[2] = Card.TEN_OF_DIAMONDS;
		fiveCards[3] = Card.SIX_OF_DIAMONDS;
		fiveCards[4] = Card.EIGHT_OF_SPADES;
		for(int i = 0; i < 5; i++) {
			System.out.print(fiveCards[i].getShortName() + ", ");
		}
		
		FiveCardHand fiveCardHand = HandEvaluator.evaluate(fiveCards);
		
		System.out.println(" ... HAND = " + fiveCardHand);
	}
	
	@Test
	public void testFlush() {
		
		Deck deck = new Deck();
		deck.init();
		deck.shuffle();
		Card [] fiveCards = new Card[5];

		fiveCards[0] = Card.EIGHT_OF_HEARTS;
		fiveCards[1] = Card.NINE_OF_HEARTS;
		fiveCards[2] = Card.TEN_OF_HEARTS;
		fiveCards[3] = Card.QUEEN_OF_HEARTS;
		fiveCards[4] = Card.TWO_OF_HEARTS;
		for(int i = 0; i < 5; i++) {
			System.out.print(fiveCards[i].getShortName() + ", ");
		}
		
		FiveCardHand fiveCardHand = HandEvaluator.evaluate(fiveCards);
		
		System.out.println(" ... HAND = " + fiveCardHand);
	}
	
	@Test
	public void testFullHouse() {
		
		Deck deck = new Deck();
		deck.init();
		deck.shuffle();
		Card [] fiveCards = new Card[5];

		fiveCards[0] = Card.SIX_OF_CLUBS;
		fiveCards[1] = Card.JACK_OF_CLUBS;
		fiveCards[2] = Card.SIX_OF_DIAMONDS;
		fiveCards[3] = Card.JACK_OF_SPADES;
		fiveCards[4] = Card.JACK_OF_HEARTS;
		for(int i = 0; i < 5; i++) {
			System.out.print(fiveCards[i].getShortName() + ", ");
		}
		
		FiveCardHand fiveCardHand = HandEvaluator.evaluate(fiveCards);
		
		System.out.println(" ... HAND = " + fiveCardHand);
	}
	
	@Test
	public void testFourOfAKind() {
		
		Deck deck = new Deck();
		deck.init();
		deck.shuffle();
		Card [] fiveCards = new Card[5];

		fiveCards[0] = Card.EIGHT_OF_HEARTS;
		fiveCards[1] = Card.JACK_OF_CLUBS;
		fiveCards[2] = Card.EIGHT_OF_CLUBS;
		fiveCards[3] = Card.EIGHT_OF_DIAMONDS;
		fiveCards[4] = Card.EIGHT_OF_SPADES;
		for(int i = 0; i < 5; i++) {
			System.out.print(fiveCards[i].getShortName() + ", ");
		}
		
		FiveCardHand fiveCardHand = HandEvaluator.evaluate(fiveCards);
		
		System.out.println(" ... HAND = " + fiveCardHand);
	}
	
	@Test
	public void testStraightFlush() {
		
		Deck deck = new Deck();
		deck.init();
		deck.shuffle();
		Card [] fiveCards = new Card[5];

		fiveCards[0] = Card.EIGHT_OF_HEARTS;
		fiveCards[1] = Card.NINE_OF_HEARTS;
		fiveCards[2] = Card.TEN_OF_HEARTS;
		fiveCards[3] = Card.QUEEN_OF_HEARTS;
		fiveCards[4] = Card.JACK_OF_HEARTS;
		for(int i = 0; i < 5; i++) {
			System.out.print(fiveCards[i].getShortName() + ", ");
		}
		
		FiveCardHand fiveCardHand = HandEvaluator.evaluate(fiveCards);
		
		System.out.println(" ... HAND = " + fiveCardHand);
	}

	@Test
	public void testRoyalFlush() {
		
		Deck deck = new Deck();
		deck.init();
		deck.shuffle();
		Card [] fiveCards = new Card[5];

		fiveCards[0] = Card.ACE_OF_DIAMONDS;
		fiveCards[1] = Card.JACK_OF_DIAMONDS;
		fiveCards[2] = Card.TEN_OF_DIAMONDS;
		fiveCards[3] = Card.KING_OF_DIAMONDS;
		fiveCards[4] = Card.QUEEN_OF_DIAMONDS;
		for(int i = 0; i < 5; i++) {
			System.out.print(fiveCards[i].getShortName() + ", ");
		}
		
		FiveCardHand fiveCardHand = HandEvaluator.evaluate(fiveCards);
		
		System.out.println(" ... HAND = " + fiveCardHand);
	}
	*/
}
