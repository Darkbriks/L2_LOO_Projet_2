package com.scramble_like.game.map;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.scramble_like.game.GameConstant;
import com.scramble_like.game.ScrambleLikeApplication;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.essential.exception.SceneIsNullException;
import com.scramble_like.game.essential.factory.SoundFactory;
import com.scramble_like.game.game_object.ChunkManager;
import com.scramble_like.game.game_object.Player;
import com.scramble_like.game.game_object.enemy.MovingEnemy;

public class TestMap extends Scene
{
    public TestMap()
    {
        super("TestMap");

        try
        {
            ChunkManager chunkManager = new ChunkManager("ChunkManager", this, 0, -500);
            AddGameObject(chunkManager);

            Player go1 = new Player("Player", this, new Vector3(-350, 0, 0));
            AddGameObject(go1);

            //AddGameObject(new Particule("Particule",this,"Walk.png",4));

            /*Background background =  new Background("Background", this, "backG.png");
            AddGameObject(background);*/

            chunkManager.setPlayer(go1);

            Vector2[] waypoints = {new Vector2(0, 300), new Vector2(300, 300), new Vector2(300, 0), new Vector2(0, 0)};
            AddGameObject(new MovingEnemy("Enemy", this, "badlogic.jpg", 5, false, null, waypoints, 150));
        }
        catch (SceneIsNullException e) { System.out.println("Error: " + e.getMessage()); }

        SoundFactory.getInstance().loadSound("Drop", GameConstant.SOUND_PATH("drop.wav"));
        SoundFactory.getInstance().playSound("Drop", 1.0f);
    }
}
