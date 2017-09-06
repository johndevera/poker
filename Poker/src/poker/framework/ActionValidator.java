package poker.framework;

import poker.framework.DecisionType;

public class ActionValidator{
	
	//private Decision decision = new Decision(null, 0);
	
	public static int getFoldAmount() {
		return 0;
	}
	public static int getCheckAmount() {
		return 0;
	}
	public static int getCallAmount(Player player, Game game) {
		return game.getCurrentBet() - player.getMyBet();
	}
	public static int getMinRaiseAmount(Game game) {
		return 2*game.getCurrentBet();
	}
	public static int getMaxRaiseAmount(Player player) {
		return player.getStack();
	}
	public static int getAllInAmount(Player player) {
		return player.getStack();
	}
	
	
	public static boolean canRaise(double mult, int amount, Player player, Game game) {
		double value = mult*amount;
		return (value >= getMinRaiseAmount(game) && value < getMaxRaiseAmount(player));
	}
	
	public static boolean canCall(Player player, Game game) {
		return (getCallAmount(player, game) < player.getStack() && getCallAmount(player, game) > 0);
	}
		
	public static boolean canCheck(Player player, Game game) {
		return game.getCurrentBet() == player.getMyBet();
	}
	
	public Decision fold(double mult, int amount, Player player, Game game) {
		return DecisionMaker.getDecision(DecisionType.FOLD, mult, amount, player, game);
	}
	
	public Decision check(double mult, int amount, Player player, Game game) {
		return DecisionMaker.getDecision(DecisionType.CHECK, mult, amount, player, game);

	}
	
	public Decision call(double mult, int amount, Player player, Game game) {
		return DecisionMaker.getDecision(DecisionType.CALL, mult, amount, player, game);
		
	}
	
	public Decision raise(double mult, int amount, Player player, Game game) {
		return DecisionMaker.getDecision(DecisionType.RAISE, mult, amount, player, game);

	}
	
	public Decision allIn(double mult, int amount, Player player, Game game) {
		return DecisionMaker.getDecision(DecisionType.ALL_IN, mult, amount, player, game);
	}
	/*
	public class Decision {

		private DecisionType decisionType;
		private int amount;
		
		public Decision(DecisionType decisionType, int amount) {
			super();
			this.decisionType = decisionType;
			this.amount = amount;
		}
		
		public DecisionType getType() {
			return this.decisionType;
		}
		
		public int getAmount() {
			return this.amount;
		}
		
		public void setType(DecisionType decision) {
			//return decision;
			this.decisionType = decision;
		}
		
		public void setAmount(int amount) {
			//return amount;
			this.amount = amount;
		}
	*/
}


