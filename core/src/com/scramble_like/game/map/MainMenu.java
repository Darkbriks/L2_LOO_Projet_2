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
import com.scramble_like.game.component.paper2d.Tile;
import com.scramble_like.game.essential.GameObject;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.essential.exception.SceneIsNullException;
import com.scramble_like.game.essential.factory.SoundFactory;
import com.scramble_like.game.essential.utils.Writer;
import com.scramble_like.game.ui.AE_Label;
import com.scramble_like.game.ui.AE_TitleLabel;

public class MainMenu extends Scene
{
    public MainMenu()
    {
        super("MainMenu");

        getCamera().setPosition(0, 0);
        SoundFactory.getInstance().changeBackgroundMusicWithFade(GameConstant.MUSIC_PATH("ecran_titre.mp3"), GameConstant.SOUND_MUSIC_VOLUME, 3);

        backgroundColor = new Vector4(0, 0, 0, 1);

        Label titleText = new AE_TitleLabel("Aqua Escape", this.getSkin());
        titleText.setPosition((-titleText.getWidth()) / 2 , getCamera().getPosition().y + 100);
        this.getStage().addActor(titleText);

        Label playButton = new AE_Label("Play", this.getSkin());
        playButton.setSize(200,50);
        playButton.setPosition(-playButton.getWidth() / 2, getCamera().getPosition().y);
        playButton.addListener(new ClickListener() { @Override public void clicked(InputEvent event, float x, float y) { getGame().setScreen(new Level_1()); dispose(); } });
        this.getStage().addActor(playButton);

        Label levelMapButton = new AE_Label("Level Map", this.getSkin());
        levelMapButton.setSize(200,50);
        levelMapButton.setPosition(-levelMapButton.getWidth() / 2 - 100, getCamera().getPosition().y - 50);
        levelMapButton.addListener(new ClickListener() { @Override public void clicked(InputEvent event, float x, float y) { getGame().setScreen(new LevelMap()); dispose(); } });
        this.getStage().addActor(levelMapButton);

        Label optionButton = new AE_Label("Options", this.getSkin());
        optionButton.setSize(200,50);
        optionButton.setPosition(-optionButton.getWidth() / 2+ 100 , getCamera().getPosition().y - 50);
        optionButton.addListener(new ClickListener() { @Override public void clicked(InputEvent event, float x, float y) { getGame().setScreen(new OptionMenu()); dispose(); } });
        this.getStage().addActor(optionButton);

        Label exitButton = new AE_Label("Exit", this.getSkin());
        exitButton.setSize(200,50);
        exitButton.setPosition(-exitButton.getWidth() / 2 , getCamera().getPosition().y - 90);
        exitButton.addListener(new ClickListener() {  @Override public void clicked(InputEvent event, float x, float y) { Gdx.app.exit(); } });
        this.getStage().addActor(exitButton);

        try
        {
            GameObject background = new GameObject("Background", this);
            background.AddComponent(new Tile("Background/menu.png", -800, -450, GameConstant.WIDTH, GameConstant.HEIGHT));
            AddGameObject(background);

            GameObject leftMeduse = new GameObject("LeftMeduse", this);
            leftMeduse.AddComponent(new Flipbook("Characters/Fish/Jellyfish/Idle.png", 4));
            leftMeduse.getTransform().Translate(-180, -35);
            AddGameObject(leftMeduse);

            GameObject rightMeduse = new GameObject("RightMeduse", this);
            rightMeduse.AddComponent(new Flipbook("Characters/Fish/Jellyfish/Idle.png", 4));
            rightMeduse.getTransform().Translate(165, -35);
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

