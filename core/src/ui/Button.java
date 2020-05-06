package ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.io2020.entities.Entity;
import com.io2020.entities.EntityType;
import com.io2020.entities.Inventory.Item;
import com.io2020.game.BuildingManager;

import java.util.ArrayList;

public class Button extends GUIEntity {
    public OnClickListener listener;
    public Rectangle hitbox;
    public Texture icon;
    public Entity selector;
    public ArrayList<Item> stack;
    public Item item;
    public BuildingManager buildingManager;

    public Button(Vector3 position, float width, float height, Texture texture, Entity selector,  BuildingManager buildingManager) {
        super(EntityType.GUI, position, width, height);
        this.texture = texture;
        this.selector = selector;
        hitbox = new Rectangle(position.x, position.y, width, height);
        stack = null;
        this.buildingManager = buildingManager;
    }

    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void draw(SpriteBatch batch) {
        if (texture != null) {
            batch.draw(texture, position.x, position.y, width, height);
        }
        if (icon != null) {
            batch.draw(icon, position.x, position.y, width, height);
        }
        if (isHovered() && selector != null) {
            selector.draw(batch);
        }
        if (stack != null && !stack.isEmpty()) {
            stack.get(0).draw(batch, position, stack.size());
        }
    }

    // Is button currently being hovered over by the mouse
    private boolean isHovered() {
        return state == GUIEntityState.HOVERING;
    }

    // Updates the position and size of the hitbox (Rectangle)
    public void updateHitbox() {
        hitbox.set(position.x, position.y, width, height);
    }

    public void setStack(ArrayList<Item> stack) {
        this.stack = stack;
    }

    public void setItem(Item item) {
        this.item = item;
        this.icon = item.getTexture();
    }

}