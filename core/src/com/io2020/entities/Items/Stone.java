package com.io2020.entities.Items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Stone extends Item {
    public Stone() {
        super();
        type = itemType.STONE;
        texture = new Texture(Gdx.files.internal("WorldAnimation/rock.png"));
    }
}
