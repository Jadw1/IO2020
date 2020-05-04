package ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.io2020.game.Control;

public class SquareMenu extends Menu {
    public BuildMenu build;
    public BuildMenu inventory;

    public SquareMenu(final Control control){
        super(0, 0, 2, new Texture("GUI/square_menu.png"));

        int scale = 2;
        addButtons(3, 2, 2, new Texture("GUI/pink_button.png"),
                new Texture("GUI/selector.png"), scale);

        Button btn = buttons.get(0);
        btn.icon = new Texture("GUI/icons/inventory.png");
        btn.setOnClickListener(
                new OnClickListener(){
                    @Override
                    public void onClick(Button b) {
                        inventory.toggleActive();
                    }
                });

        btn = buttons.get(1);
        btn.icon = new Texture("GUI/icons/settings.png");
        btn.setOnClickListener(
                new OnClickListener(){
                    @Override
                    public void onClick(Button b) {
                        System.out.println("Settings.");
                    }
                });

        btn = buttons.get(2);
        btn.icon = new Texture("GUI/icons/resources.png");
        btn.setOnClickListener(
                new OnClickListener(){
                    @Override
                    public void onClick(Button b) {
//                        control.inventory = true;
                    }
                });

        btn = buttons.get(3);
        btn.icon = new Texture("GUI/icons/build.png");
        buttons.get(3).setOnClickListener(
                new OnClickListener(){
                    @Override
                    public void onClick(Button b) {
                        build.toggleActive();
                    }
                });

        // BUILDING
        build = new BuildMenu(pos.x + width, 0, 2,
                new Texture("GUI/main_background.png"));

        // INVENTORY
        inventory = new BuildMenu(pos.x + width, 0, 2,
                new Texture("GUI/main_background.png"));
    }

    // Draw the extended menu and also the build menu.
    @Override
    public void draw(SpriteBatch batch){
        super.draw(batch);
        build.draw(batch);
    }

    // Check if the menu / build menu buttons are being hovered over.
    @Override
    public void checkHover(Vector2 pos) {
        super.checkHover(pos);
        build.checkHover(pos);
    }

}