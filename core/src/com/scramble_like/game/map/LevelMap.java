package com.scramble_like.game.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector4;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.scramble_like.game.GameConstant;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.ui.AE_Label;

public class LevelMap extends Scene {

    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer tiledMapRenderer;
    private OrthographicCamera camera;

    public LevelMap() {
        super("LevelMap");

        camera = new OrthographicCamera();
        camera.setToOrtho(false, GameConstant.WIDTH, GameConstant.HEIGHT);

        tiledMap = new TmxMapLoader().load("LevelMap/Lmap.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

        backgroundColor = new Vector4(0.0f, 0.0f, 10.0f, 1);

        Label backButton = new AE_Label("Back", this.getSkin());
        backButton.setPosition(-600, Gdx.graphics.getHeight() - 400);
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                getGame().setScreen(new MainMenu());
                dispose();
            }
        });
        this.getStage().addActor(backButton);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        camera.update();
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();

        this.getStage().getBatch().setProjectionMatrix(camera.combined);
        this.getStage().draw();
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);
    }

    @Override
    public void dispose() {
        super.dispose();
        tiledMap.dispose();
        tiledMapRenderer.dispose();
    }
}
