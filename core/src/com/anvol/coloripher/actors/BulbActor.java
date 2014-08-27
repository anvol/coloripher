package com.anvol.coloripher.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Ellipse;

/**
 * Created by Andrey on 19.08.2014.
 */
public class BulbActor extends CircleActor {

    public BulbActor(ShapeRenderer shapeRenderer, Color color) {
        super(shapeRenderer, color, 0);
        setSize(400, 300);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.end();

        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        shapeRenderer.setTransformMatrix(batch.getTransformMatrix());
        shapeRenderer.translate(getX(), getY(), 0);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(getColor());
        shapeRenderer.ellipse(0, 0, 400, 300);
        shapeRenderer.end();

        batch.begin();
    }

    @Override
    public void setColor(Color color) {
        super.setColor(color);
        Gdx.input.vibrate(20);
    }

    public Ellipse getShape(){
        return new Ellipse(getCenterX(), getCenterY(), 400, 300);
    }
}
