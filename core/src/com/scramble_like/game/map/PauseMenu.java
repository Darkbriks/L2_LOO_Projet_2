package com.scramble_like.game.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector4;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.ui.AE_Label;
import com.scramble_like.game.ui.AE_TitleLabel;

public class PauseMenu extends Scene {

    private final Scene previousScene;

    public PauseMenu(Scene previousScene) {
        super("PauseMenu");
        this.previousScene = previousScene;

        backgroundColor = new Vector4(0, 0, 0, 0.75f);

        float viewportWidth = getStage().getViewport().getWorldWidth();
        float viewportHeight = getStage().getViewport().getWorldHeight();

        Label pauseText = new AE_TitleLabel("Paused", this.getSkin());
        pauseText.setPosition((viewportWidth - pauseText.getWidth()) / 2-600, viewportHeight - pauseText.getHeight() - 500);
        this.getStage().addActor(pauseText);

        Label continueButton = new AE_Label("Continue", this.getSkin());
        continueButton.setPosition((viewportWidth - continueButton.getWidth()) / 2-600, viewportHeight / 2 -320);
        continueButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {getGame().setScreen(previousScene);dispose();}});
        this.getStage().addActor(continueButton);

        Label tryAgainButton = new AE_Label("Try Again", this.getSkin());
        tryAgainButton.setPosition((viewportWidth - tryAgainButton.getWidth()) / 2-600, viewportHeight / 2 - 360);
        tryAgainButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                getGame().setScreen(new TestMap());
                dispose();
            }
        });
        this.getStage().addActor(tryAgainButton);

        Label mainMenuButton = new AE_Label("Main Menu", this.getSkin());
        mainMenuButton.setPosition((viewportWidth - mainMenuButton.getWidth()) / 2-600, viewportHeight / 2 - 400);
        mainMenuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                getGame().setScreen(new MainMenu());
                dispose();
            }
        });
        this.getStage().addActor(mainMenuButton);

        Label exitButton = new AE_Label("Exit", this.getSkin());
        exitButton.setPosition((viewportWidth - exitButton.getWidth()) / 2 -600, viewportHeight / 2 - 460);
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {Gdx.app.exit();}});
        this.getStage().addActor(exitButton);
    }

    @Override
    public void render(float delta) {super.render(delta);}
}
