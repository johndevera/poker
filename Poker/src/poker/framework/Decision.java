package poker.framework;
/*
public interface Decision{
	//public Decision(DecisionType decisionType, int amount);
	
	public DecisionType getType();
	
	public int getAmount();
	
	public void setType(DecisionType decision);
	
	public void setAmount(int amount);
}
*/

public class Decision {

	private DecisionType decisionType;
	private int amount;
	
	//public Decision(Player player, Game game, DecisionType decisionType, int amount) {
	private Decision(DecisionType decisionType, int amount) {
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
	
	public static Decision fold() {
		return new Decision(DecisionType.FOLD, ActionValidator.getFoldAmount());
	}
	
	public static Decision call(Player player, Game game) {
		if(ActionValidator.canCall(player, game)){
			return new Decision(DecisionType.CALL, ActionValidator.getCallAmount(player, game));
		}
		//return null;
		return new Decision(DecisionType.FOLD, ActionValidator.getFoldAmount());
	}
	
	public static Decision check(Player player, Game game) {
		if(ActionValidator.canCheck(player, game)) {
			//return new Decision(DecisionType.CHECK, 0);
			return new Decision(DecisionType.CHECK, ActionValidator.getCheckAmount());
		}
		else if (ActionValidator.canCall(player, game)) {
			return new Decision(DecisionType.CALL, ActionValidator.getCheckAmount());
		}
		return new Decision(DecisionType.FOLD, ActionValidator.getFoldAmount());
	}
	
	public static Decision raise(double mult, int amount, Player player, Game game) {
		if(ActionValidator.canRaise(mult, amount, player, game)) {
			return new Decision(DecisionType.RAISE, (int)(mult*amount));
		}
		else if(ActionValidator.canCheck(player, game)) {
			//return new Decision(DecisionType.CHECK, 0);
			return new Decision(DecisionType.CHECK, ActionValidator.getCheckAmount());
		}
		else if (ActionValidator.canCall(player, game)) {
			return new Decision(DecisionType.CALL, ActionValidator.getCheckAmount());
		}
		return new Decision(DecisionType.FOLD, ActionValidator.getFoldAmount());
	}
	
	public static Decision allIn(Player player) {
		//return new Decision(DecisionType.ALL_IN, player.getStack());
		return new Decision(DecisionType.ALL_IN, ActionValidator.getAllInAmount(player));
	}
	
	
	
}