package com.anvol.coloripher;

import com.anvol.coloripher.screens.GameoverScreen;
import com.anvol.coloripher.screens.GameplayScreen;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;


public class MyGdxGame extends Game {
    public GameplayScreen gameScreen;
    public GameoverScreen overScreen;

    public Music backgroundTrack;

    @Override
	public void create () {
        gameScreen = new GameplayScreen(this);
        overScreen = new GameoverScreen(this);
        this.setScreen(gameScreen);
        backgroundTrack = Gdx.audio.newMusic(Gdx.files.internal("back.ogg"));
        backgroundTrack.setLooping(true);
        backgroundTrack.play();
	}

	@Override
	public void render () {
        super.render();
	}
}
