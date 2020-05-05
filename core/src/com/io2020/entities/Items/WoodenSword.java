package com.io2020.entities.Items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class WoodenSword extends Item {
    public WoodenSword() {
        super(false, true);
        type = itemType.SWORD;
        texture = new Texture(Gdx.files.internal("WorldAnimation/woode_sword32.png"));
    }
}
