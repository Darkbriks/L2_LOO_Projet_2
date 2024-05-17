package com.scramble_like.game.map;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.scramble_like.game.ScrambleLikeApplication;
import com.scramble_like.game.component.Tile;
import com.scramble_like.game.essential.GameObject;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.essential.exception.GameIsNullException;
import com.scramble_like.game.essential.exception.SceneIsNullException;
import com.scramble_like.game.game_object.ChunkManager;
import com.scramble_like.game.game_object.Particule;
import com.scramble_like.game.game_object.Player;
import com.scramble_like.game.game_object.enemy.MovingEnemy;

public class TestMap extends Scene
{
    public TestMap(ScrambleLikeApplication game) throws GameIsNullException
    {
        super(game, "TestMap");

        try
        {
            ChunkManager chunkManager = new ChunkManager("ChunkManager", this, 0, -500);
            AddGameObject(chunkManager);

            Player go1 = new Player("Player", this, new Vector3(-350, 0, 0));
            AddGameObject(go1);
            Particule p1 = new Particule("Particule",this,"Walk.png",4,2f, new Vector3(-350, 0, 0));
            AddGameObject(p1);

            chunkManager.setPlayer(go1);

            /*GameObject go2 = new GameObject("GameObject", this);
            go2.AddComponent(new Tile("badlogic.jpg", 0, 0));
            AddGameObject(go2);*/

            //AddGameObject(new Projectile("Projectile", this, go1, new Vector3(500, 0, 0)));
            Vector2[] waypoints = {new Vector2(0, 300), new Vector2(300, 300), new Vector2(300, 0), new Vector2(0, 0)};
            AddGameObject(new MovingEnemy("Enemy", this, "badlogic.jpg", 100, false, null, waypoints, 150));
        }
        catch (SceneIsNullException e) { System.out.println("Error: " + e.getMessage()); }


    }
}
