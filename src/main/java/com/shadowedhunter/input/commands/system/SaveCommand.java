package com.shadowedhunter.input.commands.system;

import com.shadowedhunter.core.GameEngine;
import com.shadowedhunter.input.Command;
import com.shadowedhunter.persistence.SaveSystem;

public class SaveCommand implements Command {
    private final GameEngine engine;

    public SaveCommand(GameEngine engine) {
        this.engine = engine;
    }

    @Override
    public void execute(GameEngine engine) {
        SaveSystem.saveGame(engine, "Save.txt");
        engine.displayMessage("Game saved");
    }
}
