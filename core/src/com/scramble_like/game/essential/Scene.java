package com.scramble_like.game.essential;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector4;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.scramble_like.game.ScrambleLikeApplication;
import com.scramble_like.game.essential.event_dispatcher.EventDispatcher;
import com.scramble_like.game.essential.exception.GameIsNullException;

import java.util.ArrayList;

public abstract class Scene implements Screen
{
    private String name;
    private final ScrambleLikeApplication game;
    private Stage stage;

    protected Vector4 backgroundColor = new Vector4(0, 0, 0.2f, 1);
    protected ArrayList<GameObject> gameObjects;
    protected ArrayList<GameObject> addGameObjects;
    protected ArrayList<GameObject> markedForDestructionGos;
    protected ArrayList<Component> markedForDestructionComps;
    protected EventDispatcher eventDispatcher = new EventDispatcher();

    public Scene(ScrambleLikeApplication game, String name) throws GameIsNullException
    {
        if (game == null) { throw new GameIsNullException("Game is null in scene " + name); }
        this.game = game;
        this.name = name;

        stage = new Stage();
        this.gameObjects = new ArrayList<>();
        this.addGameObjects = new ArrayList<>();
        this.markedForDestructionGos = new ArrayList<>();
        this.markedForDestructionComps = new ArrayList<>();

        Gdx.input.setInputProcessor(stage);
    }

    public String getName() { return this.name; }
    public ScrambleLikeApplication getGame() { return this.game; }
    public Stage getStage() { return this.stage; }
    public GameCamera getCamera() { return this.game.getCamera(); }
    public SpriteBatch getBatch() { return this.game.getBatch(); }
    public BitmapFont getFont() { return this.game.getFont(); }
    public EventDispatcher getEventDispatcher() { return this.eventDispatcher; }
    public ArrayList<GameObject> getGameObjects() { return this.gameObjects; }

    public void AddGameObject(GameObject gameObject) { this.addGameObjects.add(gameObject); gameObject.BeginPlay(); }
    public void DestroyGameObject(GameObject gameObject)
    {
        markedForDestructionGos.add(gameObject);
        markedForDestructionComps.addAll(gameObject.GetComponents());
        this.gameObjects.remove(gameObject);
        gameObject.Destroy();
    }

    public void DestroyComponent(Component component)
    {
        markedForDestructionComps.add(component);
        component.Destroy();
    }

    public void LateUpdate()
    {
        for (GameObject go : markedForDestructionGos) { go.Destroying(); }
        for (Component c : markedForDestructionComps) { c.Destroying(); }
    }

    @Override
    public void show() {}

    @Override
    public void render(float v)
    {
        ScreenUtils.clear(backgroundColor.x, backgroundColor.y, backgroundColor.z, backgroundColor.w);
        game.getBatch().begin();

        gameObjects.addAll(addGameObjects);

        for (GameObject go : gameObjects) { go.Update(v); go.Render(); }

        game.getBatch().end();

        LateUpdate();
    }

    @Override
    public void resize(int i, int i1) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose()
    {
        for (GameObject go : gameObjects) { DestroyGameObject(go); }
        for (GameObject go : markedForDestructionGos) { go.Destroying(); }
        for (Component c : markedForDestructionComps) { c.Destroying(); }
    }
}
