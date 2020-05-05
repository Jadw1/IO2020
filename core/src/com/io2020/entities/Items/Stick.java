package com.io2020.entities.Items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Stick extends Item {
        public Stick() {
            super(false, false);
            type = itemType.STICK;
            texture = new Texture(Gdx.files.internal("WorldAnimation/sapling.png"));
        }


}
