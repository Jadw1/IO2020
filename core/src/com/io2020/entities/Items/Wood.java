package com.io2020.entities.Items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Wood extends Item {
    public Wood() {
        super(false, false);
        type = itemType.WOOD;
        texture = new Texture(Gdx.files.internal("WorldAnimation/wood32.png"));
    }
}
