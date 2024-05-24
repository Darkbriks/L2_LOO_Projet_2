package com.scramble_like.game.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.scramble_like.game.GameConstant;
import com.scramble_like.game.component.paper2d.Tile;
import com.scramble_like.game.essential.GameObject;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.ui.AE_Label;

public class LevelMap extends Scene
{
    public LevelMap()
    {
        super("LevelMap");

        getCamera().setPosition(0, 0);

        try
        {
            GameObject background = new GameObject("Background", this);
            background.AddComponent(new Tile("LevelMap/LMAP.png", (float) -GameConstant.WIDTH / 2, (float) -GameConstant.HEIGHT / 2, GameConstant.WIDTH, GameConstant.HEIGHT));
            AddGameObject(background);
        }
        catch (Exception e) { Gdx.app.error("LevelMap", e.getMessage()); }

        Label backButton = new AE_Label("Back", this.getSkin(),new Color(0,0,0,1));
        backButton.setPosition((float) GameConstant.WIDTH / 2 - 300, (float) GameConstant.HEIGHT / 2 - 50);
        backButton.addListener(new ClickListener() { @Override public void clicked(InputEvent event, float x, float y) { getGame().setScreen(new MainMenu()); dispose(); } });
        this.getStage().addActor(backButton);

        Label level1 =new AE_Label("1", this.getSkin(),new Color(0.8f, 0.2f, 0.0f, 1));
        level1.setPosition((float) GameConstant.WIDTH / 2 - 555, (float) GameConstant.HEIGHT / 2 - 495);
        level1.setFontScale(1.5f);
        level1.addListener(new ClickListener() { @Override public void clicked(InputEvent event, float x, float y) { getGame().setScreen(new Level_1()); dispose(); } });
        this.getStage().addActor(level1);

        if (GameConstant.LEVEL_2_UNLOCKED())
        {
            Label level2 =new AE_Label("2", this.getSkin(),new Color(0.8f, 0.2f, 0.0f, 1));
            level2.setPosition((float) -GameConstant.WIDTH / 2 + 535, (float) -GameConstant.HEIGHT / 2 + 280);
            level2.setFontScale(1.5f);
            level2.addListener(new ClickListener() { @Override public void clicked(InputEvent event, float x, float y) { getGame().setScreen(new Level_2()); dispose(); } });
            this.getStage().addActor(level2);
        }

        if (GameConstant.LEVEL_3_UNLOCKED())
        {
            Label level3 =new AE_Label("3", this.getSkin(),new Color(0.8f, 0.2f, 0.0f, 1));
            level3.setPosition((float) -GameConstant.WIDTH / 2 + 85, -15);
            level3.setFontScale(1.5f);
            level3.addListener(new ClickListener() { @Override public void clicked(InputEvent event, float x, float y) { getGame().setScreen(new Level_3()); dispose(); } });
            this.getStage().addActor(level3);
        }
    }
}
