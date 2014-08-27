package com.anvol.coloripher.screens;

import com.anvol.coloripher.MyGdxGame;
import com.anvol.coloripher.actors.BulbActor;
import com.anvol.coloripher.actors.CircleActor;
import com.anvol.coloripher.actors.GameplayStage;
import com.anvol.coloripher.actors.ScoreActor;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by Andrey on 19.08.2014.
 */
public class GameplayScreen extends BaseScreen {

    private GameplayStage stage;

    final MyGdxGame game;

    public GameplayScreen(final MyGdxGame gam) {
        game = gam;

        camera = new OrthographicCamera();

        viewport = new FillViewport(800, 480, camera);
        stage = new GameplayStage(viewport);
        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);
    }

    public void startNewGame() {
        stage.dispose();
        stage = new GameplayStage(viewport);
        game.backgroundTrack.setVolume(1f);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        // 98C000 3D4C53
        Gdx.gl.glClearColor((0x3D & 0xFF) / 255f, (0x4C & 0xFF) / 255f, (0x53 & 0xFF) / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // tell the camera to update its matrices.
        camera.update();
        stage.act(delta);
        stage.draw();

        if (stage.finished){
            game.backgroundTrack.setVolume(0.5f);
            game.overScreen.setScore(stage.getMaxScore());
            game.setScreen(game.overScreen);
        }
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
