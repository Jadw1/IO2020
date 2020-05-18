package com.io2020.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;

public class Lighting {

    private final float _12h = 20.0f;
    private float time;
    private boolean am;
    private final float blueColor = 0.6f;

    public Lighting() {
        time = 0f;
        am = false;
    }

    public void update(float dt) {
        time += dt;
        if(time >= _12h) {
            time -= _12h;
            am = !am;
        }
    }

    public void setLight(Batch batch) {
        float progress = time/_12h;
        float blue = (am) ? MathUtils.lerp(blueColor, 1.0f, progress) : MathUtils.lerp(1.0f, blueColor, progress);
        float rgColor = (am) ? MathUtils.lerp(0.0f, 1.0f, progress) : MathUtils.lerp(1.0f, 0.0f, progress);

        batch.setColor(rgColor, rgColor, blue, 1.0f);
    }

    public void resetLight(Batch batch) {
        batch.setColor(1.0f, 1.0f, 1.0f, 1.0f);
    }
}
