package ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.io2020.entities.Items.Item;

import java.util.ArrayList;

public class BuildMenu extends Menu {

    static final Texture pinkButton = new Texture("GUI/pink_button.png");
    static final Texture selector = new Texture("GUI/selector.png");
    static final Texture close_menu = new Texture("GUI/icons/close_menu.png");

    private ArrayList<ArrayList<Item>> items;

    public BuildMenu(float x, int y, int scale, Texture mainBack) {
        super(x, y, 2, mainBack);
        addButtons(3, 11, 2, pinkButton, selector, 2);
        setInactive();

//        // Add a close button
//        Button close = new Button(new Vector3(), close_menu.getWidth() * scale,
//                close_menu.getHeight() * scale, close_menu, null);
//        close.getPosition().x = x + width - (close_menu.getWidth() * scale) - (6 * scale);
//        close.getPosition().y = height - (close_menu.getHeight() * scale) - (6 * scale);
//        close.updateHitbox();
//        close.setOnClickListener(
//                new OnClickListener() {
//                    @Override
//                    public void onClick(Button b) {
//                        toggleActive();
//                    }
//                });
//        buttons.add(close);
        items = new ArrayList<>();
    }

    // Only draw when the menu is active.
    public void draw(SpriteBatch batch) {
        if (isActive()) {
            super.draw(batch);
        }
    }

    public void setItems(ArrayList<ArrayList<Item>> items) {
        this.items = items;
    }
}