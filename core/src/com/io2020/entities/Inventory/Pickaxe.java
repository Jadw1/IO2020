package com.io2020.entities.Inventory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class Pickaxe extends Item {
    public Pickaxe() {
        super(false, true);
        type = itemType.PICKAXE;
        texture = new Texture(Gdx.files.internal("WorldAnimation/pickaxe32.png"));

        craftReq = new ArrayList<>();
        craftReq.add(new Pair(new Stone(), 4));
        craftReq.add(new Pair(new Flint(), 2));
        craftReq.add(new Pair(new Stick(), 2));
    }
}
