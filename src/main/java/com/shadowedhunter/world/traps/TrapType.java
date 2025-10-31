package com.shadowedhunter.world.traps;

public enum TrapType {
    POISON("pi", 25, 0.25),
    SPIKE("sp", 75, 0.75),
    FALLING("fp", 40, 0.40),
    PROJECTILE("pa", 50, 0.50),
    WALL("sw", 90, 0.90),
    BLADE("wb", 100, 1.0),
    HOLE("h", 20, 0.0),
    PORTCULLIS("p", 0, 0.0);

    private final String code;
    private final int damage;
    private final double deathChance;

    TrapType(String code, int damage, double deathChance) {
        this.code = code;
        this.damage = damage;
        this.deathChance = deathChance;
    }

    public String getCode() {
        return code;
    }

    public int getDamage() {
        return damage;
    }

    public double getDeathChance() {
        return deathChance;
    }

    public static TrapType fromCode(String code) {
        for (TrapType t : values()) {
            if (t.code.equals(code)) return t;
        }
        return null;
    }
}
