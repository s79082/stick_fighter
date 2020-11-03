package org.mo.stickfighter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

import java.util.List;

public class Spawner<T extends Actor>
{
    float intervall, time;
    Group group;
    Class<T> tClass;

    public Spawner(float i, Class<T> c)
    {
        intervall = i;
        tClass = c;
        time = 0;
    }

    public Bullet spawn()
    {
        try {
            return new Bullet();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public boolean readyToSpawn()
    {
        time += Gdx.graphics.getDeltaTime();
        if (time >= intervall)
        {
            time = 0;
            return true;
        }

        return false;
    }

}
