package com.scramble_like.game.essential.event_dispatcher.event.game;

import com.scramble_like.game.essential.GameObject;

import java.util.EventObject;

public class PlayerDieEvent extends EventObject {
    public GameObject Player;

    public PlayerDieEvent(GameObject Player)
    {
        super(Player);
        this.Player = Player;

    }
}
