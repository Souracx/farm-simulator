package items;

import util.GameConstants;

/**
 * Represents a Grain crop.
 * Low-cost, fast-growing crop with decent lifespan.
 * 
 * Properties defined in GameConstants:
 * - Cost: $1
 * - Maturation: 2 ticks (fastest to mature)
 * - Death: 6 ticks (good lifespan)
 * - Value: $2 when mature
 * - Net profit: $1 per harvest
 * 
 */
public class Grain extends Food {
	
	// Track total grain created during the game
	public static int generationCount = 0;
	
	// Cost to plant this crop 
	public static final int COST = GameConstants.GRAIN_COST;

	/**
	 * Creates a new Grain crop.
	 * Increments generation counter.
	 */
	public Grain() { 
		super(GameConstants.GRAIN_MATURATION,  // Maturation age from constants
		      GameConstants.GRAIN_DEATH,        // Death age from constants
		      GameConstants.GRAIN_VALUE);       // Value from constants
		generationCount++; 
	}
	
	/**
	 * Gets the total number of grain created during the game.
	 * 
	 * @return Total grain generation count
	 */
	public static int getGenerationCount() { 
		return generationCount; 
	}
	
	/**
	 * Returns the visual representation of grain.
	 * Shows 'g' when immature, 'G' when mature (harvestable).
	 * 
	 * @return "g" if immature, "G" if mature
	 */
	@Override
	public String toString() { 
		if (getAge() < getMaturationAge()) { 
			return "g"; // Immature grain
		} else { 
			return "G"; // Mature grain (ready to harvest)
		}
	}
}