package com.scramble_like.game.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector4;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.ui.AE_Label;
import com.scramble_like.game.ui.AE_TitleLabel;

public class EndGame extends Scene {
    public EndGame() {
        super("EndGameScene");

        // Background color or image
        backgroundColor = new Vector4(0, 0, 0, 1);

        Label titleText = new AE_TitleLabel("Congratulations! You have successfully ended the game <3", this.getSkin());
        titleText.setPosition((-titleText.getWidth()) / 2 , getCamera().getPosition().y + 100);
        this.getStage().addActor(titleText);

        Label exitButton = new AE_Label("Exit", this.getSkin());
        exitButton.setSize(200,50);
        exitButton.setPosition(-exitButton.getWidth() / 2 , getCamera().getPosition().y - 90);
        exitButton.addListener(new ClickListener() {  @Override public void clicked(InputEvent event, float x, float y) { Gdx.app.exit(); } });
        this.getStage().addActor(exitButton);
    }
}
