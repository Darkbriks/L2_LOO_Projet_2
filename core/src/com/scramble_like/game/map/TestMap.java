package com.scramble_like.game.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.scramble_like.game.GameConstant;
import com.scramble_like.game.component.controller.PlayerController;
import com.scramble_like.game.essential.chunk.ChunkHelper;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.essential.exception.SceneIsNullException;
import com.scramble_like.game.essential.factory.ImageFactory;
import com.scramble_like.game.essential.factory.SoundFactory;
import com.scramble_like.game.essential.chunk.ChunkManager;
import com.scramble_like.game.game_object.LevelLoader;
import com.scramble_like.game.game_object.Player;
import com.scramble_like.game.game_object.enemy.MovingEnemy;
import com.scramble_like.game.ui.AE_Label;
import com.scramble_like.game.ui.LifeActor;

public class TestMap extends Scene
{
    private Label scoreActor;

    private LifeActor lifeActor;
    private TextureRegion[] frames;
    public TestMap()
    {
        super("TestMap");


        // Load all tiles in chunkHelper
        for (int i = 0; i < ChunkHelper.getTileMapSize(); i++)
        {
            String path = ChunkHelper.getTilePath(i);
            if (path != null && !path.isEmpty()) { ImageFactory.loadTexture(ChunkHelper.getTilePath(i)); }
        }

        try
        {
            //Actor score = new Actor();
            //score.setBounds(-350,500,64,64);
            Player go1 = new Player("Player", this, new Vector2(-350, 0));
            AddGameObject(go1);

            //Background background =  new Background("Background", this, "Background/backG.png",768, 192);
            //AddGameObject(background);

            Vector2[] waypoints = {new Vector2(1000, 300), new Vector2(1000, 0)};
            AddGameObject(new MovingEnemy("Enemy", this, "badlogic.jpg", 1, waypoints, 300));

            ChunkManager chunkManager = new ChunkManager("ChunkManager", this, 3);
            AddGameObject(chunkManager);
            chunkManager.setPlayer(go1);

            LevelLoader levelLoader = new LevelLoader("LevelLoader", this, 0);
            levelLoader.getTransform().setLocation(new Vector2(1000, 0));
            AddGameObject(levelLoader);
        }
        catch (SceneIsNullException e) { System.out.println("Error: " + e.getMessage()); }

        CreateUI();
        SoundFactory.getInstance().playBackgroundMusicWithFade("Audio/Music/Reach for the Summit.mp3", 1, 10);
    }

    private void CreateUI()
    {
        lifeActor = new LifeActor(this.getStage(), "UI/heart.png", 5);
        lifeActor.setCurrentRegion(0);
        lifeActor.setPosition(-450,390);
        //lifeActor.setZIndex(CoreConstant.MIN_Z_INDEX);
        this.getStage().addActor(lifeActor);

        scoreActor = new AE_Label( "Score : "+ this.getPlayer().GetFirstComponentFromClass(PlayerController.class).getScore(), this.getSkin());
        scoreActor.setPosition(-700,390);
        scoreActor.setFontScale(1.5f,1.5f);
        //scoreActor.setZIndex(CoreConstant.MIN_Z_INDEX);
        this.getStage().addActor(scoreActor);
    }

    @Override
    public void render(float delta) {
        this.getStage().act(delta);
        this.getStage().draw();
        this.scoreActor.setText("Score : "+ this.getPlayer().GetFirstComponentFromClass(PlayerController.class).getScore());
        this.scoreActor.setX(this.scoreActor.getX()+ delta*GameConstant.CAMERA_SPEED);
        this.lifeActor.setX(this.lifeActor.getX()+ delta*GameConstant.CAMERA_SPEED);

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
}