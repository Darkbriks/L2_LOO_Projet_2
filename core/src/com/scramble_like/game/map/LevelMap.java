package com.scramble_like.game.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.essential.factory.ImageFactory;
import com.scramble_like.game.ui.AE_Label;

public class LevelMap extends Scene
{
    private final Texture backgroundTexture;

    public LevelMap()
    {
        super("LevelMap");

        System.out.println("LevelMap");

        getCamera().setPosition(0, 0);

        //backgroundTexture = new Texture(Gdx.files.internal("LevelMap/LMAP.png"));
        backgroundTexture = ImageFactory.getTexture("LevelMap/LMAP.png");

        Label backButton = new AE_Label("Back", this.getSkin(),new Color(0,0,0,1));
        backButton.setPosition(-800, Gdx.graphics.getHeight() - 400);
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                getGame().setScreen(new MainMenu());
                dispose();
            }
        });
        this.getStage().addActor(backButton);


        Label level1 =new AE_Label("1", this.getSkin(),new Color(0.8f, 0.2f, 0.0f, 1));
        level1.setPosition(145, Gdx.graphics.getHeight() - 730);
        level1.setFontScale(1.5f);
        level1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                getGame().setScreen(new MainMenu());
                dispose();
            }
        });
        this.getStage().addActor(level1);

        Label level2 =new AE_Label("2", this.getSkin(),new Color(0.8f, 0.2f, 0.0f, 1));
        level2.setPosition(-262, Gdx.graphics.getHeight() - 825f);
        level2.setFontScale(1.5f);
        level2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                getGame().setScreen(new MainMenu());
                dispose();
            }
        });
        this.getStage().addActor(level2);

        Label level3 =new AE_Label("3", this.getSkin(),new Color(0.8f, 0.2f, 0.0f, 1));
        level3.setPosition(-620, Gdx.graphics.getHeight() - 705);
        level3.setFontScale(1.5f);
        level3.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                getGame().setScreen(new MainMenu());
                dispose();
            }
        });
        this.getStage().addActor(level3);
    }

    @Override
    public void render(float delta)
    {
        this.getBatch().begin();
        this.getBatch().draw(backgroundTexture, -640, -346, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.getBatch().end();

        super.render(delta);
    }

    @Override
    public void dispose()
    {
        super.dispose();
        backgroundTexture.dispose();
    }
}
