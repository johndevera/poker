package poker.learning;

public abstract class InfoBits {

	private boolean [] bits;
	
	public InfoBits(int numBits) {
		bits = new boolean[numBits];
	}
	
	public boolean [] getBits() {
		return bits;
	}
	
	public int numBits() {
		return bits.length;
	}
	
	public void update(boolean bit, int index) {
		
		if(index < 0 || index >= bits.length) {
			throw new RuntimeException("ERROR: You cannot update this InfoBit as index = " + index + " is outside the range of bits.  Max = " + numBits());
		}
	}
}

