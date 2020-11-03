package org.mo.stickfighter;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Bullet extends Image
{
    float speed_x = 10f;

    public Bullet()
    {
        super(new Texture("badlogic.jpg"));
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        float x, y;
        x = getX();
        y = getY();

        setPosition(x + speed_x, y);
    }
}
