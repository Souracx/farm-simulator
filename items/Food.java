package items;

/**
 * Abstract base class for all food items (crops).
 * Extends Item to add food-specific behaviors like maturation-based value.
 * 
 * Uses Template Method pattern - provides common food behavior
 * while allowing specific crops to customize their appearance.
 */

public abstract class Food extends Item {

	public Food(int maturationAge, int deathAge, int monetaryValue) {
		super(maturationAge, deathAge, monetaryValue);
	}
	
	@Override
	public int getValue() { 
		//check if crop has reached maturation age 
		if (getAge()>=getMaturationAge()){ 
			return getMonetaryValue(); 
		}else {
			return 0; //Immature crops have no value 
		}
	}
	
	//Method that increases the Age and sets it to Maturation Age
	public void fertiliser() { 
		setAge(getMaturationAge()); 
	}

}