package poker.framework;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import poker.framework.HandEvaluator.CardRankComparator;

import static poker.framework.Rank.*;


public class HandEvaluator {

	public static FiveCardHand evaluate(Card[] hand) {
		if(hand == null || hand.length != 5) 
			throw new RuntimeException("This method should never be called without passing 5-cards!");
		
		return evaluateHand(hand);
	}
	
	private static FiveCardHand evaluateHand(Card [] hand) {
		
		Arrays.sort(hand, new CardRankComparator());
		
		Set<FiveCardHand> bestHands = new HashSet<>();
					
		FiveCardHand onePairHand = evaluateOnePair(hand);
			
		if(onePairHand != null) {
			
			bestHands.add(onePairHand);
			
			FiveCardHand twoPairHand = evaluateTwoPair(hand);
			
			if(twoPairHand != null) {
				bestHands.clear();
				bestHands.add(twoPairHand);				
			}
			
			FiveCardHand threeOfAKindHand = evaluateThreeOfAKind(hand);
			
			if(threeOfAKindHand != null) {
				bestHands.clear();
				bestHands.add(threeOfAKindHand);
				
				FiveCardHand fullHouseHand = evaluateFullHouse(hand);
				
				if(fullHouseHand != null) {
					bestHands.clear();
					bestHands.add(fullHouseHand);						
				}
				
				FiveCardHand fourOfAKindHand = evaluateFourOfAKind(hand);
				
				if(fourOfAKindHand != null) {
					bestHands.clear();
					bestHands.add(fourOfAKindHand);							
				}
			}			
		}				
		
		FiveCardHand currentBestHand = null;
		if(bestHands.size() > 0) {
			currentBestHand = bestHands.iterator().next();
		}
		
		if(currentBestHand == null || currentBestHand.getValue() < FiveCardHand.STRAIGHT.getValue()) {
			FiveCardHand straight = evaluateStraight(hand);
			if(straight != null) {
				currentBestHand = straight;
				bestHands.clear();
				bestHands.add(currentBestHand);
			}
		}
		
		if(currentBestHand == null || currentBestHand.getValue() < FiveCardHand.FLUSH.getValue()) {
			FiveCardHand flush = evaluateFlush(hand);
			if(flush != null) {
				currentBestHand = flush;
				bestHands.clear();
				bestHands.add(currentBestHand);
			}
		}
		
		if(currentBestHand == null || currentBestHand.getValue() < FiveCardHand.STRAIGHT_FLUSH.getValue()) {
			FiveCardHand straightFlush = evaluateStraightFlush(hand);
			if(straightFlush != null) {
				currentBestHand = straightFlush;
				bestHands.clear();
				bestHands.add(currentBestHand);
			}
		}
		
		if(currentBestHand == null || currentBestHand.getValue() < FiveCardHand.ROYAL_FLUSH.getValue()) {
			FiveCardHand royalFlush = evaluateRoyalFlush(hand);
			if(royalFlush != null) {
				currentBestHand = royalFlush;
				bestHands.clear();
				bestHands.add(currentBestHand);
			}
		}
		
		if(bestHands.isEmpty()) {
			bestHands.add(FiveCardHand.HIGH_CARD);
		}
	/*
		else {
			FiveCardHand[] drawHand = evaluateDraw(hand);
			if(drawHand[0] == null && drawHand[1] == null && drawHand[2] == null) {
				bestHands.clear();
				bestHands.add(FiveCardHand.NO_DRAW);
				System.out.println("NO DRAW");
			}
			else {
				System.out.println("YES DRAW");
				if (drawHand[0] != null) {
					bestHands.clear();
					bestHands.add(FiveCardHand.FLUSH_DRAW);
				}
				
				if (drawHand[1] != null) {
					bestHands.clear();
					bestHands.add(FiveCardHand.INSIDE_STRAIGHT_DRAW);
				}
				
				if(drawHand[2] != null) {
					bestHands.clear();
					bestHands.add(FiveCardHand.OPEN_ENDED_STRAIGHT_DRAW);
				}
			}
		}
*/
		return bestHands.iterator().next();
	}
	
	public static FiveCardHand evaluateHighCard(Card [] hand) {
		int highCard = 0;
		FiveCardHand topCard = FiveCardHand.HIGH_CARD;
		
		for (int i = 0; i < hand.length; i++) {
			int rank = hand[i].getRank().getValue();
			if(highCard < rank) {
				highCard = rank;
			}
		}
		topCard.setCard(1, highCard);
		return topCard;
		
	}
	
	
	public static FiveCardHand evaluateOnePair(Card [] hand) {
		
		FiveCardHand onePair = FiveCardHand.ONE_PAIR;
		for(int i=0; i < hand.length-1; i++) {
			Rank rank = hand[i].getRank();
			if(rank == hand[i+1].getRank()) {
				onePair.setCardValue(rank.getValue());
				return onePair;
			}
		}
		return null;
	}
	
	public static FiveCardHand evaluateTwoPair(Card [] hand) {
		if( 	(hand[0].getRank() == hand[1].getRank() && hand[2].getRank() == hand[3].getRank()) ||	
				(hand[1].getRank() == hand[2].getRank() && hand[3].getRank() == hand[4].getRank()) ||
				(hand[0].getRank() == hand[1].getRank() && hand[3].getRank() == hand[4].getRank())) {
			FiveCardHand twoPair = FiveCardHand.TWO_PAIR;
			//inside for cards 1 and 3 will always stay the same
			if(hand[1].getRank().getValue() > hand[3].getRank().getValue()) {
				twoPair.setCardValue(hand[1].getRank().getValue());
				return twoPair;
			}
			twoPair.setCardValue(hand[3].getRank().getValue());
			return twoPair;
		}
		return null;
	}
	
	
	/*
	public static FiveCardHand evaluateTwoPair(Card [] hand) { 
		
		for(int i = 0; i<hand.length-2; i++){
			//Rank rank1 = hand[i].getRank();
			for(int j = i+1; j < hand.length-1; j++) {
				if(i<hand.length-2 && hand[i].getRank() == hand[j].getRank()) { //if after 3rd index, there is no twopair, it does not exist
					//Rank rank2 = hand[i+2].getRank();
					FiveCardHand twoPair = FiveCardHand.TWO_PAIR;
					if(i<3 && j<3 && hand[i+2].getRank() == hand[j+2].getRank()) { //XAABB
						
						if(hand[i].getRank().getValue() > hand[i+2].getRank().getValue()) {
							twoPair.setCardValue(hand[i].getRank().getValue());
						}
						else {
							twoPair.setCardValue(hand[i+2].getRank().getValue());
						}
					if(i<3 && j==3 && hand[i+1].getRank() == hand[j+1].getRank()) {//XABAB
						if(hand[i].getRank().getValue() > hand[j+1].getRank().getValue()) {
							twoPair.setCardValue(hand[i].getRank().getValue());
						}
						else {
							twoPair.setCardValue(hand[j].getRank().getValue());
						}
					if(i<3 && j==4 && hand[i+1].getRank() == hand[j].getRank()) { //XABBA
						
					}
						if(hand[i].getRank().getValue() > hand[j-1].getRank().getValue()) {
							twoPair.setCardValue(hand[i].getRank().getValue());
						}
						else {
							twoPair.setCardValue(hand[j-1].getRank().getValue());
						}						
					}
					
					}
				}
			}

		}
		return null;
	}
	*/
	
	private static FiveCardHand evaluateThreeOfAKind(Card [] hand) {
		
		int numMatch = 1;
		for(int j = 0; j < 3; j++) {
			for(int i = 0; i < 2; i++) {
				
				if(hand[j+i].getRank() == hand[j+i+1].getRank()) {
					numMatch++;
				}						
			}
			if(numMatch == 3)
				return FiveCardHand.THREE_OF_A_KIND;
			else
				numMatch = 1;
		}		
		return null;
	}
	
	private static FiveCardHand evaluateFourOfAKind(Card [] hand) {
		/*for (int i = 0; i < hand.length; i++) {
			if (hand[i+3] == null){
				return null;
			}
			if(hand[i].getRank() == hand[i+1].getRank() &&
					hand[i].getRank() == hand[i+2].getRank() &&
					hand[i].getRank() == hand[i+3].getRank()) {
				return FiveCardHand.FOUR_OF_A_KIND;
			}
		}
		return null;
		*/
		
		if( 	(hand[0].getRank() == hand[1].getRank() &&
				hand[0].getRank() == hand[2].getRank() &&
				hand[0].getRank() == hand[3].getRank()) ||
				
				(hand[1].getRank() == hand[2].getRank() &&
				hand[1].getRank() == hand[3].getRank() &&
				hand[1].getRank() == hand[4].getRank())
				
			) {
		
			return FiveCardHand.FOUR_OF_A_KIND;
		}
		return null;
		
	}
	
	private static FiveCardHand evaluateFullHouse(Card [] hand) {
		
		int numMatch = 1;
		for(int j = 0; j < 3; j++) {
			for(int i = 0; i < 2; i++) {
				
				if(hand[j+i].getRank() == hand[j+i+1].getRank()) {
					numMatch++;
				}						
			}
			if(numMatch == 3) {
				
				// ONCE I DETERMINED 3-OF-A-KIND, I WANT TO CHECK IF EITHER LEADING CARDS
				// OR ENDING CARDS ARE A PAIR
				if (
						(j==0 && hand[3].getRank() == hand[4].getRank()) ||
						(j==2 && hand[0].getRank() == hand[1].getRank())) {
							return FiveCardHand.FULL_HOUSE;					
				}
			}
			else
				numMatch = 1;
		}		
		return null;
	}
	
	private static FiveCardHand evaluateStraight(Card [] hand) {
		
		int rankValue0 = hand[0].getRank().getValue();
		int rankValue1 = hand[1].getRank().getValue();
		int rankValue2 = hand[2].getRank().getValue();
		int rankValue3 = hand[3].getRank().getValue();
		int rankValue4 = hand[4].getRank().getValue();
		
		if(((rankValue0 == rankValue1 - 1) &&
			(rankValue1 == rankValue2 - 1) &&
			(rankValue2 == rankValue3 - 1) &&
			(rankValue3 == rankValue4 - 1)) ||
			
			(hand[0].getRank() == Rank.TWO &&
			 hand[1].getRank() == Rank.THREE &&
			 hand[2].getRank() == Rank.FOUR &&
			 hand[3].getRank() == Rank.FIVE &&
			 hand[4].getRank() == Rank.ACE)) {
			return FiveCardHand.STRAIGHT;
		}
		return null;	
	}

	private static FiveCardHand evaluateFlush(Card [] hand) {
		
		int sameSuit = 1;
		for(int i = 0; i < 4; i++) {
			if(hand[i].getSuit() == hand[i+1].getSuit()) {
				sameSuit++;
			}
		}
		if(sameSuit == 5)
			return FiveCardHand.FLUSH;
		
		return null;		
	}
	
	private static FiveCardHand evaluateStraightFlush(Card [] hand) {
		FiveCardHand straight = evaluateStraight(hand);
		
		if(straight == FiveCardHand.STRAIGHT) {
			
			FiveCardHand flush = evaluateFlush(hand);
			
			if(flush == FiveCardHand.FLUSH) {
				return FiveCardHand.STRAIGHT_FLUSH;
			}
		}
		return null;
	}
	
	private static FiveCardHand evaluateRoyalFlush(Card [] hand) {
		FiveCardHand straightFlush = evaluateStraightFlush(hand);
		
		if(straightFlush == FiveCardHand.STRAIGHT_FLUSH && hand[4].getRank() == Rank.ACE) {
			return FiveCardHand.ROYAL_FLUSH;
		}
		return null;
	}
	
	private static DrawingHands evaluateFlushDraw(Card[] hand) { //return DrawingHands
		int sameSuit = 1;
		DrawingHands draw = null;
		for(int i = 0; i < 3; i++) {
			if(hand[i].getSuit() == hand[i+1].getSuit()) {
				sameSuit++;
			}
		}
		if(sameSuit == 4)
			draw = DrawingHands.FLUSH_DRAW;
			
		return draw;
	}
	
	private static DrawingHands evaluateInsideStraightDraw(Card[] hand) { //return DrawingHands
		
		DrawingHands draw = null;
		
		int rankValue0 = hand[0].getRank().getValue();
		int rankValue1 = hand[1].getRank().getValue();
		int rankValue2 = hand[2].getRank().getValue();
		int rankValue3 = hand[3].getRank().getValue();
		
		if(((rankValue0 == rankValue1 - 2) && // 2 456
			(rankValue1 == rankValue2 - 1) &&
			(rankValue2 == rankValue3 - 1))
			||
			((rankValue0 == rankValue1 - 1) && //23 56
			(rankValue1 == rankValue2 - 2) &&
			(rankValue2 == rankValue3 - 1))
			||
			((rankValue0 == rankValue1 - 1) && //234 6
			(rankValue1 == rankValue2 - 1) &&
			(rankValue2 == rankValue3 - 2))
			||
			((rankValue0 == rankValue1 - 1) && // A234 or JQKA
			(rankValue1 == rankValue2 - 1) &&
			(rankValue2 == rankValue3 - 1) &&
			((hand[3].getRank() != Rank.ACE ) &&
			(hand[0].getRank() != Rank.TWO ) && 
			(hand[2].getRank() != Rank.KING )))){
		
			draw = DrawingHands.INSIDE_STRAIGHT_DRAW;
		}
		return draw;	
	}
	
	private static DrawingHands evaluateOpenEndedStraightDraw(Card[] hand) { //return DrawingHands
		
		DrawingHands draw = null;
		
		int rankValue0 = hand[0].getRank().getValue();
		int rankValue1 = hand[1].getRank().getValue();
		int rankValue2 = hand[2].getRank().getValue();
		int rankValue3 = hand[3].getRank().getValue();

		if((rankValue0 == rankValue1 - 1) &&
			(rankValue1 == rankValue2 - 1) &&
			(rankValue2 == rankValue3 - 1) &&
			((hand[3].getRank() != Rank.ACE ) ||
			(hand[0].getRank() != Rank.TWO ) ||
			(hand[0].getRank() != Rank.KING ))){
			draw = DrawingHands.OPEN_ENDED_STRAIGHT_DRAW;
		}
		return draw;	
	}

	
	public static DrawingHands[] evaluateDraws(Card[] hand) { 
		
		if(hand == null || hand.length != 4) 
			throw new RuntimeException("This method should never be called without passing 4-cards!");
		
		DrawingHands[] draws = new DrawingHands[3]; 
		
		draws[0] = evaluateFlushDraw(hand);
		draws[1] = evaluateInsideStraightDraw(hand);
		draws[2] = evaluateOpenEndedStraightDraw(hand);

		return draws; 
	}
	
	
	public static class CardRankComparator implements Comparator<Card> {

		@Override
		public int compare(Card o1, Card o2) {
			int r1 = o1.getRank().getValue();
			int r2 = o2.getRank().getValue();
			
			if(r1 < r2) return -1;
			if(r1 > r2)	return  1;
			return 0;
		}		
	}
	
	public static class CardRankReverseComparator implements Comparator<Card> {

		@Override
		public int compare(Card o1, Card o2) {
			int r1 = o1.getRank().getValue();
			int r2 = o2.getRank().getValue();
			
			if(r1 > r2) return -1;
			if(r1 < r2)	return  1;
			
			return 0;
		}		
	}
	
	public static class CardComparator implements Comparator<Card> {

		@Override
		public int compare(Card o1, Card o2) {
			int r1 = o1.getRank().getValue();
			int r2 = o2.getRank().getValue();
			
			if(r1 < r2) return -1;
			if(r1 > r2)	return  1;
			
			int s1 = o1.getSuit().getValue();
			int s2 = o2.getSuit().getValue();
			
			if(s1 < s2) return -1;
			if(s1 > s2)	return  1;
			
			return 0;
		}		
	}
	
	public static class CardReverseComparator implements Comparator<Card> {

		@Override
		public int compare(Card o1, Card o2) {
			int r1 = o1.getRank().getValue();
			int r2 = o2.getRank().getValue();
			
			if(r1 > r2) return -1;
			if(r1 < r2)	return  1;
			
			int s1 = o1.getSuit().getValue();
			int s2 = o2.getSuit().getValue();
			
			if(s1 > s2) return -1;
			if(s1 < s2)	return  1;
			
			return 0;
		}		
	}
	
	
	
	public static PocketHand evaluatePocket(Card [] hand) { 
		
		if(hand == null || hand.length != 2) 
			throw new RuntimeException("This method should never be called without passing 2-cards!");
		
		Arrays.sort(hand, new CardRankComparator());
		
		PocketHand pocketHand = null;
		
		pocketHand = evaluateSuperPremiumPocket(hand);		
		if(pocketHand != null) return pocketHand;			
		
		pocketHand = evaluatePremiumPocket(hand);
		if(pocketHand != null) return pocketHand;			
		
		pocketHand = evaluateMiddlePairPocket(hand);
		if(pocketHand != null) return pocketHand;
		
		pocketHand = evaluateBottomPairPocket(hand);
		if(pocketHand != null) return pocketHand;

		pocketHand = evaluateSuitedConnectorPocket(hand);
		if(pocketHand != null) return pocketHand;
		
		pocketHand = evaluateSuitedOneGapperPocket(hand);
		if(pocketHand != null) return pocketHand;
		
		pocketHand = evaluatePremiumSuitedPocket(hand);
		if(pocketHand != null) return pocketHand;
		
		pocketHand = evaluateSuitedPocket(hand);
		if(pocketHand != null) return pocketHand;
		
		pocketHand = evaluateConnectedPocket(hand);
		if(pocketHand != null) return pocketHand;
		
		pocketHand = evaluateOneGapperPocket(hand);
		if(pocketHand != null) return pocketHand;
		
		pocketHand = evaluateAboveAveragePocket(hand);
		if(pocketHand != null) return pocketHand;
		
		return PocketHand.JUNK;
		
	}
	
	private static PocketHand evaluateSuperPremiumPocket(Card [] hand) {
		
		// AA, KK, QQ, AK
		if(
			(hand[0].getRank() == ACE   && hand[1].getRank() == ACE)   ||
			(hand[0].getRank() == KING  && hand[1].getRank() == KING)  ||
			(hand[0].getRank() == QUEEN && hand[1].getRank() == QUEEN) ||
			(hand[0].getRank() == KING   && hand[1].getRank() == ACE)) {   
				return PocketHand.SUPER_PREMIUM_HAND;
		}
		return null;
	}
	
	private static PocketHand evaluatePremiumPocket(Card [] hand) {
		
		// AQ, AJ, KQ, ATs, JJ, TT
		if(
			(hand[0].getRank() == QUEEN && hand[1].getRank() == ACE)   	||
			(hand[0].getRank() == JACK  && hand[1].getRank() == ACE)  	||
			(hand[0].getRank() == QUEEN && hand[1].getRank() == KING) 	||
			(hand[0].getRank() == TEN   && hand[1].getRank() == ACE &&
			 hand[0].getSuit() == hand[1].getSuit()) 					||
			(hand[0].getRank() == JACK  && hand[1].getRank() == JACK) 	||  
			(hand[0].getRank() == TEN   && hand[1].getRank() == TEN))	{
				return PocketHand.PREMIUM_HAND;
		}
		return null;
	}
	
	private static PocketHand evaluateMiddlePairPocket(Card [] hand) {
		
		// 99, 88, 77, 66
		if(
			(hand[0].getRank() == NINE  && hand[1].getRank() == NINE)   ||
			(hand[0].getRank() == EIGHT && hand[1].getRank() == EIGHT)  ||
			(hand[0].getRank() == SEVEN && hand[1].getRank() == SEVEN) 	||
			(hand[0].getRank() == SIX   && hand[1].getRank() == SIX)) {
			return PocketHand.MIDDLE_PAIR;
		}			
		return null;
	}
	
	private static PocketHand evaluateBottomPairPocket(Card [] hand) {
		
		// 55, 44, 33, 22
		if(
			(hand[0].getRank() == FIVE  && hand[1].getRank() == FIVE)  ||
			(hand[0].getRank() == FOUR  && hand[1].getRank() == FOUR)  ||
			(hand[0].getRank() == THREE && hand[1].getRank() == THREE) ||
			(hand[0].getRank() == TWO   && hand[1].getRank() == TWO)) {
			return PocketHand.BOTTOM_PAIR;
		}			
		return null;
	}
	
	private static PocketHand evaluateSuitedConnectorPocket(Card [] hand) {
		
		// QJs, JTs, 98s, 87s, ... , 32s
		if(
			(hand[0].getRank().getValue() + 1 == hand[1].getRank().getValue()) &&
			(hand[0].getSuit() == hand[1].getSuit())) {
			return PocketHand.SUITED_CONNECTOR;
		}			
		return null;
	}
	
	private static PocketHand evaluateSuitedOneGapperPocket(Card [] hand) {
		
		// QTs, J9s, T8s, 97s, ... , 42s
		if(
			(hand[0].getRank().getValue() + 2 == hand[1].getRank().getValue()) &&
			(hand[0].getSuit() == hand[1].getSuit())) {
			return PocketHand.SUITED_ONE_GAPPER;
		}			
		return null;
	}
	
	private static PocketHand evaluatePremiumSuitedPocket(Card [] hand) {
		
		// Axs
		if(
			hand[1].getRank() == ACE &&
			hand[0].getSuit() == hand[1].getSuit()) {
			return PocketHand.PREMIUM_SUITED;
		}			
		return null;
	}
	
	private static PocketHand evaluateSuitedPocket(Card [] hand) {
		
		// Axs
		if(
			hand[0].getSuit() == hand[1].getSuit()) {
			return PocketHand.SUITED;
		}			
		return null;
	}
	
	private static PocketHand evaluateConnectedPocket(Card [] hand) {
		
		// JT, 98, 65
		if(
			hand[0].getRank().getValue() + 1 == hand[1].getRank().getValue()) {
			return PocketHand.CONNECTED;
		}			
		return null;
	}
	
	private static PocketHand evaluateOneGapperPocket(Card [] hand) {
		
		// QT, J9, 64
		if(
			hand[0].getRank().getValue() + 2 == hand[1].getRank().getValue()) {
			return PocketHand.ONE_GAPPER;
		}			
		return null;
	}
	
	private static PocketHand evaluateAboveAveragePocket(Card [] hand) {
		
		// 55, 44, 33, 22
		if(
			(hand[0].getRank() == TEN  && hand[1].getRank() == ACE)  ||
			(hand[0].getRank() == NINE  && hand[1].getRank() == ACE)  ||
			(hand[0].getRank() == EIGHT && hand[1].getRank() == ACE) ||
			(hand[0].getRank() == TEN  && hand[1].getRank() == KING)  ||
			(hand[0].getRank() == NINE  && hand[1].getRank() == KING)  ||
			(hand[0].getRank() == NINE && hand[1].getRank() == QUEEN) ||
			(hand[0].getRank() == EIGHT  && hand[1].getRank() == QUEEN)  ||
			(hand[0].getRank() == EIGHT  && hand[1].getRank() == JACK)  ||
			(hand[0].getRank() == SEVEN && hand[1].getRank() == JACK)) {
			return PocketHand.ABOVE_AVERAGE;
		}			
		return null;
	}

	/// JOHN'S WORK 6 and 7 eval
	public static FiveCardHand evaluateSix(Card [] sixHand) { // 6 cards
		//ABCDEF
		int numCards = sixHand.length;
		int value = 0;
		FiveCardHand topHand = null;

		for(int i = 0; i < numCards; i++) { //omit one element from sixHand

			List<Card> list = new ArrayList<Card>(Arrays.asList(sixHand));
			//list = Arrays.asList(sixHand);
			list.remove(i);
			Card[] cards = list.toArray(new Card[5]);
			FiveCardHand hand = HandEvaluator.evaluate(cards);
				if (hand.getValue() > value) {
					value = hand.getValue();
					topHand = hand;
				}
			}	
		return topHand;
	}
	
	public static FiveCardHand evaluateSeven(Card [] sevenHand) {
		//ABCDEFG
		int numCards = sevenHand.length;
		int value = 0;
		FiveCardHand topHand = null;
		
		for(int i = 0; i < numCards; i++) {
			List<Card> list = new ArrayList<Card>(Arrays.asList(sevenHand));
			//list = Arrays.asList(sevenHand);
			list.remove(i);
			for(int j = i; j < numCards-1; j++) {
				List<Card> subList = new ArrayList<Card>(list);
				subList.remove(j);
				Card[] cards = subList.toArray(new Card[5]);
				FiveCardHand hand = HandEvaluator.evaluate(cards);
				if (hand.getValue() > value) {
					value = hand.getValue();
					topHand = hand;
				}
			}
		}	
		return topHand;
	}	
	
	///////// evaluating two hands which are same fivecardhand
	
	/**
	 * This method gets the best five cards from seven cards
	 * @param sevenHand
	 * @return
	 */
	public static Card [] getBestFiveCards(Card [] sevenHand) {
		//ABCDEFG
		Arrays.sort(sevenHand, new CardRankComparator());
		int numCards = sevenHand.length;
		int value = 0;
		int cardValue = 0;
		int oldRank = 0;
		int rank1 = 0;
		int rank3 = 0;
		boolean secondPair = false;

		List<Card[]> eligibleFiveCards = new ArrayList<>();
		
		for(int i = 0; i < numCards; i++) {
			List<Card> list = new ArrayList<Card>(Arrays.asList(sevenHand));

			list.remove(i);
			for(int j = i; j < numCards-1; j++) {

				List<Card> subList = new ArrayList<Card>(list);
				subList.remove(j);

				Card[] cards = subList.toArray(new Card[5]);
				FiveCardHand hand = HandEvaluator.evaluate(cards);
				
				rank1 = cards[1].getRank().getValue();
				rank3 = cards[3].getRank().getValue();
				if(rank1 > rank3) {

					hand.setCardValue(rank1);
					if(rank3 > oldRank) {
						oldRank = rank3;
						secondPair = true;
						
					}
					else {
						secondPair = false;
					}
				}
				else {
					hand.setCardValue(rank3);
					if(rank1 > oldRank) {
						oldRank = rank1;
						secondPair = true;
						
					}
					else {
						secondPair = false;
					}

				}
				if (hand.getValue() > value || (hand.getValue() == value && hand.getCardValue() > cardValue && secondPair)) {
					value = hand.getValue();
					cardValue = hand.getCardValue();
					eligibleFiveCards.clear();
					eligibleFiveCards.add(cards);
				}
			}
		}
		return eligibleFiveCards.get(eligibleFiveCards.size()-1);
	}
	
	private static void validate(Card[] _this, Card[] _that, FiveCardHand handType) {
		
		FiveCardHand f1 = evaluate(_this);
		FiveCardHand f2 = evaluate(_that);
		
		if(f1 != handType || f2 != handType) {
			throw new RuntimeException("Both hands must be of FiveCardHand type = " + handType);
		}
	}
	
	public static boolean isThisHandBetterThanThat(Card[] _this, Card[] _that, FiveCardHand fiveCardHand) {
		
		if(fiveCardHand == FiveCardHand.ONE_PAIR) return isThisOnePairBetterThanThat(_this, _that);
		
		if(fiveCardHand == FiveCardHand.TWO_PAIR) return isThisTwoPairBetterThanThat(_this, _that);
		
		if(fiveCardHand == FiveCardHand.THREE_OF_A_KIND) return isThisTripsBetterThanThat(_this, _that);
		
		if(fiveCardHand == FiveCardHand.STRAIGHT) return isThisStraightBetterThanThat(_this, _that);
		
		if(fiveCardHand == FiveCardHand.FLUSH) return isThisFlushBetterThanThat(_this, _that);
		
		if(fiveCardHand == FiveCardHand.FULL_HOUSE) return isThisFullHouseBetterThanThat(_this, _that);
		
		if(fiveCardHand == FiveCardHand.FOUR_OF_A_KIND) return isThisFoursBetterThanThat(_this, _that);
		
		if(fiveCardHand == FiveCardHand.STRAIGHT_FLUSH) return isThisStraightFlushBetterThanThat(_this, _that);
		
		return false;
	}
	
	public static boolean isThisOnePairBetterThanThat(Card[] _this, Card[] _that) {
		
		validate(_this, _that, FiveCardHand.ONE_PAIR);
		
		Rank _thisTopPairRank = null;
		Rank _thatTopPairRank = null;
		
		Arrays.sort(_this, new CardRankComparator());
		Arrays.sort(_that, new CardRankComparator());
		
		for(int i = 0; i < _this.length-1; i++) {
			if(_this[i].getRank() == _this[i+1].getRank()) {
				_thisTopPairRank = _this[i].getRank();
			}
		}
		
		for(int i = 0; i < _that.length-1; i++) {
			if(_that[i].getRank() == _that[i+1].getRank()) {
				_thatTopPairRank = _that[i].getRank();
			}
		}
		
		if(_thisTopPairRank.getValue() > _thatTopPairRank.getValue()) {
			return true;
		}
		else if(_thisTopPairRank.getValue() < _thatTopPairRank.getValue()) {
			return false;
		}
		else { // Each hand's one pair is same, therefore evaluate their next best 3 cards
				
			Card [] _thisOtherThreeCards = new Card[3];
			Card [] _thatOtherThreeCards = new Card[3];
			
			int index = 0;
			for(int i = 0; i < _this.length; i++) {
				if(_this[i].getRank() != _thisTopPairRank) {
					_thisOtherThreeCards[index] = _this[i];
					index++;
				}
			}
			
			index = 0;
			for(int i = 0; i < _that.length; i++) {
				if(_that[i].getRank() != _thatTopPairRank) {
					_thatOtherThreeCards[index] = _that[i];
					index++;
				}
			}
			
			// Sort each of the 3 cards
			Arrays.sort(_thisOtherThreeCards, new CardRankReverseComparator());
			Arrays.sort(_thatOtherThreeCards, new CardRankReverseComparator());
			
			for(int i = 0; i < 3; i++) {
				int thisVal = _thisOtherThreeCards[i].getRank().getValue();
				int thatVal = _thatOtherThreeCards[i].getRank().getValue();
				
				if(thisVal > thatVal) {
					return true;
				}
				else if(thisVal < thatVal) {
					return false;
				}
			}
		}
		return false;
	}
	
	public static boolean isThisTwoPairBetterThanThat(Card[] _this, Card[] _that) {
		
		validate(_this, _that, FiveCardHand.TWO_PAIR);
		
		return false;
	}
	
	public static boolean isThisTripsBetterThanThat(Card[] _this, Card[] _that) {
		
		validate(_this, _that, FiveCardHand.THREE_OF_A_KIND);
		
		Arrays.sort(_this, new CardRankComparator());
		Arrays.sort(_that, new CardRankComparator());
		
		return false;
	}
	
	public static boolean isThisStraightBetterThanThat(Card[] _this, Card[] _that) {
		
		validate(_this, _that, FiveCardHand.STRAIGHT);
		
		Arrays.sort(_this, new CardRankReverseComparator());
		Arrays.sort(_that, new CardRankReverseComparator());
		
		if(_this[0].getRank().getValue() > _that[0].getRank().getValue()) {
			return true;
		}
		return false;
	}

	public static boolean isThisFlushBetterThanThat(Card[] _this, Card[] _that) {
		
		validate(_this, _that, FiveCardHand.FLUSH);
		
		Arrays.sort(_this, new CardRankReverseComparator());
		Arrays.sort(_that, new CardRankReverseComparator());
		
		if(_this[0].getRank().getValue() > _that[0].getRank().getValue()) {
			return true;
		}
		return false;
	}
	
	public static boolean isThisFullHouseBetterThanThat(Card[] _this, Card[] _that) {
		
		validate(_this, _that, FiveCardHand.FULL_HOUSE);
		
		Arrays.sort(_this, new CardRankComparator());
		Arrays.sort(_that, new CardRankComparator());
		
		// Determine the rank of the 3 matching cards and 2 matching cards for this hand
		Rank _thisTrips = null;
		Rank _thisPair = null;
		Rank _thatTrips = null;
		Rank _thatPair = null;
		
		if(_this[1].getRank() == _this[2].getRank()) {
			_thisTrips = _this[0].getRank();
			_thisPair = _this[4].getRank();
		}
		else {
			_thisTrips = _this[4].getRank();
			_thisPair = _this[0].getRank();
		}
		
		if(_that[1].getRank() == _that[2].getRank()) {
			_thatTrips = _that[0].getRank();
			_thatPair = _that[4].getRank();
		}
		else {
			_thatTrips = _that[4].getRank();
			_thatPair = _that[0].getRank();
		}
		
		// Compare the trips cards for each
		if(_thisTrips.getValue() > _thatTrips.getValue()) { // i.e.) 99922 > 444KK
			return true;
		}
		else if (_thisTrips.getValue() < _thatTrips.getValue()) {
			return false;
		}
		else { // The trips cards are same, now compare the pair cards for each
			
			if(_thisPair.getValue() > _thatPair.getValue()) { // i.e.) KKKJJ > KKK88
				return true;
			}
			else if (_thisPair.getValue() < _thatPair.getValue()) {
				return false;
			}
		}
		return false;
	}
	
	public static boolean isThisFoursBetterThanThat(Card[] _this, Card[] _that) {
		
		validate(_this, _that, FiveCardHand.FOUR_OF_A_KIND);
		
		Arrays.sort(_this, new CardRankComparator());
		Arrays.sort(_that, new CardRankComparator());
		
		// Determine the rank of the 4 matching cards and 1 high cards for this hand
		Rank _thisFours = null;
		Rank _thisHigh = null;
		Rank _thatFours = null;
		Rank _thatHigh = null;
		
		if(_this[0].getRank() == _this[1].getRank()) {
			_thisFours = _this[0].getRank();
			_thisHigh = _this[4].getRank();
		}
		else {
			_thisFours = _this[4].getRank();
			_thisHigh = _this[0].getRank();
		}
		
		if(_that[0].getRank() == _that[1].getRank()) {
			_thatFours = _that[0].getRank();
			_thatHigh = _that[4].getRank();
		}
		else {
			_thatFours = _that[4].getRank();
			_thatHigh = _that[0].getRank();
		}
		
		// Compare the fours cards for each
		if(_thisFours.getValue() > _thatFours.getValue()) { // i.e.) KKKK7 > 55553
			return true;
		}
		else if (_thisFours.getValue() < _thatFours.getValue()) {
			return false;
		}
		else { // The fours cards are same, now compare the high cards for each
			
			if(_thisHigh.getValue() > _thatHigh.getValue()) { // i.e.) QQQQ8 > QQQQ6
				return true;
			}
			else if (_thisHigh.getValue() < _thatHigh.getValue()) {
				return false;
			}
		}
		return false;
	}
	
	public static boolean isThisStraightFlushBetterThanThat(Card[] _this, Card[] _that) {
		
		validate(_this, _that, FiveCardHand.STRAIGHT_FLUSH);
		
		Arrays.sort(_this, new CardRankReverseComparator());
		Arrays.sort(_that, new CardRankReverseComparator());
		
		if(_this[0].getRank().getValue() > _that[0].getRank().getValue()) {
			return true;
		}
		
		return false;
	}
	
	public static void sortAscending(Card [] cards) {
		Arrays.sort(cards, new CardComparator());
	}
	
	public static void sortDescending(Card [] cards) {
		Arrays.sort(cards, new CardReverseComparator());
	}
	
	/**
	 * Gets the best hand(s) from list of hands.  A hand is 5-cards.
	 * @param hands
	 * @return
	 */
	public static List<Card []> getBestHand(List<Card []> hands) {
		
		if(hands == null || hands.size() == 0) {
			throw new RuntimeException("This method was called with a null or empty list.  This should never happen!");
		}
		
		for(Card [] hand : hands) {
			if(hand == null || hand.length != 5) {
				throw new RuntimeException("At least one hand passed into this method is null OR not five cards.");
			}
		}
		
		List<Card []> bestHands = new ArrayList<>();
		
		if(hands.size() == 1) {
			bestHands.add(hands.get(0));
			return bestHands;
		}
		
		FiveCardHand winningHand = null;
		
		List<Card [] > winningHands = new ArrayList<>();
		
		for(Card [] hand : hands) {
			
			FiveCardHand fiveCardHand = HandEvaluator.evaluate(hand);
			
			if(winningHand == null || fiveCardHand.getValue() > winningHand.getValue()) {
				
				winningHand = fiveCardHand;
				winningHands.clear();
				winningHands.add(hand);
			}
			else if (fiveCardHand == winningHand) {
				winningHands.add(hand);
			}
		}
		
		if(winningHands.size() == 1) {
			bestHands.add(winningHands.get(0));
			return bestHands;
		}
		
		// Further compare the hands in winningHands against each other
		
		return null;
	}
}


