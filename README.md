# Java 2D Adventure Game

A modular, tile-based 2D adventure game framework built entirely in Java using Swing for graphical rendering. This project serves as both a basic game engine and a learning resource for Java-based game development, featuring map management, player control, save/load mechanisms, and scripted event handling. The architecture is designed for extensibility and customization.

---

## Table of Contents

* [Overview](#overview)
* [Features](#features)
* [Project Structure](#project-structure)
* [Installation](#installation)
* [Usage](#usage)
* [Extending the Game](#extending-the-game)
* [Contributing](#contributing)
* [License](#license)

---

## Overview

This game simulates a simple 2D adventure environment, where a player can move across a grid-based map, interact with tiles, and trigger basic scripted events. Designed as an educational project, it emphasizes modularity and simplicity, enabling easy modification and future enhancements.

Key components include:

* **Graphical User Interface (GUI):** Managed via Java Swing.
* **Tile Map System:** Handles grid-based game maps.
* **Player Navigation:** Controlled through key inputs.
* **Save/Load System:** Allows persistent game state saving.
* **Scripted Events:** Placeholder scripting for game logic extensions.

---

## Features

* **Grid-Based Tile Map:**

  * Rendered using Java Swing.
  * Supports dynamic map generation and tile state management.

* **Player System:**

  * Movement via keyboard inputs.
  * Position tracking using a coordinate system.

* **Persistent Save/Load:**

  * Save player state and map using file serialization.
  * Reload previous game state at startup.

* **Modular Scripting Support:**

  * Placeholder class for event scripting and interactive story elements.

* **Menu System:**

  * Basic menu navigation using buttons.
  * Launches gameplay window via the main menu.

* **Educational Focus:**

  * Designed for easy code exploration, understanding, and modification.

---

## Project Structure

| File                  | Description                                                       |
| --------------------- | ----------------------------------------------------------------- |
| `GameLauncher.java`   | Entry point; initializes main menu and game window.               |
| `MainMenu.java`       | Displays the start menu using Swing components.                   |
| `MainGameWindow.java` | Handles the main game window, player input, and rendering loop.   |
| `GameMap.java`        | Defines the map grid, rendering logic, and tile management.       |
| `Player.java`         | Manages player state, movement, and interaction logic.            |
| `Position.java`       | Simple utility class representing 2D coordinates.                 |
| `SaveLoadSystem.java` | Serializes and deserializes game state for saving/loading.        |
| `Script.java`         | Skeleton class for implementing scripted game events and actions. |

---

## Installation

### Prerequisites

* Java Development Kit (JDK) 17 or higher
* Command-line terminal

### Compile

```bash
javac *.java
```

### Run

```bash
java GameLauncher
```

Ensure all `.java` files are in the same directory when compiling.

---

## Usage

1. Launch the game using `GameLauncher`.
2. Navigate through the main menu to start the game.
3. Use keyboard arrow keys (or configured keys) to move the player around the tile map.
4. The game state auto-saves or can be manually saved (depending on future UI updates).
5. Upon restarting, the previous game state can be loaded automatically.

---

## Extending the Game

### Adding New Tiles or Maps

* Modify or extend `GameMap.java` to include new tile types, map layouts, or visual changes.

### Player Mechanics

* Extend `Player.java` to include new abilities, stats, or interaction mechanics.

### Event Scripting

* Implement event triggers and interactive story elements via `Script.java`.
* Example: triggering dialogues or tile-based traps.

### UI Enhancements

* Refine the graphical interface by editing `MainGameWindow.java` and `MainMenu.java`.
* Replace placeholder buttons with styled components.

### Saving Enhancements

* Introduce JSON/XML save formats using libraries like Jackson or Gson.
* Enhance error handling in `SaveLoadSystem.java`.

---

## Contributing

Contributions are welcome! To contribute:

1. Fork the repository.
2. Create a new feature branch.
3. Commit your changes.
4. Submit a pull request describing your modifications.

Please keep code readable and modular for educational clarity.

---

## License

This project is released under the MIT License. Feel free to use, modify, and distribute as per the license terms.

---

## Notes

This project is intended for educational purposes and prototyping. For professional game development, consider using specialized frameworks or engines like LibGDX or Unity.

---
