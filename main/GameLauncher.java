package main;

import game.FarmSimulator;

/**
 * Entry point for Farm Simulator.
 * Launches the game with default settings.
 */
public class GameLauncher {
    
    public static void main(String[] args) {
        // Launch the farm simulator
        FarmSimulator simulator = new FarmSimulator(10, 5, 10);
        simulator.run();
    }
}