package com.shadowedhunter.world.traps.traps;

import com.shadowedhunter.core.GameEngine;
import com.shadowedhunter.resources.TrapMessages;
import com.shadowedhunter.world.traps.Trap;
import com.shadowedhunter.world.traps.TrapType;

public class ProjectileTrap extends Trap {
    public ProjectileTrap() {
        super(TrapType.PROJECTILE);
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

        return TrapMessages.getProjectileMessage(dies, random.nextInt(4));
    }
}
