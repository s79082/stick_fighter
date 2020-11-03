package org.mo.stickfighter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameHUD implements Disposable{
    public Stage stage;
    private Viewport viewport;

    //score && time tracking variables
    private Integer worldTimer;
    private float timeCount;
    private static Integer score;
    private boolean timeUp;

    //Scene2D Widgets
    private Label countdownLabel, timeLabel, linkLabel;
    private static Label scoreLabel;

    public GameHUD(SpriteBatch sb, Button b, Touchpad t) {
        //define tracking variables
        worldTimer = 250;
        timeCount = 0;
        score = 0;

        //setup the HUD viewport using a new camera seperate from gamecam
        //define stage using that viewport and games spritebatch
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new OrthographicCamera());
        stage = new Stage(viewport, sb);

        //define a table used to organize hud's labels
        Table table = new Table();
        table.top();
        table.setFillParent(true);

        //add labels to table, padding the top, and giving them all equal width with expandX
       /*
        table.add(linkLabel).expandX().padTop(10);
        table.add(timeLabel).expandX().padTop(10);
        table.row();
        table.add(scoreLabel).expandX();
        table.add(countdownLabel).expandX();
        */



        table.bottom();
        ChangeListener touchpadListener = new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.log("asdas", "dsad");
            }
        };
        t.addListener(touchpadListener);
        table.add(t).width(500).height(500).expandX();
        table.add(b).width(300).height(300).expandX();

        //add table to the stage
        stage.addActor(table);

    }

    public void update(float dt) {
    }

    public static void addScore(int value) {
        score += value;
        scoreLabel.setText(String.format("%06d", score));
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    public boolean isTimeUp() {
        return timeUp;
    }


    public static Label getScoreLabel() {
        return scoreLabel;
    }

    public static Integer getScore() {
        return score;
    }
}
