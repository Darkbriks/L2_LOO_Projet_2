package com.scramble_like.game.map;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.math.Vector3;
import com.scramble_like.game.component.Collider;
import com.scramble_like.game.component.Sprite;
import com.scramble_like.game.component.TestComponent;
import com.scramble_like.game.essential.GameObject;
import com.scramble_like.game.essential.Scene;

public class TestMap extends Scene
{
    public TestMap(Game game)
    {
        super(game);

        // Debug - Add GO
        //TODO
        GameObject go = new GameObject("Test", this);
        go.getTransform().setLocation(new Vector3(-400,0,0));
        go.AddComponent(new Collider(0,0,200,200));
        AddGameObject(go);

        GameObject go2 = new GameObject("test2", this);
        go2.AddComponent(new Sprite());
        go2.AddComponent(new TestComponent());
        go2.AddComponent(new Collider(0,0,200,200));
        AddGameObject(go2);
        //go2.getTransform().setAlignment(new Vector2(1.0f, 1.0f));
    }
}
