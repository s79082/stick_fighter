package org.mo.stickfighter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class MyActor extends Image {

    float last_x;

    Animation<TextureRegion> animation;

    MyAnimation idleAnimation, walkAnimation, current;


    public MyActor(TextureRegion[] region) {
        super(region[0]);

        setOrigin(getWidth() / 2, getHeight() / 2);

        setPosition(100, 100);
        last_x = 100;
        scaleBy(5, 5);

        animation = new Animation<TextureRegion>(0.25f, region);
        idleAnimation = new MyAnimation(region, 0, 2);
        walkAnimation = new MyAnimation(region, 1, 3);

        current = idleAnimation;
    }
    @Override
    public void act(float delta) {
        super.act(delta);

        if (getX() != last_x)
            current = walkAnimation;
        else
            current = idleAnimation;

        last_x = getX();

        current.update();
        setDrawable(new TextureRegionDrawable(current.getKeyFrame()));

    }

    public void change()
    {
        if (current == walkAnimation)
            current = idleAnimation;
        else
            current = walkAnimation;
    }
}
