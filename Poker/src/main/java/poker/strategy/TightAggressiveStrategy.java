package poker.strategy;

import poker.framework.ActionValidator;
import poker.framework.Card;
import poker.framework.Decision;
import poker.framework.DecisionType;
import poker.framework.Game;
import poker.framework.Hand;
import poker.framework.HandEvaluator;
import poker.framework.Player;
import poker.framework.PocketHand;
import poker.framework.Street;
import java.util.EnumMap;

public class TightAggressiveStrategy implements IStrategy {

	@Override
	public Decision decide(Player player, Hand hand, Game game, IKnowledge knowledge, ActionValidator action) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Decision preFlopDecision(Player player, Hand hand, Game game, IKnowledge knowledge, PocketHand pocketHand, ActionValidator options) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Decision flopDecision(Player player, Hand hand, Game game, IKnowledge knowledge, PocketHand pocketHand, ActionValidator options) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Decision turnDecision(Player player, Hand hand, Game game, IKnowledge knowledge, PocketHand pocketHand, ActionValidator options) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Decision riverDecision(Player player, Hand hand, Game game, IKnowledge knowledge, PocketHand pocketHand, ActionValidator options) {
		// TODO Auto-generated method stub
		return null;
	}



}
