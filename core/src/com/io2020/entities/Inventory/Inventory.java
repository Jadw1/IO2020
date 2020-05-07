package com.io2020.entities.Inventory;

import java.util.ArrayList;

public class Inventory {
    public ArrayList<Pair> items;
    public int inventorySize;

    public Inventory(int inventorySize) {
        this.inventorySize = inventorySize;
        items = new ArrayList<>();
    }

    private int findX(itemType type) {
        boolean found = false;
        int i;
        for (i = 0; i < items.size(); i++) {
            if (items.get(i).item.type == type) {
                found = true;
                break;
            }
        }
        if (found) {
            return i;
        } else {
            return -1;
        }
    }

    public void addItem(Item item) {
        int i = findX(item.type);
        if (i != -1) {
            items.get(i).quantity++;
        } else if (items.size() < inventorySize) {
            items.add(new Pair(item, 1));
        }
    }

    public void addItems(Item item, Integer quantity) {
        itemType type = item.type;
        int i = findX(type);
        if (i != -1) {
            items.get(i).quantity += quantity;
        } else if (items.size() < inventorySize) {
            items.add(new Pair(item, quantity));
        }
    }


    public boolean containsX(int x, itemType type) {
        return x <= getQuantity(type);
    }

    public boolean deleteX(int x, itemType type) {
        if(x == 0) {
            return true;
        }
        int i = findX(type);
        if (i == -1) {
            return false;
        }
        int sizeAfterDeletion = items.get(i).quantity - x;
        if (sizeAfterDeletion > 0) {
            items.get(i).quantity = sizeAfterDeletion;
            return true;
        } else if(sizeAfterDeletion == 0) {
            items.remove(i);
            return true;
        } else {
            return false;
        }
    }

    public Integer getQuantity(itemType type) {
        int i = findX(type);
        if (i == -1) {
            return 0;
        } else {
            return items.get(i).quantity;
        }
    }
}
