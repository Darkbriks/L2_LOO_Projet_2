package com.scramble_like.game.utils;

import com.badlogic.gdx.math.Rectangle;
import com.scramble_like.game.GameConstant;
import org.w3c.dom.css.Rect;

import java.util.ArrayList;

public class Chunk{
    public int x=0,y=1;
    public ArrayList<String> chunkOriginal;
    public ArrayList<Rectangle> chunk = new ArrayList<>();

    public Chunk(){
    }
    public void convertion(ArrayList<String> co){
        int yy =0;
        for (String ligne : co) {
            for (int xx = 0; xx < ligne.length(); xx++) {
                if (ligne.charAt(xx) != ' ') {
                    chunk.add(new Rectangle((xx * GameConstant.SQUARE_SIDE)+(x*GameConstant.CHUNK_SIZE) , -(yy * GameConstant.SQUARE_SIDE)+(y*GameConstant.CHUNK_SIZE)-(55), GameConstant.SQUARE_SIDE, GameConstant.SQUARE_SIDE)); // Exemple de coordonnées et taille
                }
            }
            yy++;
        }
    }
    public void wave(ArrayList<Rectangle> r){
        for (Rectangle w : r) {
            w.x-=1;
        }
    }
}
