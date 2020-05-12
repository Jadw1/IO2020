package com.io2020.entities.Inventory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class WoodenWall extends Item {
    public WoodenWall() {
        super(true, false);
        type = itemType.WOODEN_WALL;
        texture = new Texture(Gdx.files.internal("WorldAnimation/wooden_wall.png"));

        craftReq = new ArrayList<>();
        craftReq.add(new Pair(new Wood(), 5));
        craftReq.add(new Pair(new Stick(), 2));
    }
}
