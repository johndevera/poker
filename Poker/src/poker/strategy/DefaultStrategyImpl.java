package poker.strategy;

import poker.PocketHand;
import poker.framework.ActionValidator;
import poker.framework.Card;
import poker.framework.Decision;
import poker.framework.DecisionType;
import poker.framework.Game;
import poker.framework.Hand;
import poker.framework.HandEvaluator;
import poker.framework.Player;
import poker.framework.Street;
import java.util.EnumMap;
import poker.framework.Table;

public class DefaultStrategyImpl implements IStrategy {

	@Override
	public Decision decide(Player player, Hand hand, Game game, IKnowledge knowledge) {
		
		Card [] pocketCards = new Card[2];
		pocketCards[0] = hand.getFirst();
		pocketCards[1] = hand.getSecond();
		PocketHand pocketHand = HandEvaluator.evaluatePocket(pocketCards);
		
		int currentBet = game.getCurrentBet();
		int currentPot = game.getPot();
		int bet = player.getMyBet();
		
		//game.playerBets(Table.positions)
		//game.playerBets.put(, value)
		//int myBet = game.playerBets[player.]
		EnumMap<DecisionType, Integer> options = ActionValidator.getPossibleActions(player.getStack(), currentBet, bet, currentPot);
		
		Street street = game.getCurrentStreet();
		
		if (street == Street.PRE_FLOP) {
			return preFlopDecision(player, hand, game, knowledge, pocketHand, currentBet, options);
		}
		else if (street == Street.FLOP) {
			return flopDecision(player, hand, game, knowledge, pocketHand, currentBet, options);
		}
		else if (street == Street.TURN) {
			return turnDecision(player, hand, game, knowledge, pocketHand, currentBet, options);
		}
		else { //Street = River
			return riverDecision(player, hand, game, knowledge, pocketHand, currentBet, options);
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
	public Decision preFlopDecision(Player player, Hand hand, Game game, IKnowledge knowledge, PocketHand pocketHand, int currentBet, EnumMap<DecisionType, Integer> options) {

		//options is used to get the value amounts of various action options
		
		if(pocketHand == PocketHand.JUNK) {
			if(currentBet == 0) {
				return new Decision(DecisionType.CHECK, options.get(DecisionType.CHECK));
				//options.get(DecisionType.FOLD);
			}
			else {
				return new Decision(DecisionType.FOLD, options.get(DecisionType.FOLD));
			}
			
		}
		
		else if (pocketHand.getValue() <= 8) {
			return new Decision(DecisionType.CALL, options.get(DecisionType.CALL));
		}
		
		else if (pocketHand == PocketHand.MIDDLE_PAIR || pocketHand == PocketHand.PREMIUM_HAND) {
			return new Decision(DecisionType.RAISE, currentBet * 3);
		}
		
		else { // SUPER PREMIUM HAND
			return new Decision(DecisionType.RAISE, currentBet * 4);
		}
	}

	@Override
	public Decision flopDecision(Player player, Hand hand, Game game, IKnowledge knowledge, PocketHand pocketHand, int currentBet, EnumMap<DecisionType, Integer> options) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Decision turnDecision(Player player, Hand hand, Game game, IKnowledge knowledge, PocketHand pocketHand, int currentBet, EnumMap<DecisionType, Integer> options) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Decision riverDecision(Player player, Hand hand, Game game, IKnowledge knowledge, PocketHand pocketHand, int currentBet, EnumMap<DecisionType, Integer> options) {
		// TODO Auto-generated method stub
		return null;
	}

}
