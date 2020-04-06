package com.io2020.map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.io2020.box2d.Box2DHandler;
import com.io2020.entities.Entity;
import com.io2020.entities.EntityType;
import com.io2020.tileSet.Tile;

public abstract class MapEntity extends Entity {

    protected int x, y;
    protected float tileWidth, tileHeight;
    protected Vector3 position;
    boolean blocking;

    public MapEntity(EntityType entityType, int x, int y) { // default value of blocking = false
        super(entityType);

        this.x = x;
        this.y = y;

        position = new Vector3(x, y, 0);
        this.blocking = false;
    }

    public MapEntity(EntityType entityType, int x, int y, boolean blocking) {
        super(entityType);

        this.x = x;
        this.y = y;

        position = new Vector3(x, y, 0);
        this.blocking = blocking;
    }

    public abstract void drawForeground(SpriteBatch batch);

    protected  void drawTile(SpriteBatch batch, Tile tile,  int drawX,  int drawY) {
        batch.draw(tile.getTexture(), tileWidth * drawX, tileHeight * drawY, tileWidth, tileHeight);
    }

    public void setTileWidth(float tileWidth) {
        this.tileWidth = tileWidth;
    }

    public void setTileHeight(float tileHeight) {
        this.tileHeight = tileHeight;
    }

    public boolean isBlocking() {
        return blocking;
    }

}
