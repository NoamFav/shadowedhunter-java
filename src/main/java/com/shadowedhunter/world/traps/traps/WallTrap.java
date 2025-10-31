package com.shadowedhunter.world.traps.traps;

import com.shadowedhunter.core.GameEngine;
import com.shadowedhunter.resources.TrapMessages;
import com.shadowedhunter.world.traps.Trap;
import com.shadowedhunter.world.traps.TrapType;

public class WallTrap extends Trap {
    public WallTrap() {
        super(TrapType.WALL);
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

        return TrapMessages.getWallMessage(dies, random.nextInt(4));
    }
}
