package com.scramble_like.game.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.Vector4;
import com.scramble_like.game.component.paper2d.Text;
import com.scramble_like.game.essential.GameObject;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.essential.exception.SceneIsNullException;

public class GameOver extends Scene
{
    private Rectangle playAgainButtonBounds;
    private Rectangle retryFromLastCheckpointBounds;
    private Rectangle menuButtonBounds;
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

        try {
            GameObject gameOverText = new GameObject("GameOverText", this);
            gameOverText.AddComponent(new Text("Game Over", 4, Color.RED));
            gameOverText.getTransform().Translate(0, 150);
            AddGameObject(gameOverText);

            GameObject scoreText = new GameObject("ScoreText", this);
            scoreText.AddComponent(new Text("Score", 3, Color.WHITE));
            scoreText.getTransform().Translate(0, 50);
            AddGameObject(scoreText);

            GameObject scoreValue = new GameObject("ScoreValue", this);
            scoreValue.AddComponent(new Text(String.valueOf(score), 3, Color.WHITE));
            scoreValue.getTransform().Translate(0, 0);
            AddGameObject(scoreValue);

            GameObject playAgainButton = new GameObject("PlayAgainButton", this);
            playAgainButton.AddComponent(new Text("Play Again", 3, Color.WHITE));
            playAgainButton.getTransform().Translate(0, -100);
            AddGameObject(playAgainButton);
            playAgainButtonBounds = new Rectangle(0, -125, 205, 50);

            if (retryFromLastCheckpoint)
            {
                GameObject retryFromLastCheckpointButton = new GameObject("RetryFromLastCheckpointButton", this);
                retryFromLastCheckpointButton.AddComponent(new Text("Retry From Last Checkpoint", 3, Color.WHITE));
                retryFromLastCheckpointButton.getTransform().Translate(0, -150);
                AddGameObject(retryFromLastCheckpointButton);
                retryFromLastCheckpointBounds = new Rectangle(0, -175, 500, 50);
            }

            GameObject menuButton = new GameObject("MenuButton", this);
            menuButton.AddComponent(new Text("Menu", 2, Color.WHITE));
            menuButton.getTransform().Translate(350, 200);
            AddGameObject(menuButton);
            menuButtonBounds = new Rectangle(345, 175, 100, 50);
        } catch (SceneIsNullException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void render(float delta) {
        if (Gdx.input.justTouched()) {
            Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            Camera camera = getCamera().getCamera();
            camera.unproject(touchPos);

            if (playAgainButtonBounds.contains(touchPos.x, touchPos.y))
            {
                try
                {
                    AbstractLevel newScene = previous.getConstructor().newInstance();
                    getGame().setScreen(newScene);
                    dispose();
                }
                catch (Exception e) { Gdx.app.error("GameOver", e.getMessage()); }
            }
            else if (menuButtonBounds.contains(touchPos.x, touchPos.y)) { getGame().setScreen(new MainMenu()); dispose(); }
            else if (retryFromLastCheckpointBounds != null && retryFromLastCheckpointBounds.contains(touchPos.x, touchPos.y))
            {
                System.out.println("Retry from last checkpoint");
                try
                {
                    AbstractLevel newScene = previous.getConstructor().newInstance();
                    newScene.RealoadFromACheckpoint(checkpointX, checkpointY);
                    getGame().setScreen(newScene);
                    dispose();
                }
                catch (Exception e) { Gdx.app.error("GameOver", e.getMessage()); }
            }
        }

        super.render(delta);
    }
}
