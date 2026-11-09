package com.scramble_like.game.essential.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.math.Vector2;
import com.scramble_like.game.GameConstant;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.game_object.Player;

import java.util.Locale;
import java.util.zip.Deflater;

/**
 * Outils de test du mode autoplay (-Dae.level) :
 *   -Dae.bot=x,offsetY   pendant le scrolling vertical, dirige le joueur vers l'abscisse x en le gardant
 *                        à offsetY de la caméra ; il lâche les commandes dès que le scrolling redevient horizontal
 *   -Dae.shoot=true      tir principal en continu
 *   -Dae.shots=dossier   capture d'écran PNG toutes les ae.shotEvery secondes (5 par défaut)
 */
public class Autoplay
{
    private static final float[] BOT = parse(System.getProperty("ae.bot"));
    private static final boolean SHOOT = Boolean.getBoolean("ae.shoot");
    private static final String SHOTS_DIR = System.getProperty("ae.shots");
    private static final float SHOT_EVERY = Float.parseFloat(System.getProperty("ae.shotEvery", "5"));

    private static float elapsed = 0;
    private static float nextShot = SHOT_EVERY;
    private static boolean botReleased = false;

    private Autoplay() {}

    private static float[] parse(String value)
    {
        if (value == null) { return null; }
        String[] parts = value.split(",");
        return new float[] { Float.parseFloat(parts[0]), Float.parseFloat(parts[1]) };
    }

    public static void update(Scene scene)
    {
        elapsed += Gdx.graphics.getDeltaTime();
        if (SHOOT) { GameConstant.SHOOT_BUTTON = true; }
        if (BOT == null || botReleased) { return; }

        Player player = scene.getPlayer();
        if (player == null || player.getPlayerController() == null) { return; }
        if (!player.getPlayerController().isInverseScroll())
        {
            botReleased = true;
            GameConstant.X_AXIS_VALUE = 0;
            GameConstant.Y_AXIS_VALUE = 0;
            return;
        }

        Vector2 location = player.getTransform().getLocation();
        float targetY = scene.getCamera().getPosition().y + BOT[1];
        GameConstant.X_AXIS_VALUE = Math.max(-1, Math.min(1, (BOT[0] - location.x) / 50f));
        GameConstant.Y_AXIS_VALUE = Math.max(-1, Math.min(1, -(targetY - location.y) / 50f));
    }

    public static void capture(Scene scene)
    {
        if (SHOTS_DIR == null || elapsed < nextShot) { return; }
        nextShot += SHOT_EVERY;

        int width = Gdx.graphics.getBackBufferWidth();
        int height = Gdx.graphics.getBackBufferHeight();
        Pixmap pixmap = Pixmap.createFromFrameBuffer(0, 0, width, height);
        String name = String.format(Locale.ROOT, "%s/t%03d_%s_%dx%d.png", SHOTS_DIR, Math.round(elapsed), scene.getName(), width, height);
        PixmapIO.writePNG(Gdx.files.absolute(name), pixmap, Deflater.DEFAULT_COMPRESSION, true);
        pixmap.dispose();
    }
}
