package com.io2020.map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.io2020.entities.Entity;
import com.io2020.entities.EntityType;

public abstract class MapEntity extends Entity {

    public MapEntity(EntityType entityType) {
        //nie wiem do konca co z tym width i heigh ??
        super(entityType, 0.0f, 0.0f);
    }

    public abstract void drawForeground(SpriteBatch batch);
}
