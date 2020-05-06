package com.io2020.entities.Inventory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Stone extends Item {
    public Stone() {
        super(false, false);
        type = itemType.STONE;
        texture = new Texture(Gdx.files.internal("WorldAnimation/rock.png"));
    }
}
