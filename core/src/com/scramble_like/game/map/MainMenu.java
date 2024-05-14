package com.scramble_like.game.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import com.scramble_like.game.ScrambleLikeApplication;


public class MainMenu {
    final ScrambleLikeApplication scramble;
    OrthographicCamera camera;

    public MainMenu(ScrambleLikeApplication scramble){
        this.scramble = scramble;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

    }
    @Override
    public void Render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        camera.update();
        scramble.batch.setProjectionMatrix(camera.combined);
        scramble.batch.begin();
        scramble.font.draw(scramble.batch, "Welcome to Scramble Like!!! ", 100, 150);
        scramble.font.draw(scramble.batch, "Tap anywhere to begin!", 100, 100);
        scramble.batch.end();
        if (Gdx.input.isTouched()) {
            scramble.setScreen(new GameScreen(scramble));
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
    public void Destroy(){
        
    }
}
