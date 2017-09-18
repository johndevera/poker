package poker.learning;

public enum Size {

	BIG(4),
	ABOVE_AVERAGE(3),
	AVERAGE(2),
	BELOW_AVERAGE(1),
	SMALL(0);
	
	private int value;
	
	private Size(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
	public static Size fromValue(int value) {
		for(Size size : Size.values()) {
			if(size.getValue() == value) {
				return size;
			}
		}
		return null;
	}
	
	public static Size random() {
		
		int total = Size.values().length;
		
		int randomSize = (int) (total * Math.random());
		
		return fromValue(randomSize);
	}
	
	public static void main (String [] args) {
		
		for(int i = 0; i < 100; i++) {
			int total = Size.values().length;
			
			int randomSize = (int) (total * Math.random());
			
			System.out.println(randomSize);
		}
	}
}
