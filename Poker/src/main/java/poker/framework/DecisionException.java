package poker.framework;

public class DecisionException extends Exception {
	
	private DecisionType decisionType;
	private int amount;
	
	//public Decision(Player player, Game game, DecisionType decisionType, int amount) {
	DecisionException(DecisionType decisionType, int amount) {
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
	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;

}
