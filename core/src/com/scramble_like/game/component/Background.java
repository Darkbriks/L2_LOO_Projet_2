package com.scramble_like.game.component;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.scramble_like.game.essential.Component;
import com.scramble_like.game.utils.Chunk;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Background extends Component {
    /*ArrayList<String> map = new ArrayList<>();
    ArrayList<Chunk> chunks = new ArrayList<>();
    ShapeRenderer s = new ShapeRenderer();
    int compteur =0;
    private int taille = 5;//, position = 0;
    public Background(){
        super();
        read("text_art (1).txt");
        read("text_art (2).txt");
        read("text_art (3).txt");
        read("text_art (4).txt");
        read("text_art (1).txt");
        read("text_art (2).txt");
        read("text_art (3).txt");
        read("text_art (4).txt");
        read("text_art (1).txt");
        read("text_art (2).txt");
        read("text_art (3).txt");
        read("text_art (4).txt");
        chunks.get(0).convertion(chunks.get(0).chunkOriginal);
        chunks.get(1).convertion(chunks.get(1).chunkOriginal);
        chunks.get(2).convertion(chunks.get(2).chunkOriginal);
        chunks.get(3).convertion(chunks.get(3).chunkOriginal);
        chunks.get(4).convertion(chunks.get(4).chunkOriginal);
        chunks.get(5).convertion(chunks.get(5).chunkOriginal);
        chunks.get(6).convertion(chunks.get(6).chunkOriginal);
        chunks.get(7).convertion(chunks.get(7).chunkOriginal);
        chunks.get(8).convertion(chunks.get(8).chunkOriginal);
        chunks.get(9).convertion(chunks.get(9).chunkOriginal);
        chunks.get(10).convertion(chunks.get(10).chunkOriginal);
        chunks.get(11).convertion(chunks.get(11).chunkOriginal);
        s=new ShapeRenderer();
    }
    public Background(String path){
        super();
        read(path);
        s=new ShapeRenderer();
    }
    public Background(ArrayList<String> map,int taille){
        super();
        for(String path : map){
            read(path);
        }
        s=new ShapeRenderer();
        this.taille = taille;
    }

    private void read(String path) {
        try {
            Chunk c = new Chunk();
            map.add(path);
            List<String> lignes = Files.readAllLines(Paths.get(path));
            c.chunkOriginal = new ArrayList<>(lignes);
            if(!chunks.isEmpty()){
                if(!estRempli()){
                    System.out.println("Compteur =" +compteur);
                    c.actif=true;
                    if(chunks.get(chunks.size()-1).x/(taille-1)==1){
                        c.x=0;
                        c.y=chunks.get(chunks.size()-1).y+1;
                    }
                    else{
                        c.x=chunks.get(chunks.size()-1).x+1;
                        c.y=chunks.get(chunks.size()-1).y;
                    }

                }
            }
            chunks.add(c);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void waving(){
        for(Chunk c : this.chunks){
            c.wave(c.chunk);
        }
    }

    public void charge() throws IOException {
        while(!(estRempli()) && map.size()>compteur){
            System.out.println("passé");
            read(map.get(8));
            read(map.get(9));
        }
    }

    public boolean estRempli(){
        int compteur = 0;
        for( Chunk c : chunks){
            if(c.actif){
                compteur++;
            }
        }
        if(compteur>=10){
            this.compteur=compteur;
            return true;
        }
        this.compteur=compteur;
        return false;
    }
    @Override
    public void Update(double Deltatime) {
        //System.out.println(chunks.size());
        System.out.println("size = " + chunks.size());
        System.out.println("compteur = "+ this.compteur);
        if(this.Owner.IsActive()){
            for(int i=0;i<chunks.size();i++){
                System.out.println(chunks.get(i).chunk.get(chunks.get(i).chunk.size()-1).x<-10);
                if(chunks.get(i).chunk.get(chunks.get(i).chunk.size()-1).x<-10){
                    chunks.remove(i);
                    map.remove(i);
                    //compteur--;
                }
                estRempli();
            }
            //position++;
            waving();
            /*try {
                charge();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }*/
        /*}
    }

    @Override
    public void Render() {
        s.begin(ShapeRenderer.ShapeType.Filled);
        for (Chunk chunk : chunks) {
            for (Rectangle rect : chunk.chunk) {
                s.rect(rect.x, rect.y, rect.width, rect.height);
            }
        }
        s.end();
    }

    @Override
    public void Destroy(){
        s.begin(ShapeRenderer.ShapeType.Filled);
        for (com.badlogic.gdx.math.Rectangle rect : chunks.get(0).chunk) {
            s.dispose();
        }
        s.end();
    }*/
}
