package com.scramble_like.game.component.music;

import com.badlogic.gdx.Gdx;


public class MusicPlayer  {

    private com.badlogic.gdx.audio.Music music;
    public MusicPlayer() {
        music = Gdx.audio.newMusic(Gdx.files.internal("music/rain.mp3"));
        music.setLooping(true);
    }

    public void play() {
        music.play();
    }

    public void pause() {
        music.pause();
    }

    public void stop() {
        music.stop();
    }

    public boolean isPlaying() {
        return music.isPlaying();
    }

    public void setLooping(boolean isLooping) {
        music.setLooping(isLooping);
    }

    public boolean isLooping() {
        return music.isLooping();
    }

    public void setVolume(float volume) {
        music.setVolume(volume);
    }

    public float getVolume() {
        return music.getVolume();
    }

    public void setPan(float pan, float volume) {
        music.setPan(pan, volume);
    }

    public void setPosition(float position) {
        music.setPosition(position);
    }

    public float getPosition() {
        return music.getPosition();
    }

    public void dispose() {
        music.dispose();
    }


}
