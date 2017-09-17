package poker.framework;

public class Decision {

	private DecisionType decisionType;
	private int amount;
	
	/**
	 * Contains the data for a decision such as the type and its associated amount
	 */
	private Decision(DecisionType decisionType, int amount) {
		super();
		this.decisionType = decisionType;
		this.amount = amount;
	}
	
	/**
	 * Returns an enum of DecisionType
	 * @return
	 */
	public DecisionType getType() {
		return this.decisionType;
	}
	
	/**
	 * Returns the amount associated with the decision.
	 * @return
	 */
	public int getAmount() {
		return this.amount;
	}
	
	/**
	 * Sets the decision with a specific DecisionType enum.
	 * @param decision
	 */
	public void setType(DecisionType decision) {
		//return decision;
		this.decisionType = decision;
	}
	
	/**
	 * Sets the amount of the decision a specific amount. 
	 * @param amount
	 */
	public void setAmount(int amount) {
		//return amount;
		this.amount = amount;
	}
	
	/**
	 * Returns the decision associated with fold and runs ActionValidator to ensure it works.
	 * @return
	 */
	public static Decision fold(){
		return new Decision(DecisionType.FOLD, ActionValidator.getFoldAmount());
	}
	
	/**
	 * Checks ActionValidator canCall and if true, returns a Call Decision with the appropriate amount.
	 * @param player
	 * @param game
	 * @return
	 */
	public static Decision call(Player player, Game game) {//throws DecisionException{
		if(ActionValidator.canCall(player, game)){
			return new Decision(DecisionType.CALL, ActionValidator.getCallAmount(player, game));
		}
		return new Decision(DecisionType.FOLD, ActionValidator.getFoldAmount());
	}
	
	/**
	 * Checks ActionValidator on canCheckAmount. If false, it tests if it needs to call, and if that fails, it folds.
	 * @param player
	 * @param game
	 * @return
	 */
	public static Decision check(Player player, Game game) {
		if(ActionValidator.canCheck(player, game)) {
			return new Decision(DecisionType.CHECK, ActionValidator.getCheckAmount());
		}
		else if (ActionValidator.canCall(player, game)) {
			return new Decision(DecisionType.CALL, ActionValidator.getCheckAmount());
		}
		return new Decision(DecisionType.FOLD, ActionValidator.getFoldAmount());
	}
	
	/**
	 * Checks ActionValidator on canRaise. If false, if it can check. As well if it can't do that, it will try to call. Otherwise it just folds.
	 * @param mult
	 * @param amount
	 * @param player
	 * @param game
	 * @return
	 */
	public static Decision raise(double mult, int amount, Player player, Game game) {
		if(ActionValidator.canRaise(mult, amount, player, game)) {
			return new Decision(DecisionType.RAISE, (int)(mult*amount));
		}
		else if(ActionValidator.canCheck(player, game)) {
			return new Decision(DecisionType.CHECK, ActionValidator.getCheckAmount());
		}
		else if (ActionValidator.canCall(player, game)) {
			return new Decision(DecisionType.CALL, ActionValidator.getCheckAmount());
		}
		return new Decision(DecisionType.FOLD, ActionValidator.getFoldAmount());
	}
	
	/**
	 * Checks ActionValidator on getAllInAmount and returns it as part of the new Decision.
	 * @param player
	 * @return
	 */
	public static Decision allIn(Player player) {

		return new Decision(DecisionType.ALL_IN, ActionValidator.getAllInAmount(player));
	}
	
	
	
}