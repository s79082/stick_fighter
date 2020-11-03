package org.mo.stickfighter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

import java.util.Arrays;

public class MyAnimation {
    Animation<TextureRegion> animation;
    int len;
    float time = 0;

    public MyAnimation(TextureRegion[] sheet, int start, int len)
    {
        TextureRegion[] frames = Arrays.copyOfRange(sheet, start, start + len);
        animation = new Animation<TextureRegion>(0.25f, frames);
    }

    public TextureRegion getKeyFrame()
    {
        return animation.getKeyFrame(time, true);
    }

    public void update()
    {
        time += Gdx.graphics.getDeltaTime();
    }
}
