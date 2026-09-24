package com.shadowedhunter.input.commands.inventory;

import com.shadowedhunter.core.GameEngine;
import com.shadowedhunter.input.Command;

public class StabSelfCommand implements Command {
    private final GameEngine engine;
    private final boolean killSelf;

    public StabSelfCommand(GameEngine engine) {
        this(engine, false);
    }

    public StabSelfCommand(GameEngine engine, boolean killSelf) {
        this.engine = engine;
        this.killSelf = killSelf;
    }

    @Override
    public void execute(GameEngine engine) {
        if (killSelf) {
            engine.displayMessage("You committed suicide");
            engine.getGameState().handleDeath();
            engine.refreshDisplay();
        } else {
            boolean fatal = engine.getGameState().getHealth() <= 10;
            engine.getGameState().damagePlayer(10);
            engine.displayMessage(
                    fatal
                            ? "You stabbed yourself one time too many. You died"
                            : "You stabbed yourself: -10HP");
            engine.refreshDisplay();
        }
    }
}
