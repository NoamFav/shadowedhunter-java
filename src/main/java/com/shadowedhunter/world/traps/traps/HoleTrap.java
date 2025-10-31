package com.shadowedhunter.world.traps.traps;

import com.shadowedhunter.core.GameEngine;
import com.shadowedhunter.resources.TrapMessages;
import com.shadowedhunter.world.traps.Trap;
import com.shadowedhunter.world.traps.TrapType;

public class HoleTrap extends Trap {
    public HoleTrap() {
        super(TrapType.HOLE);
    }

    @Override
    public String trigger(GameEngine engine) {
        int currentHealth = engine.getGameState().getHealth();
        boolean dies = currentHealth <= 20;

        if (dies) {
            engine.getGameState().handleDeath();
            engine.getWorld().descendFloor();
        } else {
            engine.getGameState().damagePlayer(type.getDamage());
            engine.getWorld().descendFloor();
        }

        return TrapMessages.getHoleMessage(dies, random.nextInt(4));
    }
}
