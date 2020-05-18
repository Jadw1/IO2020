package com.io2020.entities.Inventory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class Tower2 extends Item {
    public Tower2() {
        super(true, false);
        type = itemType.TOWER2;
        texture = new Texture(Gdx.files.internal("WorldAnimation/tower2.png"));

        craftReq = new ArrayList<>();
        craftReq.add(new Pair(new Stone(), 15));
        craftReq.add(new Pair(new Wood(), 25));
    }
}
