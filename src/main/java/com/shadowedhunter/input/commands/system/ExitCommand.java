package com.shadowedhunter.input.commands.system;

import com.shadowedhunter.core.GameEngine;
import com.shadowedhunter.input.Command;
import com.shadowedhunter.persistence.SaveSystem;

public class ExitCommand implements Command {
    private final GameEngine engine;

    public ExitCommand(GameEngine engine) {
        this.engine = engine;
    }

    @Override
    public void execute(GameEngine engine) {
        engine.displayMessage("Exiting");
        SaveSystem.saveGame(engine, "Save.txt");
        System.exit(0);
    }
}
