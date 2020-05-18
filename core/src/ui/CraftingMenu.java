package ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.io2020.entities.Inventory.*;
import com.io2020.game.BuildingManager;

public class CraftingMenu extends BuildMenu {

    private final BuildingManager buildingManager;

    public CraftingMenu(float x, int y, int scale, Texture mainBack, final BuildingManager buildingManager) {
        super(x, y, scale, mainBack, 1, buildingManager);
        this.buildingManager = buildingManager;

        Item[] items = new Item[]{new Pickaxe(), new Axe(), new WoodenSword(), new StoneSword(),
        new Tower1(), new Tower2(), new Tower3(), new Tower4(),
        new WoodenWall(), new StoneWall(), new Fireplace()};

        for (int i = 0; i < 11; i++) {
            Button button = buttons.get(i);
            button.setItem(items[i]);

            button.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(Button b) {
                    buildingManager.craft(b.item);
                }
            });
        }
    }
}
