# Project File Structure

```
src/main/java/com/shadowedhunter/
│
├── Application.java                          # Main entry point
│
├── core/                                     # Core game engine
│   ├── GameEngine.java                       # Main game coordinator
│   ├── GameState.java                        # Central game state
│   └── GameStats.java                        # Score, time, deaths tracking
│
├── entity/                                   # Game entities
│   ├── Player.java                           # Player state and actions
│   └── Entity.java                           # Base entity class (if needed)
│
├── world/                                    # World/map system
│   ├── World.java                            # Manages all floors
│   ├── Floor.java                            # Single floor/level
│   ├── Tile.java                             # Abstract tile base class
│   ├── TileType.java                         # Enum of all tile types
│   ├── TileFactory.java                      # Creates tiles from codes
│   │
│   ├── tiles/                                # Concrete tile implementations
│   │   ├── EmptyTile.java
│   │   ├── WallTile.java
│   │   ├── DoorTile.java
│   │   ├── LockedDoorTile.java
│   │   ├── SecretDoorTile.java
│   │   ├── TrapTile.java
│   │   ├── ItemTile.java
│   │   ├── StairsTile.java
│   │   └── LoreTile.java
│   │
│   └── traps/                                # Trap system
│       ├── TrapType.java                     # Enum of trap types
│       ├── Trap.java                         # Abstract trap base
│       └── traps/
│           ├── PoisonTrap.java
│           ├── SpikeTrap.java
│           ├── FallingTrap.java
│           ├── ProjectileTrap.java
│           ├── WallTrap.java
│           ├── BladeTrap.java
│           ├── HoleTrap.java
│           └── PortcullisTrap.java
│
├── input/                                    # Input handling (Command pattern)
│   ├── InputHandler.java                     # Processes player input
│   ├── Command.java                          # Command interface
│   │
│   └── commands/                             # All command implementations
│       ├── movement/
│       │   ├── MoveCommand.java
│       │   ├── JumpCommand.java
│       │   └── ClimbStairsCommand.java
│       │
│       ├── interaction/
│       │   ├── OpenDoorCommand.java
│       │   ├── UnlockDoorCommand.java
│       │   ├── PickupCommand.java
│       │   └── LookCommand.java
│       │
│       ├── inventory/
│       │   ├── UsePotionCommand.java
│       │   └── StabSelfCommand.java
│       │
│       └── system/
│           ├── IntroCommand.java
│           ├── HelpCommand.java
│           ├── ExitCommand.java
│           └── SaveCommand.java
│
├── inventory/                                # Inventory system
│   ├── Inventory.java                        # Inventory container
│   ├── ItemType.java                         # Enum of all items
│   └── Item.java                             # Item base class (if needed)
│
├── ui/                                       # User interface
│   ├── GameWindow.java                       # Main game window
│   ├── GamePanel.java                        # Game rendering panel
│   ├── MainMenu.java                         # Main menu screen
│   ├── MessageSystem.java                    # Centralized messaging
│   ├── MessageDisplay.java                   # Message display interface
│   │
│   ├── components/                           # UI components
│   │   ├── HealthBar.java
│   │   ├── InventoryPanel.java
│   │   ├── MapRenderer.java
│   │   ├── TimeCounter.java
│   │   └── FogOfWar.java
│   │
│   └── dialogs/
│       └── CheatSheetDialog.java
│
├── audio/                                    # Audio system
│   ├── AudioManager.java                     # Manages all audio
│   ├── AudioClip.java                        # Single audio clip wrapper
│   └── MusicPlayer.java                      # Background music player
│
├── persistence/                              # Save/load system
│   ├── SaveSystem.java                       # Save/load operations
│   ├── GameSaveData.java                     # Serializable save data
│   └── SaveLoadException.java                # Custom exception
│
├── resources/                                # Text resources
│   ├── TextResources.java                    # Text resource manager
│   ├── LoreTexts.java                        # Story/lore texts
│   ├── TrapMessages.java                     # Trap descriptions
│   └── DialogTexts.java                      # Dialog/intro texts
│
└── util/                                     # Utility classes
    ├── Position.java                         # Immutable position
    ├── Direction.java                        # Direction enum
    ├── Constants.java                        # Game constants
    ├── FontLoader.java                       # Custom font loading
    └── ResourceLoader.java                   # Generic resource loader

src/main/resources/                           # Resource files
│
├── maps/                                     # Map CSV files
│   ├── floor0.csv
│   ├── floor1.csv
│   ├── floor2.csv
│   ├── floor3.csv
│   ├── playerFloor1.png
│   ├── playerFloor2.png
│   ├── playerFloor3.png
│   └── playerFloor4.png
│
├── audio/                                    # Audio files
│   ├── ThemeMusic.mp3
│   └── VoidBgMusic.mp3
│
├── images/                                   # Image resources
│   ├── icon.png
│   ├── BackgroundGame.png
│   ├── CheatSheet.png
│   ├── ground.jpg
│   ├── Title.png
│   │
│   └── inventoryItems/
│       ├── key.png
│       ├── healthPotion.png
│       ├── Sword.png
│       └── Shield.png
│
├── fonts/                                    # Font files
│   └── Dungeon.TTF
│
└── text/                                     # Text resource files
    ├── intro.txt
    ├── lore.properties
    └── traps.properties

Save/                                         # Save game directory
└── Save.txt (or .json)

build.gradle (or pom.xml)                     # Build configuration
README.md                                     # Project documentation
.gitignore                                    # Git ignore file
```

---

## **Package Breakdown by Responsibility**

### **`core/`** - Game Engine Core
- Central coordination
- Game state management
- High-level game loop

### **`entity/`** - Game Entities
- Player
- Future: Enemies, NPCs

### **`world/`** - World/Map System
- Floor management
- Tile system
- Trap mechanics

### **`input/`** - Command Processing
- Input parsing
- Command pattern implementation
- All player actions

### **`inventory/`** - Inventory Management
- Item storage
- Item usage
- Item definitions

### **`ui/`** - User Interface
- Swing components
- Rendering
- Visual feedback

### **`audio/`** - Sound System
- Music playback
- Sound effects (future)

### **`persistence/`** - Save/Load
- Game serialization
- File I/O

### **`resources/`** - Text/Data
- Story text
- Messages
- Descriptions

### **`util/`** - Utilities
- Helper classes
- Common data structures
- Constants

---

## **Key Differences from Original Structure**

### **Before:**
```
com.ShadowedHunter/
├── GameLauncher.java      (Does everything: audio, fonts, main)
├── GameMap.java           (Map + movement + validation)
├── MainGameWindow.java    (UI + rendering + game state)
├── MainMenu.java          (Menu)
├── Player.java            (Just position and inventory)
├── Position.java          (Position)
├── SaveLoadSystem.java    (Save/load)
└── Script.java            (ALL GAME LOGIC - God class)
```

### **After:**
- **7 files** → **50+ files** (more files, but each is simple)
- **God classes eliminated** → Each class has one clear job
- **Better organization** → Easy to find and modify code
- **Testable** → Each component can be unit tested
- **Extensible** → Add features without changing existing code

---

## **Benefits of New Structure**

✅ **Find code faster** - Need to add a new command? Go to `commands/`
✅ **Less merge conflicts** - Team members work on different files
✅ **Easier testing** - Test `MoveCommand` without loading UI
✅ **Clear dependencies** - Can see what depends on what
✅ **Reusable components** - `Inventory` can be used in different games
✅ **Onboarding** - New developers understand structure immediately

---

## **File Count by Package**

| Package | Files | Purpose |
|---------|-------|---------|
| `core/` | 3 | Game engine foundation |
| `entity/` | 2 | Player and entities |
| `world/` | 18 | Maps, tiles, traps |
| `input/` | 15 | Commands and input |
| `inventory/` | 3 | Item management |
| `ui/` | 10 | User interface |
| `audio/` | 3 | Sound system |
| `persistence/` | 3 | Save/load |
| `resources/` | 4 | Text resources |
| `util/` | 6 | Utilities |
| **Total** | **67** | *vs 8 original* |

More files, but **each file is 50-200 lines** instead of 500-1000 lines!