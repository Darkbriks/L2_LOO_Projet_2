package com.scramble_like.game.essential;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Disposable;
import com.scramble_like.game.ScrambleLikeApplication;
import com.scramble_like.game.essential.event_dispatcher.EventDispatcher;
import com.scramble_like.game.essential.exception.OwnerIsNullException;
import com.scramble_like.game.essential.exception.SceneIsNullException;
import com.scramble_like.game.essential.utils.Transform;
import java.util.ArrayList;

public class GameObject implements Disposable
{
    private static int globalID = 0;
    private final int ID;
    private final String name;
    private final Transform transform;
    private final Scene scene;
    private boolean isActive = true;
    private boolean isMarkedForDestruction = false;
    private ArrayList<Component> components;
    private ArrayList<Component> componentsToRemove;
    private final EventDispatcher eventDispatcher;

    public GameObject(String name, Scene scene) throws SceneIsNullException
    {
        this.ID = globalID++;
        this.name = name;
        this.scene = scene;
        this.components = new ArrayList<>();
        this.componentsToRemove = new ArrayList<>();
        this.transform = new Transform();
        this.eventDispatcher = new EventDispatcher();
        if (this.scene == null) { this.Destroy(); this.Destroying(); throw new SceneIsNullException(this); }
        this.isActive = false;
    }

    public int getID() { return this.ID; }
    public String getName() { return this.name; }
    public Transform getTransform() { return this.transform; }
    public Scene getScene() { return this.scene; }
    public ScrambleLikeApplication getGame() { return this.scene.getGame(); }
    public GameCamera getCamera() { return this.scene.getCamera(); }
    public ShapeRenderer getShapeRenderer() { return this.scene.getGame().getShapeRenderer(); }
    public SpriteBatch getBatch() { return this.scene.getBatch(); }
    public BitmapFont getFont() { return this.scene.getFont(); }

    public boolean IsActive() { return this.isActive; }
    public boolean IsMarkedForDestruction() { return this.isMarkedForDestruction; }

    public void setActive(boolean isActive) { this.isActive = isActive; }

    public ArrayList<Component> GetComponents() { return this.components; }

    public <T extends Component> T GetFirstComponentFromClass(Class<T> componentClass)
    {
        for (int i = 0; i < components.size(); i++)
        {
            if (componentClass.isAssignableFrom(components.get(i).getClass()))
            {
                try { return componentClass.cast(components.get(i)); }
                catch (ClassCastException e) { System.err.println("Error: " + e.getMessage()); }
            }
        }
        return null;
    }

    public <T extends Component> ArrayList<T> GetAllComponentsFromClass(Class<T> componentClass)
    {
        ArrayList<T> allComponents = new ArrayList<>();
        for (int i = 0; i < components.size(); i++)
        {
            Component c = components.get(i);
            if (c != null && componentClass.isAssignableFrom(c.getClass()))
            {
                try { allComponents.add(componentClass.cast(c)); }
                catch (ClassCastException e) { System.err.println("Error: " + e.getMessage()); }
            }
        }
        return allComponents;
    }

    public void AddComponent(Component component)
    {
        if (component == null) { return; }
        try { component.Init(this); this.components.add(component); component.BeginPlay(); }
        catch (OwnerIsNullException e) { System.err.println("Error: " + e.getMessage()); }
    }

    public void RemoveComponent(Component component) { this.getScene().DestroyComponent(component); this.componentsToRemove.add(component); }

    public void RemoveAllComponents() { for (Component c : this.components) { this.getScene().DestroyComponent(c); } this.componentsToRemove.clear(); }

    public EventDispatcher getEventDispatcher() { return this.eventDispatcher; }

    public void BeginPlay() { for (Component c : this.components) { c.BeginPlay(); } this.isActive = true;}

    public void Update(float dt)
    {
        if (!this.isActive) { return; }
        for (int i = 0; i < components.size(); i++) { Component c = components.get(i); if (c != null) { c.Update(dt); } }
    }

    public void Render()
    {
        if (!this.isActive) { return; }
        for (int i = 0; i < components.size(); i++) { Component c = components.get(i); if (c != null) { c.Render(); } }

        for (int i = 0; i < componentsToRemove.size(); i++) { components.remove(componentsToRemove.get(i)); }
    }

    public void Destroy()
    {
        for (int i = 0; i < components.size(); i++) { this.getScene().DestroyComponent(components.get(i)); }
        this.isMarkedForDestruction = true;
    }

    public void Destroying()
    {
        /*this.name = null;
        this.transform = null;
        this.scene = null;
        this.components = null;*/
        this.isActive = false;
    }

    public void DestroyThisInScene() { this.getScene().DestroyGameObject(this); }

    @Override
    public void dispose()
    {
        for (int i = 0; i < components.size(); i++) { components.get(i).dispose(); }
        components.clear();
        componentsToRemove.clear();
    }
}
