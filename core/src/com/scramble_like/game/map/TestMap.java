package com.scramble_like.game.map;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.math.Vector3;
import com.scramble_like.game.ScrambleLikeApplication;
import com.scramble_like.game.component.TestComponent;
import com.scramble_like.game.essential.GameObject;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.essential.event_dispatcher.EventIndex;
import com.scramble_like.game.essential.event_dispatcher.EventListener;
import com.scramble_like.game.essential.event_dispatcher.event.chunk.ChunkLoadedEvent;
import com.scramble_like.game.essential.exception.GameIsNullException;
import com.scramble_like.game.essential.exception.SceneIsNullException;
import com.scramble_like.game.essential.event_dispatcher.EventDispatcher;
import com.scramble_like.game.game_object.ChunkManager;
import com.scramble_like.game.game_object.Enemy;
import com.scramble_like.game.game_object.Player;
import com.scramble_like.game.game_object.Projectile;
import com.scramble_like.game.utils.Chunk;

import java.util.EventObject;

public class TestMap extends Scene
{
    public TestMap(ScrambleLikeApplication game) throws GameIsNullException
    {
        super(game, "TestMap");

        try
        {
            AddGameObject(new ChunkManager("ChunkManager", this, 0));

            GameObject go1 = new Player("Player", this, new Vector3(-350, 0, 0));
            //go1.AddComponent(new TestComponent("Map/Level_1/text_art (1).txt"));
            //go1.AddComponent(new TestComponent("Map/Level_1/text_art (2).txt"));
            //go1.AddComponent(new TestComponent("Map/Level_1/text_art (3).txt"));
            //go1.AddComponent(new TestComponent("Map/Level_1/text_art (4).txt"));
            AddGameObject(go1);
            //AddGameObject(new Projectile("Projectile", this, go1, new Vector3(500, 0, 0)));
            //AddGameObject(new Enemy("Enemy", this, 100, 100, true, go1, "badlogic.jpg", 10));
        }
        catch (SceneIsNullException e) { System.out.println("Error: " + e.getMessage()); }


    }
}
