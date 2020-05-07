package ui;

import com.badlogic.gdx.graphics.Texture;
import com.io2020.entities.Inventory.Inventory;
import com.io2020.entities.Inventory.Item;
import com.io2020.entities.Inventory.Pair;
import com.io2020.game.BuildingManager;

import java.util.ArrayList;

public class InventoryMenu extends BuildMenu {

    private ArrayList<ArrayList<Item>> items;

    public InventoryMenu(float x, int y, int scale, Texture mainBack, final BuildingManager buildingManager) {
        super(x, y, scale, mainBack, 2, buildingManager);

        for (Button b : buttons) {
            b.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(Button b) {
                    if (b.stack != null && b.stack.quantity > 0) {
                        if (b.stack.item.toBuild) {
                            System.out.println("CLICK");
                            buildingManager.build(b.stack.item);
                        }
//                        System.out.println(b.stack.get(0).getClass().getSimpleName());
                    }
                }
            });
        }

        items = new ArrayList<>();
    }

    public void addItemsToButtons(Inventory playersInventory) {
        ArrayList<Pair> items = playersInventory.items;
        int i = 0;

        for (Button b : buttons) {
            while (!items.isEmpty() && i < items.size() && items.get(i).quantity == 0) {
                i++;
            }

            if (!items.isEmpty() && i < items.size() && items.get(i).quantity > 0) {
                b.setStack(items.get(i));

                i++;
            } else {
                b.setStack(null);
            }
        }
    }
}
