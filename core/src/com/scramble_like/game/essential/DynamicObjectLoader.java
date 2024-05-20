package com.scramble_like.game.essential;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.scramble_like.game.game_object.enemy.MovingEnemy;
import com.scramble_like.game.game_object.enemy.Turtle;
import com.scramble_like.game.game_object.foreground.ForegroundObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DynamicObjectLoader
{
    private static final Map<Integer, Class<? extends GameObject>> objectMap = new HashMap<>();
    static
    {
        objectMap.put(48, Turtle.class);//0
        objectMap.put(49, ForegroundObject.class);//1
        objectMap.put(50, null);//2
        objectMap.put(51, null);//3
        objectMap.put(52, null);//4
        objectMap.put(53, null);//5
        objectMap.put(54, null);//6
        objectMap.put(55, null);//7
        objectMap.put(56, null);//8
        objectMap.put(57, null);//9
    }

    private static final DynamicObjectLoader instance = new DynamicObjectLoader();

    private DynamicObjectLoader() {}

    public static DynamicObjectLoader getInstance() { if (instance == null) { return new DynamicObjectLoader(); } return instance; }

    public void loadAll(Scene scene, String path)
    {
        try
        {
            List<String> lines = Files.readAllLines(Paths.get(path));

            for (int i = 0; i < lines.size(); i++)
            {
                List<String> args = List.of(lines.get(i).split(" "));
                if (args.size() == 3) { loadObjectWithPosition(scene, Integer.parseInt(args.get(0)), Float.parseFloat(args.get(1)), Float.parseFloat(args.get(2))); }
                else if (args.size() == 4) { loadObjectWithPositionAndPath(scene, Integer.parseInt(args.get(0)), Float.parseFloat(args.get(1)), Float.parseFloat(args.get(2)), args.get(3)); }
                else { loadObjectWithWaypoints(scene, Integer.parseInt(args.get(0)), parseWaypoints(args)); }
            }
        }
        catch (Exception e) { Gdx.app.error("DynamicObjectLoader1", "Error: " + e.getMessage()); }
    }

    private void loadObjectWithPosition(Scene scene, int id, float x, float y)
    {
        try
        {
            GameObject go = objectMap.get(id).getConstructor(String.class, Scene.class, float.class, float.class).newInstance("DynamicObject", scene, x, y);
            scene.AddGameObject(go);
        }
        catch (Exception e) { Gdx.app.error("DynamicObjectLoader2", "Error: " + e.getMessage()); }
    }

    private void loadObjectWithPositionAndPath(Scene scene, int id, float x, float y, String path)
    {
        try
        {
            GameObject go = objectMap.get(id).getConstructor(String.class, Scene.class, float.class, float.class, String.class).newInstance("DynamicObject", scene, x, y, path);
            scene.AddGameObject(go);
        }
        catch (Exception e) { Gdx.app.error("DynamicObjectLoader3", "Error: " + e.getMessage()); }
    }

    private void loadObjectWithWaypoints(Scene scene, int id, Vector2[] waypoints)
    {
        try
        {
            Gdx.app.error("DynamicObjectLoader", "ID: " + id + " Waypoints: " + waypoints[0] + " " + waypoints[1]);
            GameObject go = objectMap.get(id).getConstructor(String.class, Scene.class, Vector2[].class).newInstance("DynamicObject", scene, waypoints);
            Gdx.app.error("DynamicObjectLoader", "GameObject: " + go);
            scene.AddGameObject(go);
        }
        catch (Exception e) { Gdx.app.error("DynamicObjectLoader4", "Error: " + e.getMessage()); }
    }

    private Vector2[] parseWaypoints(List<String> args)
    {
        //Debugging
        for (int i = 0; i < args.size(); i++) { Gdx.app.error("DynamicObjectLoader_", "Arg: " + args.get(i)); }

        Vector2[] waypoints = new Vector2[args.size() / 2];
        for (int i = 1; i < args.size(); i += 2) { waypoints[i / 2] = new Vector2(Float.parseFloat(args.get(i)), Float.parseFloat(args.get(i + 1))); }

        //Debugging
        for (int i = 0; i < waypoints.length; i++) { Gdx.app.error("DynamicObjectLoader__", "Waypoint: " + waypoints[i]); }

        return waypoints;
    }
}
