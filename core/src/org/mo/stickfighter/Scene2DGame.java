package org.mo.stickfighter;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class Scene2DGame extends ApplicationAdapter {

    GameStage gameStage;
    NewGameStage stage;


    @Override
    public void create()
    {
        stage = new NewGameStage();
        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();

        stage.draw();

    }
}
