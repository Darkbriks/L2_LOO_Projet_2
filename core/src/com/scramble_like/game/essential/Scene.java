package com.scramble_like.game.essential;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;

import java.util.ArrayList;

public class Scene implements Screen
{
    protected Game game;
    protected OrthographicCamera camera;
    protected ArrayList<GameObject> gameObjects;
    protected ArrayList<GameObject> markedForDestruction;

    public Scene(Game game)
    {
        this.game = game;
    }

    public void AddGameObject(GameObject gameObject) { this.gameObjects.add(gameObject); gameObject.BeginPlay(); }
    public void Destroy(GameObject gameObject) { this.gameObjects.remove(gameObject); gameObject.Destroy(); markedForDestruction.add(gameObject); }

    public void LateUpdate()
    {
        // Destroy all game objects marked for destruction
        for (GameObject go : markedForDestruction)
        {

        }
    }

    @Override
    public void show() {}

    @Override
    public void render(float v)
    {

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
    public void dispose() {}
}
