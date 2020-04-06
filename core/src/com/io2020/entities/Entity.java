package com.io2020.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity {

    protected EntityType type;


    public Entity(EntityType entityType) {
        this.type = entityType;
    }



    public abstract void draw(SpriteBatch batch);

    public abstract void update(float dt);
}
