package ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.io2020.entities.Inventory.Inventory;
import com.io2020.game.BuildingManager;
import com.io2020.game.Control;

public class SquareMenu extends Menu {
    public CraftingMenu crafting;
    public InventoryMenu inventory;
    private Inventory playersInventory;

    public SquareMenu(final Control control, Inventory playersInventory, BuildingManager buildingManager) {
        super(0, 0, 2, new Texture("GUI/square_menu.png"));
        this.playersInventory = playersInventory;

        int scale = 2;
        addButtons(3, 2, 2, new Texture("GUI/pink_button.png"),
                new Texture("GUI/selector.png"), scale, buildingManager);

        Button btn = buttons.get(0);
        btn.icon = new Texture("GUI/icons/inventory.png");
        btn.setOnClickListener(
                new OnClickListener() {
                    @Override
                    public void onClick(Button b) {
                        crafting.disactive();
                        inventory.toggleActive();
                    }
                });

        btn = buttons.get(1);
        btn.icon = new Texture("GUI/icons/settings.png");
        btn.setOnClickListener(
                new OnClickListener() {
                    @Override
                    public void onClick(Button b) {
                        System.out.println("Settings.");
                    }
                });

        btn = buttons.get(2);
        btn.icon = new Texture("GUI/icons/resources.png");
        btn.setOnClickListener(
                new OnClickListener() {
                    @Override
                    public void onClick(Button b) {
//                        control.inventory = true;
                    }
                });

        btn = buttons.get(3);
        btn.icon = new Texture("GUI/icons/build.png");
        btn.setOnClickListener(
                new OnClickListener() {
                    @Override
                    public void onClick(Button b) {
                        inventory.disactive();
                        crafting.toggleActive();
                    }
                });

        final Texture menuBackground = new Texture("GUI/main_background11.png");

        // CRAFTING
        crafting = new CraftingMenu(pos.x + width, 0, 2, menuBackground, buildingManager);

        // INVENTORY
        inventory = new InventoryMenu(pos.x + width, 0, 2, menuBackground, buildingManager);
    }

    // Draw the extended menu and also the build menu.
    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        crafting.draw(batch);
        inventory.addItemsToButtons(playersInventory);
        inventory.draw(batch);
    }

    // Check if the menu / build menu buttons are being hovered over.
    @Override
    public void checkHover(Vector2 pos) {
        super.checkHover(pos);
        crafting.checkHover(pos);
        inventory.checkHover(pos);
    }

}