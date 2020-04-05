package com.io2020.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity {
    protected float width;
    protected float height;
    protected EntityType type;
    protected Vector2 position;

    public Entity(EntityType entityType, float width, float height) {
        this.type = entityType;
        this.width = width;
        this.height = height;

        position = new Vector2();
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public void move(Vector2 moveBy) {
        position.add(moveBy);
    }

    public abstract void draw(SpriteBatch batch);

    public abstract void update(float dt);
}
