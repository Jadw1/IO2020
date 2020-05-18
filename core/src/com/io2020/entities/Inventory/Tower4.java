package com.io2020.entities.Inventory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class Tower4 extends Item {
    public Tower4() {
        super(true, false);
        type = itemType.TOWER4;
        texture = new Texture(Gdx.files.internal("WorldAnimation/tower4.png"));

        craftReq = new ArrayList<>();
        craftReq.add(new Pair(new Gold(), 5));
        craftReq.add(new Pair(new Stone(), 15));
        craftReq.add(new Pair(new Wood(), 10));
    }
}
