package com.scramble_like.game.map;

import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.scramble_like.game.GameConstant;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.essential.utils.Writer;

public class OptionMenu extends Scene {

    private Slider volumeSlider;
    private Slider soundEffectsSlider;
    private Slider speedSlider;

    public OptionMenu()
    {
        super("OptionMenu");
        getCamera().setPosition(0, 0);
        createUI();
    }

    private void createUI()
    {
        Table table = new Table();
        table.setPosition((float) -GameConstant.WIDTH / 2, (float) -GameConstant.HEIGHT / 2);
        table.setFillParent(true);
        table.center();

        Label titleLabel = new Label("Options", getSkin(), "default");
        titleLabel.setFontScale(2);
        table.add(titleLabel).colspan(2).padBottom(50);
        table.row();

        Label volumeLabel = new Label("Volume", getSkin(), "default");
        volumeSlider = new Slider(0, 1, 0.01f, false, getSkin());
        float initialVolume = Writer.getSetting(GameConstant.VOLUME_OPTION, GameConstant.SETTINGS_FILE) / 100.0f;
        volumeSlider.setValue(initialVolume);
        volumeSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                float volume = volumeSlider.getValue();
                Writer.writeSetting(String.valueOf((int) (volume * 100)), GameConstant.VOLUME_OPTION, GameConstant.SETTINGS_FILE, GameConstant.OPTION_LIST,false);
                GameConstant.SOUND_MUSIC_VOLUME = volume;
            }
        });
        table.add(volumeLabel).padBottom(10);
        table.add(volumeSlider).padBottom(10);
        table.row();

        Label soundEffectsLabel = new Label("SoundsEffects",getSkin(),"default");
        soundEffectsSlider = new Slider(0,1,0.01f,false,getSkin());
        float initialSoundEffect = Writer.getSetting(GameConstant.SOUND_EFFECTS_OPTION, GameConstant.SETTINGS_FILE);
        soundEffectsSlider.setValue(initialSoundEffect);
        soundEffectsSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                float soundEffect = soundEffectsSlider.getValue();
                Writer.writeSetting(String.valueOf((int) soundEffect*100), GameConstant.SOUND_EFFECTS_OPTION, GameConstant.SETTINGS_FILE, GameConstant.OPTION_LIST,false);
                GameConstant.SOUND_EFFECT_VOLUME = soundEffect;
            }
        });
        table.add(soundEffectsLabel).padBottom(10);
        table.add(soundEffectsSlider).padBottom(10);
        table.row();

        Label playerSpeedLabel = new Label("ScrollSpeedMultiplier",getSkin(),"default");
        speedSlider = new Slider(0,100,1,false,getSkin());
        float initialPlayerSpeed = Writer.getSetting(String.valueOf(GameConstant.CAMERA_SPEED_MULTIPLIER), GameConstant.SETTINGS_FILE);
        speedSlider.setValue(initialPlayerSpeed);
        speedSlider.addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                float playerSpeed = speedSlider.getValue();
                Writer.writeSetting(String.valueOf((int) playerSpeed), String.valueOf(GameConstant.CAMERA_SPEED_MULTIPLIER), GameConstant.SETTINGS_FILE, GameConstant.OPTION_LIST,false);
                GameConstant.CAMERA_SPEED_MULTIPLIER = (playerSpeed/100.0f)+0.5f;
            }
        });
        table.add(playerSpeedLabel).padBottom(10);
        table.add(speedSlider).padBottom(10);
        table.row();


        Label controlsLabel = new Label("Controls", getSkin(), "default");
        TextButton controlsButton = new TextButton("Configure", getSkin());
        controlsButton.addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                getGame().setScreen(new ControlConfig());
                dispose();
            }
        });
        table.add(controlsLabel).padBottom(25);
        table.add(controlsButton).padBottom(25);
        table.row();

        TextButton backButton = new TextButton("Back", getSkin());
        backButton.addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                getGame().setScreen(new MainMenu());
                dispose();
            }
        });
        table.add(backButton).colspan(2);

        getStage().addActor(table);
    }
}
