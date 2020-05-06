package com.io2020.entities.Inventory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class Tower3 extends Item {
    public Tower3() {
        super(true, false);
        type = itemType.TOWER3;
        texture = new Texture(Gdx.files.internal("WorldAnimation/tower3.png"));

        craftReq = new ArrayList<>();
        craftReq.add(new Pair(new Stone(), 30));
        craftReq.add(new Pair(new Wood(), 20));
    }
}
