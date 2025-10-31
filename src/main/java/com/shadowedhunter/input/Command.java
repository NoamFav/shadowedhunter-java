package com.shadowedhunter.input;

import com.shadowedhunter.core.GameEngine;

@FunctionalInterface
public interface Command {
    void execute(GameEngine engine);
}
