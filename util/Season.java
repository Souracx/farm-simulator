package util;

/**
 * Represents the four seasons in the game.
 * Each season affects crop growth rates differently.
 */
public enum Season {
    SPRING("Spring", 1.0, "\u001B[32m"), // Green
    SUMMER("Summer", 1.0, "\u001B[33m"), // Yellow
    FALL("Fall", 0.8, "\u001B[31m"),       // Red
    WINTER("Winter", 0.5, "\u001B[36m"); // Cyan
    
    private final String name;
    private final double growthMultiplier;
    private final String colorCode;
    
    Season(String name, double growthMultiplier, String colorCode) {
        this.name = name;
        this.growthMultiplier = growthMultiplier;
        this.colorCode = colorCode;
    }
    
    /**
     * @return The display name of the season
     */
    public String getName() {
        return name;
    }
    
    /**
     * @return Growth rate multiplier for this season (1.0 = normal, 0.5 = half speed)
     */
    public double getGrowthMultiplier() {
        return growthMultiplier;
    }
    
    /**
     * @return ANSI color code for visual representation
     */
    public String getColorCode() {
        return colorCode;
    }
    
    /**
     * Get the next season in the cycle
     * @return The next season
     */
    public Season next() {
        Season[] seasons = values();
        return seasons[(this.ordinal() + 1) % seasons.length];
    }
    
    /**
     * Get colored season name for console output
     * @return Colored season name string
     */
    public String getColoredName() {
        return colorCode + name + "\u001B[0m";
    }
}