package poker.framework;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;



import static org.junit.Assert.*;

import static poker.framework.Card.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HandEvaluator_BestFiveCardsUnitTest {

	@Test
	public void test_01_HighCard_1() {
		
		System.out.println("test_01_HighCard_1");
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
		
		System.out.println((areEqual) ? "PASS\n" : "FAIL\n");
		assertTrue(areEqual);
	}
	
	@Test
	public void test_02_OnePair_1() {
		
		System.out.println("test_02_OnePair_1");
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
		
		System.out.println((areEqual) ? "PASS\n" : "FAIL\n");
		assertTrue(areEqual);
	}
	
	@Test
	public void test_03_OnePair_2() {
		
		System.out.println("test_03_OnePair_2");
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
		
		System.out.println((areEqual) ? "PASS\n" : "FAIL\n");
		assertTrue(areEqual);
	}
	
	@Test
	public void test_04_TwoPair_AABBCCx() {
		
		System.out.println("test_04_TwoPair_AABBCCx");
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
		
		System.out.println((areEqual) ? "PASS\n" : "FAIL\n");
		assertTrue(areEqual);
	}
	
	@Test
	public void test_05_TwoPair_QQ77K93() {
		
		System.out.println("test_05_TwoPair_QQ77K93");
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
		
		System.out.println((areEqual) ? "PASS\n" : "FAIL\n");
		assertTrue(areEqual);
	}
	
	@Test
	public void test_06_Trips_1() {
		
		System.out.println("test_06_Trips_1");
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
		
		System.out.println((areEqual) ? "PASS\n" : "FAIL\n");
		assertTrue(areEqual);
	}
	
	@Test
	public void test_07_Straight_low() {
		
		System.out.println("test_07_Straight_low");
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
			
			System.out.println((areEqual) ? "PASS\n" : "FAIL\n");
			assertTrue(areEqual);			
		}
	}
	
	@Test
	public void test_08_Straight_middle() {
		
		System.out.println("test_08_Straight_middle");
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
		
		System.out.println((areEqual) ? "PASS\n" : "FAIL\n");
		assertTrue(areEqual);			
	}
	
	@Test
	public void test_09_Straight_high() {
		
		System.out.println("test_09_Straight_high");
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
		System.out.println((areEqual) ? "PASS\n" : "FAIL\n");
	}
	
	@Test
	public void test_10_Flush_1() {
		
		System.out.println("test_10_Flush_1");
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
		
		System.out.println((areEqual) ? "PASS\n" : "FAIL\n");
		assertTrue(areEqual);
	}
	
	@Test
	public void test_11_Flush_2() {
		
		System.out.println("test_11_Flush_2");
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
		
		System.out.println((areEqual) ? "PASS\n" : "FAIL\n");
		assertTrue(areEqual);			
	}
	
	@Test
	public void test_12_Flush_3() {
		
		System.out.println("test_12_Flush_3");
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
		
		System.out.println((areEqual) ? "PASS\n" : "FAIL\n");
		assertTrue(areEqual);			
	}
	
	@Test
	public void test_13_FullHouse_1() {
		
		System.out.println("test_13_FullHouse_1");
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
		
		System.out.println((areEqual) ? "PASS\n" : "FAIL\n");
		assertTrue(areEqual);			
	}
	
	@Test
	public void test_14_FullHouse_2() {
		
		System.out.println("test_14_FullHouse_2");
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
		
		System.out.println((areEqual) ? "PASS\n" : "FAIL\n");
		assertTrue(areEqual);			
	}
	
	@Test
	public void test_15_FourOfAKind() {
		
		System.out.println("test_15_FourOfAKind");
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
		
		System.out.println((areEqual) ? "PASS\n" : "FAIL\n");
		assertTrue(areEqual);			
	}
	
	@Test
	public void test_16_StraightFlush() {
		
		System.out.println("test_16_StraightFlush");
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
		
		System.out.println((areEqual) ? "PASS\n" : "FAIL\n");
		assertTrue(areEqual);			
	}
	
	@Test
	public void test_17_StraightFlush_2() {
		
		System.out.println("test_17_StraightFlush_2");
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
		
		System.out.println((areEqual) ? "PASS\n" : "FAIL\n");
		assertTrue(areEqual);			
	}
	
	@Test
	public void test_18_RoyalFlush() {
		
		System.out.println("test_18_RoyalFlush");
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
		
		System.out.println((areEqual) ? "PASS\n" : "FAIL\n");
		assertTrue(areEqual);			
	}
	
	@Test
	public void test_19_GetBestHand() {
		
		System.out.println("test_19_GetBestHand");
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
		
		System.out.println((areEqual) ? "PASS\n" : "FAIL\n");
		assertTrue(areEqual);			
	}
	
	@Test
	public void test_20_GetBestHand_Pair() {
		
		System.out.println("test_20_GetBestHand_Pair");
		System.out.println("-------------");
		
		Card [] hand1 = {ACE_OF_SPADES, ACE_OF_CLUBS, QUEEN_OF_DIAMONDS, JACK_OF_SPADES, TEN_OF_HEARTS};
		Card [] hand2 = {ACE_OF_SPADES, ACE_OF_CLUBS, KING_OF_DIAMONDS, THREE_OF_HEARTS, TWO_OF_HEARTS};
		
		List<Card []> bestHands = HandEvaluator.getBestHand(Arrays.asList(hand1, hand2));
		
		assertNotNull(bestHands);
		assertEquals(1, bestHands.size());
		
		Card [] actual = bestHands.get(0);
		Card [] expected = hand2;

		HandEvaluator.sortDescending(actual);
		HandEvaluator.sortDescending(hand2);
		
		print("hand1: ", hand1);
		print("hand2: ", hand2);
		System.out.println("---");
		print("expected: ", expected);
		print("actual: ", actual);
		
		boolean areEqual = areCardsEqual(expected, actual);
		
		System.out.println((areEqual) ? "PASS\n" : "FAIL\n");
		assertTrue(areEqual);
	}
	
	@Test
	public void test_21_GetBestHand_TwoPair() {
		
		System.out.println("test_21_GetBestHand_TwoPair");
		System.out.println("-------------");
		
		//Card [] hand1 = {ACE_OF_SPADES, ACE_OF_CLUBS, QUEEN_OF_DIAMONDS, TEN_OF_DIAMONDS, TEN_OF_HEARTS};
		//Card [] hand2 = {KING_OF_CLUBS, KING_OF_SPADES, THREE_OF_DIAMONDS, THREE_OF_HEARTS, TWO_OF_HEARTS};
		Card [] hand1 = {ACE_OF_SPADES, ACE_OF_CLUBS, KING_OF_DIAMONDS, QUEEN_OF_DIAMONDS, KING_OF_HEARTS};
		Card [] hand2 = {ACE_OF_SPADES, ACE_OF_CLUBS, KING_OF_DIAMONDS, JACK_OF_DIAMONDS, KING_OF_HEARTS};
		
		List<Card []> bestHands = HandEvaluator.getBestHand(Arrays.asList(hand1, hand2));
		
		assertNotNull(bestHands);
		assertEquals(1, bestHands.size());
		
		Card [] actual = bestHands.get(0);
		Card [] expected = hand1;
		
		print("hand1: ", hand1);
		print("hand2: ", hand2);
		System.out.println("---");
		print("expected: ", expected);
		print("actual: ", actual);
		
		boolean areEqual = areCardsEqual(expected, actual);
		
		System.out.println((areEqual) ? "PASS\n" : "FAIL\n");
		assertTrue(areEqual);
	}
	
	@Test
	public void test_22_GetBestHand_TwoPair_ThreeHands() {
		
		System.out.println("test_22_GetBestHand_TwoPair_ThreeHands");
		System.out.println("-------------");
		
		Card [] hand1 = {ACE_OF_SPADES, ACE_OF_CLUBS, QUEEN_OF_DIAMONDS, TEN_OF_DIAMONDS, TEN_OF_HEARTS};
		Card [] hand2 = {KING_OF_CLUBS, KING_OF_SPADES, THREE_OF_DIAMONDS, THREE_OF_HEARTS, TWO_OF_HEARTS};
		Card [] hand3 = {ACE_OF_SPADES, ACE_OF_CLUBS, KING_OF_HEARTS, TEN_OF_DIAMONDS, TEN_OF_HEARTS};

		
		List<Card []> bestHands = HandEvaluator.getBestHand(Arrays.asList(hand1, hand2, hand3));
		
		assertNotNull(bestHands);
		assertEquals(1, bestHands.size());
		
		Card [] actual = bestHands.get(0);
		Card [] expected = hand3;
		boolean areEqual = areCardsEqual(expected, actual);
				
		print("hand1: ", hand1);
		print("hand2: ", hand2);
		print("hand3: ", hand3);

		System.out.println("---");
		print("expected: ", expected);
		print("actual: ", actual);
		
		System.out.println((areEqual) ? "PASS\n" : "FAIL\n");
		assertTrue(areEqual);
	}
	
	@Test
	public void test_23_GetBestHand_TwoPair_4ofTheSAME() {
		
		System.out.println("test_23_GetBestHand_TwoPair_4ofTheSAME");
		System.out.println("-------------");
		
		//Card [] hand1 = {ACE_OF_SPADES, ACE_OF_CLUBS, QUEEN_OF_DIAMONDS, TEN_OF_DIAMONDS, TEN_OF_HEARTS};
		//Card [] hand2 = {KING_OF_CLUBS, KING_OF_SPADES, THREE_OF_DIAMONDS, THREE_OF_HEARTS, TWO_OF_HEARTS};
		Card [] hand3 = {ACE_OF_SPADES, ACE_OF_CLUBS, KING_OF_HEARTS, TEN_OF_DIAMONDS, TEN_OF_HEARTS};
		Card [] hand4 = {ACE_OF_SPADES, ACE_OF_CLUBS, KING_OF_HEARTS, TEN_OF_DIAMONDS, TEN_OF_HEARTS};
		Card [] hand5 = {ACE_OF_SPADES, ACE_OF_CLUBS, KING_OF_HEARTS, TEN_OF_DIAMONDS, TEN_OF_HEARTS};
		Card [] hand6 = {ACE_OF_SPADES, ACE_OF_CLUBS, KING_OF_HEARTS, TEN_OF_DIAMONDS, TEN_OF_HEARTS};

		
		List<Card []> bestHands = HandEvaluator.getBestHand(Arrays.asList( hand3, hand4, hand5, hand6));
		
		assertNotNull(bestHands);
		assertEquals(4, bestHands.size());
		
		Card [] actual = bestHands.get(0);
		Card [] expected = hand3;
		boolean areEqual = areCardsEqual(expected, actual);
				
		//print("hand1: ", hand1);
		//print("hand2: ", hand2);
		print("hand3: ", hand3);
		print("hand4: ", hand4); //same as 1
		print("hand5: ", hand5); //same as 2
		print("hand6: ", hand6); //same as 3
		System.out.println("---");
		print("expected: ", expected);
		print("actual: ", actual);
		
		System.out.println((areEqual) ? "PASS\n" : "FAIL\n");
		assertTrue(areEqual);
	}
	
	@Test
	public void test_24_GetBestHand_Straight() {
		
		System.out.println("test_24_GetBestHand_Straight");
		System.out.println("-------------");
		
		Card [] hand1 = {ACE_OF_SPADES, TWO_OF_HEARTS, THREE_OF_SPADES, FIVE_OF_DIAMONDS, FOUR_OF_HEARTS};
		Card [] hand2 = {SIX_OF_CLUBS, TWO_OF_HEARTS, THREE_OF_SPADES, FIVE_OF_DIAMONDS, FOUR_OF_HEARTS};
		//Card [] hand3 = {SIX_OF_CLUBS, SEVEN_OF_CLUBS, THREE_OF_SPADES, FIVE_OF_DIAMONDS, FOUR_OF_HEARTS};

		
		//List<Card []> bestHands = HandEvaluator.getBestHand(Arrays.asList(hand1, hand2, hand3));
		List<Card []> bestHands = HandEvaluator.getBestHand(Arrays.asList(hand1, hand2));
		
		assertNotNull(bestHands);
		assertEquals(1, bestHands.size());
		
		Card [] actual = bestHands.get(0);
		Card [] expected = hand2;
		boolean areEqual = areCardsEqual(expected, actual);
				
		print("hand1: ", hand1);
		print("hand2: ", hand2);
		//print("hand3: ", hand3);
		System.out.println("---");
		print("expected: ", expected);
		print("actual: ", actual);
		
		System.out.println((areEqual) ? "PASS\n" : "FAIL\n");
		assertTrue(areEqual);
	}
	
	@Test
	public void test_25_GetBestHand_Straight_Tie() {
		
		System.out.println("test_25_GetBestHand_Straight_Tie");
		System.out.println("-------------");
		
		Card [] hand1 = {ACE_OF_SPADES, TWO_OF_HEARTS, THREE_OF_SPADES, FIVE_OF_DIAMONDS, FOUR_OF_HEARTS};
		Card [] hand2 = {ACE_OF_HEARTS, TWO_OF_HEARTS, THREE_OF_SPADES, FIVE_OF_DIAMONDS, FOUR_OF_HEARTS};

		List<Card []> bestHands = HandEvaluator.getBestHand(Arrays.asList(hand1, hand2));
		
		assertNotNull(bestHands);
		assertEquals(2, bestHands.size());
		
		Card [] actual1 = bestHands.get(0);
		Card [] actual2 = bestHands.get(1);

		Card [] expected1 = hand1;
		Card [] expected2 = hand2;
		
		boolean areEqual1 = areCardsEqual(expected1, actual1);
		boolean areEqual2 = areCardsEqual(expected2, actual2);
				
		print("hand1: ", hand1);
		print("hand2: ", hand2);
		System.out.println("---");
		print("expected1: ", expected1);
		print("actual11: ", actual1);
		System.out.println("---");
		print("expected2: ", expected2);
		print("actual2: ", actual2);
		
		boolean areEqual = areEqual1 && areEqual2;
		System.out.println((areEqual) ? "PASS\n" : "FAIL\n");
		assertTrue(areEqual);
	}
	@Test
	public void test_26_GetBestHand_TwoPair_4Hands2Ties() {
		
		System.out.println("test_26_GetBestHand_TwoPair_4Hands2Ties");
		System.out.println("-------------");
		
		//Card [] hand1 = {ACE_OF_SPADES, ACE_OF_CLUBS, QUEEN_OF_DIAMONDS, TEN_OF_DIAMONDS, TEN_OF_HEARTS};
		//Card [] hand2 = {KING_OF_CLUBS, KING_OF_SPADES, THREE_OF_DIAMONDS, THREE_OF_HEARTS, TWO_OF_HEARTS};
		Card [] hand3 = {ACE_OF_SPADES, ACE_OF_CLUBS, KING_OF_HEARTS, TEN_OF_DIAMONDS, TEN_OF_HEARTS};
		Card [] hand4 = {ACE_OF_SPADES, ACE_OF_CLUBS, KING_OF_HEARTS, TEN_OF_DIAMONDS, TEN_OF_HEARTS};
		Card [] hand5 = {KING_OF_SPADES, QUEEN_OF_CLUBS, KING_OF_HEARTS, TEN_OF_DIAMONDS, TEN_OF_HEARTS};
		Card [] hand6 = {KING_OF_SPADES, QUEEN_OF_CLUBS, KING_OF_HEARTS, TEN_OF_DIAMONDS, TEN_OF_HEARTS};

		
		List<Card []> bestHands = HandEvaluator.getBestHand(Arrays.asList( hand3, hand4, hand5, hand6));
		
		assertNotNull(bestHands);
		assertEquals(2, bestHands.size());
		
		Card [] actual = bestHands.get(0);
		Card [] expected = hand3;
		boolean areEqual = areCardsEqual(expected, actual);
				
		//print("hand1: ", hand1);
		//print("hand2: ", hand2);
		print("hand3: ", hand3);
		print("hand4: ", hand4); //same as 1
		print("hand5: ", hand5); //same as 2
		print("hand6: ", hand6); //same as 3
		System.out.println("---");
		print("expected: ", expected);
		print("actual: ", actual);
		
		System.out.println((areEqual) ? "PASS\n" : "FAIL\n");
		assertTrue(areEqual);
	}
	
	@Test
	public void test_27_GetBestHand_TwoPair_6Hands3Ties() {
		
		System.out.println("test_27_GetBestHand_TwoPair_6Hands3Ties");
		System.out.println("-------------");
		
		//Card [] hand1 = {ACE_OF_SPADES, ACE_OF_CLUBS, QUEEN_OF_DIAMONDS, TEN_OF_DIAMONDS, TEN_OF_HEARTS};
		//Card [] hand2 = {KING_OF_CLUBS, KING_OF_SPADES, THREE_OF_DIAMONDS, THREE_OF_HEARTS, TWO_OF_HEARTS};
		Card [] hand3 = {ACE_OF_SPADES, ACE_OF_CLUBS, KING_OF_HEARTS, TEN_OF_DIAMONDS, TEN_OF_HEARTS};
		Card [] hand4 = {ACE_OF_SPADES, ACE_OF_CLUBS, KING_OF_HEARTS, TEN_OF_DIAMONDS, TEN_OF_HEARTS};
		Card [] hand5 = {KING_OF_SPADES, QUEEN_OF_CLUBS, KING_OF_HEARTS, TEN_OF_DIAMONDS, TEN_OF_HEARTS};
		Card [] hand6 = {KING_OF_SPADES, QUEEN_OF_CLUBS, KING_OF_HEARTS, TEN_OF_DIAMONDS, TEN_OF_HEARTS};
		Card [] hand7 = {QUEEN_OF_SPADES, QUEEN_OF_CLUBS, KING_OF_HEARTS, TEN_OF_DIAMONDS, TEN_OF_HEARTS};
		Card [] hand8 = {QUEEN_OF_SPADES, QUEEN_OF_CLUBS, KING_OF_HEARTS, TEN_OF_DIAMONDS, TEN_OF_HEARTS};
		
		
		List<Card []> bestHands = HandEvaluator.getBestHand(Arrays.asList( hand3, hand4, hand5, hand6, hand7, hand8));
		
		assertNotNull(bestHands);
		assertEquals(2, bestHands.size());
		
		Card [] actual = bestHands.get(0);
		Card [] expected = hand3;
		boolean areEqual = areCardsEqual(expected, actual);
				
		//print("hand1: ", hand1);
		//print("hand2: ", hand2);
		print("hand3: ", hand3);
		print("hand4: ", hand4);
		print("hand5: ", hand5);
		print("hand6: ", hand6);
		print("hand7: ", hand5);
		print("hand8: ", hand6);
		
		System.out.println("---");
		print("expected: ", expected);
		print("actual: ", actual);
		
		System.out.println((areEqual) ? "PASS\n" : "FAIL\n");
		assertTrue(areEqual);
	}
	
	@Test
	public void test_28_GetBestHand_TwoPair_6Hands2Ties() {
		
		System.out.println("test_28_GetBestHand_TwoPair_6Hands2Ties");
		System.out.println("-------------");
		
		//Card [] hand1 = {ACE_OF_SPADES, ACE_OF_CLUBS, QUEEN_OF_DIAMONDS, TEN_OF_DIAMONDS, TEN_OF_HEARTS};
		//Card [] hand2 = {KING_OF_CLUBS, KING_OF_SPADES, THREE_OF_DIAMONDS, THREE_OF_HEARTS, TWO_OF_HEARTS};
		Card [] hand3 = {ACE_OF_SPADES, ACE_OF_CLUBS, KING_OF_HEARTS, TEN_OF_DIAMONDS, TEN_OF_HEARTS};
		Card [] hand4 = {ACE_OF_SPADES, ACE_OF_CLUBS, KING_OF_HEARTS, TEN_OF_DIAMONDS, TEN_OF_HEARTS};
		Card [] hand5 = {ACE_OF_SPADES, ACE_OF_CLUBS, KING_OF_HEARTS, TEN_OF_DIAMONDS, TEN_OF_HEARTS};
		Card [] hand6 = {QUEEN_OF_SPADES, QUEEN_OF_CLUBS, KING_OF_HEARTS, TEN_OF_DIAMONDS, TEN_OF_HEARTS};
		Card [] hand7 = {QUEEN_OF_SPADES, QUEEN_OF_CLUBS, KING_OF_HEARTS, TEN_OF_DIAMONDS, TEN_OF_HEARTS};
		Card [] hand8 = {QUEEN_OF_SPADES, QUEEN_OF_CLUBS, KING_OF_HEARTS, TEN_OF_DIAMONDS, TEN_OF_HEARTS};
		
		
		List<Card []> bestHands = HandEvaluator.getBestHand(Arrays.asList( hand3, hand4, hand5, hand6, hand7, hand8));
		
		assertNotNull(bestHands);
		assertEquals(3, bestHands.size());
		
		Card [] actual = bestHands.get(0);
		Card [] expected = hand3;
		boolean areEqual = areCardsEqual(expected, actual);
				
		//print("hand1: ", hand1);
		//print("hand2: ", hand2);
		print("hand3: ", hand3);
		print("hand4: ", hand4);
		print("hand5: ", hand5);
		print("hand6: ", hand6);
		print("hand7: ", hand7);
		print("hand8: ", hand8);
		
		System.out.println("---");
		print("expected: ", expected);
		print("actual: ", actual);
		
		System.out.println((areEqual) ? "PASS\n" : "FAIL\n");
		assertTrue(areEqual);
	}
	
	@Test
	public void test_29_GetBestHand_Flush() {
		
		System.out.println("test_29_GetBestHand_Flush");
		System.out.println("-------------");

		Card [] hand1 = {ACE_OF_SPADES, QUEEN_OF_SPADES, TEN_OF_SPADES, FIVE_OF_SPADES, THREE_OF_SPADES};
		Card [] hand2 = {KING_OF_SPADES, QUEEN_OF_SPADES, TEN_OF_SPADES, NINE_OF_SPADES, EIGHT_OF_SPADES};
		
		List<Card []> bestHands = HandEvaluator.getBestHand(Arrays.asList(hand1, hand2));
		
		assertNotNull(bestHands);
		assertEquals(1, bestHands.size());
		
		Card [] actual = bestHands.get(0);
		Card [] expected = hand1;
		
		print("hand1: ", hand1);
		print("hand2: ", hand2);
		System.out.println("---");
		print("expected: ", expected);
		print("actual: ", actual);
		
		boolean areEqual = areCardsEqual(expected, actual);
		
		System.out.println((areEqual) ? "PASS\n" : "FAIL\n");
		assertTrue(areEqual);
	}
	
	@Test
	public void test_30_GetBestHand_Flush() {
		
		System.out.println("test_30_GetBestHand_Flush");
		System.out.println("-------------");

		Card [] hand1 = {ACE_OF_SPADES, QUEEN_OF_SPADES, TEN_OF_SPADES, NINE_OF_SPADES, THREE_OF_SPADES};
		Card [] hand2 = {ACE_OF_SPADES, QUEEN_OF_SPADES, TEN_OF_SPADES, NINE_OF_SPADES, TWO_OF_SPADES};
		
		List<Card []> bestHands = HandEvaluator.getBestHand(Arrays.asList(hand1, hand2));
		
		assertNotNull(bestHands);
		assertEquals(1, bestHands.size());
		
		Card [] actual = bestHands.get(0);
		Card [] expected = hand1;
		
		print("hand1: ", hand1);
		print("hand2: ", hand2);
		System.out.println("---");
		print("expected: ", expected);
		print("actual: ", actual);
		
		boolean areEqual = areCardsEqual(expected, actual);
		
		System.out.println((areEqual) ? "PASS\n" : "FAIL\n");
		assertTrue(areEqual);
	}
	
	@Test
	public void test_31_GetBestHand_FullHouse_1() {
		
		System.out.println("test_31_GetBestHand_FullHouse_1");
		System.out.println("-------------");

		Card [] hand1 = {JACK_OF_HEARTS, JACK_OF_DIAMONDS, THREE_OF_HEARTS, THREE_OF_DIAMONDS, THREE_OF_SPADES};
		Card [] hand2 = {ACE_OF_SPADES, ACE_OF_HEARTS, TWO_OF_CLUBS, TWO_OF_DIAMONDS, TWO_OF_SPADES};
		
		List<Card []> bestHands = HandEvaluator.getBestHand(Arrays.asList(hand1, hand2));
		
		assertNotNull(bestHands);
		assertEquals(1, bestHands.size());
		
		Card [] actual = bestHands.get(0);
		Card [] expected = hand1;
		
		print("hand1: ", hand1);
		print("hand2: ", hand2);
		System.out.println("---");
		print("expected: ", expected);
		print("actual: ", actual);
		
		boolean areEqual = areCardsEqual(expected, actual);
		
		System.out.println((areEqual) ? "PASS\n" : "FAIL\n");
		assertTrue(areEqual);
	}
	
	@Test
	public void test_32_GetBestHand_FullHouse_2() {
		
		System.out.println("test_32_GetBestHand_FullHouse_2");
		System.out.println("-------------");

		Card [] hand1 = {ACE_OF_SPADES, ACE_OF_HEARTS, THREE_OF_HEARTS, THREE_OF_DIAMONDS, THREE_OF_SPADES};
		Card [] hand2 = {ACE_OF_SPADES, ACE_OF_HEARTS, TWO_OF_CLUBS, TWO_OF_DIAMONDS, TWO_OF_SPADES};
		
		List<Card []> bestHands = HandEvaluator.getBestHand(Arrays.asList(hand1, hand2));
		
		assertNotNull(bestHands);
		assertEquals(1, bestHands.size());
		
		Card [] actual = bestHands.get(0);
		Card [] expected = hand1;
		
		print("hand1: ", hand1);
		print("hand2: ", hand2);
		System.out.println("---");
		print("expected: ", expected);
		print("actual: ", actual);
		
		boolean areEqual = areCardsEqual(expected, actual);
		
		System.out.println((areEqual) ? "PASS\n" : "FAIL\n");
		assertTrue(areEqual);
	}
	
	@Test
	public void test_33_GetBestHand_FullHouse_3() {
		
		System.out.println("test_33_GetBestHand_FullHouse_3");
		System.out.println("-------------");

		Card [] hand1 = {ACE_OF_SPADES, ACE_OF_HEARTS, ACE_OF_DIAMONDS, SEVEN_OF_HEARTS, SEVEN_OF_CLUBS};
		Card [] hand2 = {ACE_OF_SPADES, ACE_OF_HEARTS, ACE_OF_DIAMONDS, NINE_OF_CLUBS, NINE_OF_SPADES};
		
		List<Card []> bestHands = HandEvaluator.getBestHand(Arrays.asList(hand1, hand2));
		
		assertNotNull(bestHands);
		assertEquals(1, bestHands.size());
		
		Card [] actual = bestHands.get(0);
		Card [] expected = hand2;
		
		print("hand1: ", hand1);
		print("hand2: ", hand2);
		System.out.println("---");
		print("expected: ", expected);
		print("actual: ", actual);
		
		boolean areEqual = areCardsEqual(expected, actual);
		
		System.out.println((areEqual) ? "PASS\n" : "FAIL\n");
		assertTrue(areEqual);
	}
	
	@Test
	public void test_34_GetBestHand_Straight_Flush_1() {
		
		System.out.println("test_34_GetBestHand_Straight_Flush_1");
		System.out.println("-------------");

		Card [] hand1 = {FOUR_OF_SPADES, FIVE_OF_SPADES, SIX_OF_SPADES, SEVEN_OF_SPADES, EIGHT_OF_SPADES};
		Card [] hand2 = {FIVE_OF_SPADES, SIX_OF_SPADES, SEVEN_OF_SPADES, EIGHT_OF_SPADES, NINE_OF_SPADES};
		
		List<Card []> bestHands = HandEvaluator.getBestHand(Arrays.asList(hand1, hand2));
		
		assertNotNull(bestHands);
		assertEquals(1, bestHands.size());
		
		Card [] actual = bestHands.get(0);
		Card [] expected = hand2;
		
		print("hand1: ", hand1);
		print("hand2: ", hand2);
		System.out.println("---");
		print("expected: ", expected);
		print("actual: ", actual);
		
		boolean areEqual = areCardsEqual(expected, actual);
		
		System.out.println((areEqual) ? "PASS\n" : "FAIL\n");
		assertTrue(areEqual);
	}
	
	@Test
	public void test_34_GetBestHand_Straight_Flush_2_vs_Straight() {
		
		System.out.println("test_34_GetBestHand_Straight_Flush_1");
		System.out.println("-------------");

		Card [] hand1 = {FOUR_OF_SPADES, FIVE_OF_SPADES, SIX_OF_SPADES, SEVEN_OF_SPADES, EIGHT_OF_SPADES};
		Card [] hand2 = {FIVE_OF_SPADES, SIX_OF_SPADES, SEVEN_OF_SPADES, EIGHT_OF_SPADES, NINE_OF_CLUBS};
		
		List<Card []> bestHands = HandEvaluator.getBestHand(Arrays.asList(hand1, hand2));
		
		assertNotNull(bestHands);
		assertEquals(1, bestHands.size());
		
		Card [] actual = bestHands.get(0);
		Card [] expected = hand2;
		
		print("hand1: ", hand1);
		print("hand2: ", hand2);
		System.out.println("---");
		print("expected: ", expected);
		print("actual: ", actual);
		
		boolean areEqual = areCardsEqual(expected, actual);
		
		System.out.println((areEqual) ? "PASS\n" : "FAIL\n");
		assertTrue(areEqual);
	}
	
	@Test
	public void test_35_GetBestHand_RoyalFlush_vs_FourOfAKind() {
		
		System.out.println("test_35_GetBestHand_RoyalFlush_vs_FourOfAKind");
		System.out.println("-------------");

		Card [] hand1 = {KING_OF_HEARTS, JACK_OF_HEARTS, QUEEN_OF_HEARTS, TEN_OF_HEARTS, ACE_OF_HEARTS};
		Card [] hand2 = {FIVE_OF_SPADES, FIVE_OF_CLUBS, ACE_OF_DIAMONDS, FIVE_OF_HEARTS, FIVE_OF_DIAMONDS};
		
		List<Card []> bestHands = HandEvaluator.getBestHand(Arrays.asList(hand1, hand2));
		
		assertNotNull(bestHands);
		assertEquals(1, bestHands.size());
		
		Card [] actual = bestHands.get(0);
		Card [] expected = hand1;
		
		print("hand1: ", hand1);
		print("hand2: ", hand2);
		System.out.println("---");
		print("expected: ", expected);
		print("actual: ", actual);
		
		boolean areEqual = areCardsEqual(expected, actual);
		
		System.out.println((areEqual) ? "PASS\n" : "FAIL\n");
		assertTrue(areEqual);
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
