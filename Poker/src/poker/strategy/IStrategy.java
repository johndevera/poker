package poker.strategy;

import poker.PocketHand;
import poker.framework.ActionValidator;
import poker.framework.Decision;
import poker.framework.Game;
import poker.framework.Hand;
import poker.framework.Player;

public interface IStrategy {

	public Decision decide(Player player, Hand hand, Game game, IKnowledge knowledge, ActionValidator action); 
	
	Decision preFlopDecision(Player player, Hand hand, Game game, IKnowledge knowledge, PocketHand pocketHand, ActionValidator options);

	Decision flopDecision(Player player, Hand hand, Game game, IKnowledge knowledge, PocketHand pocketHand, ActionValidator options);

	Decision turnDecision(Player player, Hand hand, Game game, IKnowledge knowledge, PocketHand pocketHand, ActionValidator options);

	Decision riverDecision(Player player, Hand hand, Game game, IKnowledge knowledge, PocketHand pocketHand, ActionValidator options);
}
