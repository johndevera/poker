package poker.framework;

public class DecisionMaker {

	static Decision getDecision(DecisionType decisionType, double mult, int amount, Player player, Game game) {
		
		if (decisionType == DecisionType.FOLD) {
			Decision fold = new DecisionFold();
			fold.setAmount(ActionValidator.getFoldAmount());
			return fold;
		}
		else if (decisionType == DecisionType.CALL) {
			Decision call = new DecisionCall();
			if(ActionValidator.canCall(player, game)){
				call.setAmount(ActionValidator.getCallAmount(player, game));
			}
			return call;
		}
		else if (decisionType == DecisionType.CHECK) {
			Decision check = new DecisionCheck();
			if(ActionValidator.canCheck(player, game)) {
				check.setAmount(ActionValidator.getCheckAmount());
			}
			return check;
		}
		else if (decisionType == DecisionType.RAISE) {
			Decision raise = new DecisionRaise();
			if(ActionValidator.canRaise(mult, amount, player, game) == true) {
				raise.setAmount((int)(mult*amount));
			}
			return raise;
		}
		else if (decisionType == DecisionType.ALL_IN) {
			Decision allIn = new DecisionAllIn();
			allIn.setAmount(ActionValidator.getAllInAmount(player));
			return allIn;
		}
		return null;
	}
}
