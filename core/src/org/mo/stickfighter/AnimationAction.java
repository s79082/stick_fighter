package org.mo.stickfighter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.RelativeTemporalAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class AnimationAction extends RelativeTemporalAction {

    Animation<TextureRegion> animation;
    float timer = 0;

    public AnimationAction(Texture sheet, Vector2 pos) {
        super();

        TextureRegion[] frames = TextureRegion.split(sheet, sheet.getWidth() /4,
                sheet.getHeight() / 1)[0];

        //actor.scaleBy(5);
        animation = new Animation<TextureRegion>(0.5f, frames);
    }

    @Override
    protected void updateRelative(float percentDelta) {
        timer += Gdx.graphics.getDeltaTime();
        ((Image) target).setDrawable(new TextureRegionDrawable(animation.getKeyFrame(timer, true)));

    }
}
