package items;

/**
 * Abstract base class for all items that can exist in the field.
 * Provides common properties and behaviors for all game items.
 * 
 * Uses the Template Method design pattern - defines the structure
 * while allowing subclasses to override specific behaviors.
 * 
 */

public  abstract class Item {
	
	//Item properties 
	private int age; //current age 
	private int maturationAge; 	//age when it matures(crops) 
	private int deathAge; //Age when it dies 
	private int monetaryValue; //Value when it is harvested
	
	
	public Item(int maturationAge, int deathAge, int monetaryValue) { 
		this.age=0; 
		this.maturationAge=maturationAge; 
		this.deathAge=deathAge; 
		this.monetaryValue=monetaryValue; 
	}
	
	//Increases the age by 1; 
	//Called when w is used 
	public void tick() { 
		age++; 
	}
	
	//Returns the age of Item
	public int getAge() { 
		return age; 
	}
	
	//Sets the age of an Item
	public void setAge(int age) {
		this.age=age; 
	}
	
	public int getMaturationAge() { 
		return maturationAge; 
	}
	
	public int getMonetaryValue() {
		return monetaryValue; 
	}
	
	//Returns true if item age passes deathAge 
	public boolean died() { 
		return age>deathAge; 
	}
	
	public int getValue() { 
		return monetaryValue; 
	}
	
	//check if another item is equal to this one
	@Override
	public boolean equals(Object obj) { 
		if (this == obj) { 
			return true; 
		}if (obj == null || getClass() != obj.getClass()) {
			return false; 
		}
		Item other = (Item)obj; 
		return age==other.age && maturationAge == other.maturationAge && deathAge == other.deathAge && monetaryValue == other.monetaryValue; 
		
	}
	
	@Override
	public abstract String toString();
}
