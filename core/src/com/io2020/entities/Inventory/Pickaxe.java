package com.io2020.entities.Inventory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Pickaxe extends Item {
    public Pickaxe() {
        super();
        type = itemType.PICKAXE;
        texture = new Texture(Gdx.files.internal("WorldAnimation/pickaxe32.png"));
    }
}