package ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.io2020.entities.Entity;
import com.io2020.entities.EntityType;

public class Button extends GUIEntity {
    public OnClickListener listener;
    public Rectangle hitbox;
    public Texture icon;
    public Entity selector;

    public Button(Vector3 position, float width, float height, Texture texture, Entity selector) {
        super(EntityType.GUI, position, width, height);
        this.texture = texture;
        this.selector = selector;
        hitbox = new Rectangle(position.x, position.y, width, height);
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
    }

    // Is button currently being hovered over by the mouse
    private boolean isHovered() {
        return state == GUIEntityState.HOVERING;
    }

    // Updates the position and size of the hitbox (Rectangle)
    public void updateHitbox() {
        hitbox.set(position.x, position.y, width, height);
    }
}