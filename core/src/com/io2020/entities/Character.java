package com.io2020.entities;

import com.badlogic.gdx.math.Vector3;

public abstract class Character extends Entity {

    protected float width, height;
    protected Vector3 position;

    public Character(EntityType entityType, float width, float height) {
        super(entityType);
        this.width = width;
        this.height = height;

        position = new Vector3();
    }

    public Vector3 getPosition() {
        return position;
    }

    public void setPosition(Vector3 position) {
        this.position = position;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public void move(Vector3 moveBy) {
        position.add(moveBy);
    }
}
