package com.io2020.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

//tymaczsowo, zeby było cokowliek w tle
public class World extends Image {

    public World() {
        super(new Texture("map.jpg"));

        setPosition(0.0f, 0.0f);
    }
}
