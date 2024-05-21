package com.scramble_like.game.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.scramble_like.game.GameConstant;
import com.scramble_like.game.component.controller.PlayerController;
import com.scramble_like.game.essential.CoreConstant;
import com.scramble_like.game.essential.DynamicObjectLoader;
import com.scramble_like.game.essential.chunk.ChunkHelper;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.essential.exception.SceneIsNullException;
import com.scramble_like.game.essential.factory.ImageFactory;
import com.scramble_like.game.essential.factory.SoundFactory;
import com.scramble_like.game.essential.chunk.ChunkManager;
import com.scramble_like.game.game_object.LevelLoader;
import com.scramble_like.game.game_object.Player;
import com.scramble_like.game.ui.AE_Label;
import com.scramble_like.game.ui.LifeActor;
import com.scramble_like.game.game_object.Background;

import static java.lang.Math.round;

public class TestMap extends Scene
{
    private static final int id = 3;

    // Main UI
    private Label scoreActor;
    private LifeActor lifeActor;

    private int currentframe;
    public TestMap()
    {
        super("TestMap");

        getCamera().setPosition(0, 0);

        // Load all tiles in chunkHelper
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

            ChunkManager chunkManager = new ChunkManager("ChunkManager", this, 3);
            AddGameObject(chunkManager);
            chunkManager.setPlayer(go1);

            LevelLoader levelLoader = new LevelLoader("LevelLoader", this, 0);
            levelLoader.getTransform().setLocation(new Vector2(1000, 0));
            AddGameObject(levelLoader);
        }
        catch (SceneIsNullException e) { System.out.println("Error: " + e.getMessage()); }

        CreateUI();
        DynamicObjectLoader.getInstance().loadAll(this, "Level_0_DynamicObject.txt");
        SoundFactory.getInstance().playBackgroundMusicWithFade("Audio/Music/Reach for the Summit.mp3", 0, 10);
    }

    @Override
    public void render(float delta)
    {
        this.scoreActor.setText("Score : "+ this.getPlayer().GetFirstComponentFromClass(PlayerController.class).getScore());
        this.scoreActor.setPosition(getCamera().getPosition().x - GameConstant.SCORE_X, getCamera().getPosition().y + GameConstant.SCORE_Y);
        this.lifeActor.setPosition(getCamera().getPosition().x - GameConstant.LIFE_X, getCamera().getPosition().y + GameConstant.LIFE_Y);
        newLife();

        if (Gdx.input.isKeyJustPressed(GameConstant.TOGGLE_PAUSE)) { getGame().setScreen(new PauseMenu(this)); }

        super.render(delta);
    }

    @Override
    public void dispose()
    {
        super.dispose();
        SoundFactory.getInstance().stopBackgroundMusic();

        for (int i = 0; i < ChunkHelper.getTileMapSize(); i++)
        {
            String path = ChunkHelper.getTilePath(i);
            if (path != null && !path.isEmpty()) { ImageFactory.disposeTexture(ChunkHelper.getTilePath(i)); }
        }
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
}