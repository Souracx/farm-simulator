package util;

/**
 * Central repository for all game constants.
 * Improves maintainability and makes game balancing easier.
 */
public class GameConstants {
    
    // Economic Constants
    public static final int FERTILISER_COST = 2;
    public static final int STARTING_FUNDS = 10;
    
    // Probability Constants
    public static final double WEED_SPAWN_CHANCE = 0.2;
    
    // Crop Costs
    public static final int APPLE_COST = 2;
    public static final int GRAIN_COST = 1;
    public static final int CARROT_COST = 1;
    public static final int CORN_COST = 3;
    
    // Crop Properties (maturation, death, value)
    public static final int APPLE_MATURATION = 3;
    public static final int APPLE_DEATH = 5;
    public static final int APPLE_VALUE = 3;
    
    public static final int GRAIN_MATURATION = 2;
    public static final int GRAIN_DEATH = 6;
    public static final int GRAIN_VALUE = 2;
    
    public static final int CARROT_MATURATION = 2;
    public static final int CARROT_DEATH = 5;
    public static final int CARROT_VALUE = 2;
    
    public static final int CORN_MATURATION = 4;
    public static final int CORN_DEATH = 7;
    public static final int CORN_VALUE = 5;
    
    // Season Multipliers
    public static final double SPRING_GROWTH_BONUS = 1.0;
    public static final double SUMMER_GROWTH_BONUS = 1.0;
    public static final double FALL_GROWTH_BONUS = 0.8;
    public static final double WINTER_GROWTH_BONUS = 0.5;
    
    // Game Settings
    public static final int TICKS_PER_SEASON = 20;
    public static final int MAX_FIELD_SIZE = 10;
    
    private GameConstants() {
        // Prevent instantiation
    }
}