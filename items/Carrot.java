package items;

import util.GameConstants;

/**
 * Represents a Carrot crop.
 * Low-cost, fast-growing crop - great for quick profits.
 * 
 * Properties defined in GameConstants:
 * - Cost: $1
 * - Maturation: 2 ticks (very fast)
 * - Death: 5 ticks (moderate lifespan)
 * - Value: $2 when mature
 * - Net profit: $1 per harvest
 * 
 * 

 */
public class Carrot extends Food {
    
    // Track total carrots created during the game
    public static int generationCount = 0;
    
    // Cost to plant this crop 
    public static final int COST = GameConstants.CARROT_COST;
    
    /**
     * Creates a new Carrot crop.
     * Uses GameConstants for all properties to maintain single source of truth.
     * Increments generation counter.
     */
    public Carrot() {
        super(GameConstants.CARROT_MATURATION,  // Maturation age from constants
              GameConstants.CARROT_DEATH,        // Death age from constants
              GameConstants.CARROT_VALUE);       // Value from constants
        generationCount++;
    }
    
    /**
     * Gets the total number of carrots created during the game.
     * 
     * @return Total carrot generation count
     */
    public static int getGenerationCount() {
        return generationCount;
    }
    
    /**
     * Returns the visual representation of carrot.
     * Shows 'c' when immature, 'C' when mature (harvestable).
     * 
     * @return "c" if immature, "C" if mature
     */
    @Override
    public String toString() {
        if (getAge() < getMaturationAge()) {
            return "c"; // Immature carrot
        } else {
            return "C"; // Mature carrot (ready to harvest)
        }
    }
}