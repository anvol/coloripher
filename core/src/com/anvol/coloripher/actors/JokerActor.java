package com.anvol.coloripher.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by Andrey on 28.08.2014.
 */
public class JokerActor extends CircleActor {
    private final Color trueColor;

    public JokerActor(ShapeRenderer sr, Color color) {
        super(sr, Color.BLACK, 30);

        this.trueColor = color;
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if (getCenterY() <= 250) setColor(trueColor);
    }
}
