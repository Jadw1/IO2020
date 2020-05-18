package com.io2020.entities.Inventory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class WoodenSword extends Item {
    public WoodenSword() {
        super(false, true);
        type = itemType.WOODEN_SWORD;
        texture = new Texture(Gdx.files.internal("WorldAnimation/woode_sword32.png"));

        craftReq = new ArrayList<>();
        craftReq.add(new Pair(new Wood(), 2));
        craftReq.add(new Pair(new Stick(), 2));
    }
}
