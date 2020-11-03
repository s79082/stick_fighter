package org.mo.stickfighter;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import jdk.nashorn.internal.scripts.JO;

public class StickFighterGame extends ApplicationAdapter {
	SpriteBatch batch;

	//Fighter fighter;

	Vector2 position, displacement;

    Animation<TextureRegion> animation;
    TextureRegion[] animation_sheet;
    float frame_time;

    int FRAME_WIDTH, FRAME_HEIGHT;

    float WORLD_WIDTH, WORLD_HEIGHT;

    OrthographicCamera camera;

    Joystick joystick;
	
	@Override
	public void create () {
		batch = new SpriteBatch();

		Texture texture = new Texture("fighter_sheet.png");
		FRAME_WIDTH = texture.getWidth();
		FRAME_HEIGHT = texture.getHeight();

		WORLD_WIDTH = Gdx.graphics.getWidth();
		WORLD_HEIGHT = Gdx.graphics.getHeight();

		Gdx.app.log("w", String.valueOf(WORLD_WIDTH));
		Gdx.app.log("h", String.valueOf(WORLD_HEIGHT));


		animation_sheet = TextureRegion.split(texture, FRAME_WIDTH/ 4, FRAME_HEIGHT / 1)[0];
		animation = new Animation<>(0.25f, animation_sheet);
		frame_time = 0;

		camera = new OrthographicCamera(WORLD_WIDTH, WORLD_HEIGHT);
		camera.setToOrtho(false, WORLD_WIDTH * (WORLD_WIDTH / WORLD_HEIGHT), WORLD_HEIGHT );
		//camera.setToOrtho(false, WORLD_HEIGHT, WORLD_WIDTH * (WORLD_HEIGHT / WORLD_WIDTH));
		//fighter = new Fighter(new Vector2(150, 150));

		position = new Vector2(Vector2.Zero);
		displacement = new Vector2(Vector2.Zero);

		joystick = new Joystick(new Vector2(500f, 500f));
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		displacement = joystick.getStickDisplacement().scl(1f);

		position.add(Vector2.X.scl(displacement.x));
		Gdx.app.log("move", String.valueOf(displacement.x * -1f));

		joystick.render();

		//fighter.update();
        frame_time += Gdx.graphics.getDeltaTime();
        TextureRegion frame = animation.getKeyFrame(frame_time, true);

		batch.begin();
		batch.setProjectionMatrix(camera.combined);

		batch.draw(frame, position.x, position.y, 0, 0, FRAME_WIDTH,
				FRAME_HEIGHT, 4, 4, 0);
		//fighter.render(batch);

		batch.end();

	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
