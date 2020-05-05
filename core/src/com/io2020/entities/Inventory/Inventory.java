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

    public boolean containsX(int x, itemType type) {
        boolean contains = false;
        int i;
        for(i = 0; i < items.size(); i++) {
            if(items.get(i).get(0).type == type) {
                contains = true;
                break;
            }
        }
        if(contains) {
            return (items.get(i).size() >= x);
        } else {
            return false;
        }
    }

    public boolean deleteX(int x, itemType type) {
        // assert (this.containsX(x, type));
        boolean found = false;
        int i;
        for(i = 0; i < items.size(); i++) {
            if(items.get(i).get(0).type == type) {
                found = true;
                break;
            }
        }
        int sizeAfterDeletion = items.get(i).size() - x;
        if(found && sizeAfterDeletion >= 0) {
            while(items.get(i).size() > sizeAfterDeletion) {
                items.get(i).remove(items.get(i).size());
            }
            return true;
        } else {
            return false;
        }
    }

    //- dodaj do eq item x (czyli wieżyczki, mury, kilof, miecz itd.)
}
