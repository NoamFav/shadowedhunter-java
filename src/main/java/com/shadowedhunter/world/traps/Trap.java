package com.shadowedhunter.world.traps;

import com.shadowedhunter.core.GameEngine;

import java.util.Random;

public abstract class Trap {
    protected final TrapType type;
    protected final Random random;

    public Trap(TrapType type) {
        this.type = type;
        this.random = new Random();
    }

    public abstract String trigger(GameEngine engine);

    protected boolean shouldKill(int currentHealth) {
        double chance = random.nextDouble();
        return chance <= type.getDeathChance() || currentHealth <= type.getDamage();
    }

    public TrapType getType() {
        return type;
    }
}
