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
    private boolean isFadingOut;
    private String newFilePath;

    private SoundFactory() { soundMap = new ObjectMap<>(); ScrambleLikeApplication.getInstance().AddTickableObject(this); }

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

    public void playBackgroundMusic(String filePath)
    {
        stopBackgroundMusic();
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal(filePath));
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(1);
        backgroundMusic.play();
    }

    public void stopBackgroundMusic()
    {
        if (backgroundMusic != null)
        {
            backgroundMusic.stop();
            backgroundMusic.dispose();
            backgroundMusic = null;
        }
    }

    public void changeBackgroundMusicWithFade(String filePath, float duration)
    {
        if (backgroundMusic != null && backgroundMusic.isPlaying())
        {
            targetVolume = 0;
            fadeDuration = duration;
            fadeTime = 0;
            isFadingOut = true;
            newFilePath = filePath;
        }
        else { playBackgroundMusic(filePath); }
    }

    @Override
    public void Tick(float deltaTime)
    {
        if (isFadingOut && backgroundMusic != null)
        {
            fadeTime += deltaTime;
            float volume = 1 - (fadeTime / fadeDuration);
            backgroundMusic.setVolume(volume);
            if (fadeTime >= fadeDuration)
            {
                backgroundMusic.stop();
                backgroundMusic.dispose();
                backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal(newFilePath));
                backgroundMusic.setLooping(true);
                backgroundMusic.setVolume(0);
                backgroundMusic.play();
                isFadingOut = false;
                fadeTime = 0;
                targetVolume = 1;
            }
        }
        else if (backgroundMusic != null && targetVolume != 1)
        {
            fadeTime += deltaTime;
            float volume = (fadeTime / fadeDuration) * targetVolume;
            backgroundMusic.setVolume(volume);
            if (fadeTime >= fadeDuration) { backgroundMusic.setVolume(targetVolume); targetVolume = 1; }
        }
    }

    @Override
    public void dispose() { unloadAllSounds(); }
}