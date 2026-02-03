package game;

import java.io.*;
import java.util.*;

import items.*;
import util.*;

/**
 * Farm simulation with seasonal gameplay, statistics tracking,
 * and save/load functionality.
 */
public class FarmSimulator {
    
    private int balance;		//current money
    private FieldGrid field;	//game field grid
    private Season currentSeason;	//Current season (Spring/Summer/Fall/Winter)
    private int ticksUntilNextSeason;	// Countdown to next season change
    private GameStats stats;	// Statistics tracker
    private Scanner scanner;	// For reading user input
    
    /**
     * Creates a new FarmSimulator with specified dimensions and starting funds.
     * 
     * @param fieldWidth Width of the field
     * @param fieldHeight Height of the field
     * @param startingFunds Initial money available to the player
     */
    public FarmSimulator(int fieldWidth, int fieldHeight, int startingFunds) {
        this.balance = startingFunds;
        this.field = new FieldGrid(fieldHeight, fieldWidth);
        this.currentSeason = Season.SPRING;
        this.ticksUntilNextSeason = GameConstants.TICKS_PER_SEASON;
        this.stats = new GameStats();
        this.scanner = new Scanner(System.in);
    }
    
    /**
     * Main game loop - displays menu, processes commands, and updates game state.
     * Continues until player types "q" 
     */
    public void run() {
        displayWelcome();
        displayGameState();
        
        String input = "";
        
        //continue until q is typed 
        while (!input.equals("q")) {
            input = scanner.nextLine().trim();
            
            //skip empty lines 
            if (input.isEmpty()) continue;
            
            processCommand(input);	//process command
            
            if (!input.equals("q")) {
                displayGameState();
            }
        }
        
        displayGoodbye();
    }
    
    /**
     * Processes a single command from the user.
     * 
     * @param input The command string entered by the user
     */
    private void processCommand(String input) {
        String[] tokens = input.split("\\s+");
        String command = tokens[0].toLowerCase();
        
        try {
            switch (command) {
                case "t":
                    handleTill(tokens);
                    break;
                case "p":
                    handlePlant(tokens);
                    break;
                case "h":
                    handleHarvest(tokens);
                    break;
                case "f":
                    handleFertilise(tokens);
                    break;
                case "s":
                    handleSummary();
                    break;
                case "w":
                    handleWait();
                    break;
                case "stats":
                    handleStats();
                    break;
                case "save":
                    handleSave(tokens);
                    break;
                case "load":
                    handleLoad(tokens);
                    break;
                case "help":
                    displayHelp();
                    break;
                case "q":
                    // Quit handled in main loop
                    break;
                default:
                    System.out.println("Invalid command. Type 'help' for available commands.");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        	// Handle invalid command format (missing x or y coordinates)
            System.out.println("Invalid command format. Type 'help' for usage instructions.");
        }
    }
    
    //Till command - converts plot to soil
    //Format: t x y
    private void handleTill(String[] tokens) throws ArrayIndexOutOfBoundsException {
        int x = Integer.parseInt(tokens[1]) - 1;
        int y = Integer.parseInt(tokens[2]) - 1;
        
        field.till(x, y);
        stats.incrementFieldsTilled();
        System.out.println("Tilled field at (" + (x+1) + ", " + (y+1) + ")");
    }
    
    
      //plant command - plants a crop at specified location.
    private void handlePlant(String[] tokens) throws ArrayIndexOutOfBoundsException {
        int x = Integer.parseInt(tokens[1]) - 1;
        int y = Integer.parseInt(tokens[2]) - 1;
        
        // Show crop selection menu
        displayPlantMenu();
        String choice = scanner.nextLine().toLowerCase();
        
        // Create the selected crop and get its cost
        Food crop = createCrop(choice);
        int cost = getCropCost(choice);
        
        // Validate crop selection
        if (crop == null) {
            System.out.println("Invalid crop selection.");
            return;
        }
        
        // Check if player has enough money
        if (balance < cost) {
            System.out.println("Insufficient funds! You need $" + cost + " but only have $" + balance);
            return;
        }
        
        // Plant the crop
        field.plant(x, y, crop);
        balance -= cost;
        stats.addMoneySpent(cost);
        stats.incrementCropsPlanted();
        stats.updateHighestBalance(balance);
        
        System.out.println("Planted " + crop.getClass().getSimpleName() + " for $" + cost);
    }
    
    //harvest - handles 
    private void handleHarvest(String[] tokens) throws ArrayIndexOutOfBoundsException {
        int x = Integer.parseInt(tokens[1]) - 1;
        int y = Integer.parseInt(tokens[2]) - 1;
        
        Item item = field.get(x, y);
        
        if (!(item instanceof Food)) {
            System.out.println("There's no crop to harvest here.");
            return;
        }
        
        int value = item.getValue();
        
        if (value <= 0) {
            System.out.println("This crop is not mature yet.");
            return;
        }
        
        balance += value;
        stats.addMoneyEarned(value);
        stats.incrementCropsHarvested();
        stats.updateHighestBalance(balance);
        field.plant(x, y, new UntilledSoil());
        
        System.out.println("Harvested " + item.getClass().getSimpleName() + " for $" + value + "!");
    }
    
    
    // Handles the fertilise command - instantly matures a crop.
    //cost $
    private void handleFertilise(String[] tokens) throws ArrayIndexOutOfBoundsException {
        int x = Integer.parseInt(tokens[1]) - 1;
        int y = Integer.parseInt(tokens[2]) - 1;
        
        Item item = field.get(x, y);
        
        if (!(item instanceof Food)) {
            System.out.println("Only crops can be fertilised.");
            return;
        }
        
        if (balance < GameConstants.FERTILISER_COST) {
            System.out.println("Insufficient funds! Fertiliser costs $" + GameConstants.FERTILISER_COST);
            return;
        }
        
        ((Food) item).fertiliser();
        balance -= GameConstants.FERTILISER_COST;
        stats.addMoneySpent(GameConstants.FERTILISER_COST);
        stats.incrementFertilisersUsed();
        
        System.out.println("Fertilised crop at (" + (x+1) + ", " + (y+1) + ")");
    }
    
  
     // Handles the summary command - displays field summary.
  
    private void handleSummary() {
        System.out.println(field.getSummary());
    }
    
    
    // Handles the wait command which advances time by one tick.
    
    private void handleWait() {
        advanceTime();
        System.out.println("Time passes...");
    }
    
   
    //Handles the stats command which displays game statistics.
    
    private void handleStats() {
        System.out.println(stats);
    }
    
   
    // Handles the save command - saves game to a file.

    private void handleSave(String[] tokens) {
        String filename = tokens.length > 1 ? tokens[1] : "savegame.txt";
        
        try (PrintWriter writer = new PrintWriter(new FileWriter("saves/" + filename))) {
            writer.println(balance);
            writer.println(currentSeason.ordinal());
            writer.println(ticksUntilNextSeason);
            // Stats
            writer.println(stats.getTotalCropsPlanted());
            writer.println(stats.getTotalCropsHarvested());
            writer.println(stats.getTotalMoneyEarned());
            writer.println(stats.getTotalMoneySpent());
            writer.println(stats.getTicksPlayed());
            writer.println(stats.getHighestBalance());
            writer.println(stats.getFieldsTilled());
            writer.println(stats.getFertilisersUsed());
            
            System.out.println("Game saved to saves/" + filename);
        } catch (IOException e) {
            System.out.println("Error saving game: " + e.getMessage());
        }
    }
    
    
    //Handles the load command - loads game from a file.
    private void handleLoad(String[] tokens) {
        String filename = tokens.length > 1 ? tokens[1] : "savegame.txt";
        
        try (BufferedReader reader = new BufferedReader(new FileReader("saves/" + filename))) {
            balance = Integer.parseInt(reader.readLine());
            currentSeason = Season.values()[Integer.parseInt(reader.readLine())];
            ticksUntilNextSeason = Integer.parseInt(reader.readLine());
            // Note: Field state not saved in this simple version
            // Stats reconstruction would go here
            
            System.out.println("Game loaded from saves/" + filename);
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error loading game: " + e.getMessage());
        }
    }
    

    // Advances game time by one tick - ages crops and handles season change

    private void advanceTime() {
        field.tick();
        stats.incrementTicksPlayed();
        
        ticksUntilNextSeason--;
        if (ticksUntilNextSeason <= 0) {
            currentSeason = currentSeason.next();
            ticksUntilNextSeason = GameConstants.TICKS_PER_SEASON;
            System.out.println("\n Season changed to " + currentSeason.getColoredName() + "!");
        }
    }
    
    /**
     * Creates a crop based on user choice.
     */
    private Food createCrop(String choice) {
        switch (choice) {
            case "a": return new Apples();
            case "g": return new Grain();
            case "c": return new Carrot();
            case "o": return new Corn();
            default: return null;
        }
    }
    
    /**
     * Gets the cost of a crop based on user choice.
     */
    private int getCropCost(String choice) {
        switch (choice) {
            case "a": return Apples.COST;
            case "g": return Grain.COST;
            case "c": return Carrot.COST;
            case "o": return Corn.COST;
            default: return 0;
        }
    }
    
  
    // Displays the current game state.
    
    private void displayGameState() {
        System.out.println("\n" + field);
        System.out.println(" Balance: $" + balance + " | " + 
                         currentSeason.getColoredName() + 
                         " (Ticks remaining: " + ticksUntilNextSeason + ")");
        displayMenu();
    }
    
   
    // Displays the main menu.
    private void displayMenu() {
        System.out.println("\nCommands: [t]ill [p]lant [h]arvest [f]ertilise [s]ummary [w]ait [stats] [save] [load] [help] [q]uit");
        System.out.print("> ");
    }
    
   
     //Displays the plant menu.
    private void displayPlantMenu() {
        System.out.println("\nChoose a crop:");
        System.out.println("  [a] Apples  - $" + Apples.COST + " (matures in 3, dies at 5, worth $3)");
        System.out.println("  [g] Grain   - $" + Grain.COST + " (matures in 2, dies at 6, worth $2)");
        System.out.println("  [c] Carrot  - $" + Carrot.COST + " (matures in 2, dies at 5, worth $2)");
        System.out.println("  [o] Corn    - $" + Corn.COST + " (matures in 4, dies at 7, worth $5)");
        System.out.print("> ");
    }
    
   
    // Displays help information.
    private void displayHelp() {
        System.out.println("\n=== FARM SIMULATOR HELP ===");
        System.out.println("Commands:");
        System.out.println("  t x y       - Till the soil at position (x, y)");
        System.out.println("  p x y       - Plant a crop at position (x, y)");
        System.out.println("  h x y       - Harvest crop at position (x, y)");
        System.out.println("  f x y       - Fertilise crop at position (x, y) [$2]");
        System.out.println("  s           - Show field summary");
        System.out.println("  w           - Wait (advance time 1 tick)");
        System.out.println("  stats       - Show game statistics and achievements");
        System.out.println("  save [name] - Save game (default: savegame.txt)");
        System.out.println("  load [name] - Load game (default: savegame.txt)");
        System.out.println("  help        - Show this help message");
        System.out.println("  q           - Quit game");
        System.out.println("\nSymbols:");
        System.out.println("  . = Tilled soil    / = Untilled soil    # = Weed");
        System.out.println("  a/A = Apples (immature/mature)");
        System.out.println("  g/G = Grain  (immature/mature)");
        System.out.println("  c/C = Carrot (immature/mature)");
        System.out.println("  o/O = Corn   (immature/mature)");
    }
    
 
    // Displays welcome message.

    private void displayWelcome() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println(" FARM SIMULATOR ");
        System.out.println("=".repeat(50));
        System.out.println("Welcome! Build your farming empire!");
        System.out.println("Type 'help' for commands.\n");
    }
    

    //displays goodbye message with final statistics.
  
    private void displayGoodbye() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("Thanks for playing! Final Report:");
        System.out.println("=".repeat(50));
        System.out.println(stats);
        System.out.println("\nFinal Balance: $" + balance);
        System.out.println("\nSee you next harvest!");
    }
}