package poker.framework;

public class DecisionException extends Exception {

	private static final long serialVersionUID = 1L;
	private DecisionType decisionType;
	private int amount;
	
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
}
