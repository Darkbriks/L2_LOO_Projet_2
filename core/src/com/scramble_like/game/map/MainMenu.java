package com.scramble_like.game.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector4;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.scramble_like.game.component.controller.RotateController;
import com.scramble_like.game.component.paper2d.Flipbook;
import com.scramble_like.game.essential.GameObject;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.essential.exception.SceneIsNullException;
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
        levelMapButton.addListener(new ClickListener() { @Override public void clicked(InputEvent event, float x, float y) { getGame().setScreen(new LevelMap()); dispose(); } });
        this.getStage().addActor(levelMapButton);

        Label exitButton = new AE_Label("Exit", this.getSkin());
        exitButton.setPosition(-exitButton.getWidth() / 2 + 100, -50);
        exitButton.addListener(new ClickListener() {  @Override public void clicked(InputEvent event, float x, float y) { Gdx.app.exit(); } });
        this.getStage().addActor(exitButton);

        try
        {
            GameObject leftMeduse = new GameObject("LeftMeduse", this);
            leftMeduse.AddComponent(new Flipbook("Characters/Fish/Jellyfish/Idle.png", 4));
            leftMeduse.getTransform().Translate(-180, -35);
            AddGameObject(leftMeduse);

            GameObject rightMeduse = new GameObject("RightMeduse", this);
            rightMeduse.AddComponent(new Flipbook("Characters/Fish/Jellyfish/Idle.png", 4));
            rightMeduse.getTransform().Translate(135, -35);
            AddGameObject(rightMeduse);

            GameObject bottomMeduse = new GameObject("BottomMeduse", this);
            bottomMeduse.AddComponent(new Flipbook("Characters/Fish/Jellyfish/Idle.png", 4));
            bottomMeduse.getTransform().Translate(0, -100);
            AddGameObject(bottomMeduse);

            GameObject shark = new GameObject("shark", this);
            shark.AddComponent(new Flipbook("Characters/Fish/BigSawFish/Walk.png", 4));
            shark.getTransform().Translate(0, -150);
            AddGameObject(shark);

            Vector2[] sharkPatrolPoints =new  Vector2[]{
                    new Vector2(-50,-200),
                    new Vector2(50, -200)
            };
            RotateController sharkController = new RotateController(sharkPatrolPoints,50);
            shark.AddComponent(sharkController);
        }
        catch (SceneIsNullException e) { System.out.println("Error: " + e.getMessage()); }
    }
}

