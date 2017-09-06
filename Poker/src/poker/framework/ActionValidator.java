package poker.framework;

import java.util.EnumMap;
import poker.framework.DecisionType;

public class ActionValidator{
	
	private int currentBet;
	private int currentPot;
	private int myBet;
	private int myStack;
	
	private int foldAmount;
	private int checkAmount;
	private int callAmount;
	private int minRaiseAmount;
	private int maxRaiseAmount;
	private int allInAmount;
	
	private Decision decision = new Decision(null, 0);

	public ActionValidator(Game game, Player player) {
		//decision.getType();
		this.currentBet = game.getCurrentBet();
		this.currentPot = game.getPot();
		this.myBet = player.getMyBet();
		this.myStack = player.getStack();
		
		this.foldAmount = 0;
		this.checkAmount = 0;
		this.callAmount = currentBet - myBet;
		this.minRaiseAmount = 2*currentBet;
		this.maxRaiseAmount = myStack;
		this.allInAmount = myStack;
	}
	
	private boolean canRaise(int mult, int amount) {
		int value = mult*amount;
		if(value >= minRaiseAmount && value < maxRaiseAmount) {
			return true;
		}
		else{
			return false;
		}
	}
	
	private boolean canCall() {
		if(callAmount < myStack && callAmount > 0) { //makes sure callAmount is not negative
			return true;
		}
		else {
			return false;
		}
	}
		
	private boolean canCheck() {
		if(currentBet == myBet){
			return true;
		}
		else {
			return false;
		}
	}
	public Decision fold() {
		this.decision.setType(DecisionType.FOLD);
		this.decision.setAmount(foldAmount);
		return this.decision;
	}
	
	public Decision check() {
		if(canCheck() == true) {
			this.decision.setType(DecisionType.CHECK);
			this.decision.setAmount(this.checkAmount);
			return this.decision;
		}
		else {
			return null;
		}
	}
	
	public Decision call() {
		if(canCall() == true){
			this.decision.setType(DecisionType.CALL);
			this.decision.setAmount(callAmount);
			return this.decision;
		}
		else {
			return null;
		}
		
	}
	
	public Decision raise(int mult, int amount) {
		if(canRaise(mult, amount) == true) {
			this.decision.setType(DecisionType.RAISE);
			this.decision.setAmount(mult*amount);
			return this.decision;
		}
		else {
			return this.decision;
		}

	}
	
	public Decision allIn() {
		this.decision.setType(DecisionType.ALL_IN);
		this.decision.setAmount(this.allInAmount);
		return this.decision;
	}
	
	
	
	
	public int getFoldAmount() {
		return this.foldAmount;
	}
	public int getCheckAmount() {
		return this.checkAmount;
	}
	public int getCallAmount() {
		return this.callAmount;
	}
	public int getMinRaiseAmount() {
		return this.minRaiseAmount;
	}
	public int getMaxRaiseAmount() {
		return this.maxRaiseAmount;
	}
	public int getAllInAmount() {
		return this.allInAmount;
	}
	
	
	/*
	private static EnumMap<DecisionType, Integer> actionsAvailable = new EnumMap<DecisionType, Integer>(DecisionType.class);
	
	public static EnumMap<DecisionType, Integer> getPossibleActions(Game game, Player player){
		
		int currentBet = game.getCurrentBet();
		int currentPot = game.getPot();
		int myBet = player.getMyBet();
		int myStack = player.getStack();

		actionsAvailable.put(DecisionType.FOLD, 0);	
		actionsAvailable.put(DecisionType.CALL, 0);
		actionsAvailable.put(DecisionType.CHECK, 0);
		actionsAvailable.put(DecisionType.RAISE, 0);
		actionsAvailable.put(DecisionType.ALL_IN, myStack);

		
		//int myBet = 0;
		int callAmount = currentBet - myBet;
		if(myBet <= currentBet && callAmount < myStack){
			//actionsAvailable.put(DecisionType.CALL, callAmount);
			}
		if(myStack >= 2*currentBet) {
			//actionsAvailable.put(DecisionType.RAISE, 2*currentBet);
		}
		return actionsAvailable;
	}
	
	public int getCallAmount() {
		return actionsAvailable.get(DecisionType.CALL);
	}
	
	public int getCheckAmount() {
		return actionsAvailable.get(DecisionType.CHECK);
	}
	
	public int getMinRaiseAmount() {
		return actionsAvailable.get(DecisionType.RAISE);
	}
	
	public int getAllInAmount() {
		return actionsAvailable.get(DecisionType.ALL_IN);
	}
	*/
}
