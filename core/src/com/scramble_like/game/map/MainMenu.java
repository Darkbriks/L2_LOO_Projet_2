package com.scramble_like.game.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.Vector4;
import com.scramble_like.game.component.paper2d.Text;
import com.scramble_like.game.essential.GameObject;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.essential.exception.SceneIsNullException;

public class MainMenu extends Scene {
    private GameObject playButton;
    private GameObject exitButton;
    private GameObject levelMapButton;
    private GameObject titleText;
    private Rectangle playButtonBounds;
    private Rectangle exitButtonBounds;
    private Rectangle levelMapButtonBounds;

    public MainMenu() {
        super("MainMenu");

        backgroundColor = new Vector4(0, 0, 0, 1);

        try {
            // Titre du jeu
            titleText = new GameObject("TitleText", this);
            titleText.AddComponent(new Text("Aqua Escape", 4, Color.CYAN));
            titleText.getTransform().Translate(0, 150); // Centrer le texte
            AddGameObject(titleText);

            // Bouton Play
            playButton = new GameObject("PlayButton", this);
            playButton.AddComponent(new Text("Play", 3, Color.WHITE));
            playButton.getTransform().Translate(0, 50); // Centrer le bouton
            AddGameObject(playButton);
            playButtonBounds = new Rectangle(-50, 25, 100, 50); // Rectangle de clic du bouton Play

            // Bouton Level Map
            levelMapButton = new GameObject("LevelMapButton", this);
            levelMapButton.AddComponent(new Text("Level Map", 3, Color.WHITE));
            levelMapButton.getTransform().Translate(-150, -50); // Positionner à gauche
            AddGameObject(levelMapButton);
            levelMapButtonBounds = new Rectangle(-180, -75, 170, 50); // Rectangle de clic du bouton Level Map

            // Bouton Exit
            exitButton = new GameObject("ExitButton", this);
            exitButton.AddComponent(new Text("Exit", 3, Color.WHITE));
            exitButton.getTransform().Translate(150, -50); // Positionner à droite
            AddGameObject(exitButton);
            exitButtonBounds = new Rectangle(155, -75, 90, 50); // Rectangle de clic du bouton Exit
        } catch (SceneIsNullException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void render(float delta) {
        if (Gdx.input.justTouched()) {
            Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            Camera camera = getCamera().getCamera();
            camera.unproject(touchPos);
            System.out.println("Touch position: " + touchPos.x + ", " + touchPos.y);

            // Si l'utilisateur clique sur le bouton "Exit"
            if (exitButtonBounds.contains(touchPos.x, touchPos.y)) {
                Gdx.app.exit();
            }
            // Si l'utilisateur clique sur le bouton "Play"
            else if (playButtonBounds.contains(touchPos.x, touchPos.y)) {
                getGame().setScreen(new TestMap());
                dispose();
            }
            // Si l'utilisateur clique sur le bouton "Level Map"
            else if (levelMapButtonBounds.contains(touchPos.x, touchPos.y)) {
                getGame().setScreen(new GameOver());
                dispose();
            }
        }

        super.render(delta);
    }
}
