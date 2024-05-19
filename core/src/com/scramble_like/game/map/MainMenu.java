package com.scramble_like.game.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector4;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.scramble_like.game.GameConstant;
import com.scramble_like.game.component.controller.RotateController;
import com.scramble_like.game.component.paper2d.Flipbook;
import com.scramble_like.game.essential.GameObject;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.essential.exception.SceneIsNullException;
import com.scramble_like.game.ui.AE_Label;
import com.scramble_like.game.ui.AE_TitleLabel;



public class MainMenu extends Scene
{

    public MainMenu() throws SceneIsNullException {
        super("MainMenu");

        backgroundColor = new Vector4(0, 0, 0, 1);

        Label titleText = new AE_TitleLabel("Aqua Escape", this.getSkin());
        titleText.setPosition((GameConstant.WIDTH - titleText.getWidth()) / 2 , (float) GameConstant.HEIGHT / 2 + 100);
        this.getStage().addActor(titleText);

        Label playButton = new AE_Label("Play", this.getSkin());
        playButton.setPosition((float) GameConstant.WIDTH / 2 - playButton.getWidth(), (float) GameConstant.HEIGHT / 2);
        playButton.addListener(new ClickListener() { @Override public void clicked(InputEvent event, float x, float y) { getGame().setScreen(new TestMap()); dispose(); } });
        this.getStage().addActor(playButton);

        Label levelMapButton = new AE_Label("Level Map", this.getSkin());
        levelMapButton.setPosition((float) GameConstant.WIDTH / 2 - levelMapButton.getWidth() / 2 - 100, (float) GameConstant.HEIGHT / 2 - 50);
        levelMapButton.addListener(new ClickListener() { @Override public void clicked(InputEvent event, float x, float y) { getGame().setScreen(new GameOver(40)); dispose(); } });
        this.getStage().addActor(levelMapButton);

        Label exitButton = new AE_Label("Exit", this.getSkin());
        exitButton.setPosition((float) GameConstant.WIDTH / 2 - exitButton.getWidth() / 2 + 100, (float) GameConstant.HEIGHT / 2 - 50);
        exitButton.addListener(new ClickListener() {  @Override public void clicked(InputEvent event, float x, float y) { Gdx.app.exit(); } });
        this.getStage().addActor(exitButton);



        GameObject leftMeduse = new GameObject("LeftMeduse", this);
        leftMeduse.AddComponent(new Flipbook("Characters/Fish/Jellyfish/Idle.png", 4));
        leftMeduse.getTransform().Translate(-200, 0);
        AddGameObject(leftMeduse);

        GameObject rightMeduse = new GameObject("RightMeduse", this);
        rightMeduse.AddComponent(new Flipbook("Characters/Fish/Jellyfish/Idle.png", 4));
        rightMeduse.getTransform().Translate(300, 0);
        AddGameObject(rightMeduse);

        GameObject bottomMeduse = new GameObject("BottomMeduse", this);
        bottomMeduse.AddComponent(new Flipbook("Characters/Fish/Jellyfish/Idle.png", 4));
        bottomMeduse.getTransform().Translate(0, -50);
        AddGameObject(bottomMeduse);

        GameObject shark = new GameObject("BottomMeduse", this);
        shark.AddComponent(new Flipbook("Characters/Fish/BigSawFish/Walk.png", 4));
        shark.getTransform().Translate(0, -100);
        AddGameObject(shark);

        Vector2[] sharkPatrolPoints =new  Vector2[]{
                new Vector2(-50,-100),
                new Vector2(50, -100)
        };
        RotateController sharkController = new RotateController(sharkPatrolPoints,50);
        shark.AddComponent(sharkController);
    }
}

