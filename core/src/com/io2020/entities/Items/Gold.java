package com.io2020.entities.Items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Gold extends Item {
    public Gold() {
        super();
        type = itemType.GOLD;
        texture = new Texture(Gdx.files.internal("WorldAnimation/gold32.png"));
    }
}
