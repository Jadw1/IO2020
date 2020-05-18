package com.io2020.entities.Inventory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class Axe extends Item {
    public Axe() {
        super(false, true);
        type = itemType.AXE;
        texture = new Texture(Gdx.files.internal("WorldAnimation/axeT32.png"));

        craftReq = new ArrayList<>();
        craftReq.add(new Pair(new Stone(), 3));
        craftReq.add(new Pair(new Flint(), 2));
        craftReq.add(new Pair(new Stick(), 2));
    }
}
