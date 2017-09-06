package poker.framework;

import java.util.EnumMap;
import poker.framework.DecisionType;

public class ActionValidator{
	
	private Decision decision = new Decision(null, 0);
	
	//Decision.newD
	
	public int getFoldAmount() {
		return 0;
	}
	public int getCheckAmount() {
		return 0;
	}
	public int getCallAmount(Player player, Game game) {
		return game.getCurrentBet() - player.getMyBet();
	}
	public int getMinRaiseAmount(Game game) {
		return 2*game.getCurrentBet();
	}
	public int getMaxRaiseAmount(Player player) {
		return player.getStack();
	}
	public int getAllInAmount(Player player) {
		return player.getStack();
	}
	
	
	private boolean canRaise(int mult, int amount, Player player, Game game) {
		int value = mult*amount;
		return (value >= getMinRaiseAmount(game) && value < getMaxRaiseAmount(player));
	}
	
	private boolean canCall(Player player, Game game) {
		return (getCallAmount(player, game) < player.getStack() && getCallAmount(player, game) > 0);
	}
		
	private boolean canCheck(Player player, Game game) {
		return game.getCurrentBet() == player.getMyBet();
	}
	
	public Decision fold() {
		this.decision.setType(DecisionType.FOLD);
		this.decision.setAmount(getFoldAmount());
		return this.decision;
	}
	
	public Decision check(Player player, Game game) {
		if(canCheck(player, game)) {
			this.decision.setType(DecisionType.CHECK);
			this.decision.setAmount(this.getCheckAmount());
			return this.decision;
		}
		else {
			return null;
		}
	}
	
	public Decision call(Player player, Game game) {
		if(canCall(player, game)){
			this.decision.setType(DecisionType.CALL);
			this.decision.setAmount(getCallAmount(player, game));
			return this.decision;
		}
		else {
			return null;
		}
	}
	
	public Decision raise(int mult, int amount, Player player, Game game) {
		if(canRaise(mult, amount, player, game) == true) {
			this.decision.setType(DecisionType.RAISE);
			this.decision.setAmount(mult*amount);
			return this.decision;
		}
		else {
			return this.decision;
		}
	}
	/*
	public Decision allIn(Player player) {
		this.decision.setType(DecisionType.ALL_IN);
		this.decision.setAmount(this.getAllInAmount(player));
		return this.decision;
	}
	
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


