package com.io2020.entities.Inventory;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.io2020.map.MapEntity;

import java.util.ArrayList;

public abstract class Item {
    public Texture texture;
    public itemType type;
    public boolean toBuild;
    public boolean toCraft;

    public ArrayList<Pair> craftReq;

    public Item(boolean toBuild, boolean toCraft) {
        this.texture = null;
        this.toBuild = toBuild;
        this.toCraft = toCraft;
        this.craftReq = null;
    }

    public void draw(SpriteBatch batch, Vector3 pos, int number) {
        if(texture != null) {
            batch.draw(texture, pos.x, pos.y, 40, 40);
            BitmapFont font = new BitmapFont();
            font.draw(batch, Integer.toString(number), pos.x + 5, pos.y + 10);
        }
    }

    public Texture getTexture() {
        return texture;
    }

    public ArrayList<Pair> getCraftReq() {
        return craftReq;
    }
}
