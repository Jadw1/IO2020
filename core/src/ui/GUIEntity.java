package ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.io2020.entities.Entity;
import com.io2020.entities.EntityType;

public class GUIEntity extends Entity {
    public Texture texture;
    public GUIEntityState state;


    public GUIEntity(EntityType entityType, Vector3 position, float width, float height) {
        super(entityType, position, width, height);
    }

    @Override
    public void draw(SpriteBatch batch) {
        if (texture != null) {
            batch.draw(texture, position.x, position.y, width, height);
        }
    }

    @Override
    public void update(float dt) {
    }


}
