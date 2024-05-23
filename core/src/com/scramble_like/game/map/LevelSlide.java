package com.scramble_like.game.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.scramble_like.game.essential.Scene;

public class LevelSlide extends Scene {

    private Scene currentScene;
    private Scene nextScene;

    public LevelSlide(Scene currentScene, Scene nextScene) {
        super("LevelSlide");
        this.currentScene = currentScene;
        this.nextScene = nextScene;
        createUI();
    }

    private void createUI() {
        Table table = new Table();
        table.setFillParent(true);
        table.center();

        Label titleLabel = new Label("Level Completed!", getSkin(), "default");
        titleLabel.setFontScale(2);
        table.add(titleLabel).colspan(2).padTop(400).padRight(1200);
        table.row();

        TextButton continueButton = new TextButton("Continue", getSkin());
        continueButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                try {
                    Gdx.app.log("LevelSlide", "Continuing to the next level...");
                    getGame().setScreen(nextScene);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                dispose();
            }
        });
        table.add(continueButton).padBottom(20).colspan(2).padRight(1200);
        table.row();

        TextButton retryButton = new TextButton("Retry", getSkin());
        retryButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.log("LevelSlide", "Retrying current level...");
                try {
                    Scene newCurrentScene = currentScene.getClass().getConstructor().newInstance();
                    getGame().setScreen(newCurrentScene);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        table.add(retryButton).padBottom(20).colspan(2).padRight(1200);
        table.row();

        TextButton menuButton = new TextButton("Main Menu", getSkin());
        menuButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.log("LevelSlide", "Returning to main menu...");
                getGame().setScreen(new MainMenu());
                dispose();
            }
        });
        table.add(menuButton).padBottom(20).colspan(2).padRight(1200);

        getStage().addActor(table);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        getStage().act(delta);
        getStage().draw();
    }
}
