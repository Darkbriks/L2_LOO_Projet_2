package com.scramble_like.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.scramble_like.game.essential.ControllersListener;
import com.scramble_like.game.essential.GameCamera;
import com.scramble_like.game.essential.TickableObject;
import com.scramble_like.game.essential.chunk.ChunkHelper;
import com.scramble_like.game.essential.factory.ImageFactory;
import com.scramble_like.game.essential.factory.SoundFactory;
import com.scramble_like.game.map.MainMenu;
import com.scramble_like.game.map.SplashScreen;

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
	private Controller controller;

	public ShapeRenderer getShapeRenderer() { return shapeRenderer; }
	public SpriteBatch getBatch() { return batch; }
	public BitmapFont getFont() { return font; }
	public GameCamera getCamera() { return camera; }
	public Controller getController() { return controller; }

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
		camera = new GameCamera();
		controller = Controllers.getCurrent();
		Controllers.addListener(new ControllersListener());

		tickableObjects = new ArrayList<>();
		tickableObjects.add(camera);

		SoundFactory.getInstance().loadSound("takeDamage",GameConstant.SOUND_PATH("damage_taken.mp3"));
		SoundFactory.getInstance().loadSound("dead",GameConstant.SOUND_PATH("dead.mp3"));
		SoundFactory.getInstance().loadSound("shoot",GameConstant.SOUND_PATH("Shoot.mp3"));

		for (int i = 0; i < ChunkHelper.getTileMapSize(); i++)
		{
			String path = ChunkHelper.getTilePath(i);
			if (path != null && !path.isEmpty()) { ImageFactory.loadTexture(ChunkHelper.getTilePath(i)); }
		}

		if (GameConstant.DEBUG) { setScreen(new MainMenu()); }
		else { setScreen(new SplashScreen()); }
	}

	@Override
	public void render ()
	{
		if (Gdx.input.isKeyJustPressed(GameConstant.TOGGLE_DEBUG)) { GameConstant.DEBUG = !GameConstant.DEBUG;}
		if (Gdx.input.isKeyJustPressed(GameConstant.TOGGLE_GOD_MODE)) { GameConstant.GOD_MODE = !GameConstant.GOD_MODE; }
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
		for (int i = 0; i < ChunkHelper.getTileMapSize(); i++)
		{
			String path = ChunkHelper.getTilePath(i);
			if (path != null && !path.isEmpty()) { ImageFactory.disposeTexture(ChunkHelper.getTilePath(i)); }
		}

		super.dispose();
		SoundFactory.getInstance().unloadAllSounds();
		shapeRenderer.dispose();
		batch.dispose();
		font.dispose();
		camera.dispose();
	}
}