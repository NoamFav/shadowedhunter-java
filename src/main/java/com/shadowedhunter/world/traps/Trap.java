package com.shadowedhunter.world.traps;

import com.shadowedhunter.core.GameEngine;
import com.shadowedhunter.world.traps.traps.*;

import java.util.Random;

public abstract class Trap {
    protected final TrapType type;
    protected final Random random;

    public Trap(TrapType type) {
        this.type = type;
        this.random = new Random();
    }

    public abstract String trigger(GameEngine engine);

    public static Trap forType(TrapType type) {
        return switch (type) {
            case POISON -> new PoisonTrap();
            case SPIKE -> new SpikeTrap();
            case FALLING -> new FallingTrap();
            case PROJECTILE -> new ProjectileTrap();
            case WALL -> new WallTrap();
            case BLADE -> new BladeTrap();
            case HOLE -> new HoleTrap();
            case PORTCULLIS -> new PortcullisTrap();
        };
    }

    protected boolean shouldKill(int currentHealth) {
        double chance = random.nextDouble();
        return chance <= type.getDeathChance() || currentHealth <= type.getDamage();
    }

    public TrapType getType() {
        return type;
    }
}
