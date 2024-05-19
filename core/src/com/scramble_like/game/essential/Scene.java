package com.scramble_like.game.essential;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector4;
import com.badlogic.gdx.utils.ScreenUtils;
import com.scramble_like.game.GameConstant;
import com.scramble_like.game.ScrambleLikeApplication;
import com.scramble_like.game.essential.event_dispatcher.EventDispatcher;
import com.scramble_like.game.game_object.Player;

import java.util.ArrayList;

public abstract class Scene implements Screen
{
    private final String name;
    private final ScrambleLikeApplication game;

    protected Vector4 backgroundColor = new Vector4(0, 0, 0.2f, 1);
    protected ArrayList<GameObject> gameObjectsToAdd;
    protected ArrayList<ArrayList<GameObject>> gameObjects;
    protected ArrayList<GameObject> markedForDestructionGos;
    protected ArrayList<Component> markedForDestructionComps;
    protected EventDispatcher eventDispatcher = new EventDispatcher();
    protected Player player;

    public Scene(String name)
    {
        this.game = ScrambleLikeApplication.getInstance();
        this.name = name;

        this.gameObjectsToAdd = new ArrayList<>();
        this.gameObjects = new ArrayList<>();
        this.markedForDestructionGos = new ArrayList<>();
        this.markedForDestructionComps = new ArrayList<>();

        this.game.getCamera().setPosition(0, 0);

        for (int i = GameConstant.MIN_Z_INDEX; i <= GameConstant.MAX_Z_INDEX; i++) { this.gameObjects.add(new ArrayList<>()); }
    }

    public String getName() { return this.name; }
    public ScrambleLikeApplication getGame() { return this.game; }
    public GameCamera getCamera() { return this.game.getCamera(); }
    public SpriteBatch getBatch() { return this.game.getBatch(); }
    public BitmapFont getFont() { return this.game.getFont(); }
    public Controller getController() { return this.game.getController(); }
    public EventDispatcher getEventDispatcher() { return this.eventDispatcher; }
    public Player getPlayer() { return this.player; }
    public void setPlayer(Player player) { this.player = player; }
    public ArrayList<GameObject> getGameObjects() { ArrayList<GameObject> gos = new ArrayList<>(); for (ArrayList<GameObject> go : gameObjects) { gos.addAll(go); } return gos; }

    public void AddGameObject(GameObject gameObject) { this.gameObjectsToAdd.add(gameObject); gameObject.BeginPlay(); }
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
        for (int i = 0; i < markedForDestructionGos.size(); i++) { markedForDestructionGos.get(i).Destroying(); gameObjects.get(markedForDestructionGos.get(i).getTransform().getZIndex()).remove(markedForDestructionGos.get(i)); }
        for (int i = 0; i < markedForDestructionComps.size(); i++) { markedForDestructionComps.get(i).Destroying(); }
        for (int i = 0; i < gameObjectsToAdd.size(); i++) { this.gameObjects.get(gameObjectsToAdd.get(i).getTransform().getZIndex()).add(gameObjectsToAdd.get(i)); }
        markedForDestructionGos.clear();
        markedForDestructionComps.clear();
        gameObjectsToAdd.clear();
    }

    @Override
    public void show() {}

    @Override
    public void render(float v)
    {
        ScreenUtils.clear(backgroundColor.x, backgroundColor.y, backgroundColor.z, backgroundColor.w);

        this.getGame().UpdateTickableObjects(v * GameConstant.UPDATE_MULTIPLIER);

        game.getBatch().begin();

        for (int i = GameConstant.MAX_Z_INDEX; i >= GameConstant.MIN_Z_INDEX; i--)
        {
            for (int j = 0; j < gameObjects.get(i).size(); j++)
            {
                gameObjects.get(i).get(j).Update(v * GameConstant.UPDATE_MULTIPLIER);
            }
        }

        for (int i = GameConstant.MAX_Z_INDEX; i >= GameConstant.MIN_Z_INDEX; i--)
        {
            for (int j = 0; j < gameObjects.get(i).size(); j++)
            {
                gameObjects.get(i).get(j).Render();
            }
        }

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
        //for (GameObject go : gameObjects) { DestroyGameObject(go); }
        for (int i = GameConstant.MAX_Z_INDEX; i >= GameConstant.MIN_Z_INDEX; i--)
        {
            for (int j = 0; j < gameObjects.get(i).size(); j++)
            {
                DestroyGameObject(gameObjects.get(i).get(j));
            }
        }
        for (GameObject go : markedForDestructionGos) { go.Destroying(); }
        for (Component c : markedForDestructionComps) { c.Destroying(); }
    }
}
