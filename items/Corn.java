package items;

import util.GameConstants;

/**
 * High-cost, slow-growing crop with high reward.
 * 
 * Properties defined in GameConstants:
 * - Cost: $3 (most expensive)
 * - Maturation: 4 ticks (slowest to mature)
 * - Death: 7 ticks (longest lifespan)
 * - Value: $5 when mature (highest value)
 * - Net profit: $2 per harvest (best profit)
 * 
 */
public class Corn extends Food {
    
    // Track total corn created during the game
    public static int generationCount = 0;
    
    // Cost to plant this crop 
    public static final int COST = GameConstants.CORN_COST;
    
    /**
     * Creates a new Corn crop.
     * Uses GameConstants for all properties to maintain single source of truth.
     * Increments generation counter.
     */
    public Corn() {
        super(GameConstants.CORN_MATURATION,  // Maturation age from constants
              GameConstants.CORN_DEATH,        // Death age from constants
              GameConstants.CORN_VALUE);       // Value from constants
        generationCount++;
    }
    
    /**
     * Gets the total number of corn created during the game.
     * 
     * @return Total corn generation count
     */
    public static int getGenerationCount() {
        return generationCount;
    }
    
    /**
     * Returns the visual representation of corn.
     * Shows 'o' when immature, 'O' when mature (harvestable).
     * Uses 'o' because it visually resembles corn kernels.
     * 
     * @return "o" if immature, "O" if mature
     */
    @Override
    public String toString() {
        if (getAge() < getMaturationAge()) {
            return "o"; // Immature corn
        } else {
            return "O"; // Mature corn (ready to harvest)
        }
    }
}