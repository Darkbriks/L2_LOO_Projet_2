package com.scramble_like.game.essential.utils;

import com.badlogic.gdx.Gdx;
import com.scramble_like.game.essential.GameObject;
import com.scramble_like.game.essential.Scene;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Profiler optionnel, actif uniquement si la propriété système "ae.profile" est définie
 * (chemin du fichier CSV de sortie). Une ligne est écrite par seconde de jeu.
 *
 * Propriétés associées :
 *   -Dae.profile=chemin.csv  active le profiler
 *   -Dae.duration=60         quitte le jeu après N secondes (optionnel)
 */
public class Profiler
{
    public static final boolean ENABLED = System.getProperty("ae.profile") != null;
    private static final float DURATION = Float.parseFloat(System.getProperty("ae.duration", "0"));

    private static final String[] SECTIONS = { "act", "tick", "update", "render", "draw", "late" };
    private static final long[] sectionNanos = new long[SECTIONS.length];
    private static final Map<String, long[]> updateByClass = new HashMap<>();
    private static final Map<String, long[]> renderByClass = new HashMap<>();

    private static PrintWriter out;
    private static long startNanos;
    private static long windowStart;
    private static long frameStart;
    private static long lastMark;
    private static long lastFrameStart;
    private static long workNanos;
    private static long maxWorkNanos;
    private static long maxIntervalNanos;
    private static int frames;

    private Profiler() {}

    private static void open()
    {
        try
        {
            out = new PrintWriter(new FileWriter(System.getProperty("ae.profile")), true);
            out.println("t_s,scene,fps,work_avg_ms,work_max_ms,interval_max_ms,act_ms,tick_ms,update_ms,render_ms,draw_ms,late_ms,cam_x,cam_y,game_objects,components,pending_removals,grid_entries,threads,heap_mb,top_update,top_render");
        }
        catch (IOException e) { Gdx.app.error("Profiler", "Impossible d'ouvrir le fichier : " + e.getMessage()); }
        startNanos = System.nanoTime();
        windowStart = startNanos;
    }

    public static void beginFrame()
    {
        if (!ENABLED) { return; }
        if (out == null) { open(); }
        long now = System.nanoTime();
        if (lastFrameStart != 0) { maxIntervalNanos = Math.max(maxIntervalNanos, now - lastFrameStart); }
        lastFrameStart = now;
        frameStart = now;
        lastMark = now;
    }

    /** Attribue le temps écoulé depuis le dernier mark à la section donnée (index dans SECTIONS). */
    public static void mark(int section)
    {
        if (!ENABLED) { return; }
        long now = System.nanoTime();
        sectionNanos[section] += now - lastMark;
        lastMark = now;
    }

    public static long now() { return ENABLED ? System.nanoTime() : 0; }

    public static void objectUpdated(GameObject go, long t0) { if (ENABLED) { accumulate(updateByClass, go, t0); } }
    public static void objectRendered(GameObject go, long t0) { if (ENABLED) { accumulate(renderByClass, go, t0); } }

    private static void accumulate(Map<String, long[]> map, GameObject go, long t0)
    {
        long dt = System.nanoTime() - t0;
        map.computeIfAbsent(go.getClass().getSimpleName(), k -> new long[1])[0] += dt;
    }

    public static void endFrame(Scene scene)
    {
        if (!ENABLED) { return; }
        long now = System.nanoTime();
        long work = now - frameStart;
        workNanos += work;
        maxWorkNanos = Math.max(maxWorkNanos, work);
        frames++;

        if (now - windowStart >= 1_000_000_000L)
        {
            writeLine(scene, now);
            windowStart = now;
            frames = 0; workNanos = 0; maxWorkNanos = 0; maxIntervalNanos = 0;
            for (int i = 0; i < sectionNanos.length; i++) { sectionNanos[i] = 0; }
            updateByClass.clear();
            renderByClass.clear();
        }

        if (DURATION > 0 && (now - startNanos) / 1e9 >= DURATION) { out.close(); Gdx.app.exit(); }
    }

    private static void writeLine(Scene scene, long now)
    {
        int gameObjects = 0, components = 0, pending = 0;
        for (GameObject go : scene.getGameObjects())
        {
            gameObjects++;
            components += go.GetComponents().size();
            pending += go.getPendingRemovalCount();
        }

        Runtime rt = Runtime.getRuntime();
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(java.util.Locale.ROOT, "%.1f,%s,%d,%.2f,%.2f,%.2f",
                (now - startNanos) / 1e9, scene.getName(), frames,
                ms(workNanos) / Math.max(frames, 1), ms(maxWorkNanos), ms(maxIntervalNanos)));
        for (long s : sectionNanos) { sb.append(String.format(java.util.Locale.ROOT, ",%.2f", ms(s) / Math.max(frames, 1))); }
        sb.append(String.format(java.util.Locale.ROOT, ",%.0f,%.0f,%d,%d,%d,%d,%d,%d,%s,%s",
                scene.getCamera().getPosition().x, scene.getCamera().getPosition().y,
                gameObjects, components, pending, scene.getSpatialGrid().countEntries(),
                Thread.activeCount(), (rt.totalMemory() - rt.freeMemory()) / (1024 * 1024),
                top(updateByClass), top(renderByClass)));
        out.println(sb);
    }

    private static double ms(long nanos) { return nanos / 1e6; }

    /** Les 3 classes de GameObject les plus coûteuses sur la fenêtre, en ms par frame. */
    private static String top(Map<String, long[]> map)
    {
        List<Map.Entry<String, long[]>> entries = new ArrayList<>(map.entrySet());
        entries.sort((a, b) -> Long.compare(b.getValue()[0], a.getValue()[0]));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Math.min(3, entries.size()); i++)
        {
            if (i > 0) { sb.append(" "); }
            sb.append(String.format(java.util.Locale.ROOT, "%s=%.2f", entries.get(i).getKey(), ms(entries.get(i).getValue()[0]) / Math.max(frames, 1)));
        }
        return sb.toString();
    }
}
