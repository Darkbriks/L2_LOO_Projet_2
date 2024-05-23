package com.scramble_like.game.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.scramble_like.game.GameConstant;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.essential.utils.Writer;

import java.util.Map;
import java.util.HashMap;

public class OptionMenu extends Scene {

    private Slider volumeSlider;
    private Slider soundEffectsSlider;
    private Slider speedSlider;

    public OptionMenu() {
        super("OptionMenu");
        createUI();
    }

    private void createUI() {
        Table table = new Table();
        table.setFillParent(true);
        table.center();

        Label titleLabel = new Label("Options", getSkin(), "default");
        titleLabel.setFontScale(2);
        table.add(titleLabel).colspan(2).padTop(400).padRight(1200);
        table.row();

        Label volumeLabel = new Label("Volume", getSkin(), "default");
        volumeSlider = new Slider(0, 1, 0.01f, false, getSkin());
        float initialVolume = Writer.getSetting(GameConstant.VOLUME_OPTION, GameConstant.SETTINGS_FILE) / 100.0f;
        volumeSlider.setValue(initialVolume);
        volumeSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                float volume = volumeSlider.getValue();
                Gdx.app.log("OptionMenu", "Volume adjusted to: " + volume);

                Writer.writeSetting(String.valueOf((int) (volume * 100)), GameConstant.VOLUME_OPTION, GameConstant.SETTINGS_FILE, GameConstant.OPTION_LIST,false);
                GameConstant.SOUND_MUSIC_VOLUME = volume;
            }
        });
        table.add(volumeLabel).padBottom(10);
        table.add(volumeSlider).padBottom(10).padRight(1200);
        table.row();

        Label soundEffectsLabel = new Label("SoundsEffects",getSkin(),"default");
        soundEffectsSlider = new Slider(0,1,0.01f,false,getSkin());
        float initialSoundEffect = Writer.getSetting(GameConstant.SOUND_EFFECTS_OPTION, GameConstant.SETTINGS_FILE);
        soundEffectsSlider.setValue(initialSoundEffect);
        soundEffectsSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                float soundEffect = soundEffectsSlider.getValue();
                Gdx.app.log("OptionMenu", "Player speed adjusted to: " + soundEffect);
                Writer.writeSetting(String.valueOf((int) soundEffect*100), GameConstant.SOUND_EFFECTS_OPTION, GameConstant.SETTINGS_FILE, GameConstant.OPTION_LIST,false);
                GameConstant.SOUND_EFFECT_VOLUME = soundEffect;
            }
        });

        table.add(soundEffectsLabel).padBottom(10);
        table.add(soundEffectsSlider).padBottom(10).padRight(1200);
        table.row();

        Label playerSpeedLabel = new Label("PlayerSpeed",getSkin(),"default");
        speedSlider = new Slider(100,1000,1,false,getSkin());
        float initialPlayerSpeed = Writer.getSetting(GameConstant.PLAYER_SPEED_OPTION, GameConstant.SETTINGS_FILE);
        speedSlider.setValue(initialPlayerSpeed);
        speedSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                float playerSpeed = speedSlider.getValue();
                Gdx.app.log("OptionMenu", "Player speed adjusted to: " + playerSpeed);
                Writer.writeSetting(String.valueOf((int) playerSpeed), GameConstant.PLAYER_SPEED_OPTION, GameConstant.SETTINGS_FILE, GameConstant.OPTION_LIST,false);
                GameConstant.PLAYER_SPEED = playerSpeed;
            }
        });

        table.add(playerSpeedLabel).padBottom(10);
        table.add(speedSlider).padBottom(10).padRight(1200);
        table.row();


        Label controlsLabel = new Label("Controls", getSkin(), "default");
        TextButton controlsButton = new TextButton("Configure", getSkin());
        controlsButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.log("OptionMenu", "Opening control configuration...");
                getGame().setScreen(new ControlConfig());
                dispose();
            }
        });
        table.add(controlsLabel).padBottom(10);
        table.add(controlsButton).padBottom(10).padRight(1200);
        table.row();

        TextButton backButton = new TextButton("Back", getSkin());
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.log("OptionMenu", "Returning to main menu...");
                getGame().setScreen(new MainMenu());
                dispose();
            }
        });
        table.add(backButton).colspan(2).padTop(20).padRight(1200);

        getStage().addActor(table);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        getStage().act(delta);
        getStage().draw();
    }
}