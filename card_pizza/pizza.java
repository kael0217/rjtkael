package card_pizza;

abstract public class pizza {
		
	String name;
	String discription;
	int cost;
	
	public void setCost(int c) {
		this.cost=c;
	}
	
	public float getCost() {
		return this.cost;
	}

}
