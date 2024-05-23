package com.scramble_like.game.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector4;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.ui.AE_Label;

public class GameOver extends Scene
{
    private final Class<? extends AbstractLevel> previous;
    private final float checkpointX;
    private final float checkpointY;

    public GameOver(Class<? extends AbstractLevel> previous, int score, boolean retryFromLastCheckpoint, float x, float y)
    {
        super("GameOver");

        this.previous = previous;
        this.checkpointX = x;
        this.checkpointY = y;

        getCamera().setPosition(0, 0);
        backgroundColor = new Vector4(0, 0, 0, 1);

        Label youDiedLabel = new AE_Label("YOU DIED", this.getSkin(),Color.RED);
        youDiedLabel.setFontScale(10,10);
        youDiedLabel.setPosition((-youDiedLabel.getWidth()) / 2, 50);
        this.getStage().addActor(youDiedLabel);

        AE_Label scoreLabel = new AE_Label("Score: " + score, this.getSkin());
        scoreLabel.setPosition((-scoreLabel.getWidth()) / 2, -50);
        this.getStage().addActor(scoreLabel);

        AE_Label playAgainButton = new AE_Label("Play Again", this.getSkin());
        playAgainButton.setPosition(-playAgainButton.getWidth() / 2, -100);
        playAgainButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                try {
                    AbstractLevel newScene = previous.getConstructor().newInstance();
                    getGame().setScreen(newScene);
                    dispose();
                } catch (Exception e) {
                    Gdx.app.error("GameOver", e.getMessage());
                }
            }
        });
        this.getStage().addActor(playAgainButton);

        AE_Label mainMenuButton = new AE_Label("Main Menu", this.getSkin());
        mainMenuButton.setPosition(-mainMenuButton.getWidth() / 2 -400, 250);
        mainMenuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                getGame().setScreen(new MainMenu());
                dispose();
            }
        });
        this.getStage().addActor(mainMenuButton);

        if(retryFromLastCheckpoint) {
            AE_Label retryFromLastCheckpointButton = new AE_Label("Retry From Last Position", this.getSkin());
            retryFromLastCheckpointButton.setPosition(-retryFromLastCheckpointButton.getWidth() / 2, -150);
            retryFromLastCheckpointButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    try {
                        AbstractLevel newScene = previous.getConstructor().newInstance();
                        newScene.RealoadFromACheckpoint(checkpointX, checkpointY);
                        getGame().setScreen(newScene);
                        dispose();
                    } catch (Exception e) {
                        Gdx.app.error("GameOver", e.getMessage());
                    }
                }
            });
            this.getStage().addActor(retryFromLastCheckpointButton);
        }
        AE_Label exitButton = new AE_Label("Exit", this.getSkin());
        exitButton.setPosition(- exitButton.getWidth() / 2, - 200);
        exitButton.addListener(new ClickListener() { @Override public void clicked(InputEvent event, float x, float y) { Gdx.app.exit(); } });
        this.getStage().addActor(exitButton);
    }
}