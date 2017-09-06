package poker.framework;

public class DecisionFold implements Decision  {

	private DecisionType decisionType = DecisionType.FOLD;
	private int amount;
	
	public DecisionType getType() {
		return this.decisionType;
	}
	
	public int getAmount() {
		return this.amount;
	}
	
	public void setType(DecisionType decision) {
		this.decisionType = decision;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
}
