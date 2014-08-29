package com.anvol.coloripher.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by Andrey on 28.08.2014.
 */
public class PowerUpActor extends CircleActor {

    public final BubbleType type = BubbleType.POWERUP;

    public PowerUpActor(ShapeRenderer shapeRenderer) {
        super(shapeRenderer, new Color(1,1,1,0.7f), 10);
    }
}
