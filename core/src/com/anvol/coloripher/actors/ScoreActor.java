package com.anvol.coloripher.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

/**
 * Created by Andrey on 25.08.2014.
 */
public class ScoreActor extends Label {
    protected long value = 0;
    private long maxValue;
    private long level;

    public ScoreActor() {
        super("Level: 1\nScore: 0\nMax: 0", new LabelStyle(new BitmapFont(), Color.WHITE));
        value = 0;
        level = 1;
        maxValue = 0;
    }

    public void addScore(long add){
        value += add;
        maxValue = maxValue < value ? value : maxValue;
        setText("Level: " + Long.toString(level) + "\nScore: " + Long.toString(value) + "\nMax: " + Long.toString(maxValue));
    }

    public void setLevel(long level) {
        this.level = level;
    }

    public long getScore(){
        return value;
    }

    public long getMaxScore(){
        return maxValue;
    }
}
