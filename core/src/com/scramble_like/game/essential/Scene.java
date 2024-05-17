package com.scramble_like.game.essential;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector4;
import com.badlogic.gdx.utils.ScreenUtils;
import com.scramble_like.game.ScrambleLikeApplication;
import com.scramble_like.game.essential.event_dispatcher.EventDispatcher;

import java.util.ArrayList;

public abstract class Scene implements Screen
{
    private final String name;
    private final ScrambleLikeApplication game;

    protected Vector4 backgroundColor = new Vector4(0, 0, 0.2f, 1);
    protected ArrayList<GameObject> gameObjects;
    protected ArrayList<GameObject> markedForDestructionGos;
    protected ArrayList<Component> markedForDestructionComps;
    protected EventDispatcher eventDispatcher = new EventDispatcher();

    public Scene(String name)
    {
        this.game = ScrambleLikeApplication.getInstance();;
        this.name = name;

        this.gameObjects = new ArrayList<>();
        this.markedForDestructionGos = new ArrayList<>();
        this.markedForDestructionComps = new ArrayList<>();
    }

    public String getName() { return this.name; }
    public ScrambleLikeApplication getGame() { return this.game; }
    public GameCamera getCamera() { return this.game.getCamera(); }
    public SpriteBatch getBatch() { return this.game.getBatch(); }
    public BitmapFont getFont() { return this.game.getFont(); }
    public EventDispatcher getEventDispatcher() { return this.eventDispatcher; }
    public ArrayList<GameObject> getGameObjects() { return this.gameObjects; }

    public void AddGameObject(GameObject gameObject) { this.gameObjects.add(gameObject); gameObject.BeginPlay(); }
    public void DestroyGameObject(GameObject gameObject)
    {
        markedForDestructionGos.add(gameObject);
        markedForDestructionComps.addAll(gameObject.GetComponents());
        gameObject.Destroy();
    }

    public void DestroyComponent(Component component)
    {
        markedForDestructionComps.add(component);
        component.Destroy();
    }

    public void LateUpdate()
    {
        for (GameObject go : markedForDestructionGos) { go.Destroying(); gameObjects.remove(go); }
        for (Component c : markedForDestructionComps) { c.Destroying(); }
    }

    @Override
    public void show() {}

    @Override
    public void render(float v)
    {
        ScreenUtils.clear(backgroundColor.x, backgroundColor.y, backgroundColor.z, backgroundColor.w);

        this.getGame().UpdateTickableObjects(v);

        game.getBatch().begin();
        for (int i = 0; i < gameObjects.size(); i++) { gameObjects.get(i).Update(v); gameObjects.get(i).Render(); }
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
