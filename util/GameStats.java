package util;

/**
 * Tracks game statistics and achievements for player progress.
 * Provides insights into farming efficiency and milestones reached.
 */
public class GameStats {
    
    private int totalCropsPlanted;
    private int totalCropsHarvested;
    private int totalMoneyEarned;
    private int totalMoneySpent;
    private int ticksPlayed;
    private int highestBalance;
    private int fieldsTilled;
    private int fertilisersUsed;
    
    /**
     * Creates a new GameStats tracker with all values at zero.
     */
    public GameStats() {
        this.totalCropsPlanted = 0;
        this.totalCropsHarvested = 0;
        this.totalMoneyEarned = 0;
        this.totalMoneySpent = 0;
        this.ticksPlayed = 0;
        this.highestBalance = 0;
        this.fieldsTilled = 0;
        this.fertilisersUsed = 0;
    }
    
    // Increment methods
    public void incrementCropsPlanted() { totalCropsPlanted++; }
    public void incrementCropsHarvested() { totalCropsHarvested++; }
    public void incrementFieldsTilled() { fieldsTilled++; }
    public void incrementFertilisersUsed() { fertilisersUsed++; }
    public void incrementTicksPlayed() { ticksPlayed++; }
    
    public void addMoneyEarned(int amount) { totalMoneyEarned += amount; }
    public void addMoneySpent(int amount) { totalMoneySpent += amount; }
    
    public void updateHighestBalance(int currentBalance) {
        if (currentBalance > highestBalance) {
            highestBalance = currentBalance;
        }
    }
    
    // Getters
    public int getTotalCropsPlanted() { return totalCropsPlanted; }
    public int getTotalCropsHarvested() { return totalCropsHarvested; }
    public int getTotalMoneyEarned() { return totalMoneyEarned; }
    public int getTotalMoneySpent() { return totalMoneySpent; }
    public int getTicksPlayed() { return ticksPlayed; }
    public int getHighestBalance() { return highestBalance; }
    public int getFieldsTilled() { return fieldsTilled; }
    public int getFertilisersUsed() { return fertilisersUsed; }
    
    /**
     * Calculate farming efficiency (harvested / planted ratio)
     * @return Efficiency percentage (0-100)
     */
    public double getFarmingEfficiency() {
        if (totalCropsPlanted == 0) return 0.0;
        return (totalCropsHarvested * 100.0) / totalCropsPlanted;
    }
    
    /**
     * Calculate net profit
     * @return Total money earned minus total money spent
     */
    public int getNetProfit() {
        return totalMoneyEarned - totalMoneySpent;
    }
    
    /**
     * Check if player has reached milestones
     * @return Array of achievement strings
     */
    public String[] getAchievements() {
        java.util.ArrayList<String> achievements = new java.util.ArrayList<>();
        
        if (totalCropsHarvested >= 10) achievements.add("🌾 Novice Farmer (10 crops harvested)");
        if (totalCropsHarvested >= 50) achievements.add("🌾 Experienced Farmer (50 crops harvested)");
        if (totalCropsHarvested >= 100) achievements.add("🌾 Master Farmer (100 crops harvested)");
        
        if (highestBalance >= 50) achievements.add("💰 Wealthy (Balance reached $50)");
        if (highestBalance >= 100) achievements.add("💰 Rich (Balance reached $100)");
        
        if (getFarmingEfficiency() >= 80) achievements.add("⭐ Efficient Farmer (80%+ efficiency)");
        
        if (ticksPlayed >= 50) achievements.add("⏰ Dedicated (50 ticks played)");
        
        return achievements.toArray(new String[0]);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n=== GAME STATISTICS ===\n");
        sb.append("Crops Planted:    ").append(totalCropsPlanted).append("\n");
        sb.append("Crops Harvested:  ").append(totalCropsHarvested).append("\n");
        sb.append("Farming Efficiency: ").append(String.format("%.1f", getFarmingEfficiency())).append("%\n");
        sb.append("Money Earned:     $").append(totalMoneyEarned).append("\n");
        sb.append("Money Spent:      $").append(totalMoneySpent).append("\n");
        sb.append("Net Profit:       $").append(getNetProfit()).append("\n");
        sb.append("Highest Balance:  $").append(highestBalance).append("\n");
        sb.append("Fields Tilled:    ").append(fieldsTilled).append("\n");
        sb.append("Fertilisers Used: ").append(fertilisersUsed).append("\n");
        sb.append("Ticks Played:     ").append(ticksPlayed).append("\n");
        
        String[] achievements = getAchievements();
        if (achievements.length > 0) {
            sb.append("\n=== ACHIEVEMENTS ===\n");
            for (String achievement : achievements) {
                sb.append(achievement).append("\n");
            }
        }
        
        return sb.toString();
    }
}