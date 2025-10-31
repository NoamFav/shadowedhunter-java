package com.shadowedhunter.inventory;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private final Map<ItemType, Integer> items;

    public Inventory() {
        this.items = new HashMap<>();
    }

    public void addItem(ItemType type) {
        items.put(type, items.getOrDefault(type, 0) + 1);
    }

    public boolean removeItem(ItemType type) {
        int count = items.getOrDefault(type, 0);
        if (count > 0) {
            items.put(type, count - 1);
            return true;
        }
        return false;
    }

    public int getItemCount(ItemType type) {
        return items.getOrDefault(type, 0);
    }

    public void setItemCount(ItemType type, int count) {
        items.put(type, count);
    }

    public boolean hasItem(ItemType type) {
        return getItemCount(type) > 0;
    }

    public void clear() {
        items.clear();
    }

    public Map<ItemType, Integer> getAllItems() {
        return new HashMap<>(items);
    }
}
