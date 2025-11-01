package com.shadowedhunter.input;

import com.shadowedhunter.core.GameEngine;
import com.shadowedhunter.input.commands.interaction.*;
import com.shadowedhunter.input.commands.inventory.*;
import com.shadowedhunter.input.commands.movement.*;
import com.shadowedhunter.input.commands.system.*;

import java.util.HashMap;
import java.util.Map;

public class InputHandler {
    private final GameEngine engine;
    private final Map<String, Command> commands;

    public InputHandler(GameEngine engine) {
        this.engine = engine;
        this.commands = new HashMap<>();
        registerCommands();
    }

    private void registerCommands() {
        // Movement
        commands.put("n", new MoveCommand(engine, "north"));
        commands.put("s", new MoveCommand(engine, "south"));
        commands.put("e", new MoveCommand(engine, "east"));
        commands.put("w", new MoveCommand(engine, "west"));
        commands.put("jump north", new JumpCommand(engine, "north"));
        commands.put("jump south", new JumpCommand(engine, "south"));
        commands.put("jump east", new JumpCommand(engine, "east"));
        commands.put("jump west", new JumpCommand(engine, "west"));
        commands.put("climb up the stairs", new ClimbStairsCommand(engine, true));
        commands.put("climb down the stairs", new ClimbStairsCommand(engine, false));

        // Interaction
        commands.put("open northern door", new OpenDoorCommand(engine, "north"));
        commands.put("open southern door", new OpenDoorCommand(engine, "south"));
        commands.put("open eastern door", new OpenDoorCommand(engine, "east"));
        commands.put("open western door", new OpenDoorCommand(engine, "west"));
        commands.put("unlock northern door", new UnlockDoorCommand(engine, "north"));
        commands.put("unlock southern door", new UnlockDoorCommand(engine, "south"));
        commands.put("unlock eastern door", new UnlockDoorCommand(engine, "east"));
        commands.put("unlock western door", new UnlockDoorCommand(engine, "west"));
        commands.put("look around", new LookCommand(engine));
        commands.put("l", new LookCommand(engine));
        commands.put("pick up item", new PickupCommand(engine));

        // Inventory
        commands.put("use potion", new UsePotionCommand(engine));
        commands.put("stab myself", new StabSelfCommand(engine));
        commands.put("kill myself", new StabSelfCommand(engine, true));

        // System
        commands.put("intro", new IntroCommand(engine));
        commands.put("help", new HelpCommand(engine));
        commands.put("exit", new ExitCommand(engine));
        commands.put("jump", (e) -> e.displayMessage("Youpi!!!"));
    }

    public void handleInput(String input) {
        String normalized = input.trim().toLowerCase();

        String[] parts = normalized.split("\\s+");
        if (parts.length == 2) {
            Command base = commands.get(parts[0]);
            if (base instanceof MoveCommand moveCmd) {
                try {
                    int steps = Integer.parseInt(parts[1]);
                    if (steps > 0) {
                        moveCmd.execute(engine, steps);
                        return;
                    }
                } catch (NumberFormatException ignored) {
                }
            }
        }

        Command command = commands.get(normalized);
        if (command != null) {
            command.execute(engine);
        } else {
            engine.displayMessage("Invalid input");
        }
    }
}
