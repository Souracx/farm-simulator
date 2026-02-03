package items;

public class Weed extends Item{

	//Using Integer.MAX_VALUE to simulate infinite maturation and death age. 
	public Weed() { 
		super(Integer.MAX_VALUE,Integer.MAX_VALUE,-1); 
	}
	
	@Override
	public String toString() { 
		return "#"; 
	}
}
