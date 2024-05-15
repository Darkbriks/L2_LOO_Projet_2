package com.scramble_like.game.map;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector4;
import com.scramble_like.game.component.Text;
import com.scramble_like.game.essential.GameObject;
import com.scramble_like.game.essential.Scene;

public class GameOver extends Scene {
    public GameOver(Game game)
    {
        super(game);

        backgroundColor = new Vector4(0, 0, 0, 1);

        GameObject gameOverText = new GameObject("GameOverText", this);
        gameOverText.AddComponent(new Text("RIP loser!!!!", 2, Color.RED));
        gameOverText.getTransform().Translate(-190, 25, 0);
        AddGameObject(gameOverText);
    }
}
