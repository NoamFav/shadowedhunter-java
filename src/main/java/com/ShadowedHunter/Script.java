package com.ShadowedHunter;

import java.util.*;

public class Script {
    public static int health = 100;
    public static MainGameWindow gameWindow = MainGameWindow.getInstance();
    public static GameMap gameMap = new GameMap();
    private static int counter = 0;

    // Move text to external resource files
    private static final TextResources texts = new TextResources();

    public static TextResources getTexts() {
        return texts;
    }

    public static void setGameWindow(MainGameWindow window) {
        gameWindow = window;
    }

    public static void updateHealth(int newHealth) {
        health = newHealth;
        gameWindow.repaint();
    }

    public static void kill() {
        updateHealth(100);
        MainGameWindow.setIconPosition(429, 301);
        MainGameWindow.panel.incrementLifeNumber();
        gameMap.switchMap(0);
        Map<String, Integer> savedInventory = gameMap.getPlayer().inventory;
        gameMap.initializePlayer(1, 17);
        gameMap.getPlayer().loadInventory(savedInventory);
    }

    public static void Action(String Action) {
        String action = Action.toLowerCase();
        Player player = gameMap.getPlayer();
        String[][] currentMap = gameMap.getCurrentMap();
        int x = player.getPosition().getX();
        int y = player.getPosition().getY();

        switch (action) {
            case "intro" -> showTimedMessages(texts.getIntroMessages());
            case "help" -> gameWindow.setOutputText("Showing cheatSheet for 10s");
            case "n" -> movePlayer(Direction.NORTH);
            case "e" -> movePlayer(Direction.EAST);
            case "w" -> movePlayer(Direction.WEST);
            case "s" -> movePlayer(Direction.SOUTH);
            case "jump north" -> jumpPlayer(Direction.NORTH);
            case "jump east" -> jumpPlayer(Direction.EAST);
            case "jump west" -> jumpPlayer(Direction.WEST);
            case "jump south" -> jumpPlayer(Direction.SOUTH);
            case "open northern door" -> openDoor(Direction.NORTH, x, y, currentMap);
            case "open eastern door" -> openDoor(Direction.EAST, x, y, currentMap);
            case "open western door" -> openDoor(Direction.WEST, x, y, currentMap);
            case "open southern door" -> openDoor(Direction.SOUTH, x, y, currentMap);
            case "unlock northern door" -> unlockDoor(Direction.NORTH, x, y, currentMap);
            case "unlock eastern door" -> unlockDoor(Direction.EAST, x, y, currentMap);
            case "unlock western door" -> unlockDoor(Direction.WEST, x, y, currentMap);
            case "unlock southern door" -> unlockDoor(Direction.SOUTH, x, y, currentMap);
            case "jump" -> gameWindow.setOutputText("Youpi!!!");
            case "look around", "l" -> lookAround(x, y, currentMap);
            case "stab myself" -> stabSelf();
            case "use potion" -> usePotion();
            case "kill myself" -> {
                gameWindow.setOutputText("You committed suicide");
                kill();
            }
            case "climb up the stairs" -> gameMap.stairsUp();
            case "climb down the stairs" -> gameMap.stairsDown();
            case "pick up item" -> pickItems();
            case "exit" -> exitGame();
            default -> gameWindow.setOutputText("Invalid input \n");
        }
    }

    private static void movePlayer(Direction dir) {
        gameWindow.setOutputText("Going " + dir.name().toLowerCase());
        dir.move(gameMap);
        traps();
        loreRoom();
    }

    private static void jumpPlayer(Direction dir) {
        gameWindow.setOutputText("Jumping " + dir.name().toLowerCase());
        dir.move(gameMap);
        dir.move(gameMap);
        traps();
        loreRoom();
    }

    private static void openDoor(Direction dir, int x, int y, String[][] map) {
        if (dir == Direction.WEST && x == 1 && y == 17) {
            gameWindow.setOutputText("You can't turn back, you have a mission to do.");
            return;
        }

        int[] pos = dir.getOffset(x, y);
        String cell = map[pos[1]][pos[0]];

        switch (cell) {
            case "d" -> {
                gameWindow.setOutputText("Opening the door");
                dir.open(gameMap);
                traps();
            }
            case "t" -> {
                if (trapDoor()) {
                    map[pos[1]][pos[0]] = "d";
                    dir.open(gameMap);
                    traps();
                }
            }
            case "s" -> {
                secretDoor();
                dir.open(gameMap);
            }
            case "l" -> gameWindow.setOutputText("The door is locked");
            default -> gameWindow.setOutputText("No door here");
        }
    }

    private static void unlockDoor(Direction dir, int x, int y, String[][] map) {
        int[] pos = dir.getOffset(x, y);
        if (map[pos[1]][pos[0]].equals("l") && gameMap.getPlayer().getItemCount("k") >= 1) {
            gameWindow.setOutputText("the " + dir.name().toLowerCase() + " door is now unlocked");
            gameMap.getPlayer().removeItem("k");
            map[pos[1]][pos[0]] = "d";
        }
    }

    private static void lookAround(int x, int y, String[][] map) {
        String[] directions = {"north", "south", "east", "west", "instance"};
        int[][] offsets = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}, {0, 0}};
        String[] data = new String[5];

        for (int i = 0; i < 5; i++) {
            int nx = x + offsets[i][0];
            int ny = y + offsets[i][1];

            if (ny < 0 || ny >= map.length || nx < 0 || nx >= map[0].length) {
                data[i] = "There is nothing";
            } else {
                data[i] = describeTile(map[ny][nx]);
            }
        }

        gameWindow.setOutputText(
                String.format(
                        "%s in the north. %s in the south. %s in the east. %s in the west. %s where"
                                + " you are.",
                        data[0], data[1], data[2], data[3], data[4]));
    }

    private static String describeTile(String tile) {
        return switch (tile) {
            case "x" -> "There is a wall";
            case "l", "t", "d" -> "There is a door";
            case "s" -> "There is a wall, a strange-looking wall";
            case "p" -> "There is a portcullis";
            case "a" -> "There is an archway";
            case "k" -> "There is a rusty key";
            case "he" -> "There is a health potion";
            case "stu" -> "There is a stair going up";
            case "std" -> "There is a stair going down";
            default -> "There is nothing";
        };
    }

    private static void stabSelf() {
        gameWindow.setOutputText("you stabbed yourself: -10HP");
        updateHealth(health - 10);
    }

    private static void usePotion() {
        if (gameMap.getPlayer().getItemCount("he") < 1) {
            gameWindow.setOutputText("You don't have any health potion");
        } else if (health == 100) {
            gameWindow.setOutputText("Your health is already maxed out.");
        } else {
            gameMap.getPlayer().removeItem("he");
            updateHealth(100);
            gameWindow.setOutputText("Using health potion");
        }
    }

    private static void exitGame() {
        gameWindow.setOutputText("Exiting\n");
        SaveLoadSystem.saveGame(GameMap.maps, gameMap.getPlayer(), "Save.txt");
        System.exit(0);
    }

    public static void pickItems() {
        Player player = gameMap.getPlayer();
        String[][] currentMap = gameMap.getCurrentMap();
        int x = player.getPosition().getX();
        int y = player.getPosition().getY();

        switch (currentMap[y][x]) {
            case "he" -> {
                player.pickUpItem("he");
                gameWindow.setOutputText(
                        "You picked up a potion of Health, when use it will restore all your life,"
                                + " use wisely.");
                currentMap[y][x] = "";
            }
            case "k" -> {
                player.pickUpItem("k");
                gameWindow.setOutputText(
                        "You picked up an old rusty key, it can be used to open locked door.");
                currentMap[y][x] = "";
            }
            default -> gameWindow.setOutputText("Nothing to pick up");
        }
    }

    public static void secretDoor() {
        gameWindow.setOutputText(texts.getRandomSecretDoorText());
    }

    public static boolean trapDoor() {
        Random rand = new Random();
        double chance = rand.nextDouble();

        if (chance <= 0.50 || health <= 50) {
            gameWindow.setOutputText(texts.getRandomTrapDoorDeathText());
            kill();
            return false;
        } else {
            gameWindow.setOutputText(texts.getRandomTrapDoorSurviveText());
            updateHealth(health - 50);
            return true;
        }
    }

    public static void traps() {
        Player player = gameMap.getPlayer();
        String[][] currentMap = gameMap.getCurrentMap();
        Random rand = new Random();
        int x = player.getPosition().getX();
        int y = player.getPosition().getY();
        String cell = currentMap[y][x];

        TrapType trap = TrapType.fromCode(cell);
        if (trap != null) {
            trap.trigger(rand, currentMap, x, y);
        }
    }

    public static void loreRoom() {
        Player player = gameMap.getPlayer();
        String[][] currentMap = gameMap.getCurrentMap();
        int x = player.getPosition().getX();
        int y = player.getPosition().getY();
        String cell = currentMap[y][x];

        if (cell.equals("301")) {
            showTimedMessages(texts.getFinalStageMessages());
            deleteLore(cell);
        } else {
            String loreText = texts.getLoreText(cell);
            if (loreText != null) {
                gameWindow.setOutputText(loreText);
                deleteLore(cell);
            }
        }
    }

    public static void deleteLore(String room) {
        String[][] currentMap = gameMap.getCurrentMap();
        for (int i = 1; i < currentMap.length; i++) {
            for (int j = 1; j < currentMap[i].length; j++) {
                if (currentMap[i][j].equals(room)) {
                    currentMap[i][j] = "";
                }
            }
        }
    }

    private static void showTimedMessages(String[] messages) {
        counter = 0;
        Timer timer = new Timer();
        timer.schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        if (counter < messages.length) {
                            gameWindow.setOutputText(messages[counter++]);
                        } else {
                            timer.cancel();
                        }
                    }
                },
                0,
                5000);
    }

    public static void action(String action) {
        Action(action);
    }
}

enum Direction {
    NORTH(0, -1),
    SOUTH(0, 1),
    EAST(1, 0),
    WEST(-1, 0);
    final int dx, dy;

    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    int[] getOffset(int x, int y) {
        return new int[] {x + dx, y + dy};
    }

    void move(GameMap map) {
        switch (this) {
            case NORTH -> map.movePlayerUp();
            case SOUTH -> map.movePlayerDown();
            case EAST -> map.movePlayerRight();
            case WEST -> map.movePlayerLeft();
        }
    }

    void open(GameMap map) {
        switch (this) {
            case NORTH -> map.openUp();
            case SOUTH -> map.openDown();
            case EAST -> map.openRight();
            case WEST -> map.openLeft();
        }
    }
}

enum TrapType {
    POISON("pi", 25, 0.25),
    SPIKE("sp", 75, 0.75),
    FALLING("fp", 40, 0.40),
    PROJECTILE("pa", 50, 0.50),
    WALL("sw", 90, 0.90),
    BLADE("wb", 100, 1.0),
    HOLE("h", 20, 0.0),
    PORTCULLIS("p", 0, 0.0);

    final String code;
    final int damage;
    final double deathChance;

    TrapType(String code, int damage, double deathChance) {
        this.code = code;
        this.damage = damage;
        this.deathChance = deathChance;
    }

    static TrapType fromCode(String code) {
        for (TrapType t : values()) {
            if (t.code.equals(code)) return t;
        }
        return null;
    }

    void trigger(Random rand, String[][] map, int x, int y) {
        double chance = rand.nextDouble();
        boolean dies =
                (this != HOLE && this != PORTCULLIS)
                        && (chance <= deathChance || Script.health <= damage);

        if (this == BLADE) dies = true;
        if (this == HOLE && Script.health <= 20) dies = true;

        Script.gameWindow.setOutputText(
                Script.getTexts()
                        .getTrapText(
                                TextResources.TrapType.valueOf(this.name()),
                                dies,
                                rand.nextInt(4)));

        if (dies) {
            Script.kill();
            if (this == HOLE) {
                Script.gameMap.switchMap(GameMap.currentMapIndex - 1);
                Script.traps();
            }
        } else if (damage > 0) {
            Script.updateHealth(Script.health - damage);
        }

        if (this != PORTCULLIS) map[y][x] = "";
    }
}
