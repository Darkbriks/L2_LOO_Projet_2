package com.scramble_like.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.scramble_like.game.essential.GameCamera;
import com.scramble_like.game.essential.exception.GameIsNullException;
import com.scramble_like.game.map.MainMenu;

public class ScrambleLikeApplication extends Game
{
	private ShapeRenderer shapeRenderer;
	private SpriteBatch batch;
	private BitmapFont font;
	private GameCamera camera;

	public ShapeRenderer getShapeRenderer() { return shapeRenderer; }
	public SpriteBatch getBatch() { return batch; }
	public BitmapFont getFont() { return font; }
	public GameCamera getCamera() { return camera; }

	@Override
	public void create ()
	{
		shapeRenderer = new ShapeRenderer();
		batch = new SpriteBatch();
		font = new BitmapFont();
		camera = new GameCamera(GameConstant.WIDTH, GameConstant.HEIGHT);

		try { setScreen(new MainMenu(this)); }
		catch (GameIsNullException e) { System.err.println(e.getMessage()); Gdx.app.exit(); }
	}

	@Override
	public void render ()
	{
		batch.setProjectionMatrix(camera.getCombined());
		super.render();
	}

	@Override
	public void resize(int width, int height)
	{
		camera.resize(width, height);
		super.resize(width, height);
	}

	@Override
	public void dispose ()
	{
		shapeRenderer.dispose();
		batch.dispose();
		font.dispose();
		camera.dispose();
	}
}