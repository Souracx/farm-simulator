package items;

import util.GameConstants;

/**
 * Represents an Apple crop.
 * Medium-cost crop with moderate growth time and value.
 * 
 * Properties defined in GameConstants:
 * - Cost: $2
 * - Maturation: 3 ticks
 * - Death: 5 ticks
 * - Value: $3 when mature
 * - Net profit: $1 per harvest
 * 
 */
public class Apples extends Food {
	
	// Track total apples created during the game
	public static int generationCount = 0;
	
	// Cost to plant this crop 
	public static final int COST = GameConstants.APPLE_COST;

	/**
	 * Creates a new Apple crop.
	 * Uses GameConstants for all properties to maintain single source of truth.
	 * Increments generation counter.
	 */
	public Apples() { 
		super(GameConstants.APPLE_MATURATION,  // Maturation age from constants
		      GameConstants.APPLE_DEATH,        // Death age from constants
		      GameConstants.APPLE_VALUE);       // Value from constants
		generationCount++; 
	}
	
	/**
	 * Gets the total number of apples created during the game.
	 * 
	 * @return Total apple generation count
	 */
	public static int getGenerationCount() { 
		return generationCount; 
	}
	
	/**
	 * Returns the visual representation of the apple.
	 * Shows 'a' when immature, 'A' when mature (harvestable).
	 * 
	 * @return "a" if immature, "A" if mature
	 */
	@Override
	public String toString() { 
		if(getAge() < getMaturationAge()) { 
			return "a"; // Immature apple
		} else { 
			return "A"; // Mature apple (ready to harvest)
		}
	}
}