package org.mo.stickfighter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;

public class Explosion extends Image
{
    float timer = 0;
    Animation<TextureRegionDrawable> animation;

    public Explosion(Texture framesheet, Vector2 pos) {
        super();

        int tileWidth, tileHeight;
        tileWidth = framesheet.getWidth() /4;
        tileHeight = framesheet.getHeight() / 1;

        setPosition(pos.x, pos.y);
        setSize(tileWidth, tileHeight);
        setScale(5);

        TextureRegion[] frames = TextureRegion.split(framesheet, tileWidth, tileHeight)[0];

        Array<TextureRegionDrawable> frames_drwb = new Array<>();
        for(TextureRegion tr: frames)
            frames_drwb.add(new TextureRegionDrawable(tr));

        animation = new Animation<TextureRegionDrawable>(0.25f, frames_drwb);

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        timer += Gdx.graphics.getDeltaTime();
        this.setDrawable(animation.getKeyFrame(timer, true));
        if(timer >= animation.getAnimationDuration())
            addAction(Actions.removeActor(this));
    }
}
