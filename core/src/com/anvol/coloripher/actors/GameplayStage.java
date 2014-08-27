package com.anvol.coloripher.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Iterator;

/**
 * Created by Andrey on 19.08.2014.
 */
public class GameplayStage extends Stage {

    protected Array<CircleActor> indiego = new Array<CircleActor>();
    protected Group groupBulb;
    protected ScoreActor score;
    final ShapeRenderer sr = new ShapeRenderer();
    final BulbActor bulb = new BulbActor(sr, Color.GRAY);
    final public Array<Color> colors = new Array<Color>(2);

    protected Pool<CircleActor> poolActors;
    protected Array<CircleActor> listActiveActors = new Array<CircleActor>(false, 100);
    private long level = 1;
    public boolean finished = false;


    public GameplayStage(Viewport viewport) {
        super(viewport);
        // colors from https://kuler.adobe.com/Pirate-Pop-Colors-color-theme-4182026/
        colors.add(new Color((0x98 & 0xFF) / 255f, (0xC0 & 0xFF) / 255f, 0, 1));
        colors.add(new Color((0xEA & 0xFF) / 255f, (0x2E & 0xFF) / 255f, (0x49 & 0xFF) / 255f, 1));

//        colors.add(new Color((0xFF & 0xFF) / 255f, (0xE1 & 0xFF) / 255f, (0x1A & 0xFF) / 255f, 1));
//        colors.add(new Color((0x0C & 0xFF) / 255f, (0xDB & 0xFF) / 255f, (0xE8 & 0xFF) / 255f, 1));

        final GameplayStage s = this;
        poolActors = new Pool<CircleActor>(100) {
            @Override
            protected CircleActor newObject() {
                return new CircleActor(s, sr, colors.random(), MathUtils.random(5, 50));
            }
        };

        createUI();
    }

    void createUI(){
        Group groupUI = new Group();
        groupBulb = new Group();
        bulb.setPosition(200, -220);

        // buttons
        final CircleActor btnLeft = new CircleActor(sr, colors.get(0), 45);
        btnLeft.setPosition(45, 50);
        btnLeft.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                bulb.setColor(btnLeft.getColor());
                return true;
            }
        });
        groupUI.addActor(btnLeft);

        final CircleActor btnRight = new CircleActor(sr, colors.get(1), 45);
        btnRight.setPosition(675, 50);
        btnRight.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                bulb.setColor(btnRight.getColor());
                return true;
            }
        });
        groupUI.addActor(btnRight);

        ScoreActor scoreActor = new ScoreActor();
        scoreActor.setPosition(10, 400);
        groupUI.addActor(scoreActor);

        score = scoreActor;
        addActor(groupUI);
        addActor(groupBulb);
        addActor(bulb);
    }

    public void addScore(long add){
        score.addScore(add);
    }

    float deltaNewBubble = 0;

    @Override
    public void act(float delta) {
        super.act(delta);

        if (!finished && score.getScore() < 0) {
            listActiveActors.clear();
            poolActors.clear();
            score.addScore(-score.getScore());
            finished = true;
            return;
        }

        deltaNewBubble += delta;
        if (deltaNewBubble >= 1.5f-level*0.05f) {
            deltaNewBubble = 0;
            createBubble();
        }

        checkBubbleCollisions();
        setLevel(score.getScore());
    }

    long lastLevel = 1;

    final long baseLevel = 20000;
    private void setLevel(long score) {
        level = 2*score / baseLevel;
        if (level <= 0) level = 1;
        if (lastLevel > level) finished = true;

        if (lastLevel == 9 && level == 10){
            addNewButtonsAndColors();
        }

        lastLevel = level;
        this.score.setLevel(level);
    }

    private void addNewButtonsAndColors() {
        colors.add(new Color((0xFF & 0xFF) / 255f, (0xE1 & 0xFF) / 255f, (0x1A & 0xFF) / 255f, 1));
        colors.add(new Color((0x0C & 0xFF) / 255f, (0xDB & 0xFF) / 255f, (0xE8 & 0xFF) / 255f, 1));


        final CircleActor btnLeftUpper = new CircleActor(sr, colors.get(2), 45);
        btnLeftUpper.setPosition(25, 140);
        btnLeftUpper.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                bulb.setColor(btnLeftUpper.getColor());
                return true;
            }
        });
        addActor(btnLeftUpper);

        final CircleActor btnRightUpper = new CircleActor(sr, colors.get(3), 45);
        btnRightUpper.setPosition(695, 140);
        btnRightUpper.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                bulb.setColor(btnRightUpper.getColor());
                return true;
            }
        });
        addActor(btnRightUpper);
    }

    @Override
    public boolean keyDown(int keyCode) {
        if (keyCode == Input.Keys.BACK) {
            Gdx.app.exit();
        }
        return super.keyDown(keyCode);
    }

    private void checkBubbleCollisions() {
        for (Iterator<CircleActor> it = listActiveActors.iterator(); it.hasNext(); ) {
            CircleActor ca = it.next();
            if (!ca.isVisible()) {
                it.remove();
                poolActors.free(ca);
            }
        }

        for (CircleActor actor : listActiveActors){

            if (actor.connected){
                actor.setColor(bulb.getColor());
                continue;
            }

            if (bulb.getShape().contains(actor.getX(), actor.getY())){
                if (actor.getColor().toIntBits() == bulb.getColor().toIntBits()){
                    actor.connected = true;
                    addScore((long) (actor.radius * 100));
                }
                else
                {
                    actor.connected = true;
                    addScore((long) (-actor.radius * 120));
                }
            }
        }
    }

    private void createBubble() {
        CircleActor actor = poolActors.obtain();
        actor.setVisible(true);
        actor.setPosition(MathUtils.random(-100, 900), 500);

        actor.addAction(Actions.sequence(
                Actions.moveTo(400, -150, MathUtils.random(5 - 0.2f * (level - 1), 10 - 0.4f * (level - 1))),
                Actions.removeActor()));
        groupBulb.addActor(actor);
        listActiveActors.add(actor);
    }

    public long getMaxScore() {
        return score.getMaxScore();
    }
}
