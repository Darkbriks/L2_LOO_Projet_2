package com.scramble_like.game.map;

import com.badlogic.gdx.math.Vector2;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.essential.exception.SceneIsNullException;
import com.scramble_like.game.essential.factory.SoundFactory;
import com.scramble_like.game.game_object.ChunkManager;
import com.scramble_like.game.game_object.Player;
import com.scramble_like.game.game_object.enemy.MovingEnemy;
import com.scramble_like.game.game_object.projectiles.SimpleBullet;

public class TestMap extends Scene
{
    public TestMap()
    {
        super("TestMap");

        try
        {
            Player go1 = new Player("Player", this, new Vector2(-350, 0));
            AddGameObject(go1);

            //Background background =  new Background("Background", this, "Background/backG.png",1335);
            //AddGameObject(background);

            Vector2[] waypoints = {new Vector2(0, 300), new Vector2(300, 300), new Vector2(300, 0), new Vector2(0, 0)};
            AddGameObject(new MovingEnemy("Enemy", this, "badlogic.jpg", 5, waypoints, 150));

            ChunkManager chunkManager = new ChunkManager("ChunkManager", this, 2, -500);
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
    }
}