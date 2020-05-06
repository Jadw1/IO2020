package com.io2020.entities.Inventory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class PickedFlower extends Item {
    public PickedFlower() {
        super(false, false);
        type = itemType.FLOWER;
        texture = new Texture(Gdx.files.internal("WorldAnimation/flower2.png"));
    }
}
