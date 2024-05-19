package com.scramble_like.game.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector4;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.ui.AE_Label;
import com.scramble_like.game.ui.AE_TitleLabel;

public class MainMenu extends Scene
{
    public MainMenu()
    {
        super("MainMenu");

        backgroundColor = new Vector4(0, 0, 0, 1);

        Label titleText = new AE_TitleLabel("Aqua Escape", this.getSkin());
        titleText.setPosition((-titleText.getWidth()) / 2 , 100);
        this.getStage().addActor(titleText);

        Label playButton = new AE_Label("Play", this.getSkin());
        playButton.setPosition(-playButton.getWidth(), 0);
        playButton.addListener(new ClickListener() { @Override public void clicked(InputEvent event, float x, float y) { getGame().setScreen(new TestMap()); dispose(); } });
        this.getStage().addActor(playButton);

        Label levelMapButton = new AE_Label("Level Map", this.getSkin());
        levelMapButton.setPosition(-levelMapButton.getWidth() / 2 - 100, -50);
        levelMapButton.addListener(new ClickListener() { @Override public void clicked(InputEvent event, float x, float y) { getGame().setScreen(new GameOver(40)); dispose(); } });
        this.getStage().addActor(levelMapButton);

        Label exitButton = new AE_Label("Exit", this.getSkin());
        exitButton.setPosition(-exitButton.getWidth() / 2 + 100, -50);
        exitButton.addListener(new ClickListener() {  @Override public void clicked(InputEvent event, float x, float y) { Gdx.app.exit(); } });
        this.getStage().addActor(exitButton);
    }
}
