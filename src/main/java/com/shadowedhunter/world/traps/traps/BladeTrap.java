package com.shadowedhunter.world.traps.traps;

import com.shadowedhunter.core.GameEngine;
import com.shadowedhunter.resources.TrapMessages;
import com.shadowedhunter.world.traps.Trap;
import com.shadowedhunter.world.traps.TrapType;

public class BladeTrap extends Trap {
    public BladeTrap() {
        super(TrapType.BLADE);
    }

    @Override
    public String trigger(GameEngine engine) {
        // Blade trap always kills
        engine.getGameState().handleDeath();
        return TrapMessages.getBladeMessage(true, random.nextInt(4));
    }
}
