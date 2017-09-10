package poker.framework;

import org.junit.Test;



import static org.junit.Assert.*;

import static poker.framework.Card.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class HandEvaluator_BestFiveCardsUnitTest {

	@Test
	public void testHighCard_1() {
		
		System.out.println("testHighCard_1");
		System.out.println("-------------");
		
		Card [] sevenCards = {ACE_OF_HEARTS, NINE_OF_SPADES, SIX_OF_HEARTS, SEVEN_OF_HEARTS, EIGHT_OF_HEARTS, JACK_OF_CLUBS, FOUR_OF_CLUBS};
	
		Card [] bestFiveExpected = {ACE_OF_HEARTS, JACK_OF_CLUBS, NINE_OF_SPADES, EIGHT_OF_HEARTS, SEVEN_OF_HEARTS};
		
		Card [] bestFiveActual = HandEvaluator.getBestFiveCards(sevenCards);
		
		HandEvaluator.sortDescending(bestFiveExpected);
		HandEvaluator.sortDescending(bestFiveActual);
		
		print("Seven Card: ", sevenCards);
		print("Expected: ", bestFiveExpected);
		print("Actual: ", bestFiveActual);
		
		boolean areEqual = 
				areCardsEqual(bestFiveExpected, bestFiveActual);
		
		assertTrue(areEqual);
		
		System.out.println("PASS = " + areEqual + "\n");
	}
	
	@Test
	public void testOnePair_1() {
		
		System.out.println("testOnePair_1");
		System.out.println("-------------");
		
		Card [] sevenCards = {QUEEN_OF_CLUBS, FIVE_OF_HEARTS, QUEEN_OF_DIAMONDS, SEVEN_OF_HEARTS, NINE_OF_SPADES, THREE_OF_CLUBS, KING_OF_HEARTS};
	
		Card [] bestFiveExpected = {QUEEN_OF_DIAMONDS, QUEEN_OF_CLUBS, NINE_OF_SPADES, SEVEN_OF_HEARTS, KING_OF_HEARTS};
		
		Card [] bestFiveActual = HandEvaluator.getBestFiveCards(sevenCards);
		
		HandEvaluator.sortDescending(bestFiveExpected);
		HandEvaluator.sortDescending(bestFiveActual);
		
		print("Seven Card: ", sevenCards);
		print("Expected: ", bestFiveExpected);
		print("Actual: ", bestFiveActual);
		
		boolean areEqual = 
				areCardsEqual(bestFiveExpected, bestFiveActual);
		
		assertTrue(areEqual);
		
		System.out.println("PASS = " + areEqual + "\n");
	}
	
	@Test
	public void testOnePair_2() {
		
		System.out.println("testOnePair_2");
		System.out.println("-------------");
		
		Card [] sevenCards = {THREE_OF_SPADES, FIVE_OF_HEARTS, QUEEN_OF_DIAMONDS, SEVEN_OF_HEARTS, NINE_OF_SPADES, THREE_OF_CLUBS, KING_OF_HEARTS};
	
		Card [] bestFiveExpected = {THREE_OF_SPADES, THREE_OF_CLUBS, NINE_OF_SPADES, QUEEN_OF_DIAMONDS, KING_OF_HEARTS};
		
		Card [] bestFiveActual = HandEvaluator.getBestFiveCards(sevenCards);
		
		HandEvaluator.sortDescending(bestFiveExpected);
		HandEvaluator.sortDescending(bestFiveActual);
		
		print("Seven Card: ", sevenCards);
		print("Expected: ", bestFiveExpected);
		print("Actual: ", bestFiveActual);
		
		boolean areEqual = 
				areCardsEqual(bestFiveExpected, bestFiveActual);
		
		assertTrue(areEqual);
		
		System.out.println("PASS = " + areEqual + "\n");
	}
	
	@Test
	public void testTwoPair_AABBCCx() {
		
		System.out.println("testTwoPair_AABBCCx");
		System.out.println("-------------");
		
		Card [] sevenCards = {ACE_OF_CLUBS, KING_OF_DIAMONDS, ACE_OF_HEARTS, KING_OF_CLUBS, SEVEN_OF_SPADES, THREE_OF_CLUBS, SEVEN_OF_HEARTS};
	
		Card [] bestFiveExpected1 = {ACE_OF_CLUBS, KING_OF_DIAMONDS, ACE_OF_HEARTS, KING_OF_CLUBS, SEVEN_OF_SPADES};
		Card [] bestFiveExpected2 = {ACE_OF_CLUBS, KING_OF_DIAMONDS, ACE_OF_HEARTS, KING_OF_CLUBS, SEVEN_OF_HEARTS};
		
		Card [] bestFiveActual = HandEvaluator.getBestFiveCards(sevenCards);
		
		HandEvaluator.sortDescending(bestFiveExpected1);
		HandEvaluator.sortDescending(bestFiveExpected2);
		HandEvaluator.sortDescending(bestFiveActual);
		
		print("Seven Card: ", sevenCards);
		print("Expected 1: ", bestFiveExpected1);
		print("Expected 2: ", bestFiveExpected2);
		print("Actual: ", bestFiveActual);
		
		boolean areEqual = 
				areCardsEqual(bestFiveExpected1, bestFiveActual) || areCardsEqual(bestFiveExpected2, bestFiveActual);
		
		assertTrue(areEqual);		
		
		System.out.println("PASS = " + areEqual + "\n");
	}
	
	@Test
	public void testTwoPair_QQ77K93() {
		
		System.out.println("testTwoPair_QQ77K93");
		System.out.println("-------------");
		
		Card [] sevenCards = {QUEEN_OF_CLUBS, SEVEN_OF_DIAMONDS, QUEEN_OF_DIAMONDS, SEVEN_OF_HEARTS, NINE_OF_SPADES, THREE_OF_CLUBS, KING_OF_HEARTS};
	
		Card [] bestFiveExpected = {QUEEN_OF_DIAMONDS, QUEEN_OF_CLUBS, SEVEN_OF_DIAMONDS, SEVEN_OF_HEARTS, KING_OF_HEARTS};
		
		Card [] bestFiveActual = HandEvaluator.getBestFiveCards(sevenCards);
		
		HandEvaluator.sortDescending(bestFiveExpected);
		HandEvaluator.sortDescending(bestFiveActual);
		
		print("Seven Card: ", sevenCards);
		print("Expected: ", bestFiveExpected);
		print("Actual: ", bestFiveActual);
		
		boolean areEqual = 
				areCardsEqual(bestFiveExpected, bestFiveActual);
		
		assertTrue(areEqual);			
		
		System.out.println("PASS = " + areEqual + "\n");
	}
	
	@Test
	public void testTrips_1() {
		
		System.out.println("testTrips_1");
		System.out.println("-------------");
		
		Card [] sevenCards = {FOUR_OF_DIAMONDS, ACE_OF_DIAMONDS, EIGHT_OF_SPADES, FOUR_OF_SPADES, FOUR_OF_HEARTS, NINE_OF_CLUBS, JACK_OF_CLUBS};
	
		Card [] bestFiveExpected = {FOUR_OF_DIAMONDS, FOUR_OF_SPADES, FOUR_OF_HEARTS, ACE_OF_DIAMONDS, JACK_OF_CLUBS};
		
		Card [] bestFiveActual = HandEvaluator.getBestFiveCards(sevenCards);
		
		HandEvaluator.sortDescending(bestFiveExpected);
		HandEvaluator.sortDescending(bestFiveActual);
		
		print("Seven Card: ", sevenCards);
		print("Expected: ", bestFiveExpected);
		print("Actual: ", bestFiveActual);
		
		boolean areEqual = 
				areCardsEqual(bestFiveExpected, bestFiveActual);
		
		assertTrue(areEqual);			
		
		System.out.println("PASS = " + areEqual + "\n");
	}
	
	@Test
	public void testStraight_low() {
		
		System.out.println("testStraight_low");
		System.out.println("-------------");
		
		Card [] sevenCards = {QUEEN_OF_CLUBS, ACE_OF_DIAMONDS, QUEEN_OF_DIAMONDS, TWO_OF_HEARTS, FOUR_OF_SPADES, THREE_OF_CLUBS, FIVE_OF_CLUBS};
	
		Card [] bestFiveExpected = {TWO_OF_HEARTS, FIVE_OF_CLUBS, ACE_OF_DIAMONDS, FOUR_OF_SPADES, THREE_OF_CLUBS};
		
		Card [] bestFiveActual = HandEvaluator.getBestFiveCards(sevenCards);

		if(bestFiveActual != null) {
			HandEvaluator.sortDescending(bestFiveExpected);
			HandEvaluator.sortDescending(bestFiveActual);
			
			print("Seven Card: ", sevenCards);
			print("Expected: ", bestFiveExpected);
			print("Actual: ", bestFiveActual);
			
			boolean areEqual = 
					areCardsEqual(bestFiveExpected, bestFiveActual);
			
			assertTrue(areEqual);			
			
			System.out.println("PASS = " + areEqual + "\n");
		}
		

	}
	
	@Test
	public void testStraight_middle() {
		
		System.out.println("testStraight_middle");
		System.out.println("-------------");
		
		Card [] sevenCards = {NINE_OF_DIAMONDS, ACE_OF_DIAMONDS, SEVEN_OF_CLUBS, SIX_OF_HEARTS, KING_OF_DIAMONDS, TEN_OF_HEARTS, EIGHT_OF_DIAMONDS};
	
		Card [] bestFiveExpected = {SIX_OF_HEARTS, SEVEN_OF_CLUBS, EIGHT_OF_DIAMONDS, NINE_OF_DIAMONDS, TEN_OF_HEARTS};
		
		Card [] bestFiveActual = HandEvaluator.getBestFiveCards(sevenCards);
		
		HandEvaluator.sortDescending(bestFiveExpected);
		HandEvaluator.sortDescending(bestFiveActual);
		
		print("Seven Card: ", sevenCards);
		print("Expected 1: ", bestFiveExpected);
		print("Actual: ", bestFiveActual);
		
		boolean areEqual = 
				areCardsEqual(bestFiveExpected, bestFiveActual);
		
		assertTrue(areEqual);			
		
		System.out.println("PASS = " + areEqual + "\n");
	}
	
	@Test
	public void testStraight_high() {
		
		System.out.println("testStraight_high");
		System.out.println("-------------");
		
		Card [] sevenCards = {KING_OF_CLUBS, ACE_OF_DIAMONDS, KING_OF_SPADES, JACK_OF_SPADES, KING_OF_HEARTS, TEN_OF_HEARTS, QUEEN_OF_DIAMONDS};
	
		Card [] bestFiveExpected1 = {ACE_OF_DIAMONDS, KING_OF_HEARTS, JACK_OF_SPADES, TEN_OF_HEARTS, QUEEN_OF_DIAMONDS};
		Card [] bestFiveExpected2 = {ACE_OF_DIAMONDS, KING_OF_CLUBS, JACK_OF_SPADES, TEN_OF_HEARTS, QUEEN_OF_DIAMONDS};
		
		Card [] bestFiveActual = HandEvaluator.getBestFiveCards(sevenCards);
		
		HandEvaluator.sortDescending(bestFiveExpected1);
		HandEvaluator.sortDescending(bestFiveExpected2);
		HandEvaluator.sortDescending(bestFiveActual);
		
		print("Seven Card: ", sevenCards);
		print("Expected 1: ", bestFiveExpected1);
		print("Expected 2: ", bestFiveExpected2);
		print("Actual: ", bestFiveActual);
		
		boolean areEqual = 
				areCardsEqual(bestFiveExpected1, bestFiveActual) || areCardsEqual(bestFiveExpected2, bestFiveActual);
		
		assertTrue(areEqual);			
		
		System.out.println("PASS = " + areEqual + "\n");
	}
	
	@Test
	public void testFlush_1() {
		
		System.out.println("testFlush_1");
		System.out.println("-------------");
		
		Card [] sevenCards = {FOUR_OF_DIAMONDS, ACE_OF_DIAMONDS, KING_OF_DIAMONDS, JACK_OF_SPADES, NINE_OF_DIAMONDS, ACE_OF_HEARTS, JACK_OF_DIAMONDS};
	
		Card [] bestFiveExpected = {FOUR_OF_DIAMONDS, ACE_OF_DIAMONDS, NINE_OF_DIAMONDS, KING_OF_DIAMONDS, JACK_OF_DIAMONDS};
		
		Card [] bestFiveActual = HandEvaluator.getBestFiveCards(sevenCards);
		
		HandEvaluator.sortDescending(bestFiveExpected);
		HandEvaluator.sortDescending(bestFiveActual);
		
		print("Seven Card: ", sevenCards);
		print("Expected: ", bestFiveExpected);
		print("Actual: ", bestFiveActual);
		
		boolean areEqual = 
				areCardsEqual(bestFiveExpected, bestFiveActual);
		
		assertTrue(areEqual);			
		
		System.out.println("PASS = " + areEqual + "\n");
	}
	
	@Test
	public void testFlush_2() {
		
		System.out.println("testFlush_2");
		System.out.println("-------------");
		
		Card [] sevenCards = {FOUR_OF_DIAMONDS, ACE_OF_DIAMONDS, KING_OF_DIAMONDS, QUEEN_OF_DIAMONDS, NINE_OF_DIAMONDS, SIX_OF_DIAMONDS, JACK_OF_DIAMONDS};
	
		Card [] bestFiveExpected = {QUEEN_OF_DIAMONDS, ACE_OF_DIAMONDS, NINE_OF_DIAMONDS, KING_OF_DIAMONDS, JACK_OF_DIAMONDS};
		
		Card [] bestFiveActual = HandEvaluator.getBestFiveCards(sevenCards);
		
		HandEvaluator.sortDescending(bestFiveExpected);
		HandEvaluator.sortDescending(bestFiveActual);
		
		print("Seven Card: ", sevenCards);
		print("Expected: ", bestFiveExpected);
		print("Actual: ", bestFiveActual);
		
		boolean areEqual = 
				areCardsEqual(bestFiveExpected, bestFiveActual);
		
		assertTrue(areEqual);			
		
		System.out.println("PASS = " + areEqual + "\n");
	}
	
	@Test
	public void testFlush_3() {
		
		System.out.println("testFlush_3");
		System.out.println("-------------");
		
		Card [] sevenCards = {FOUR_OF_DIAMONDS, ACE_OF_DIAMONDS, KING_OF_DIAMONDS, ACE_OF_SPADES, KING_OF_HEARTS, SIX_OF_DIAMONDS, JACK_OF_DIAMONDS};
	
		Card [] bestFiveExpected = {ACE_OF_DIAMONDS, KING_OF_DIAMONDS, JACK_OF_DIAMONDS, SIX_OF_DIAMONDS, FOUR_OF_DIAMONDS};
		
		Card [] bestFiveActual = HandEvaluator.getBestFiveCards(sevenCards);
		
		HandEvaluator.sortDescending(bestFiveExpected);
		HandEvaluator.sortDescending(bestFiveActual);
		
		print("Seven Card: ", sevenCards);
		print("Expected: ", bestFiveExpected);
		print("Actual: ", bestFiveActual);
		
		boolean areEqual = 
				areCardsEqual(bestFiveExpected, bestFiveActual);
		
		assertTrue(areEqual);			
		
		System.out.println("PASS = " + areEqual + "\n");
	}
	
	@Test
	public void testFullHouse_1() {
		
		System.out.println("testFullHouse_1");
		System.out.println("-------------");
		
		Card [] sevenCards = {FOUR_OF_DIAMONDS, ACE_OF_DIAMONDS, ACE_OF_HEARTS, ACE_OF_SPADES, KING_OF_HEARTS, FOUR_OF_SPADES, FOUR_OF_CLUBS};
	
		Card [] bestFiveExpected1 = {ACE_OF_DIAMONDS, ACE_OF_HEARTS, ACE_OF_SPADES, FOUR_OF_SPADES, FOUR_OF_CLUBS};
		Card [] bestFiveExpected2 = {ACE_OF_DIAMONDS, ACE_OF_HEARTS, ACE_OF_SPADES, FOUR_OF_SPADES, FOUR_OF_DIAMONDS};
		Card [] bestFiveExpected3 = {ACE_OF_DIAMONDS, ACE_OF_HEARTS, ACE_OF_SPADES, FOUR_OF_CLUBS, FOUR_OF_DIAMONDS};
		
		Card [] bestFiveActual = HandEvaluator.getBestFiveCards(sevenCards);
		
		HandEvaluator.sortDescending(bestFiveExpected1);
		HandEvaluator.sortDescending(bestFiveExpected2);
		HandEvaluator.sortDescending(bestFiveExpected3);
		HandEvaluator.sortDescending(bestFiveActual);
		
		print("Seven Card: ", sevenCards);
		print("Expected 1: ", bestFiveExpected1);
		print("Expected 2: ", bestFiveExpected2);
		print("Expected 3: ", bestFiveExpected3);
		print("Actual: ", bestFiveActual);
		
		boolean areEqual = 
				areCardsEqual(bestFiveExpected1, bestFiveActual) ||
				areCardsEqual(bestFiveExpected2, bestFiveActual) ||
				areCardsEqual(bestFiveExpected3, bestFiveActual);
		
		assertTrue(areEqual);			
		
		System.out.println("PASS = " + areEqual + "\n");
	}
	
	@Test
	public void testFullHouse_2() {
		
		System.out.println("testFullHouse_2");
		System.out.println("-------------");
		
		Card [] sevenCards = {FOUR_OF_DIAMONDS, ACE_OF_DIAMONDS, ACE_OF_HEARTS, KING_OF_CLUBS, KING_OF_HEARTS, FOUR_OF_SPADES, FOUR_OF_CLUBS};
	
		Card [] bestFiveExpected = {ACE_OF_DIAMONDS, ACE_OF_HEARTS, FOUR_OF_DIAMONDS, FOUR_OF_SPADES, FOUR_OF_CLUBS};
		
		Card [] bestFiveActual = HandEvaluator.getBestFiveCards(sevenCards);
		
		HandEvaluator.sortDescending(bestFiveExpected);
		HandEvaluator.sortDescending(bestFiveActual);
		
		print("Seven Card: ", sevenCards);
		print("Expected 1: ", bestFiveExpected);
		print("Actual: ", bestFiveActual);
		
		boolean areEqual = 
				areCardsEqual(bestFiveExpected, bestFiveActual);
		
		assertTrue(areEqual);			
		
		System.out.println("PASS = " + areEqual + "\n");
	}
	
	@Test
	public void testFourOfAKind() {
		
		System.out.println("testFourOfAKind");
		System.out.println("-------------");
		
		Card [] sevenCards = {JACK_OF_HEARTS, ACE_OF_DIAMONDS, JACK_OF_SPADES, ACE_OF_CLUBS, JACK_OF_CLUBS, ACE_OF_SPADES, JACK_OF_DIAMONDS};
	
		Card [] bestFiveExpected1 = {JACK_OF_HEARTS, JACK_OF_SPADES, JACK_OF_CLUBS, JACK_OF_DIAMONDS, ACE_OF_DIAMONDS};
		Card [] bestFiveExpected2 = {JACK_OF_HEARTS, JACK_OF_SPADES, JACK_OF_CLUBS, JACK_OF_DIAMONDS, ACE_OF_SPADES};
		
		Card [] bestFiveActual = HandEvaluator.getBestFiveCards(sevenCards);
		
		HandEvaluator.sortDescending(bestFiveExpected1);
		HandEvaluator.sortDescending(bestFiveExpected2);
		HandEvaluator.sortDescending(bestFiveActual);
		
		print("Seven Card: ", sevenCards);
		print("Expected 1: ", bestFiveExpected1);
		print("Expected 2: ", bestFiveExpected2);
		print("Actual: ", bestFiveActual);
		
		boolean areEqual = 
				areCardsEqual(bestFiveExpected1, bestFiveActual) || areCardsEqual(bestFiveExpected2, bestFiveActual);
		
		assertTrue(areEqual);			
		
		System.out.println("PASS = " + areEqual + "\n");
	}
	
	@Test
	public void testStraightFlush() {
		
		System.out.println("testStraightFlush_1");
		System.out.println("-------------");
		
		Card [] sevenCards = {JACK_OF_HEARTS, NINE_OF_CLUBS, NINE_OF_SPADES, NINE_OF_HEARTS, QUEEN_OF_HEARTS, EIGHT_OF_HEARTS, TEN_OF_HEARTS};
	
		Card [] bestFiveExpected = {QUEEN_OF_HEARTS, JACK_OF_HEARTS, TEN_OF_HEARTS, NINE_OF_HEARTS, EIGHT_OF_HEARTS};		
		
		Card [] bestFiveActual = HandEvaluator.getBestFiveCards(sevenCards);
		
		HandEvaluator.sortDescending(bestFiveExpected);
		HandEvaluator.sortDescending(bestFiveActual);
		
		print("Seven Card: ", sevenCards);
		print("Expected: ", bestFiveExpected);
		print("Actual: ", bestFiveActual);
		
		boolean areEqual = 
				areCardsEqual(bestFiveExpected, bestFiveActual);
		
		assertTrue(areEqual);			
		
		System.out.println("PASS = " + areEqual + "\n");
	}
	
	@Test
	public void testStraightFlush_2() {
		
		System.out.println("testStraightFlush_2");
		System.out.println("-------------");
		
		Card [] sevenCards = {JACK_OF_HEARTS, KING_OF_HEARTS, SEVEN_OF_HEARTS, NINE_OF_HEARTS, QUEEN_OF_HEARTS, EIGHT_OF_HEARTS, TEN_OF_HEARTS};
	
		Card [] bestFiveExpected = {KING_OF_HEARTS, QUEEN_OF_HEARTS, JACK_OF_HEARTS, TEN_OF_HEARTS, NINE_OF_HEARTS};		
		
		Card [] bestFiveActual = HandEvaluator.getBestFiveCards(sevenCards);
		
		HandEvaluator.sortDescending(bestFiveExpected);
		HandEvaluator.sortDescending(bestFiveActual);
		
		print("Seven Card: ", sevenCards);
		print("Expected: ", bestFiveExpected);
		print("Actual: ", bestFiveActual);
		
		boolean areEqual = 
				areCardsEqual(bestFiveExpected, bestFiveActual);
		
		assertTrue(areEqual);			
		
		System.out.println("PASS = " + areEqual + "\n");
	}
	
	@Test
	public void testRoyalFlush() {
		
		System.out.println("testRoyalFlush");
		System.out.println("-------------");
		
		Card [] sevenCards = {JACK_OF_HEARTS, KING_OF_HEARTS, ACE_OF_HEARTS, NINE_OF_HEARTS, QUEEN_OF_HEARTS, TWO_OF_HEARTS, TEN_OF_HEARTS};
	
		Card [] bestFiveExpected = {ACE_OF_HEARTS, KING_OF_HEARTS, QUEEN_OF_HEARTS, JACK_OF_HEARTS, TEN_OF_HEARTS};		
		
		Card [] bestFiveActual = HandEvaluator.getBestFiveCards(sevenCards);
		
		HandEvaluator.sortDescending(bestFiveExpected);
		HandEvaluator.sortDescending(bestFiveActual);
		
		print("Seven Card: ", sevenCards);
		print("Expected: ", bestFiveExpected);
		print("Actual: ", bestFiveActual);
		
		boolean areEqual = 
				areCardsEqual(bestFiveExpected, bestFiveActual);
		
		assertTrue(areEqual);			
		
		System.out.println("PASS = " + areEqual + "\n");
	}
	
	@Test
	public void testGetBestHand() {
		
		System.out.println("testGetBestHand");
		System.out.println("-------------");
		
		//Card [] five1 = {ACE_OF_HEARTS, KING_OF_HEARTS, QUEEN_OF_HEARTS, JACK_OF_HEARTS, TEN_OF_HEARTS};
		//Card [] five2 = {NINE_OF_HEARTS, KING_OF_HEARTS, QUEEN_OF_HEARTS, JACK_OF_HEARTS, TEN_OF_HEARTS};
		//Card [] five3 = {NINE_OF_HEARTS, KING_OF_HEARTS, QUEEN_OF_HEARTS, JACK_OF_HEARTS, TEN_OF_HEARTS};
		//Card [] five4 = {NINE_OF_HEARTS, KING_OF_HEARTS, QUEEN_OF_HEARTS, JACK_OF_HEARTS, TEN_OF_HEARTS};
		//Card [] five5 = {JACK_OF_HEARTS, KING_OF_HEARTS, SEVEN_OF_HEARTS, NINE_OF_HEARTS, QUEEN_OF_HEARTS};
		//Card [] five6 = {JACK_OF_HEARTS, JACK_OF_SPADES, JACK_OF_CLUBS, JACK_OF_DIAMONDS, ACE_OF_DIAMONDS};
		//Card [] five7 = {JACK_OF_HEARTS, JACK_OF_SPADES, JACK_OF_CLUBS, JACK_OF_DIAMONDS, KING_OF_DIAMONDS};
		Card [] five8 = {ACE_OF_HEARTS, ACE_OF_SPADES, KING_OF_CLUBS, QUEEN_OF_DIAMONDS, QUEEN_OF_DIAMONDS};
		Card [] five9 = {ACE_OF_HEARTS, ACE_OF_SPADES, KING_OF_CLUBS, KING_OF_DIAMONDS, QUEEN_OF_DIAMONDS};
		
		//Arrays.sort(five1, new CardRankComparator());
		//Arrays.sort(five2, new CardRankComparator());
		//Arrays.sort(five3, new CardRankComparator());
		
		
		List<Card[]> hand = new ArrayList<>();
		//hand.add(five1);
		//hand.add(five2);
		//hand.add(five3);
		//hand.add(five4);
		//hand.add(five5);
		//hand.add(five6);
		//hand.add(five7);
		hand.add(five8);
		hand.add(five9);
		
		Card [] bestFiveExpected = {ACE_OF_HEARTS, ACE_OF_SPADES, KING_OF_CLUBS, KING_OF_DIAMONDS, QUEEN_OF_DIAMONDS};
		//Card [] bestFiveExpected = {NINE_OF_HEARTS, KING_OF_HEARTS, QUEEN_OF_HEARTS, JACK_OF_HEARTS, TEN_OF_HEARTS};	
		FiveCardHand fiveExp = HandEvaluator.evaluate(bestFiveExpected);
		
		List<Card[]> bestFiveActual = HandEvaluator.getBestHand(hand);
		FiveCardHand fiveAct = HandEvaluator.evaluate(bestFiveActual.get(0));
		
		int numberOfWinners = bestFiveActual.size(); 
		
		System.out.println("Expected: " + fiveExp);
		System.out.println("Actual: " + fiveAct);
		System.out.println("NumWinners: " + numberOfWinners);
		
		boolean areEqual = 
				areCardsEqual(bestFiveExpected, bestFiveActual.get(0));
		
		assertTrue(areEqual);			
		
		System.out.println("PASS = " + areEqual + "\n");
	}
	
	@Test
	public void testGetBestHand_Pair() {
		
		System.out.println("testGetBestHand_Pair");
		System.out.println("-------------");
		
		Card [] hand1 = {ACE_OF_SPADES, ACE_OF_CLUBS, QUEEN_OF_DIAMONDS, JACK_OF_SPADES, TEN_OF_HEARTS};
		Card [] hand2 = {ACE_OF_SPADES, ACE_OF_CLUBS, KING_OF_DIAMONDS, THREE_OF_HEARTS, TWO_OF_HEARTS};
		
		List<Card []> bestHands = HandEvaluator.getBestHand(Arrays.asList(hand1, hand2));
		
		assertNotNull(bestHands);
		assertEquals(1, bestHands.size());
		
		Card [] actual = bestHands.get(0);
		boolean areEqual = areCardsEqual(hand1, actual);
		
		assertTrue(areEqual);
		
		print("hand1: ", hand1);
		print("hand2: ", hand2);
		System.out.println("---");
		print("expected: ", hand1);
		print("actual: ", actual);
		
		System.out.println("PASS = " + areEqual + "\n");
	}
	
	@Test
	public void testGetBestHand_TwoPair() {
		
		System.out.println("testGetBestHand_TwoPair");
		System.out.println("-------------");
		
		Card [] hand1 = {ACE_OF_SPADES, ACE_OF_CLUBS, QUEEN_OF_DIAMONDS, TEN_OF_DIAMONDS, TEN_OF_HEARTS};
		Card [] hand2 = {KING_OF_CLUBS, KING_OF_SPADES, THREE_OF_DIAMONDS, THREE_OF_HEARTS, TWO_OF_HEARTS};
		
		List<Card []> bestHands = HandEvaluator.getBestHand(Arrays.asList(hand1, hand2));
		
		assertNotNull(bestHands);
		assertEquals(1, bestHands.size());
		
		Card [] actual = bestHands.get(0);
		boolean areEqual = areCardsEqual(hand1, actual);
		
		assertTrue(areEqual);
		
		print("hand1: ", hand1);
		print("hand2: ", hand2);
		System.out.println("---");
		print("expected: ", hand1);
		print("actual: ", actual);
		
		System.out.println("PASS = " + areEqual + "\n");
	}
	
	private boolean areCardsEqual(Card [] expected, Card [] actual) {
		return (
				expected[0] == actual[0] &&
				expected[1] == actual[1] &&
				expected[2] == actual[2] &&
				expected[3] == actual[3] &&
				expected[4] == actual[4]
				);
	}
	
	private void print(String s, Card [] cards) {
		System.out.print(s);
		for(int i = 0; i < cards.length; i++) {
			System.out.print(cards[i].getShortName() + ",");
		}
		System.out.println();
	}
}
