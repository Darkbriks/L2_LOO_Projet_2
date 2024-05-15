package com.scramble_like.game.map;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector4;
import com.scramble_like.game.component.Text;
import com.scramble_like.game.essential.GameObject;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.essential.exception.GameIsNullException;

public class MainMenu extends Scene
{
    public MainMenu(Game game) throws GameIsNullException
    {
        super(game, "MainMenu");

        backgroundColor = new Vector4(0, 0, 0, 1);

        GameObject titleText = new GameObject("TitleText", this);
        titleText.AddComponent(new Text("Welcome to Scramble Like!!!", 2, Color.CORAL));
        titleText.getTransform().Translate(-190, 25, 0);
        AddGameObject(titleText);

        GameObject startText = new GameObject("StartText", this);
        startText.AddComponent(new Text("Tap anywhere to begin!"));
        startText.getTransform().Translate(-75, -25, 0);
        AddGameObject(startText);
    }

    @Override
    public void render(float delta)
    {
        if (Gdx.input.isTouched())
        {
            try { GetGame().setScreen(new TestMap(GetGame())); }
            catch (GameIsNullException e) { System.out.println(e.getMessage()); Gdx.app.exit(); }
            dispose(); return;
        }
        super.render(delta);
    }
}
