package com.scramble_like.game.game_object;

import com.badlogic.gdx.Gdx;
import com.scramble_like.game.GameConstant;
import com.scramble_like.game.essential.GameObject;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.essential.chaos.SphereCollider;
import com.scramble_like.game.essential.event_dispatcher.EventIndex;
import com.scramble_like.game.essential.event_dispatcher.EventListener;
import com.scramble_like.game.essential.event_dispatcher.event.physics.EventBeginOverlap;
import com.scramble_like.game.essential.exception.SceneIsNullException;
import java.util.EventObject;

public class LevelLoader extends GameObject
{
    private final int levelToLoad;

    public LevelLoader(String name, Scene scene, int levelToLoad) throws SceneIsNullException
    {
        super(name, scene);
        this.levelToLoad = levelToLoad;
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
                    try
                    {
                        Scene newScene = GameConstant.LEVEL_LIST.get(levelToLoad).getConstructor().newInstance();
                        getScene().dispose();
                        getScene().getGame().setScreen(newScene);
                    }
                    catch (Exception exception) { Gdx.app.error("LevelLoader", "Error: " + exception.getMessage()); }
                }
            }
        });
    }
}
