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
        //System.out.println("deleting " + x + "of " + type.toString());
        if(x == 0) {
            //System.out.println("asked to delete 0");
            return true;
        }
        int i = findX(type);
        if (i == -1) {
            //System.out.println("item was not found");
            return false;
        }
        int sizeAfterDeletion = items.get(i).quantity - x;
        //System.out.println("there will be " + sizeAfterDeletion + " items  after deletion");
        if (sizeAfterDeletion > 0) {
            //System.out.println("size before deletion " + items.get(i).quantity);
            items.get(i).quantity = sizeAfterDeletion;
            //System.out.println("size after deletion " + items.get(i).quantity);
            return true;
        } else if(sizeAfterDeletion == 0) {
            items.remove(i);
            assert(findX(type) == -1);
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
