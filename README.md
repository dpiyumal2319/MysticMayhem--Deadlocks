# Mystic Mayhem

Mystic Mayhem is a terminal-based deterministic strategy game implemented in Java. It highlights core object-oriented programming (OOP) concepts through engaging gameplay. Two players manage armies with unique abilities and stats, competing in a turn-based combat system.

## Project Guide

- [Click here](/Guide/UPDATED%20OOP%20Project_%20Mystic%20Mayhem.pdf) to view the project guide.

## Features

- **Player Profiles**: Create profiles with a unique username and customizable name.
- **Resource Management**: Earn and spend gold coins for army customization.
- **Army Customization**: Choose from characters like Archers, Knights, Mages, Healers, and Mythical Creatures.
- **Equipment**: Equip characters with artifacts and armor to enhance abilities.
- **Dynamic Gameplay**: Battles are influenced by player choices, character stats, and home ground advantages.
- **Deterministic Logic**: Outcomes are based on predefined rules, ensuring fair and strategic gameplay.

## OOP Concepts

Mystic Mayhem demonstrates the following OOP principles:
- **Encapsulation**: Players, characters, and equipment are implemented as classes with private attributes and public methods.
- **Inheritance**: Characters and equipment derive from base classes.
- **Polymorphism**: Custom behavior for different character and ground types.
- **Abstraction**: Simplified interface for players to interact with game mechanics.
- **Serialization**: Player profiles are saved and loaded using Java serialization.

## Data Structures

- **ArrayList**: Used to store player profiles, characters, and equipment.
- **HashMap**: Maps character types to their respective stats and abilities.
- **Enum**: Defines character types, equipment types, and ground types.

## How to Run

1. Clone the repository or download the project files.
2. Navigate to the project directory.
3. Compile the main file:
   ```sh
   javac MysticMayhemJava/src/App.java
   ```
4. Run the game:
   ```sh
   java MysticMayhemJava/src/App.java
   ```

## Gameplay Overview

Players create profiles, customize armies, and engage in battles. Each player takes turns based on the speed of their characters. The objective is to strategically manage attacks, defenses, and equipment to win battles and earn rewards.

## Screenshots

**Main Menu**:
![Main Menu](/Screenshots/Screenshot%202024-12-11%20193425.png)

**Shop**:
![Shop](/Screenshots/Screenshot%202024-12-11%20193249.png)

**Battle**:
![Battle](/Screenshots/Screenshot%202024-12-11%20194456.png)

**Game Over**:
![Game Over](/Screenshots/Screenshot%202024-12-11%20194531.png)

## Contributing

Contributions are welcome! Feel free to fork this repository and submit pull requests.

