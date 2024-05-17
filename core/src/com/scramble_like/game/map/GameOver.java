package com.scramble_like.game.map;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector4;
import com.scramble_like.game.ScrambleLikeApplication;
import com.scramble_like.game.component.paper2d.Text;
import com.scramble_like.game.essential.GameObject;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.essential.exception.GameIsNullException;
import com.scramble_like.game.essential.exception.SceneIsNullException;

public class GameOver extends Scene
{
    public GameOver(ScrambleLikeApplication game) throws GameIsNullException
    {
        super(game, "GameOver");

        backgroundColor = new Vector4(0, 0, 0, 1);

        try
        {
            GameObject gameOverText = new GameObject("GameOverText", this);
            gameOverText.AddComponent(new Text("RIP loser!!!!", 2, Color.RED));
            gameOverText.getTransform().Translate(-190, 25, 0);
            AddGameObject(gameOverText);
        }
        catch (SceneIsNullException e) { System.err.println(e.getMessage()); }
    }
}
