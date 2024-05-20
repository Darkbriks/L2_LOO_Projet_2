package com.scramble_like.game.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector4;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.scramble_like.game.GameConstant;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.ui.AE_Label;

public class LevelMap extends Scene {

    private static final String TILE_PATH = "LevelMap/Tiles/";
    private static final String OBJECT_PATH = "LevelMap/Objects/";
    private static final  String[] OBJECT_FOLDERS = {"Other", "Trees"};

    private static final String[] OTHER_OBJECT_FILES = {"Ship1.png","Ship2.png","Ship3.png"};
    private static final String[] TREE_OBJECT_FILES ={
            "1.png","2.png","2.png",
            "4.png","5.png","6.png",
            "7.png","8.png","9.png"};
    private static final String[] TILE_FILES = {
            "Map_tile_01.png", "Map_tile_02.png", "Map_tile_03.png",
            "Map_tile_13.png", "Map_tile_14.png", "Map_tile_15.png",
            "Map_tile_25.png", "Map_tile_26.png", "Map_tile_27.png",
            "Map_tile_37.png", "Map_tile_38.png", "Map_tile_39.png",
            "Map_tile_49.png", "Map_tile_50.png", "Map_tile_51.png",
            "Map_tile_61.png", "Map_tile_62.png", "Map_tile_63.png"
    };


    private static final int TILE_WIDTH = 32;
    private static final int TILE_HEIGHT =32;

    private Texture[] tiles;
    private Texture[][] objectTextures;

    private int objectCounter = 0;

    public LevelMap() {
        super("LevelMap");

        backgroundColor = new Vector4(0.0f, 0.0f, 10.0f, 1);

        tiles = new Texture[TILE_FILES.length];
        for (int i = 0; i < TILE_FILES.length; i++) {
            tiles[i] = new Texture(Gdx.files.internal(TILE_PATH + TILE_FILES[i]));
        }
        objectTextures = new Texture[OBJECT_FOLDERS.length][];
        for (int i = 0; i < OBJECT_FOLDERS.length; i++) {
            String[] objectFiles = i == 0 ? OTHER_OBJECT_FILES : TREE_OBJECT_FILES;
            objectTextures[i] = new Texture[objectFiles.length];
            for (int j = 0; j < objectFiles.length; j++) {
                objectTextures[i][j] = new Texture(Gdx.files.internal(OBJECT_PATH + OBJECT_FOLDERS[i] + "/" + objectFiles[j]));
            }
        }


        createLevelMap();

        Label backButton = new AE_Label("Back", this.getSkin());
        backButton.setPosition(10, Gdx.graphics.getHeight() - 30);
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                getGame().setScreen(new MainMenu());
                dispose();
            }
        });
        this.getStage().addActor(backButton);
    }

    private void createLevelMap() {
        int tilesWide = GameConstant.WIDTH / TILE_WIDTH;
        int tilesHigh = GameConstant.HEIGHT / TILE_HEIGHT;

        for (int row = 0; row < tilesHigh; row++) {
            for (int col = 0; col < tilesWide; col++) {
                int tileIndex = (row + col) % tiles.length;
                if(tileIndex == 17){
                    int folderIndex = objectCounter / (OTHER_OBJECT_FILES.length + TREE_OBJECT_FILES.length) % OBJECT_FOLDERS.length;
                    int objectIndex = objectCounter % (OTHER_OBJECT_FILES.length + TREE_OBJECT_FILES.length);
                    objectCounter++;
                }else{addTile(tiles[tileIndex], col * TILE_WIDTH, GameConstant.HEIGHT - (row + 1) * TILE_HEIGHT);}
                }
        }
    }

    private void addObject(Texture object, float x, float y) {
        Image objectImage = new Image(object);
        objectImage.setPosition(x-640,y-349);
        this.getStage().addActor(objectImage);
    }


    private void addTile(Texture tile, float x, float y) {
        Image tileImage = new Image(tile);
        tileImage.setPosition(x - 640, y-349);
        this.getStage().addActor(tileImage);
    }


    private void addLevelLabel(String text, int row, int col) {
        Label levelLabel = new AE_Label(text, this.getSkin());
        float x = col * TILE_WIDTH + (TILE_WIDTH - levelLabel.getWidth()) / 2;
        float y = Gdx.graphics.getHeight() - row * TILE_HEIGHT + (TILE_HEIGHT - levelLabel.getHeight()) / 2;
        levelLabel.setPosition(x, y);
        this.getStage().addActor(levelLabel);
    }

    @Override
    public void dispose() {
        super.dispose();
        for (Texture tile : tiles) {
            tile.dispose();
        }
    }
}
