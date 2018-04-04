package c.myn4s.thetravellerandroid.GameEngine.blocks;

import android.content.Context;
import android.graphics.Canvas;

import java.util.List;

import c.myn4s.thetravellerandroid.AllDisplays.PlayActivity;
import c.myn4s.thetravellerandroid.GameEngine.gameObjects.GameObject;
import c.myn4s.thetravellerandroid.GameEngine.gameObjects.Type;

/**
 * Created by Myn4s on 04/04/2018.
 */

public class Block {
    private List<GameObject> gameObjects;
    private int posX;
    private int finX;
    private String id;
    private static final int INCREMENT = -20;
    private final Context mContext;

    Block(final Context c, List<GameObject> gameObjects, int width, String id){
        this.gameObjects = gameObjects;
        mContext = c;
        this.id = id;
        this.posX = width;
        finX = width*2;
    }

    public String getId() {
        return id;
    }

    public void resize(int w, int h) {
        for (GameObject obj: gameObjects) {
            obj.resize();
        }
    }

    private void addGameObject (GameObject obj){
        if (obj.getType() != Type.PLAYER){
            obj.setPosX(obj.getPosX() + posX);
        }
    }

    public void update() {
        posX += INCREMENT;
        for (GameObject obj: gameObjects) {
            obj.setPosX(obj.getPosX() + INCREMENT);
            obj.update();
        }
    }

    public void doDraw(Canvas canvas) {
        for (GameObject obj: gameObjects) {
            obj.draw(canvas);
        }
    }

    public void resize(){
        for (GameObject obj: gameObjects) {
            obj.resize();
        }
    }

    public int getPosX() {
        return posX;
    }

    public int getFinX() {
        return finX;
    }

    void setPosX (int value){
        posX = value;
    }
}
