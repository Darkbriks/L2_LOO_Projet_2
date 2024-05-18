package com.scramble_like.game.essential.factory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.ObjectMap;
import com.scramble_like.game.ScrambleLikeApplication;
import com.scramble_like.game.essential.TickableObject;

public class SoundFactory implements Disposable, TickableObject
{
    private static SoundFactory instance;
    private final ObjectMap<String, Sound> soundMap;

    private Music backgroundMusic;
    private float targetVolume;
    private float fadeDuration;
    private float fadeTime;
    private boolean isInFade;

    private String newFilePath;
    private float newVolume;

    private SoundFactory() { soundMap = new ObjectMap<>(); ScrambleLikeApplication.getInstance().AddTickableObject(this); newFilePath = null;}

    public static SoundFactory getInstance()
    {
        if (instance == null) { instance = new SoundFactory(); }
        return instance;
    }

    public void loadSound(String soundName, String filePath)
    {
        if (!soundMap.containsKey(soundName))
        {
            Sound sound = Gdx.audio.newSound(Gdx.files.internal(filePath));
            soundMap.put(soundName, sound);
        }
    }

    public void playSound(String soundName)
    {
        Sound sound = soundMap.get(soundName);
        if (sound != null) { sound.play(); }
        else { Gdx.app.log("SoundFactory", "Le son " + soundName + " n'a pas été chargé."); }
    }

    public void playSound(String soundName, float volume)
    {
        Sound sound = soundMap.get(soundName);
        if (sound != null) { sound.play(volume); }
        else { Gdx.app.log("SoundFactory", "Le son " + soundName + " n'a pas été chargé."); }
    }

    public void unloadSound(String soundName)
    {
        Sound sound = soundMap.remove(soundName);
        if (sound != null) { sound.dispose(); }
    }

    public void unloadAllSounds()
    {
        for (Sound sound : soundMap.values()) { sound.dispose(); }
        soundMap.clear();
    }

    public void playBackgroundMusic(String filePath, float volume)
    {
        if (backgroundMusic != null) { this.stopBackgroundMusic(); }
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal(filePath));
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(volume);
        backgroundMusic.play();
    }

    public void playBackgroundMusicWithFade(String filePath, float volume, float fadeDuration)
    {
        if (backgroundMusic != null) { this.stopBackgroundMusic(); }
        playBackgroundMusic(filePath, 0); fade(volume, fadeDuration);
    }

    public void stopBackgroundMusic()
    {
        if (backgroundMusic != null)
        {
            backgroundMusic.stop();
            backgroundMusic.dispose();
        }
    }

    public void fade(float targetVolume, float fadeDuration)
    {
        this.targetVolume = targetVolume;
        this.fadeDuration = fadeDuration;
        fadeTime = 0;
        isInFade = true;
    }

    public void changeBackgroundMusicWithFade(String filePath, float volume, float duration)
    {
        if (backgroundMusic != null && backgroundMusic.isPlaying())
        {
            targetVolume = 0;
            fadeDuration = duration / 2;
            fadeTime = 0;
            isInFade = true;
            newFilePath = filePath;
            newVolume = volume;
        }
        else { playBackgroundMusic(filePath, 0); fade(volume, duration); }
    }

    @Override
    public void Tick(float deltaTime)
    {
        if (isInFade)
        {
            fadeTime += deltaTime;
            if (fadeTime < fadeDuration)
            {
                if (targetVolume < backgroundMusic.getVolume()) { backgroundMusic.setVolume(1 - (fadeTime / fadeDuration)); }
                else { backgroundMusic.setVolume(targetVolume * (fadeTime / fadeDuration)); }
            }
            else
            {
                backgroundMusic.setVolume(targetVolume); isInFade = false;
                if (newFilePath != null) { playBackgroundMusic(newFilePath, 0); fade(newVolume, fadeDuration); newFilePath = null; }
            }
        }
    }

    @Override
    public void dispose() { unloadAllSounds(); }
}