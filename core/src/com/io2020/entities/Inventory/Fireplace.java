package com.io2020.entities.Inventory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class Fireplace extends Item {
    public Fireplace() {
        super(true, false);
        type = itemType.FIREPLACE;
        texture = new Texture(Gdx.files.internal("WorldAnimation/wood_pile.png"));


        craftReq = new ArrayList<>();
        craftReq.add(new Pair(new Wood(), 7));
        craftReq.add(new Pair(new Stick(), 3));
    }
}
