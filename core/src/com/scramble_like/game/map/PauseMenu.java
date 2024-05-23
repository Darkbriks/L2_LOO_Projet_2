package com.scramble_like.game.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector4;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.scramble_like.game.GameConstant;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.ui.AE_Label;
import com.scramble_like.game.ui.AE_TitleLabel;

public class PauseMenu extends Scene
{
    private final Scene previousScene;
    private AE_TitleLabel pauseLabel;
    private AE_Label resumeButton;
    private AE_Label reloadButton;
    private AE_Label mainMenuButton;
    private AE_Label exitButton;

    public PauseMenu(Scene previousScene)
    {
        super("PauseMenu");
        this.previousScene = previousScene;

        backgroundColor = new Vector4(0, 0, 0, 0.75f);
        CreatePauseMenu();
    }

    private void CreatePauseMenu()
    {
        float cameraX = getCamera().getPosition().x;
        float cameraY = getCamera().getPosition().y;

        pauseLabel = new AE_TitleLabel("Paused", this.getSkin());
        pauseLabel.setPosition(cameraX - pauseLabel.getWidth() / 2, cameraY + 200);
        this.getStage().addActor(pauseLabel);

        resumeButton = new AE_Label("Resume", this.getSkin());
        resumeButton.setPosition(cameraX - resumeButton.getWidth() / 2, cameraY + 50);
        resumeButton.addListener(new ClickListener() { @Override public void clicked(InputEvent event, float x, float y) { getGame().setScreen(previousScene); dispose(); } });
        this.getStage().addActor(resumeButton);

        reloadButton = new AE_Label("Try Again", this.getSkin());
        reloadButton.setPosition(cameraX - reloadButton.getWidth() / 2, cameraY);
        reloadButton.addListener(new ClickListener() { @Override public void clicked(InputEvent event, float x, float y){
            try
            {
                Scene newScene = previousScene.getClass().getConstructor().newInstance();
                getGame().setScreen(newScene);
                dispose();
            }
            catch (Exception e) { throw new RuntimeException(e); }
        } });
        this.getStage().addActor(reloadButton);

        mainMenuButton = new AE_Label("Main Menu", this.getSkin());
        mainMenuButton.setPosition(cameraX - mainMenuButton.getWidth() / 2, cameraY - 50);
        mainMenuButton.addListener(new ClickListener() { @Override  public void clicked(InputEvent event, float x, float y) { getGame().setScreen(new MainMenu()); dispose(); } });
        this.getStage().addActor(mainMenuButton);

        exitButton = new AE_Label("Exit", this.getSkin());
        exitButton.setPosition(cameraX - exitButton.getWidth() / 2, cameraY - 100);
        exitButton.addListener(new ClickListener() { @Override public void clicked(InputEvent event, float x, float y) { Gdx.app.exit(); } });
        this.getStage().addActor(exitButton);
    }

    @Override
    public void render(float delta)
    {
        if (Gdx.input.isKeyJustPressed(GameConstant.TOGGLE_PAUSE) || GameConstant.PAUSE_BUTTON)
        {
            GameConstant.PAUSE_BUTTON = false;
            getGame().setScreen(previousScene); dispose();
        }
        super.render(delta);
    }

    @Override
    public void dispose()
    {
        super.dispose();
        pauseLabel.remove();
        resumeButton.remove();
        reloadButton.remove();
        mainMenuButton.remove();
        exitButton.remove();
    }
}
