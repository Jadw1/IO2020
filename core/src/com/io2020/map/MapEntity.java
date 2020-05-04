package com.io2020.map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.io2020.entities.Entity;
import com.io2020.entities.EntityType;
import com.io2020.entities.Player;

public abstract class MapEntity extends Entity {
    protected TextureRegion texture;

    public MapEntity(EntityType entityType, Vector3 position, float width, float height) { // default value of blocking = false
        super(entityType, position, width, height);
    }

    public void interact(Player player) {
        hitPoints--;
        if (hitPoints == 0) {
            this.giveItemsToPlayer(player);
            remove = true;
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(texture, position.x - width/2, position.y, width, height);
    }

    @Override
    public void update(float dt) {}

    protected void giveItemsToPlayer(Player player) {
        // do nothing
        // functions is not overrode <=> entity doesn't drop anything
    }
}
