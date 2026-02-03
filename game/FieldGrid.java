package game;

import items.Apples;
import items.Carrot;
import items.Corn;
import items.Grain;
import items.Item;
import items.Soil;
import items.UntilledSoil;
import items.Weed;

public class FieldGrid {
	
	private int height; //row 
	private int width; 	//column
	private Item field [][]; //2D array to hold items in the field 
	
	//Initializing the field with soil in every cell
	public FieldGrid(int height, int width)
	{
		this.height = height; 
		this.width = width; 
		
		field = new Item[height][width]; 
		
		//Creates the 2D field with soil 
		for( int row = 0; row < height; row++) { 
			for (int col = 0; col < width; col++) { 
				//Initially every field is a Soil 
				field[row][col] = new Soil(); 
			}
		}
	}
	
	//Tick invokes the aging of items in the filed 
	public void tick() { 
		
		for (int row = 0; row<height; row++) { 
			
			for (int col = 0; col<width; col++) { 
				//tick function called to increment the age
				field[row][col].tick(); 	
				
				//If the item dies replace with UntilledSoil 
				if(field[row][col].died()) { 
					field[row][col]= new UntilledSoil(); 
				}
				//If it's soil and didn't die 20% it is Weed 
				else if (field[row][col] instanceof Soil) { 
					if(Math.random()<0.2) { 
						field[row][col]= new Weed(); 
					}
				}
			}	
		}
	}
	
	
	@Override
	public String toString() { 
		StringBuilder sb = new StringBuilder(); 
		sb.append("  "); 
		
		//Column header going from 1 to width 
		for(int col = 0; col < width; col++) { 
			sb.append(col+1).append(" "); //1 increment because Column number starts from 1 
		}
		sb.append("\n"); 
		
		//Row
		for(int row = 0; row < height; row++) { 
			sb.append(row+1).append(" "); //1 increment because Row number starts from 1 
			for( int col = 0; col<width; col++) { 
				sb.append(field[row][col].toString()).append(" "); //Item's toString
			}sb.append("\n"); 
			
		}return sb.toString(); 
	}
	
	
	//Converts a selected cell into Soil 
	public void till(int x, int y) { 
		int row = y ; 
		int col = x ; 
		field[row][col] = new Soil(); 
	}
	
	//Returns the symbol of the selected cell 
	public Item get(int x, int y) { 
		int row = y ; 
		int col = x ; 
		return field[row][col]; 
	}
	
	//Stores an item in given coordinate 
	public void plant(int x, int y, Item item) {
		int row = y ; 
		int col = x ; 
		field[row][col] = item; 
	}
	
	//Calculates the total value of items in the field 
	public int getValue() { 
		int sum = 0; 
		
		//Looping through every cell to get their value 
		for (int row = 0 ; row < height; row++) { 
			
			for(int col = 0; col <width; col++) {
				//Only returns value if it's matured 
				sum += field[row][col].getValue(); 
			}
		}return sum; 
	}
	
	
	//Summarizes the field's item count 
	public String getSummary() { 
		//Counters to store the amount of items
		int soilCount = 0; 
		int appleCount = 0; 
		int weedCount = 0; 
		int untillCount = 0; 
		int grainCount = 0; 
		int carrotCount = 0; 
		int cornCount = 0; 
		
		//Looping through the field 
		//Count all item type
		for ( int row = 0 ; row < height ; row++ ) { 
			for ( int col = 0; col < width ; col ++) { 
				if(field[row][col] instanceof Soil) { 
					soilCount++; 
				}
				if(field[row][col] instanceof Apples) {
					appleCount++; 
				}
				if(field[row][col] instanceof Weed) {
					weedCount++; 
				}
				if(field[row][col] instanceof UntilledSoil) {
					untillCount++; 
				}
				if(field[row][col] instanceof Grain) {
					grainCount++; 
				}
				if(field[row][col] instanceof Carrot) {
					carrotCount++; 
				}
				if(field[row][col] instanceof Corn) {
					cornCount++; 
				}
			}
		}
		
		int totalValue = getValue(); 
		int applesCreated = Apples.getGenerationCount(); 
		int grainCreated = Grain.getGenerationCount(); 
		int cornCreated = Corn.getGenerationCount(); 
		int carrotCreated = Carrot.getGenerationCount(); 
		
		return "Apples:        "+appleCount+"\n"+
			   "Grain:         "+grainCount+"\n"+
			   "Corn:          "+cornCount+"\n"+
			   "Carrot:        "+carrotCount+"\n"+
			   "Soil:	       "+soilCount+"\n"+
			   "Untilled:      "+untillCount+"\n"+
			   "Weed:          "+weedCount+"\n"+
			   "For a total of $"+totalValue+"\n"+
			   "Total apples created: "+applesCreated+"\n"+
			   "Total grain created: "+grainCreated+"\n"+
			   "Total corn created: "+cornCreated+"\n"+
			   "Total carrot created: "+carrotCreated+"\n"; 
			   
	
			   
	}
}
