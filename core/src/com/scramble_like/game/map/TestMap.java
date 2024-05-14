package com.scramble_like.game.map;

import com.badlogic.gdx.Game;
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
        GameObject go = new GameObject("Test", this);
        AddGameObject(go);

        GameObject go2 = new GameObject("test2", this);
        go2.AddComponent(new Sprite());
        go2.AddComponent(new TestComponent());
        go2.AddComponent(new Collider());
        AddGameObject(go2);
        //go2.getTransform().setAlignment(new Vector2(1.0f, 1.0f));
    }
}
