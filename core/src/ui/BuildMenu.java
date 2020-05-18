package ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.io2020.game.BuildingManager;

public abstract class BuildMenu extends Menu {

    static final Texture pinkButton = new Texture("GUI/pink_button.png");
    static final Texture selector = new Texture("GUI/selector.png");
    static final Texture close_menu = new Texture("GUI/icons/close_menu.png");


    public BuildMenu(float x, int y, int scale, Texture mainBack, int rowNum, BuildingManager buildingManager) {
        super(x, y, 2, mainBack);
        addButtons(3, 11, rowNum, pinkButton, selector, 2, buildingManager);

        setInactive();
    }

    // Only draw when the menu is active.
    public void draw(SpriteBatch batch) {
        if (isActive()) {
            super.draw(batch);
        }
    }


}