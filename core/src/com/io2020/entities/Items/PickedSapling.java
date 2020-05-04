package com.io2020.entities.Items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class PickedSapling extends Item {
        public PickedSapling() {
            super();
            type = itemType.SAPLING;
            texture = new Texture(Gdx.files.internal("WorldAnimation/sapling.png"));
        }
}
