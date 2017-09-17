package poker.learning;

import poker.framework.Decision;
import poker.framework.DecisionType;

public class DecisionBits extends InfoBits {

	private Decision decision;
	
	public DecisionBits(Decision decision) {
		super(7);
		this.decision = decision;
		init();
	}
	
	private void init() {
		DecisionType decisionType = decision.getType();
	}
	
}
