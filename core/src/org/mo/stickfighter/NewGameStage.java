package org.mo.stickfighter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class NewGameStage extends Stage
{

    MyActor player;
    Texture anim_sheet;
    TextureRegion[] sheet_pieces;
    Animation<TextureRegion> animation;
    float anim_time = 0f;

    Group bullets;
    Spawner<Bullet> bulletSpawner;
    Button shootButton;

    Joystick joystick;

    Actor test;

    @Override
    public void act() {
        super.act();



        if(bulletSpawner.readyToSpawn()) {
            Bullet b = bulletSpawner.spawn();
            b.setPosition(player.getX(), player.getY());
            //bullets.addActor(b);
        }


    }

    public NewGameStage()
    {
        super(new ScreenViewport(new OrthographicCamera(100, 100)));
        anim_sheet = new Texture("fighter_sheet.png");
        sheet_pieces = TextureRegion.split(anim_sheet, anim_sheet.getWidth() / 4,
                anim_sheet.getHeight() / 1)[0];
        animation = new Animation<TextureRegion>(0.25f, sheet_pieces);
        //gameStage = new GameStage();
        //player = new Image(animation.getKeyFrame(anim_time, true));
        //player = new Image();
        player = new MyActor(sheet_pieces);

        //Drawable d = new TextureRegionDrawable(new Texture("badlogic.jpg"));
        //shootButton = new TextButton("Shoot", new TextButton.TextButtonStyle(d, d, d, BitmapFont.

        //));
//        shootButton.setPosition(600, 600);
  //      addActor(shootButton);
        //player.setDrawable(new TextureRegionDrawable(animation.getKeyFrame(anim_time, true)));
        //player.setPosition(200, 200);
        //player.scaleBy(5);
        //this.addActor(player);
        bullets = new Group();
        //this.addActor(bullets);
        joystick = new Joystick(new Vector2(1000, 1000));
        //addActor(joystick);

        Drawable background, knob;
        background = new TextureRegionDrawable(new Texture("badlogic.jpg"));
        knob = new TextureRegionDrawable(new Texture("fighter_sheet.png"));

        /*
        Touchpad.TouchpadStyle style = new Touchpad.TouchpadStyle(background, knob);
        Touchpad touchpad = new Touchpad(1000, style);
        touchpad.setBounds(200, 100, 500, 500);
        touchpad.setScale(5);
        touchpad.setTouchable(Touchable.enabled);
        touchpad.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.log("pre", "ssed");
            }
        });

        Gdx.app.log("pre", touchpad.getTouchable().toString());
*/
        Texture tmp_tex = new Texture("badlogic.jpg");
        test = new Image(tmp_tex);
        test.setBounds(100, 100, tmp_tex.getWidth(), tmp_tex.getHeight());

        test.setTouchable(Touchable.enabled);

        test.addListener(new InputListener(){
            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                Gdx.app.log("pre", "ssed");
            }
        });

        Gdx.app.log("pre", String.valueOf(test.getWidth()));

        addActor(test);
        //addActor(touchpad);
        //Bullet b = new Bullet();
        //b.setPosition(100, 100);
        //addActor(b);


        bulletSpawner = new Spawner<>(1, Bullet.class);

    }
/*
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        float gameY = Gdx.graphics.getHeight() - screenY;
        float dist = (new Vector2(player.getX(), player.getY()).sub(new Vector2(screenX, gameY))).len();
        player.addAction(Actions.moveTo(screenX, Gdx.graphics.getHeight() - screenY
                , dist * 0.001f));
        player.change();
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

*/

}
