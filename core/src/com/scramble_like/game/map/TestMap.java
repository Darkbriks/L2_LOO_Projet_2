package com.scramble_like.game.map;

import com.badlogic.gdx.math.Vector2;
import com.scramble_like.game.essential.chunk.ChunkHelper;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.essential.exception.SceneIsNullException;
import com.scramble_like.game.essential.factory.ImageFactory;
import com.scramble_like.game.essential.factory.SoundFactory;
import com.scramble_like.game.essential.chunk.ChunkManager;
import com.scramble_like.game.game_object.Player;
import com.scramble_like.game.game_object.enemy.MovingEnemy;
import com.scramble_like.game.game_object.Background;

public class TestMap extends Scene
{
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
            Player go1 = new Player("Player", this, new Vector2(-350, 0));
            AddGameObject(go1);

            Background background =  new Background("Background", this, "Background/backG.png",768, 192);
            AddGameObject(background);

            Vector2[] waypoints = {new Vector2(1000, 300), new Vector2(1000, 0)};
            AddGameObject(new MovingEnemy("Enemy", this, "badlogic.jpg", 1, waypoints, 300));

            ChunkManager chunkManager = new ChunkManager("ChunkManager", this, 2);
            AddGameObject(chunkManager);
            chunkManager.setPlayer(go1);
        }
        catch (SceneIsNullException e) { System.out.println("Error: " + e.getMessage()); }

        SoundFactory.getInstance().playBackgroundMusicWithFade("Audio/Music/Reach for the Summit.mp3", 1, 10);
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