package com.io2020.entities.Inventory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Wood extends Item {
    public Wood() {
        super();
        type = itemType.WOOD;
        texture = new Texture(Gdx.files.internal("WorldAnimation/wood32.png"));
    }
}
