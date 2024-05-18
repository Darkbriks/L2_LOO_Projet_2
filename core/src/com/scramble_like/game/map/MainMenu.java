package com.scramble_like.game.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector4;
import com.scramble_like.game.ScrambleLikeApplication;
import com.scramble_like.game.component.paper2d.Text;
import com.scramble_like.game.essential.GameObject;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.essential.exception.SceneIsNullException;

public class MainMenu extends Scene
{
    public MainMenu()
    {
        super("MainMenu");

        backgroundColor = new Vector4(0, 0, 0, 1);

        try
        {
            GameObject titleText = new GameObject("TitleText", this);
            titleText.AddComponent(new Text("Welcome to Scramble Like!!!", 2, Color.CORAL));
            titleText.getTransform().Translate(-190, 25);
            AddGameObject(titleText);

            GameObject startText = new GameObject("StartText", this);
            startText.AddComponent(new Text("Tap anywhere to begin!"));
            startText.getTransform().Translate(-75, -25);
            AddGameObject(startText);
        }
        catch (SceneIsNullException e) { System.err.println(e.getMessage()); }
    }

    @Override
    public void render(float delta)
    {
        if (Gdx.input.isTouched()) { getGame().setScreen(new TestMap()); }
        super.render(delta);
    }
}
