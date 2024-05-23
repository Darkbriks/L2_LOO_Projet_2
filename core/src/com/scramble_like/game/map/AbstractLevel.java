package com.scramble_like.game.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector4;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.scramble_like.game.GameConstant;
import com.scramble_like.game.component.controller.PlayerController;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.essential.chunk.ChunkManager;
import com.scramble_like.game.essential.exception.SceneIsNullException;
import com.scramble_like.game.essential.utils.Writer;
import com.scramble_like.game.game_object.Background;
import com.scramble_like.game.game_object.power_up.Checkpoint;
import com.scramble_like.game.game_object.LevelLoader;
import com.scramble_like.game.game_object.Player;
import com.scramble_like.game.ui.AE_Label;
import com.scramble_like.game.ui.LifeActor;

import static java.lang.Math.round;

public abstract class AbstractLevel extends Scene
{
    private Label scoreActor,highscoreActor;
    private LifeActor lifeActor;
    private int currentframe;
    private final int level;
    protected Checkpoint lastCheckpoint;

    public AbstractLevel(String name, int level, float cameraSpeed, int backgroundSpeed, Vector2 levelLoaderLocation)
    {
        super(name);
        this.level=level;

        getCamera().setPosition(getInitPlayerAndCameraLocation().x + getInitPlayerAndCameraLocation().z, getInitPlayerAndCameraLocation().y + getInitPlayerAndCameraLocation().w);
        this.lastCheckpoint = null;
        GameConstant.CAMERA_SPEED = cameraSpeed;
        GameConstant.BACKGROUND_SPEED = backgroundSpeed;

        try
        {
            Player player = new Player("Player", this, new Vector2(this.getInitPlayerAndCameraLocation().x, this.getInitPlayerAndCameraLocation().y));
            player.getPlayerController().setOrigin(new Vector2(getInitPlayerAndCameraLocation().x + getInitPlayerAndCameraLocation().z, getInitPlayerAndCameraLocation().y + getInitPlayerAndCameraLocation().w));
            AddGameObject(player);

            Background background =  new Background("Background", this, "Background/backG.png",768, 192);
            AddGameObject(background);

            ChunkManager chunkManager = new ChunkManager("ChunkManager", this, level);
            AddGameObject(chunkManager);
            chunkManager.setPlayer(player);

            LevelLoader levelLoader = new LevelLoader("LevelLoader", this, (level + 1) % GameConstant.LEVEL_LIST.size());
            levelLoader.getTransform().setLocation(levelLoaderLocation.x,levelLoaderLocation.y);
            AddGameObject(levelLoader);
        }
        catch (SceneIsNullException e) { Gdx.app.error("Abstract", "Error: " + e.getMessage()); }

        CreateUI();
    }

    protected abstract Vector4 getInitPlayerAndCameraLocation();
    public void setLastCheckpoint(Checkpoint lastCheckpoint) { this.lastCheckpoint = lastCheckpoint; }

    public void GameOver(int score)
    {
        int x = 0, y = 0;
        if (lastCheckpoint != null)
        {
            x = (int) lastCheckpoint.getTransform().getLocation().x;
            y = (int) lastCheckpoint.getTransform().getLocation().y;
        }
        getGame().setScreen(new GameOver(getClass(), score, lastCheckpoint != null, x, y));
        dispose();
    }

    public void RealoadFromACheckpoint(float x, float y)
    {
        getCamera().setPosition(x, y);
        getPlayer().getTransform().setLocation(x, y);
    }

    private void CreateUI()
    {
        this.currentframe=0;
        lifeActor = new LifeActor(this.getStage(), "UI/heart.png", 5);
        lifeActor.setCurrentRegion(currentframe);
        this.getStage().addActor(lifeActor);

        scoreActor = new AE_Label( "Score : "+ this.getPlayer().GetFirstComponentFromClass(PlayerController.class).getScore(), this.getSkin());
        scoreActor.setPosition(-700,390);
        scoreActor.setFontScale(1.5f,1.5f);
        this.getStage().addActor(scoreActor);

        highscoreActor = new AE_Label( "HighScore : "+ Writer.getSetting( "highscore_"+level,"highscore.txt"), this.getSkin());
        highscoreActor.setPosition(0,0);
        highscoreActor.setFontScale(1.5f,1.5f);
        this.getStage().addActor(highscoreActor);
    }

    private void newLife()
    {
        int totalFrames = 5;
        this.currentframe = round(totalFrames - 1 - (((float) this.getPlayer().getPlayerController().getLife() /GameConstant.PLAYER_LIFE)*(totalFrames - 1)));
        lifeActor.setCurrentRegion(currentframe);
    }

    @Override
    public void render(float delta)
    {
        this.scoreActor.setText("Score : " + this.getPlayer().GetFirstComponentFromClass(PlayerController.class).getScore() + "  FPS : " + Gdx.graphics.getFramesPerSecond());
        this.scoreActor.setPosition(getCamera().getPosition().x - GameConstant.SCORE_X, getCamera().getPosition().y + GameConstant.SCORE_Y);
        this.highscoreActor.setPosition(getCamera().getPosition().x - GameConstant.HIGHSCORE_X, getCamera().getPosition().y + GameConstant.HIGHSCORE_Y);
        this.lifeActor.setPosition(getCamera().getPosition().x - GameConstant.LIFE_X, getCamera().getPosition().y + GameConstant.LIFE_Y);
        newLife();

        if (Gdx.input.isKeyJustPressed(GameConstant.TOGGLE_PAUSE) || GameConstant.PAUSE_BUTTON) { GameConstant.PAUSE_BUTTON = false; getGame().setScreen(new PauseMenu(this)); }

        super.render(delta);
    }

    @Override
    public void dispose()
    {
        super.dispose();
        Writer.writeSetting(String.valueOf(getPlayer().getPlayerController().getScore()), "highscore_"+level,"highscore.txt",GameConstant.HIGHSCORE_LIST,true);
        System.out.println(Writer.getSetting( "highscore_"+level,"highscore.txt"));
    }
}
