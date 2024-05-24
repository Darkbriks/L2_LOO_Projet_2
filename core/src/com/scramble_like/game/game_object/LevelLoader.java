package com.scramble_like.game.game_object;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.scramble_like.game.GameConstant;
import com.scramble_like.game.essential.GameObject;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.essential.chaos.SphereCollider;
import com.scramble_like.game.essential.event_dispatcher.EventIndex;
import com.scramble_like.game.essential.event_dispatcher.EventListener;
import com.scramble_like.game.essential.event_dispatcher.event.physics.EventBeginOverlap;
import com.scramble_like.game.essential.exception.SceneIsNullException;
import com.scramble_like.game.essential.utils.Writer;
import com.scramble_like.game.map.LevelSlide;

import java.util.EventObject;

public class LevelLoader extends GameObject
{
    private final int levelToLoad;
    private final int currentLevel;

    public LevelLoader(String name, Scene scene, int levelToLoad, int currentLevel) throws SceneIsNullException
    {
        super(name, scene);
        this.levelToLoad = levelToLoad;
        this.currentLevel = currentLevel;
    }

    @Override
    public void BeginPlay()
    {
        super.BeginPlay();

        SphereCollider sphereCollider = new SphereCollider(125, true, true);
        this.AddComponent(sphereCollider);

        this.getEventDispatcher().AddListener(EventIndex.BEGIN_OVERLAP, new EventListener() {
            @Override
            public void handleEvent(EventObject event)
            {
                EventBeginOverlap e = (EventBeginOverlap) event;
                if (e.otherGameObject instanceof Player)
                {
                    Player player = (Player) e.otherGameObject;
                    boolean hasGoldenStrawberry = player.getPlayerController().hasGoldenStrawberry();
                    Writer.writeLevelInfo(currentLevel, hasGoldenStrawberry, "golden_strawberry.txt");

                    Writer.writeLevelInfo(currentLevel, true, "unlock.txt");

                    try
                    {
                        Scene newScene = GameConstant.LEVEL_LIST.get(levelToLoad).getConstructor().newInstance();
                        getScene().dispose();
                        getScene().getGame().setScreen(new LevelSlide(LevelLoader.this.getScene(),newScene));
                    }
                    catch (Exception exception) { Gdx.app.error("LevelLoader", "Error: " + exception.getMessage()); }
                }
            }
        });
    }
}
