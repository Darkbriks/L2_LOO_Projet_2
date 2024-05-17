package com.scramble_like.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.scramble_like.game.essential.GameCamera;
import com.scramble_like.game.essential.TickableObject;
import com.scramble_like.game.map.MainMenu;

import java.util.ArrayList;
import java.util.List;

public class ScrambleLikeApplication extends Game
{
	private static ScrambleLikeApplication instance;
	public static ScrambleLikeApplication getInstance() { return instance; }

	private ShapeRenderer shapeRenderer;
	private SpriteBatch batch;
	private BitmapFont font;
	private GameCamera camera;

	public ShapeRenderer getShapeRenderer() { return shapeRenderer; }
	public SpriteBatch getBatch() { return batch; }
	public BitmapFont getFont() { return font; }
	public GameCamera getCamera() { return camera; }

	private List<TickableObject> tickableObjects;
	public List<TickableObject> getTickableObjects() { return tickableObjects; }
	public void AddTickableObject(TickableObject tickableObject) { tickableObjects.add(tickableObject); }
	public void UpdateTickableObjects(float deltaTime) { for (int i = 0; i < tickableObjects.size(); i++) { tickableObjects.get(i).Tick(deltaTime); } }

	@Override
	public void create ()
	{
		if (instance == null) { instance = this; }
		else { Gdx.app.log("ScrambleLikeApplication", "Il ne peut y avoir qu'une seule instance de ScrambleLikeApplication."); this.dispose(); }

		shapeRenderer = new ShapeRenderer();
		batch = new SpriteBatch();
		font = new BitmapFont();
		camera = new GameCamera(GameConstant.WIDTH, GameConstant.HEIGHT);

		tickableObjects = new ArrayList<>();

		setScreen(new MainMenu());
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