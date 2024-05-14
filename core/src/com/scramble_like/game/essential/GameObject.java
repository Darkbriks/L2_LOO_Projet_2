package com.scramble_like.game.essential;

import com.scramble_like.game.essential.utils.Transform;

import java.lang.reflect.Constructor;
import java.util.ArrayList;

public class GameObject
{
    private static int globalID = 0;
    private final int ID;
    private String name;
    private Transform transform;
    private Scene scene;
    private boolean isActive = true;
    private boolean isMarkedForDestruction = false;
    private ArrayList<Component> components;

    public GameObject(String name, Scene scene)
    {
        this.ID = globalID++;
        this.name = name;
        this.scene = scene;
        this.components = new ArrayList<>();
        this.transform = new Transform();
    }

    public int getID() { return this.ID; }

    public String getName() { return this.name; }

    public Transform getTransform() { return this.transform; }

    public boolean IsActive() { return this.isActive; }

    public void SetActive(boolean isActive) { this.isActive = isActive; }

    public boolean IsMarkedForDestruction() { return this.isMarkedForDestruction; }

    public ArrayList<Component> GetComponents() { return this.components; }

    public <T extends Component> T GetFirstComponentFromClass(Class<T> componentClass)
    {
        for (Component c : this.components)
        {
            if (componentClass.isAssignableFrom(c.getClass()))
            {
                try { return componentClass.cast(c); }
                catch (ClassCastException e) { System.err.println("Error: " + e.getMessage()); }
            }
        }
        return null;
    }

    public <T extends Component> ArrayList<T> GetAllComponentsFromClass(Class<T> componentClass)
    {
        ArrayList<T> components = new ArrayList<>();
        for (Component c : this.components)
        {
            if (componentClass.isAssignableFrom(c.getClass()))
            {
                try { components.add(componentClass.cast(c)); }
                catch (ClassCastException e) { System.err.println("Error: " + e.getMessage()); }
            }
        }
        return components;
    }

    public void AddComponent(Class<? extends Component> componentClass)
    {
        try
        {
            Constructor<? extends Component> constructor = componentClass.getDeclaredConstructor(Object.class);
            Component component = constructor.newInstance(this);
            components.add(component);
        }
        catch (Exception ex) { System.err.println("Erreur : " + ex.getMessage()); } //TODO: Handle this exception
    }

    public void RemoveComponent(Component component) { this.components.remove(component); }

    public void RemoveAllComponents() { this.components.clear(); }

    public void BeginPlay()
    {
        for (Component c : this.components) { c.BeginPlay(); }
    }

    public void Update(double dt)
    {
        if (!this.isActive) { return; }
        for (Component c : this.components) { c.Update(dt); }
    }

    public void Render()
    {
        if (!this.isActive) { return; }
        for (Component c : this.components) { c.Render(); }
    }

    public void Destroy()
    {
        for (Component c : this.components) { c.Destroy(); }
        this.components.clear();
        this.isMarkedForDestruction = true;
    }
}
