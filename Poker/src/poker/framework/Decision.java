package poker.framework;

public class Decision {

	private DecisionType decisionType;
	private int amount;
	
	public Decision(DecisionType decisionType, int amount) {
		super();
		this.decisionType = decisionType;
		this.amount = amount;
	}
	
	public DecisionType getType() {
		return decisionType;
	}
	
	public int getAmount() {
		return amount;
	}
}
