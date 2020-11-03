package org.mo.stickfighter;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class Joystick extends Actor
{
    Circle outer, inner;
    Circle middle;
    Vector2 position;
    float radius = 300;
    ShapeRenderer renderer;

    Texture texture;

    private Vector2 displacement;

    private class JoystickInputListener extends InputListener
    {

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        //renderer.setProjectionMatrix(batch.getProjectionMatrix());

//        batch.begin();
        batch.draw(texture, position.x, position.y);
  //      batch.end();
        render();
    }

    @Override
    public void act(float delta) {

        super.act(delta);

    }

    private void createTexture()
    {
        Pixmap pixmap = new Pixmap(600, 600, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.BLACK);
        pixmap.drawCircle(300, 300, 600);
        texture = new Texture(pixmap);
        pixmap.dispose();
    }

    public Joystick(final Vector2 p) {

        Vector2 pos = new Vector2(p.x + 300, p.y + 300);

        outer = new Circle(pos.x, pos.y, 300);
        inner = new Circle(pos.x, pos.y, 70);

        middle = new Circle(pos.x, pos.y, outer.radius - inner.radius);

        position = pos;
        renderer = new ShapeRenderer();
        displacement = new Vector2(Vector2.Zero);

        setBounds(p.x, p.y,
                outer.radius * 2, outer.radius * 2);

        Gdx.gl.glLineWidth(10);

        createTexture();

        addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                inner.setPosition(outer.x, outer.y);
                displacement = new Vector2(Vector2.Zero);

                return;
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                Gdx.app.log("dbg", "dragged");
                float gameX, gameY;
                gameX = x;
                //gameY = Gdx.graphics.getHeight() - y;
                gameY = y;
                Vector2 center, diff;
                Vector2 position = new Vector2(gameX, gameY);

                //Circle tmp_outer = new Circle(outer.x, outer.y, outer.radius - inner.radius / 2);

                // check if touch in outr circle
                if (!outer.contains(position))
                    return;

                center = new Vector2(outer.x, outer.y);

                diff = center.sub(position);
                displacement = diff;
                Gdx.app.log("displ", diff.toString());

                inner.setPosition(position);
                return;
            }
        });

        setTouchable(Touchable.enabled);

/*
        Gdx.input.setInputProcessor(new InputProcessor() {
            @Override
            public boolean keyDown(int keycode) {
                return false;
            }

            @Override
            public boolean keyUp(int keycode) {
                return false;
            }

            @Override
            public boolean keyTyped(char character) {
                return false;
            }

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {


                return false;
            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {

                inner.setPosition(outer.x, outer.y);
                displacement = new Vector2(Vector2.Zero);

                return false;
            }

            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                float gameX, gameY;
                gameX = screenX;
                gameY = Gdx.graphics.getHeight() - screenY;

                Vector2 center, diff;
                Vector2 position = new Vector2(gameX, gameY);

                //Circle tmp_outer = new Circle(outer.x, outer.y, outer.radius - inner.radius / 2);

                // check if touch in outr circle
                if (!outer.contains(position))
                    return false;

                center = new Vector2(outer.x, outer.y);

                diff = center.sub(position);
                displacement = diff;
                Gdx.app.log("displ", diff.toString());

                inner.setPosition(position);
                return false;
            }

            @Override
            public boolean mouseMoved(int screenX, int screenY) {
                return false;
            }

            @Override
            public boolean scrolled(int amount) {
                return false;
            }
        });


         */
    }

    public Vector2 getStickDisplacement()
    {
        return displacement.scl(-1f);
    }

    public Vector2 getRelativeStickDispl()
    {
        Vector2 tmp = displacement;
        tmp.set(displacement.x / outer.radius, displacement.y / radius);
        Gdx.app.log("dsipl_rel", tmp.toString());
        return tmp;
    }

    public void render()
    {

        //drawCircle(renderer, outer);
        //drawCircle(renderer, inner);

        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.setColor(Color.BLACK);
        renderer.circle(outer.x, outer.y, outer.radius);
        //renderer.circle(outer.x + outer.radius, outer.y + outer.radius, outer.radius);
        renderer.circle(inner.x , inner.y, inner.radius);
        renderer.end();
    }

    private void drawCircle(ShapeRenderer b, Circle c)
    {
        b.begin(ShapeRenderer.ShapeType.Line);
        b.setColor(Color.BLACK);
        b.circle(c.x + outer.radius, c.y + outer.radius, c.radius);
        b.end();
    }

}
