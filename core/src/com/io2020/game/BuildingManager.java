package com.io2020.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.io2020.entities.Items.Item;
import ui.BuildMenu;

public class BuildingManager
{
    private final Vector3 playerPosition;
    private final OrthographicCamera camera;

    public BuildingManager(Vector3 playerPosition, OrthographicCamera camera) {
        this.playerPosition = playerPosition;
        this.camera = camera;
    }

    public Vector2 getTouchedTile() {
        Vector2 cursorRelativeToCameraMiddle  = new Vector2((Gdx.input.getX() - camera.viewportWidth / 2) / 2,
                (-Gdx.input.getY() + camera.viewportHeight / 2) / 2);

        return new Vector2((int) (playerPosition.x + cursorRelativeToCameraMiddle.x) / 32,
                (int) (playerPosition.y + cursorRelativeToCameraMiddle.y) / 32);
    }

    public static void craft(BuildMenu inventory, Item item) {
        // robi item i wrzuca go do inventory
    }

}
