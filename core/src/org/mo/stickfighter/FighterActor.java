package org.mo.stickfighter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveBy;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.scaleTo;

public class FighterActor extends Actor
{

    Sprite frame;
    TextureRegion[] frames;
    Animation<TextureRegion> animation;
    float timer;

    @Override
    protected void positionChanged() {
        super.positionChanged();
        this.frame.setPosition(getX(), getY());

    }

    @Override
    protected void sizeChanged() {
        super.sizeChanged();


    }

    public FighterActor()
    {
        super();

        Texture tmp_texture = new Texture("fighter_sheet.png");
        frames = TextureRegion.split(tmp_texture, tmp_texture.getWidth() / 4,
                tmp_texture.getHeight() / 1)[0];

        //setBounds(100, 100, tmp_texture.getWidth() / 4, tmp_texture.getHeight() / 1);
        frame = new Sprite();

        setBounds(0, 0, tmp_texture.getWidth()  / 4, tmp_texture.getHeight() / 1);

        frame.setBounds(getX(), getY(), getWidth(), getHeight());
        animation = new Animation<>(0.25f, frames);

        timer = 0f;


        setOriginX(Gdx.graphics.getWidth() / 2);
        setOriginY(Gdx.graphics.getHeight() / 2);
        //setOriginX(getWidth() / 2);
        //setOriginY(getHeight() / 2);

        //setScale(2f);
        //addAction(Actions.scaleBy(0.5f, 0.5f));
        scaleBy(-0.5f, -0.5f);
        //this.act(0);
        //this.rotateBy(50);
        Gdx.app.log("", String.valueOf(getScaleX()));

        addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                addAction(Actions.moveBy(50, 50, 1));
                addAction(Actions.rotateBy(60, 2));
                return true;
            }
        });


    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha)
    {
        timer += Gdx.graphics.getDeltaTime();
        TextureRegion frame_texture = animation.getKeyFrame(timer, true);

        frame.setTexture(frame_texture.getTexture());
        frame.draw(batch);

        batch.draw(frame_texture, getX(), getY(), getHeight(), getWidth(), getOriginX(), getOriginY(), getScaleX(), getScaleY(), getRotation());

        //batch.draw(frame_texture, 100, 100);
    }
}
