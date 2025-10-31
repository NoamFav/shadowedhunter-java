package com.shadowedhunter.input.commands.system;

import com.shadowedhunter.core.GameEngine;
import com.shadowedhunter.input.Command;
import com.shadowedhunter.resources.DialogTexts;
import com.shadowedhunter.ui.MessageSystem;

public class IntroCommand implements Command {
    private final GameEngine engine;

    public IntroCommand(GameEngine engine) {
        this.engine = engine;
    }

    @Override
    public void execute(GameEngine engine) {
        MessageSystem.showTimedMessages(engine, DialogTexts.getIntroMessages(), 5000);
    }
}
