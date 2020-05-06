package com.io2020.entities.Inventory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class StoneWall extends Item {
    public StoneWall() {
        super(true, false);
        type = itemType.STONE_WALL;
        texture = new Texture(Gdx.files.internal("WorldAnimation/ocean_middle.png"));


        craftReq = new ArrayList<>();
        craftReq.add(new Pair(new Stone(), 3));
        craftReq.add(new Pair(new Stick(), 2));
    }
}
