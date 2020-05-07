package com.io2020.entities.Inventory;

import java.util.ArrayList;

public class Inventory {
    public ArrayList<ArrayList<Item>> items;
    public int inventorySize;

    public Inventory(int inventorySize) {
        this.inventorySize = inventorySize;
        items = new ArrayList<>();

    }

    public void addItem(Item item) {
        itemType type = item.type;
        boolean alreadyIn = false;
        int i;
        for(i = 0; i < items.size(); i++) {
            if(items.get(i).get(0).type == type) {
                alreadyIn = true;
                break;
            }
        }
        if(alreadyIn){
            items.get(i).add(item);
        } else
        if(items.size() < inventorySize) {
            ArrayList<Item> itemWrapper = new ArrayList<>();
            itemWrapper.add(item);
            items.add(itemWrapper);
        }
    }
    public void addItems(ArrayList<Item> itemsToAdd) {
        itemType type = itemsToAdd.get(0).type;
        boolean alreadyIn = false;
        int i;
        for(i = 0; i < items.size(); i++) {
            if(items.get(i).get(0).type == type) {
                alreadyIn = true;
                break;
            }
        }
        if(alreadyIn){
            items.get(i).addAll(itemsToAdd);
        } else
        if(items.size() < inventorySize) {
            items.add(itemsToAdd);
        }
    }

    private int findX(itemType type) {
        boolean found = false;
        int i;
        for(i = 0; i < items.size(); i++) {
            if(items.get(i).get(0).type == type) {
                found = true;
                break;
            }
        }
        if(found) {
            return i;
        } else {
            return -1;
        }
    }
    public boolean containsX(int x, itemType type) {
        int i = findX(type);
        if(i != -1 && items.get(i).size() >= x) {
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteX(int x, itemType type) {
        int i = findX(type);
        if(i == -1) {
            return false;
        }
        int sizeAfterDeletion = items.get(i).size() - x;
        if(sizeAfterDeletion >= 0) {
            while(items.get(i).size() > sizeAfterDeletion) {
                items.get(i).remove(0);
            }
            return true;
        } else {
            return false;
        }
    }

    //- dodaj do eq item x (czyli wieżyczki, mury, kilof, miecz itd.)
}
