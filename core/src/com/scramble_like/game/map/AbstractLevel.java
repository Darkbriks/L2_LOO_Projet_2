package com.scramble_like.game.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.scramble_like.game.GameConstant;
import com.scramble_like.game.component.controller.PlayerController;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.essential.chunk.ChunkHelper;
import com.scramble_like.game.essential.chunk.ChunkManager;
import com.scramble_like.game.essential.exception.SceneIsNullException;
import com.scramble_like.game.essential.factory.ImageFactory;
import com.scramble_like.game.essential.factory.SoundFactory;
import com.scramble_like.game.essential.utils.Writer;
import com.scramble_like.game.game_object.Background;
import com.scramble_like.game.game_object.LevelLoader;
import com.scramble_like.game.game_object.Player;
import com.scramble_like.game.ui.AE_Label;
import com.scramble_like.game.ui.LifeActor;

import static java.lang.Math.round;

public abstract class AbstractLevel extends Scene
{
    private Label scoreActor;
    private LifeActor lifeActor;

    private int currentframe;

    public AbstractLevel(String name, int level, Vector2 levelLoaderLocation)
    {
        super(name);
        SoundFactory.getInstance().loadSound("damage_taken.mp3","Audio/Sound/damage_taken.mp3");
        SoundFactory.getInstance().loadSound("dead","Audio/Sound/dead.mp3");

        getCamera().setPosition(0, 0);

        for (int i = 0; i < ChunkHelper.getTileMapSize(); i++)
        {
            String path = ChunkHelper.getTilePath(i);
            if (path != null && !path.isEmpty()) { ImageFactory.loadTexture(ChunkHelper.getTilePath(i)); }
        }

        try
        {
            Player go1 = new Player("Player", this, new Vector2(-350, 0));
            AddGameObject(go1);

            Background background =  new Background("Background", this, "Background/backG.png",768, 192);
            AddGameObject(background);

            ChunkManager chunkManager = new ChunkManager("ChunkManager", this, level);
            AddGameObject(chunkManager);
            chunkManager.setPlayer(go1);

            LevelLoader levelLoader = new LevelLoader("LevelLoader", this, (level + 1) % GameConstant.LEVEL_LIST.size());
            levelLoader.getTransform().setLocation(levelLoaderLocation);
            AddGameObject(levelLoader);
        }
        catch (SceneIsNullException e) { Gdx.app.error("Abstract", "Error: " + e.getMessage()); }

        CreateUI();
    }

    private void CreateUI()
    {
        this.currentframe=0;
        lifeActor = new LifeActor(this.getStage(), "UI/heart.png", 5);
        lifeActor.setCurrentRegion(currentframe);
        lifeActor.setPosition(730,400);
        this.getStage().addActor(lifeActor);

        scoreActor = new AE_Label( "Score : "+ this.getPlayer().GetFirstComponentFromClass(PlayerController.class).getScore(), this.getSkin());
        scoreActor.setPosition(-700,390);
        scoreActor.setFontScale(1.5f,1.5f);
        this.getStage().addActor(scoreActor);
    }

    private void newLife()
    {
        int totalFrames = 5;
        this.currentframe = round(totalFrames - 1 - (((float) this.getPlayer().GetFirstComponentFromClass(PlayerController.class).getLife() /GameConstant.PLAYER_LIFE)*(totalFrames - 1)));
        lifeActor.setCurrentRegion(currentframe);
    }

    @Override
    public void render(float delta)
    {
        this.scoreActor.setText("Score : " + this.getPlayer().GetFirstComponentFromClass(PlayerController.class).getScore() + "  FPS : " + Gdx.graphics.getFramesPerSecond());
        this.scoreActor.setPosition(getCamera().getPosition().x - GameConstant.SCORE_X, getCamera().getPosition().y + GameConstant.SCORE_Y);
        this.lifeActor.setPosition(getCamera().getPosition().x - GameConstant.LIFE_X, getCamera().getPosition().y + GameConstant.LIFE_Y);
        newLife();

        if (Gdx.input.isKeyJustPressed(GameConstant.TOGGLE_PAUSE) || GameConstant.PAUSE_BUTTON) { GameConstant.PAUSE_BUTTON = false; getGame().setScreen(new PauseMenu(this)); }

        super.render(delta);
    }

    @Override
    public void dispose()
    {
        super.dispose();
        new Writer(String.valueOf(getPlayer().getPlayerController().getScore()), "highscore_1","highscore.txt");

        SoundFactory.getInstance().unloadAllSounds();

        for (int i = 0; i < ChunkHelper.getTileMapSize(); i++)
        {
            String path = ChunkHelper.getTilePath(i);
            if (path != null && !path.isEmpty()) { ImageFactory.disposeTexture(ChunkHelper.getTilePath(i)); }
        }
    }
}
