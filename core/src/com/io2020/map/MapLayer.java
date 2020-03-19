package com.io2020.map;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Queue;

public class MapLayer extends Actor {

    private Queue<LayerElement> drawingQueue;
    private float tileWidth, tileHeight;

    public MapLayer(int width, int height, float tileWidth, float tileHeight) {
        super();

        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;

        drawingQueue = new Queue<>(width * height);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        LayerElement e;
        while(!drawingQueue.isEmpty()) {
            e = drawingQueue.removeFirst();

            //TODO: check if tile is on on screen?
            batch.draw(e.texture, e.coord.getX() * tileWidth, e.coord.getY() * tileHeight);
        }
    }

    public void addToDrawingQueue(LayerElement e) {
        drawingQueue.addLast(e);
    }
}
