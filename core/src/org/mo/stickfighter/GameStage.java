package org.mo.stickfighter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameStage extends Stage
{

    Texture texture = new Texture("badlogic.jpg");
    TextureRegion[] region;
    SpriteBatch batch = new SpriteBatch();

    Joystick joystick;

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        //addAction(Actions.moveTo(screenX, Gdx.graphics.getHeight() - screenY, 1));
        addAction(Actions.moveBy(60, 0));

        return false;

    }

    public GameStage()
    {
        super();

        //super(new FitViewport(1000, 1000));
        //OrthographicCamera camera = new OrthographicCamera();
        //Viewport viewport = new FillViewport(1000, 1000, camera);
        //viewport.apply();
        //setViewport(viewport);

        //region = new TextureRegion(texture);

        region = TextureRegion.split(texture, texture.getWidth(), texture.getHeight())[0];


        Gdx.input.setInputProcessor(this);

        joystick = new Joystick(new Vector2(200, 200));
        addActor(joystick);

        final Actor fighter = new FighterActor();
        addActor(fighter);



    }

    @Override
    public void act() {
        super.act();
    }

    @Override
    public void draw() {
        super.draw();

        batch.begin();
        //batch.draw(texture, 10, 10);
        batch.draw(region[0], 100, 100);
        batch.end();
    }
}
