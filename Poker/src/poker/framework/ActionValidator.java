package poker.framework;

import java.util.EnumMap;
import poker.framework.DecisionType;

public class ActionValidator {
	
	private static EnumMap<DecisionType, Integer> actionsAvailable = new EnumMap<DecisionType, Integer>(DecisionType.class);
	
	public static EnumMap<DecisionType, Integer> getPossibleActions(int stack, int currentBet, int myBet, int currentPot){
		actionsAvailable.put(DecisionType.FOLD, 0);	
		actionsAvailable.put(DecisionType.ALL_IN, stack);
		actionsAvailable.put(DecisionType.CALL, 0);
		actionsAvailable.put(DecisionType.CHECK, 0);
		actionsAvailable.put(DecisionType.RAISE, 0);
		
		//int myBet = 0;
		if(myBet <= currentBet){
			actionsAvailable.put(DecisionType.CALL, currentBet - myBet);
			}
		if(stack >= 2*currentBet) {
			actionsAvailable.put(DecisionType.RAISE, 2*currentBet);
		}
		return actionsAvailable;
	}
}
