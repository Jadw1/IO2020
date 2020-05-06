package com.io2020.entities.Inventory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Flint extends Item {
    public Flint() {
        super(false, false);
        type = itemType.FLINT;
        texture = new Texture(Gdx.files.internal("WorldAnimation/flint32.png"));
    }
}
