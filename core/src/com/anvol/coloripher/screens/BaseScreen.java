package com.anvol.coloripher.screens;

import com.anvol.coloripher.MyGdxGame;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by Andrey on 26.08.2014.
 */
public abstract class BaseScreen implements Screen {

    protected MyGdxGame game;
    protected Viewport viewport;
    protected Camera camera;

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
