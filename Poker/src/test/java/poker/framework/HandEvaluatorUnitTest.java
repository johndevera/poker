package poker.framework;
//import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashMap;

import org.junit.Test;

import poker.framework.HandEvaluator.CardRankComparator;

import org.junit.Assert;

import static poker.framework.Card.*;

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
	public void testEvaluate() {
		Card[] fiveCards = {ACE_OF_SPADES, ACE_OF_CLUBS, ACE_OF_DIAMONDS, KING_OF_SPADES, KING_OF_CLUBS};
		FiveCardHand result1 = HandEvaluator.evaluate(fiveCards);
		Assert.assertEquals(FiveCardHand.FLUSH_A, result1);
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
	
	@Test
	public void testHighCard() {
		Card[] fiveCards = {ACE_OF_SPADES, ACE_OF_CLUBS, ACE_OF_DIAMONDS, KING_OF_SPADES, KING_OF_CLUBS};
		FiveCardHand five = HandEvaluator.evaluateHighCard(fiveCards);
		int cardValue = five.getCardValue();
		Assert.assertEquals(14, cardValue);
	}
	
	@Test
	public void testOnePair() {
		Card[] fiveCards = {ACE_OF_SPADES, SEVEN_OF_CLUBS, SEVEN_OF_SPADES, FIVE_OF_SPADES, THREE_OF_CLUBS};
		FiveCardHand five = HandEvaluator.evaluateOnePair(fiveCards);
		int cardValue = five.getCardValue();
		Assert.assertEquals(7, cardValue);
	}
	
	@Test
	public void testTwoPair() {
		Card[] fiveCards = {ACE_OF_SPADES, QUEEN_OF_CLUBS, QUEEN_OF_SPADES, SEVEN_OF_CLUBS, SEVEN_OF_SPADES};
		FiveCardHand five = HandEvaluator.evaluateTwoPair(fiveCards);
		int cardValue = five.getCardValue();
		Assert.assertEquals(12, cardValue);
	}
	
	@Test
	public void testGetBestFiveCards() {
		Card[] sevenHand = {SEVEN_OF_SPADES, KING_OF_HEARTS, ACE_OF_CLUBS, JACK_OF_SPADES, JACK_OF_HEARTS,  ACE_OF_DIAMONDS, SEVEN_OF_CLUBS};
		//Card[] sevenHand = {ACE_OF_SPADES, KING_OF_CLUBS, ACE_OF_CLUBS, QUEEN_OF_SPADES, KING_OF_HEARTS,  QUEEN_OF_CLUBS, TWO_OF_SPADES};
		//Card[] sevenHand = {ACE_OF_SPADES, QUEEN_OF_CLUBS, THREE_OF_CLUBS, QUEEN_OF_SPADES, SEVEN_OF_CLUBS,  TWO_OF_CLUBS, SEVEN_OF_SPADES};

		Card[] five = HandEvaluator.getBestFiveCards(sevenHand);
		Card[] goodFive = {ACE_OF_CLUBS, ACE_OF_DIAMONDS, KING_OF_HEARTS, JACK_OF_SPADES, JACK_OF_HEARTS, };
		Arrays.sort(five, new CardRankComparator());
		Arrays.sort(goodFive, new CardRankComparator());
		
		System.out.println(five[0]);
		System.out.println(five[1]);
		System.out.println(five[2]);
		System.out.println(five[3]);
		System.out.println(five[4]);
		/*
		System.out.println(five[0] + " + " + goodFive[0]);
		System.out.println(five[1] + " + " + goodFive[1]);
		System.out.println(five[2] + " + " + goodFive[2]);
		System.out.println(five[3] + " + " + goodFive[3]);
		System.out.println(five[4] + " + " + goodFive[4]);
		*/
		
		Assert.assertEquals(goodFive, five);
	}
	
	@Test
	public void testSevenCards() {
		Card[] sevenHand = {ACE_OF_SPADES, QUEEN_OF_CLUBS, THREE_OF_CLUBS, QUEEN_OF_SPADES, SEVEN_OF_CLUBS,  TWO_OF_CLUBS, SEVEN_OF_SPADES};
		FiveCardHand five = HandEvaluator.evaluateSeven(sevenHand);
		Assert.assertEquals(FiveCardHand.TWO_PAIR, five);
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
