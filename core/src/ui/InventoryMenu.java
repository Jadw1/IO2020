package ui;

import com.badlogic.gdx.graphics.Texture;
import com.io2020.entities.Inventory.Inventory;
import com.io2020.entities.Inventory.Item;

import java.util.ArrayList;

public class InventoryMenu extends BuildMenu {

    private ArrayList<ArrayList<Item>> items;

    public InventoryMenu(float x, int y, int scale, Texture mainBack) {
        super(x, y, scale, mainBack);

        for (Button b : buttons) {
            b.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(Button b) {
                    if (b.stack != null && !b.stack.isEmpty()) {
                        System.out.println(b.stack.get(0).getClass().getSimpleName());
                    }
                }
            });
        }

        items = new ArrayList<>();
    }

    public void addItemsToButtons(Inventory playersInventory) {
        ArrayList<ArrayList<Item>> items = playersInventory.items;
        int i = 0;

        for (Button b : buttons) {
            while (!items.isEmpty() && i < items.size() && items.get(i).isEmpty()) {
                i++;
            }

            if (!items.isEmpty() && i < items.size() && !items.get(i).isEmpty()) {
                b.setStack(items.get(i));

                i++;
            } else {
                b.setStack(null);
            }
        }
    }
}
