package poker.framework;

public class Decision {

	private DecisionType decisionType;
	private int amount;
	
	//public Decision(Player player, Game game, DecisionType decisionType, int amount) {
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
}
