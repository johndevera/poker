package poker.framework;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import poker.strategy.DrawingHands;
import poker.strategy.FiveCardHand;
import poker.strategy.PocketHand;

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
	
	private static FiveCardHand evaluateOnePair(Card [] hand) {
		
		for(int i=0; i < 4; i++) {
			
			if(hand[i].getRank() == hand[i+1].getRank()) {
				return FiveCardHand.ONE_PAIR;
			}
		}
		return null;
	}
	
	private static FiveCardHand evaluateTwoPair(Card [] hand) {
		
		
		if( 	(hand[0].getRank() == hand[1].getRank() &&
				hand[2].getRank() == hand[3].getRank()) ||
				
				(hand[1].getRank() == hand[2].getRank() &&
				hand[3].getRank() == hand[4].getRank()) ||
				
				(hand[0].getRank() == hand[1].getRank() &&
				hand[3].getRank() == hand[4].getRank())
				
			) {
		
			return FiveCardHand.TWO_PAIR;
		}
		return null;
	}
	
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
	/*
	public static DrawingHands[] evaluateDraws(Card[] hand) {
		
		if(hand == null || hand.length != 4) 
			throw new RuntimeException("This method should never be called without passing 5-cards!");
		//List<Card> list = new ArrayList<Card>(Arrays.asList(sevenHand));
		Arrays.sort(hand, new CardRankComparator());
		DrawingHands[] draw = new DrawingHands[3];
		draw[0] = evaluateFlushDraw(hand);
		draw[1] = evaluateInsideStraightDraw(hand);
		draw[2] = evaluateOpenEndedStraightDraw(hand);
		
		//List<DrawingHands> draw = new ArrayList<DrawingHands>(null);
		//draw.add(e) evaluateStraight(hand);
		
		return draw;
	}
	*/
	
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
	
}


