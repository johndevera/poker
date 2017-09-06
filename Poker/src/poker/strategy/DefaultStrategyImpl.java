package poker.strategy;

import poker.framework.ActionValidator;
import poker.framework.Card;
import poker.framework.Decision;
import poker.framework.DecisionType;
import poker.framework.Game;
import poker.framework.Hand;
import poker.framework.HandEvaluator;
import poker.framework.Player;
import poker.framework.Street;
import java.util.List;
import java.util.Map;


public class DefaultStrategyImpl implements IStrategy {

	@Override
	public Decision decide(Player player, Hand hand, Game game, IKnowledge knowledge, ActionValidator action) {
		
		Card [] pocketCards = new Card[2];
		pocketCards[0] = hand.getFirst();
		pocketCards[1] = hand.getSecond();
		PocketHand pocketHand = HandEvaluator.evaluatePocket(pocketCards);
				
		Street street = game.getCurrentStreet();
		
		if (street == Street.PRE_FLOP) {
			return preFlopDecision(player, hand, game, knowledge, pocketHand, action);
		}
		else if (street == Street.FLOP) {
			return flopDecision(player, hand, game, knowledge, pocketHand, action);
		}
		else if (street == Street.TURN) {
			return turnDecision(player, hand, game, knowledge, pocketHand, action);
		}
		else { //Street = River
			return riverDecision(player, hand, game, knowledge, pocketHand, action);
		}
		// positions[0] = small blind player
		// positions[1] = big blind player
		// ...
		// positions[n-1] = dealer
		// 
		// where n = positions.length (i.e., # of players)
		/*
		int myPosIndex = game.getPosIndexOfPlayer(player);
		
		int playerBeforeMeIndex = (myPosIndex == 0)
				? game.getNumPlayers()-1
				: myPosIndex - 1;
		
		Player playerBeforeMe = 
				game.getPlayerAtPosIndex(playerBeforeMeIndex);
		
		Decision decisionOfPlayerBeforeMe =
				game.getPreFlopDecisions().get(playerBeforeMe);
		
		int currentPot = game.getPot();*/
		
		/*
		// If game is currently at Pre Flop, only makes sense to look at pre-flop decisions
		if(game.getCurrentStreet() == PRE_FLOP) {
		
		
			// Example of looking at decision of player before you
			if(decisionOfPlayerBeforeMe.getType() == CALL) {
				
				if(decisionOfPlayerBeforeMe.getAmount() < (int) 0.5 * currentPot) {
					return new Decision(FOLD, 0);
				}
				else {
					return new Decision(CALL, game.getCurrentBet());
				}
				
			}
			
			// How to iterate through all the decisions.  You can check for call, fold, raise, and amount of raise
			Map<Player, Decision> preFlopDecisions = game.getPreFlopDecisions();
			
			for(Decision decision : preFlopDecisions.values()) {
				
			}
			
		}
		*/
	}
	
	@Override
	public Decision preFlopDecision(Player player, Hand hand, Game game, IKnowledge knowledge, PocketHand pocketHand, ActionValidator action) {


		
		if(pocketHand == PocketHand.JUNK) {
			if(game.getCurrentBet() == 0) {
				return Decision.check(player, game);
			}
			else {
				return Decision.fold();
			}
			
		}
		
		else if (pocketHand.getValue() <= 8) {
			return Decision.call(player, game);
		}
		
		else if (pocketHand == PocketHand.MIDDLE_PAIR || pocketHand == PocketHand.PREMIUM_HAND) {

			return Decision.raise(3, game.getCurrentBet(), player, game);
		}
		
		else { // SUPER PREMIUM HAND

			return Decision.raise(4, game.getCurrentBet(), player, game);
		}
	}

	@Override
	public Decision flopDecision(Player player, Hand hand, Game game, IKnowledge knowledge, PocketHand pocketHand, ActionValidator action) {
		return doSimpleDecisionMaking(player, hand, game, knowledge, pocketHand, game.getCurrentBet(), action);		
	}

	@Override
	public Decision turnDecision(Player player, Hand hand, Game game, IKnowledge knowledge, PocketHand pocketHand, ActionValidator action) {
		return doSimpleDecisionMaking(player, hand, game, knowledge, pocketHand, game.getCurrentBet(), action);
	}

	@Override
	public Decision riverDecision(Player player, Hand hand, Game game, IKnowledge knowledge, PocketHand pocketHand, ActionValidator action) {
		return doSimpleDecisionMaking(player, hand, game, knowledge, pocketHand, game.getCurrentBet(), action);
	}
	
	/**
	 * Creating this method to test simple, dummy betting behavior
	 */
	
	
	private Decision doSimpleDecisionMaking(Player player, Hand hand, Game game, IKnowledge knowledge, PocketHand pocketHand, int currentBet, ActionValidator action) {
		
		List<Card> playerCards = game.getCommunityCards();
		playerCards.add(hand.getFirst());
		playerCards.add(hand.getSecond());

		FiveCardHand fiveCardHand = null;
		
		if(playerCards.size() == 5) {
			fiveCardHand = HandEvaluator.evaluate(playerCards.toArray(new Card[0]));
		}
		else if(playerCards.size() == 6) {
			fiveCardHand = HandEvaluator.evaluateSix(playerCards.toArray(new Card[0]));
		}
		else if(playerCards.size() == 7) {
			fiveCardHand = HandEvaluator.evaluateSeven(playerCards.toArray(new Card[0]));			
		}
				
		Decision decision = null;
		
		int pot = game.getPot();
		
		int numRaises = howManyTimesHasItBeenRaisedInThisStreet(game);
		
		if(fiveCardHand.getValue() >= FiveCardHand.TWO_PAIR.getValue()) {
			
			if(numRaises <= 2) {
				double twoThirds = 0.65;
				decision = Decision.raise(twoThirds, pot, player, game);
			}
			else {
				decision = Decision.call(player, game);
			}
		}
		else if (fiveCardHand.getValue() == FiveCardHand.ONE_PAIR.getValue()) {
			
			if(numRaises <= 2) {
				double half = 0.5;
				decision = Decision.raise(half, pot, player, game);
			}
			else {
				decision = Decision.call(player, game);
			}
			
		}
		else if (fiveCardHand.getValue() < FiveCardHand.ONE_PAIR.getValue()) {
			
			decision = (currentBet == 0)
				? Decision.check(player, game)//new Decision(DecisionType.CHECK, 0)
				: Decision.fold(); //new Decision(DecisionType.FOLD, 0);
		}
		
		// Final validation - make sure not to exceed player's stack size
		if(game.getPot() >= player.getStack()) {
			decision = Decision.allIn(player);
		}
		return decision;
	}

	private int howManyTimesHasItBeenRaisedInThisStreet(Game game) {
		
		int numRaises = 0;
		Map<Player, List<Decision>> allDecisionsThisStreet = null;
		if(game.getCurrentStreet() == Street.FLOP) {
			allDecisionsThisStreet = game.getFlopDecisions();
		}
		else if(game.getCurrentStreet() == Street.TURN) {
			allDecisionsThisStreet = game.getTurnDecisions();
		}
		else {
			allDecisionsThisStreet = game.getRiverDecisions();
		}
			
		for(List<Decision> playerDecisions : allDecisionsThisStreet.values()) {
			
			for(Decision decision : playerDecisions) {
				if(decision.getType() == DecisionType.RAISE) {
					numRaises++;
				}
			}
		}
		return numRaises;
	}
	
}
