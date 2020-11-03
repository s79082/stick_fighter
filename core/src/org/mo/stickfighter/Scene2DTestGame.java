package org.mo.stickfighter;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.util.Arrays;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class Scene2DTestGame extends ApplicationAdapter {

    Stage stage;
    Image player;

    Texture background;

    SpriteBatch batch;

    Animation<TextureRegion> walkAnimation, idleAnimation, current;
    float anim_timer = 0;

    Touchpad touchpad;
    Button button;

    GameHUD gameHUD;

    Group bullets;
    @Override
    public void create() {
        super.create();
        final Texture text = new Texture("fighter_sheet.png");
        background = new Texture("bg.png");

        final Touchpad.TouchpadStyle touchpadStyle = new Touchpad.TouchpadStyle();
        touchpadStyle.background = new TextureRegionDrawable(new Texture("badlogic.jpg"));
        batch = new SpriteBatch();
        stage = new Stage();

        TextureRegion[] frames, tmp_frames;
        float tile_width, tile_heigh;
        tile_width = text.getWidth() / 4;
        tile_heigh = text.getHeight() / 1;
        frames = TextureRegion.split(text,(int) tile_width,(int) tile_heigh)[0];

        // walk animation has 2nd to 4th frame, last index is exclusive so it becomes 3
        tmp_frames = Arrays.copyOfRange(frames, 1, 1 + 3);

        walkAnimation = new Animation<>(0.25f, tmp_frames);

        tmp_frames = Arrays.copyOfRange(frames, 0, 0 + 2);

        idleAnimation = new Animation<>(0.25f, tmp_frames);

        current = idleAnimation;


        touchpadStyle.knob = new TextureRegionDrawable(tmp_frames[0]);
        touchpad = new Touchpad(20, touchpadStyle);
        touchpad.setPosition(100, 100);
        touchpad.setTouchable(Touchable.enabled);
        touchpad.setSize(600, 600);

        button = new Button(new TextureRegionDrawable(new Texture("button.png")));
        button.setPosition(1500, 400);
        button.setSize(300, 300);

        bullets = new Group();

        //stage.addActor(button);


        player = new Image(current.getKeyFrame(anim_timer, true));
        //player = new Image(text);

        player.setPosition(50, 50);
        //player.setPosition(Gdx.graphics.getWidth() - t, 100);
        player.setSize(100, 100);
        player.setTouchable(Touchable.enabled);
        //player.scaleBy(3);
        ClickListener buttonListener = new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("drag", "down");
                final Image img = new Image(new Texture("bullet.png"));
                img.setPosition(player.getX(), player.getY());
                //img.setSize(100, 100);
                img.addAction(Actions.sequence(Actions.moveBy(100, 0, 0.5f),
                        Actions.parallel(Actions.removeActor(img),
                        Actions.run(new Runnable() {
                            @Override
                            public void run() {
                                stage.addActor(new Explosion(new Texture("explosion_sheet.png"),
                                        new Vector2(img.getX(), img.getY())));
                            }
                        }))));
                bullets.addActor(img);
                return true;
            }
        };

        button.addListener(buttonListener);

        Camera camera = stage.getCamera();
        camera.position.x = player.getX();
        camera.position.y = player.getY();
        camera.viewportWidth = 500;
        camera.viewportHeight = 500;

        ChangeListener touchpadListener = new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                player.addAction(Actions.moveBy(touchpad.getKnobPercentX() * 10,
                        touchpad.getKnobPercentY() * 10));

                Camera camera = stage.getCamera();
                camera.position.x = player.getX();
                camera.position.y = player.getY();
            }
        };
        touchpad.addListener(touchpadListener);
        //stage.addActor(touchpad);

        gameHUD = new GameHUD(batch, button, touchpad);

        stage.addActor(player);

        stage.addActor(bullets);
        Gdx.input.setInputProcessor(gameHUD.stage);
    }

    @Override
    public void render() {
        super.render();

        Gdx.gl.glClearColor(1, 1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        float dt = Gdx.graphics.getDeltaTime();
        //Gdx.app.log("1/FPS:", String.valueOf(dt));
        anim_timer += dt;
        //player.addAction(Actions.moveBy(5, 5));
        player.setDrawable(new TextureRegionDrawable(current.getKeyFrame(anim_timer, true)));

        //bullets.addAction(Actions.moveBy(2, 0));

        stage.act();

        batch.setProjectionMatrix(stage.getCamera().combined);
        batch.begin();
        batch.draw(background, 0, 0);
        batch.end();

        gameHUD.update(Gdx.graphics.getDeltaTime());

        /*
        Vector2 displ = joystick.getStickDisplacement();
        if (displ.len() >100) {

            Gdx.app.log("das", "dasdd");

            player.addAction(Actions.moveBy(displ.x * 0.1f, displ.y * 0.1f));
        }

         */
        stage.draw();

        batch.setProjectionMatrix(gameHUD.stage.getCamera().combined);
        gameHUD.stage.draw();

    }
}
