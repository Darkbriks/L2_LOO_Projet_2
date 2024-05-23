package com.scramble_like.game.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.scramble_like.game.GameConstant;
import com.scramble_like.game.essential.Scene;

public class LevelSlide extends Scene
{
    private final Scene currentScene;
    private final Scene nextScene;

    public LevelSlide(Scene currentScene, Scene nextScene)
    {
        super("LevelSlide");
        getCamera().setPosition(0, 0);
        this.currentScene = currentScene;
        this.nextScene = nextScene;
        createUI();
    }

    private void createUI() {
        Table table = new Table();
        table.setFillParent(true);
        table.setPosition((float) -GameConstant.WIDTH / 2, (float) -GameConstant.HEIGHT / 2);
        table.center();

        Label titleLabel = new Label("Level Completed!", getSkin(), "default");
        titleLabel.setFontScale(2);
        table.add(titleLabel).colspan(2).padBottom(50);
        table.row();

        TextButton continueButton = new TextButton("Continue", getSkin());
        continueButton.addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event, Actor actor) { try { getGame().setScreen(nextScene); }
            catch (Exception e) { Gdx.app.error("LevelSlide", e.getMessage()); } dispose(); }
        });
        table.add(continueButton).padBottom(20).colspan(2);
        table.row();

        TextButton retryButton = new TextButton("Retry", getSkin());
        retryButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                try { Scene newCurrentScene = currentScene.getClass().getConstructor().newInstance(); getGame().setScreen(newCurrentScene); }
                catch (Exception e) { Gdx.app.error("LevelSlide", e.getMessage()); }
            }
        });
        table.add(retryButton).padBottom(20).colspan(2);
        table.row();

        TextButton menuButton = new TextButton("Main Menu", getSkin());
        menuButton.addListener(new ChangeListener() {  @Override public void changed(ChangeEvent event, Actor actor) { getGame().setScreen(new MainMenu()); dispose(); } });
        table.add(menuButton).colspan(2);

        getStage().addActor(table);
    }
}
