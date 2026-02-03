# Farm Simulator

A Java-based farming simulation project designed to demonstrate strong **Object-Oriented Programming (OOP)** principles and simulation-based system design.

The game models crops, soil, seasons, and economy using a **tick-based simulation**, where the farm evolves over time rather than relying on scripted logic.

---

## Features
- Multiple crop types (Carrot, Grain, Apple, Corn)
- Seasonal system (Spring, Summer, Fall, Winter)
- Grid-based farm world
- Tick-based crop growth and aging
- Economy with planting costs and harvest profit
- Gameplay statistics tracking
- Save and load game state
- Console-based gameplay (extensible to GUI)

---

## Core Concepts Used
- Object-Oriented Design (OOP)
- Abstraction and Inheritance
- Polymorphism
- State-driven simulation
- Separation of concerns
- Configuration-driven design
- File I/O (persistence)

---

## Installation & Environment Setup

### Requirements
- Java Development Kit (JDK) 17 or later
- Java compiler (`javac`)

Verify Java installation:
```bash
java --version
javac --version
```
### Installation

Clone the repository:
```bash
git clone https://github.com/Souracx/farm-simulator.git
cd farm-simulator
```
### Build 

Compile the project:
```bash
javac main/GameLauncher.java
```
### Run

Start the game:
```bash
java main.GameLauncher
```
