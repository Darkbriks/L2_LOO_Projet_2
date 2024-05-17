package com.scramble_like.game.component.music;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;

public class SoundPlayer {

    private Sound sound;

    public SoundPlayer() {
        sound = Gdx.audio.newSound(Gdx.files.internal("sounds/drop.wav"));
    }
    public void update() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {sound.play();}
    }
    public void play() {
        sound.play();
    }

    public void stop() {
        sound.stop();
    }

    public void setVolume(float volume) {
        sound.setVolume(1, volume);
    }

    public void dispose() {
        sound.dispose();
    }
}
