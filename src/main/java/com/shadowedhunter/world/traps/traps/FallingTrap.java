package com.shadowedhunter.world.traps.traps;

import com.shadowedhunter.core.GameEngine;
import com.shadowedhunter.resources.TrapMessages;
import com.shadowedhunter.world.traps.Trap;
import com.shadowedhunter.world.traps.TrapType;

public class FallingTrap extends Trap {
    public FallingTrap() {
        super(TrapType.FALLING);
    }

    @Override
    public String trigger(GameEngine engine) {
        int currentHealth = engine.getGameState().getHealth();
        boolean dies = shouldKill(currentHealth);

        if (dies) {
            engine.getGameState().handleDeath();
        } else {
            engine.getGameState().damagePlayer(type.getDamage());
        }

        return TrapMessages.getFallingMessage(dies, random.nextInt(4));
    }
}
