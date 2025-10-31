package com.shadowedhunter.input.commands.system;

import com.shadowedhunter.core.GameEngine;
import com.shadowedhunter.input.Command;

public class HelpCommand implements Command {
    private final GameEngine engine;

    public HelpCommand(GameEngine engine) {
        this.engine = engine;
    }

    @Override
    public void execute(GameEngine engine) {
        engine.displayMessage("Showing cheatSheet for 10s");
        if (engine.getGameWindow() != null) {
            engine.getGameWindow().showCheatSheet();
        }
    }
}
