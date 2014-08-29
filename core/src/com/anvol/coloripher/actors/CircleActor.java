package com.anvol.coloripher.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

;

public class CircleActor extends Actor {
    protected float radius = 0.0f;
    protected ShapeRenderer shapeRenderer;
    public boolean connected = false;
    public final BubbleType type = BubbleType.NORMAL;

    public CircleActor(ShapeRenderer shapeRenderer, Color color, float radius) {
        this.shapeRenderer = shapeRenderer;
        this.setColor(color);
        this.radius = radius;
        this.setSize(2 * radius, 2 * radius);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.end();

        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        shapeRenderer.setTransformMatrix(batch.getTransformMatrix());
        shapeRenderer.translate(getCenterX(), getCenterY(), 0);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(getColor());

        shapeRenderer.circle(0, 0, radius);
        shapeRenderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);

        batch.begin();
    }

    @Override
    protected void sizeChanged() {
        super.sizeChanged();
        setBounds(this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }
}
