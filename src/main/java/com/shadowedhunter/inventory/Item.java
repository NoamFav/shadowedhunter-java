package com.shadowedhunter.inventory;

public class Item {
    private final ItemType type;
    private final String description;

    public Item(ItemType type, String description) {
        this.type = type;
        this.description = description;
    }

    public ItemType getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public static Item createKey() {
        return new Item(ItemType.KEY, "An old rusty key");
    }

    public static Item createHealthPotion() {
        return new Item(ItemType.HEALTH_POTION, "A potion that restores health");
    }
}
