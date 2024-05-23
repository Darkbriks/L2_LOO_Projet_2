package com.scramble_like.game.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.essential.utils.Writer;
import com.scramble_like.game.GameConstant;

import java.util.HashMap;
import java.util.Map;

public class ControlConfig extends Scene {

    private static final String CONTROLS_FILE = "controls.txt";
    private final Map<String, Integer> controlList;
    private TextButton currentButton;
    private String currentAction;

    public ControlConfig() {
        super("ControlConfigScreen");
        getCamera().setPosition(0, 0);
        controlList = new HashMap<>();
        loadControlSettings();
        createUI();
    }

    private void loadControlSettings() {
        controlList.put("Move Up", Writer.getSetting("Move Up", CONTROLS_FILE));
        controlList.put("Move Down", Writer.getSetting("Move Down", CONTROLS_FILE));
        controlList.put("Move Left", Writer.getSetting("Move Left", CONTROLS_FILE));
        controlList.put("Move Right", Writer.getSetting("Move Right", CONTROLS_FILE));
        controlList.put("Fire", Writer.getSetting("Fire", CONTROLS_FILE));
        controlList.put("Bomb", Writer.getSetting("Bomb", CONTROLS_FILE));
        controlList.put("Pause", Writer.getSetting("Pause", CONTROLS_FILE));
    }

    private void createUI() {
        Table table = new Table();
        table.setFillParent(true);
        table.center();

        // Create title label
        Label titleLabel = new Label("Control Configuration", getSkin(), "default");
        titleLabel.setFontScale(2);
        table.add(titleLabel).colspan(2).padTop(400).padRight(1200);
        table.row();

        // Create control configuration entries
        createControlConfigEntry(table, "Move Up");
        createControlConfigEntry(table, "Move Down");
        createControlConfigEntry(table, "Move Left");
        createControlConfigEntry(table, "Move Right");
        createControlConfigEntry(table, "Fire");
        createControlConfigEntry(table, "Bomb");
        createControlConfigEntry(table, "Pause");


        TextButton backButton = new TextButton("Back", getSkin());
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.log("ControlConfigScreen", "Returning to options menu...");
                getGame().setScreen(new OptionMenu());
                dispose();
            }
        });
        table.add(backButton).colspan(2).padTop(20).padRight(1200);

        getStage().addActor(table);

        getStage().addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if (currentButton != null && currentAction != null) {
                    controlList.put(currentAction, keycode);
                    currentButton.setText(Input.Keys.toString(keycode));
                    Writer.writeSetting(String.valueOf(keycode), currentAction, CONTROLS_FILE, null, false);
                    updateGameConstant(currentAction, keycode);
                    currentButton = null;
                    currentAction = null;
                }
                return true;
            }
        });
    }

    private void updateGameConstant(String action, int keycode) {
        switch (action) {
            case "Move Up":
                GameConstant.MOVE_UP[0] = keycode;
                break;
            case "Move Down":
                GameConstant.MOVE_DOWN[0] = keycode;
                break;
            case "Move Left":
                GameConstant.MOVE_LEFT[0] = keycode;
                break;
            case "Move Right":
                GameConstant.MOVE_RIGHT[0] = keycode;
                break;
            case "Fire":
                GameConstant.SHOOT[0] = keycode;
                break;
            case "Bomb":
                GameConstant.SECONDARY_SHOOT[0] = keycode;
                break;
            case "Pause":
                GameConstant.TOGGLE_PAUSE = keycode;
                break;
            default:
                break;
        }
    }

    private void createControlConfigEntry(Table table, String action) {
        Label actionLabel = new Label(action, getSkin(), "default");
        TextButton keyButton = new TextButton(Input.Keys.toString(controlList.get(action)), getSkin());
        keyButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                currentButton = keyButton;
                currentAction = action;
                keyButton.setText("Press any key...");
            }
        });
        table.add(actionLabel).padBottom(10);
        table.add(keyButton).padBottom(10).padRight(1200);
        table.row();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        getStage().act(delta);
        getStage().draw();
    }
}
