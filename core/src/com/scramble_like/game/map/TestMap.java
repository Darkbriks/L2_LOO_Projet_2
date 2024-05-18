package com.scramble_like.game.map;

import com.badlogic.gdx.math.Vector2;
import com.scramble_like.game.GameConstant;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.essential.exception.SceneIsNullException;
import com.scramble_like.game.essential.factory.SoundFactory;
import com.scramble_like.game.game_object.ChunkManager;
import com.scramble_like.game.game_object.Player;
import com.scramble_like.game.game_object.enemy.MovingEnemy;
import com.scramble_like.game.game_object.Background;
public class TestMap extends Scene
{
    public TestMap()
    {
        super("TestMap");

        try
        {
            Player go1 = new Player("Player", this, new Vector2(-350, 0));
            AddGameObject(go1);

            //AddGameObject(new Particule("Particule",this,"Walk.png",4));

            Background background =  new Background("Background", this, "background/backG.png",1335);
            background.getTransform().setZIndex(GameConstant.MAX_Z_INDEX);
            AddGameObject(background);

            Vector2[] waypoints = {new Vector2(0, 300), new Vector2(300, 300), new Vector2(300, 0), new Vector2(0, 0)};
            AddGameObject(new MovingEnemy("Enemy", this, "badlogic.jpg", 5, false, null, waypoints, 150));

            ChunkManager chunkManager = new ChunkManager("ChunkManager", this, 2, -500);
            AddGameObject(chunkManager);
            chunkManager.setPlayer(go1);
        }
        catch (SceneIsNullException e) { System.out.println("Error: " + e.getMessage()); }

        SoundFactory.getInstance().loadSound("Drop", GameConstant.SOUND_PATH("drop.wav"));
        SoundFactory.getInstance().playSound("Drop", 1.0f);
    }
}