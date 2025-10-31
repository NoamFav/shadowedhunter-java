package com.shadowedhunter.inventory;

public enum ItemType {
    KEY("k", "Key"),
    HEALTH_POTION("he", "Health Potion"),
    SWORD("sword", "Sword"),
    SHIELD("shield", "Shield");

    private final String code;
    private final String displayName;

    ItemType(String code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }

    public String getCode() {
        return code;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static ItemType fromCode(String code) {
        for (ItemType type : values()) {
            if (type.code.equals(code)) {
                return type;
            }
        }
        return null;
    }
}
