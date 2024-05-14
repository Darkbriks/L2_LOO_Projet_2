package com.scramble_like.game.map;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import com.scramble_like.game.ScrambleLikeApplication;
import com.scramble_like.game.essential.Scene;


public class MainMenu extends Scene {
    OrthographicCamera camera;

    public MainMenu(Game scramble){
        super(scramble);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

    }
    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        font.draw(batch, "Welcome to Scramble Like!!! ", 100, 150);
        font.draw(batch, "Tap anywhere to begin!", 100, 100);
        batch.end();
        if (Gdx.input.isTouched()) {
            game.setScreen(new Scene(game));
            dispose();
        }
    }
    @Override
    public void resize(int width, int height) {
    }
    @Override
    public void pause() {
        
    }

    @Override
    public void resume() {
        
    }

    @Override
    public void hide() {
        
    }

    @Override
    public void show() {

    }

    @Override
    public void dispose() {

    }
}
