package com.io2020.entities;

import com.badlogic.gdx.math.Vector2;

public abstract class Character extends Entity {

    protected float width, height;
    protected Vector2 position;

    public Character(EntityType entityType, float width, float height) {
        super(entityType);
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
}
