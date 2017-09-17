package poker.learning;

public class MoneyBits extends InfoBits {

	private int amount;
	
	private int max;
	
	public MoneyBits(int amount) {
		super(14);
		this.amount = amount;
		this.max = (int) (Math.pow(2.0, numBits()));
		init();
	}
	
	public void init() {
		
		if(amount < 0 || amount > max) {
			throw new RuntimeException("Amount = " + amount + " is not valid as max = " + max);
		}
		
		// Figure out how to convert amount to bits
	}
}
