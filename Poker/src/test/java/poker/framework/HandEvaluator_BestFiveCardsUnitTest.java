package poker.framework;

import org.junit.Test;

import static org.junit.Assert.*;

import static poker.framework.Card.*;

public class HandEvaluator_BestFiveCardsUnitTest {

	@Test
	public void testOnePair_1() {
		
		System.out.println("testOnePair_1");
		System.out.println("-------------");
		
		Card [] sevenCards = {QUEEN_OF_CLUBS, FIVE_OF_HEARTS, QUEEN_OF_DIAMONDS, SEVEN_OF_HEARTS, NINE_OF_SPADES, THREE_OF_CLUBS, KING_OF_HEARTS};
	
		Card [] bestFiveExpected = {QUEEN_OF_DIAMONDS, QUEEN_OF_CLUBS, NINE_OF_SPADES, SEVEN_OF_HEARTS, KING_OF_HEARTS};
		
		Card [] bestFiveActual = HandEvaluator.getBestFiveCards(sevenCards);
		
		HandEvaluator.sortDescending(bestFiveExpected);
		HandEvaluator.sortDescending(bestFiveActual);
		
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
		
		HandEvaluator.sortDescending(bestFiveExpected);
		HandEvaluator.sortDescending(bestFiveActual);
		
		print("Expected: ", bestFiveExpected);
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
		
		print("Expected 1: ", bestFiveExpected1);
		print("Expected 2: ", bestFiveExpected2);
		print("Actual: ", bestFiveActual);
		
		boolean areEqual = 
				areCardsEqual(bestFiveExpected1, bestFiveActual) || areCardsEqual(bestFiveExpected2, bestFiveActual);
		
		assertTrue(areEqual);			
		
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
