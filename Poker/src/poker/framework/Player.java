package poker.framework;

import poker.strategy.*;
import java.util.Random;

public class Player {

	private String name;
	private int stack;
	private IStrategy strategy;
	public int position;
	
	private int tightness;
	private int aggressiveness;
	//public int image;
	private int myBet;
	
	
	
	public Player(String name, int initialStack, Boolean defaultStrategy) {
		this.name = name;
		this.stack = initialStack;
		//this.strategy = strategy;
		
		//john
		this.tightness = initTraits("Tightness", 5, 3);
		this.aggressiveness = initTraits("Aggressiveness", 5, 3);
		this.strategy = setStrategy(defaultStrategy);
		this.myBet = 0;
		
	
	}
	
		//john
	public int initTraits(String typename, int mean, int stddev) {
		
		Random rand = new Random();
		int val;
		do {val = (int)rand.nextGaussian() * stddev + mean;}
		while(val < 0 && val > 10);
		System.out.println(this.name + " - " + typename + ":" +val);
		return val;
		
	}
	
	public IStrategy setStrategy(Boolean defaultStrategy) {
		if(defaultStrategy) {
			return new DefaultStrategyImpl();
		}
		if(this.tightness > 5 && this.aggressiveness > 5) {
			return new TightAggressiveStrategy();
		}
		else if(this.tightness > 5 && this.aggressiveness < 5) {
			return new TightPassiveStrategy();
		}
		else if(this.tightness < 5 && this.aggressiveness > 5) {
			return new LooseAggressiveStrategy();
		}
		else { //LoosePassiveStrategy
			return new LoosePassiveStrategy();
		}
	}
	
	public void setMyBet(int amount) {
		this.myBet = amount;
	}
	
	public int getMyBet() {
		return this.myBet;
	}
	
	public int getMyTightness() {
		return this.tightness;
	}
	
	public int getMyAggressiveness() {
		return this.aggressiveness;
	}
	
	public IStrategy getStrategy() {
		return this.strategy;
	}

	public int getStack() {
		return stack;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	public String toString() {
		return name;
	}
}
