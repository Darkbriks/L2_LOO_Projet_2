package com.scramble_like.game.essential;

import java.util.ArrayList;

public class GameObject
{
    private static int globalID = 0;
    private final int ID;
    private String name;
    private ArrayList<Component> components;

    public GameObject(String name)
    {
        this.ID = globalID++;
        this.name = name;
        this.components = new ArrayList<>();
    }
}
