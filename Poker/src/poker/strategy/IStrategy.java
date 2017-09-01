package poker.strategy;

import java.util.EnumMap;

import poker.PocketHand;
import poker.framework.Decision;
import poker.framework.DecisionType;
import poker.framework.Game;
import poker.framework.Hand;
import poker.framework.Player;

public interface IStrategy {

	public Decision decide(Player player, Hand hand, Game game, IKnowledge knowledge); 
	
	
	Decision preFlopDecision(Player player, Hand hand, Game game, IKnowledge knowledge, PocketHand pocketHand,
			int currentBet, EnumMap<DecisionType, Integer> options);

	Decision flopDecision(Player player, Hand hand, Game game, IKnowledge knowledge, PocketHand pocketHand,
			int currentBet, EnumMap<DecisionType, Integer> options);

	Decision turnDecision(Player player, Hand hand, Game game, IKnowledge knowledge, PocketHand pocketHand,
			int currentBet, EnumMap<DecisionType, Integer> options);

	Decision riverDecision(Player player, Hand hand, Game game, IKnowledge knowledge, PocketHand pocketHand,
			int currentBet, EnumMap<DecisionType, Integer> options);
	
}
