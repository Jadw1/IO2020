package com.io2020.entities.Inventory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class StoneSword extends Item {
    public StoneSword() {
        super(false, true);
        type = itemType.STONE_SWORD;
        texture = new Texture(Gdx.files.internal("WorldAnimation/stone_sword.png"));

        craftReq = new ArrayList<>();
        craftReq.add(new Pair(new Stone(), 5));
        craftReq.add(new Pair(new Wood(), 5));
        craftReq.add(new Pair(new Stick(), 5));
    }
}
