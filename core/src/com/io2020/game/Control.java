package com.io2020.game;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class Control extends InputAdapter implements InputProcessor {

    OrthographicCamera camera;
    public boolean blockControl;
    public boolean allowBlock;

    public boolean up;
    public boolean down;
    public boolean left;
    public boolean right;

    public boolean zoomIn;
    public boolean zoomOut;

    public boolean interact;

    public Control(OrthographicCamera camera) {
        this.camera = camera;
        allowBlock = true;
    }

    public void block()
    {
        blockControl = true;
        allowBlock = false;
        up = down = left = right = zoomIn = zoomOut = false;
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
}