package com.io2020.entities.Inventory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Axe extends Item {
    public Axe() {
        super();
        type = itemType.PICKAXE;
        texture = new Texture(Gdx.files.internal("WorldAnimation/axeT32.png"));
    }
}
