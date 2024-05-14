package com.scramble_like.game.essential;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.scramble_like.game.GameConstant;

import java.util.ArrayList;

public class Scene implements Screen
{
    protected Game game;
    public SpriteBatch batch;
    public BitmapFont font;

    protected OrthographicCamera camera;
    protected ArrayList<GameObject> gameObjects;
    protected ArrayList<GameObject> markedForDestructionGos;
    protected ArrayList<Component> markedForDestructionComps;

    public Scene(Game game)
    {
        this.game = game;

        this.camera = new OrthographicCamera(GameConstant.WIDTH, GameConstant.HEIGHT);
        this.camera.update();

        this.gameObjects = new ArrayList<>();
        this.markedForDestructionGos = new ArrayList<>();
        this.markedForDestructionComps = new ArrayList<>();

        batch = new SpriteBatch();
        font = new BitmapFont();

        // Debug - Add GO
        GameObject go = new GameObject("Test", this);
        AddGameObject(go);
    }

    public ArrayList<GameObject> GetGameObjects() { return this.gameObjects; }
    public ArrayList<GameObject> GetGameObjects(ArrayList<GameObject> ExcludeGos)
    {
        ArrayList<GameObject> gos = new ArrayList<>();
        for (GameObject go : this.gameObjects) { if (!ExcludeGos.contains(go)) { gos.add(go); } }
        return gos;
    }
    public void AddGameObject(GameObject gameObject) { this.gameObjects.add(gameObject); gameObject.BeginPlay(); }
    public void DestroyGameObject(GameObject gameObject)
    {
        markedForDestructionGos.add(gameObject);
        markedForDestructionComps.addAll(gameObject.GetComponents());
        this.gameObjects.remove(gameObject);
        gameObject.Destroy();
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
        ScreenUtils.clear(0, 0, 0.2f, 1);

        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        for (GameObject go : gameObjects) { go.Update(v); go.Render(); }

        batch.end();

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

        batch.dispose();
        font.dispose();
    }
}
