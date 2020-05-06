package com.io2020.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.io2020.entities.Inventory.Inventory;
import com.io2020.entities.Inventory.Item;
import com.io2020.entities.Inventory.Pair;

import java.util.ArrayList;

public class BuildingManager
{
    private final Vector3 playerPosition;
    private final OrthographicCamera camera;
    private final Inventory inventory;

    public BuildingManager(Vector3 playerPosition, OrthographicCamera camera, Inventory inventory) {
        this.playerPosition = playerPosition;
        this.camera = camera;
        this.inventory = inventory;
    }

    public Vector2 getTouchedTile() {
        Vector2 cursorRelativeToCameraMiddle  = new Vector2((Gdx.input.getX() - camera.viewportWidth / 2) / 2,
                (-Gdx.input.getY() + camera.viewportHeight / 2) / 2);

        return new Vector2((int) (playerPosition.x + cursorRelativeToCameraMiddle.x) / 32,
                (int) (playerPosition.y + cursorRelativeToCameraMiddle.y) / 32);
    }

    public void craft(Item item) {
        ArrayList<Pair> craftReq = item.getCraftReq();
        boolean available = true;

        for (Pair req: craftReq) {
            if (!inventory.containsX(req.quantity, req.item.type)) {
                available = false;
                break;
            }
        }

        if (available) {
            for (Pair req: craftReq) {
                inventory.deleteX(req.quantity, req.item.type);
            }

            inventory.addItem(item);
        }
    }

}
