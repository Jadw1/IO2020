package com.io2020.entities.Inventory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class Tower1 extends Item {
    public Tower1() {
        super(true, false);
        type = itemType.TOWER1;
        texture = new Texture(Gdx.files.internal("WorldAnimation/tower1.png"));

        craftReq = new ArrayList<>();
        craftReq.add(new Pair(new Stone(), 10));
        craftReq.add(new Pair(new Wood(), 10));
        craftReq.add(new Pair(new PickedFlower(), 10));
    }
}
