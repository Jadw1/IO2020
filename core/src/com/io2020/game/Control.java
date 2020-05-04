package com.io2020.game;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class Control extends InputAdapter implements InputProcessor {

    OrthographicCamera camera;
    public boolean blockControl;
    public boolean allowBlock;

    // DIRECTIONS
    public boolean up;
    public boolean down;
    public boolean left;
    public boolean right;

    // CAMERA
    public boolean zoomIn;
    public boolean zoomOut;

    //ACTIONS
    public boolean interact;

    // MOUSE
    public boolean  leftMouseBtn;
    public boolean  rightMouseBtn;
    public boolean  processedClick = true;
    public Vector2  mouseClickPos = new Vector2();
    public Vector2  mousePos = new Vector2();
    public Vector2  mapClickPos = new Vector2();

    // SCREEN
    public int screenWidth;
    public int screenHeight;

    public Control(int screenWidth, int screenHeight, OrthographicCamera camera) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.camera = camera;
        allowBlock = true;
    }

    public void block()
    {
        blockControl = true;
        allowBlock = false;
        up = down = left = right = zoomIn = zoomOut = false;
    }

    private void setMouseClickedPos(int screenX, int screenY){
        // Set mouse position (flip screen Y)
        mouseClickPos.set(screenX, screenHeight - screenY);
        mapClickPos.set(get_map_coords(mouseClickPos));
    }

    public Vector2 get_map_coords(Vector2 mouseCoords){
        Vector3 v3 = new Vector3(mouseCoords.x, screenHeight - mouseCoords.y, 0);
        this.camera.unproject(v3);
        return new Vector2(v3.x,v3.y);
    }

    @Override
    public boolean keyDown(int keyCode) {
        if (!blockControl) {
            switch (keyCode) {
                case Keys.DOWN:
                    down = true;
                    break;
                case Keys.UP:
                    up = true;
                    break;
                case Keys.LEFT:
                    left = true;
                    break;
                case Keys.RIGHT:
                    right = true;
                    break;
                case Keys.W:
                    up = true;
                    break;
                case Keys.A:
                    left = true;
                    break;
                case Keys.S:
                    down = true;
                    break;
                case Keys.D:
                    right = true;
                    break;
                case Keys.LEFT_BRACKET:
                    zoomIn = true;
                    break;
                case Keys.RIGHT_BRACKET:
                    zoomOut = true;
                    break;
            }
        }

        if (keyCode == Keys.E) interact = true;
        return false;
    }

    @Override
    public boolean keyUp(int keyCode) {
        if (!blockControl) {
            switch (keyCode) {
                case Keys.DOWN:
                    down = false;
                    break;
                case Keys.UP:
                    up = false;
                    break;
                case Keys.LEFT:
                    left = false;
                    break;
                case Keys.RIGHT:
                    right = false;
                    break;
                case Keys.W:
                    up = false;
                    break;
                case Keys.A:
                    left = false;
                    break;
                case Keys.S:
                    down = false;
                    break;
                case Keys.D:
                    right = false;
                    break;
                case Keys.E:
                    interact = false;
                    break;
                case Keys.LEFT_BRACKET:
                    zoomIn = false;
                    break;
                case Keys.RIGHT_BRACKET:
                    zoomOut = false;
                    break;
            }
        }

        if (keyCode == Keys.E) interact = false;
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if(pointer == 0 && button == 0){
            leftMouseBtn = true;
        } else if (pointer == 0 && button == 0){
            rightMouseBtn = true;
        }

        setMouseClickedPos(screenX, screenY);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if(pointer == 0 && button == 0){
            leftMouseBtn = false;
            processedClick = false;
        } else if (pointer == 0 && button == 0){
            rightMouseBtn = false;
        }

        setMouseClickedPos(screenX, screenY);
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        setMouseClickedPos(screenX, screenY);
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        mousePos.set(screenX, screenHeight - screenY);
        return false;
    }
}