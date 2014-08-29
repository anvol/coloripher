package com.anvol.coloripher.screens;

import com.anvol.coloripher.MyGdxGame;
import com.anvol.coloripher.actors.GameplayStage;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by Andrey on 26.08.2014.
 */
public class GameoverScreen extends BaseScreen{

    Stage stage;
    long score = 0;


    public GameoverScreen(MyGdxGame game) {
        this.game = game;
        this.score = 0;

        camera = new OrthographicCamera();

        viewport = new FillViewport(800, 480, camera);
        Gdx.input.setCatchBackKey(true);
        stage = new Stage(viewport) {
            @Override
            public boolean keyDown(int keyCode) {
                if (keyCode == Input.Keys.BACK) {
                    Gdx.app.exit();
                }
                return super.keyDown(keyCode);
            }
        };
        Gdx.input.setInputProcessor(stage);

        addUI();
    }

    private void addUI() {
        Label score = new Label("GAME OVER\nYour score: " + Long.toString(this.score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        score.setFontScale(3);

        score.setPosition(200, 230);
        stage.addActor(score);

        Label hint = new Label("Touch screen to play again or press BACK to exit", new Label.LabelStyle(new BitmapFont(), Color.LIGHT_GRAY));

        hint.setPosition(200, 130);
        stage.addActor(hint);

        totalVisible = 0;
    }

    public void setScore(long score){
        this.score = score;
        stage.clear();
        addUI();
    }

    float totalVisible;

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        totalVisible+=delta;
        if (Gdx.input.justTouched() && totalVisible > 1) {
            game.gameScreen.startNewGame();
            game.setScreen(game.gameScreen);
        }
        stage.act(delta);
        stage.draw();
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
