package ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.io2020.entities.Inventory.Inventory;
import com.io2020.entities.Inventory.Item;

import java.util.ArrayList;

public class BuildMenu extends Menu {

    static final Texture pinkButton = new Texture("GUI/pink_button.png");
    static final Texture selector = new Texture("GUI/selector.png");
    static final Texture close_menu = new Texture("GUI/icons/close_menu.png");

    private ArrayList<ArrayList<Item>> items;

    public BuildMenu(float x, int y, int scale, Texture mainBack) {
        super(x, y, 2, mainBack);
        addButtons(3, 11, 2, pinkButton, selector, 2);

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

        setInactive();

        items = new ArrayList<>();
    }

    // Only draw when the menu is active.
    public void draw(SpriteBatch batch) {
        if (isActive()) {
            super.draw(batch);
        }
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