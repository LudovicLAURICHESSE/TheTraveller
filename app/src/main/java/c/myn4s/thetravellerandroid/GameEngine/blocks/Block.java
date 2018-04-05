package c.myn4s.thetravellerandroid.GameEngine.blocks;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

import c.myn4s.thetravellerandroid.AllDisplays.PlayActivity;
import c.myn4s.thetravellerandroid.GameEngine.Vector;
import c.myn4s.thetravellerandroid.GameEngine.gameObjects.GameObject;
import c.myn4s.thetravellerandroid.GameEngine.gameObjects.Type;

/**
 * Created by Myn4s on 04/04/2018.
 */

public class Block extends GameObject{

    private List<GameObject> gameObjects;

    private static final int INCREMENT = -50;

    /**
     * Constructor for the GameObject
     *
     * @param startX      starting position in x
     * @param resourceInt id for the sprite
     */
    public Block(int startX, int resourceInt ) {
        super(startX, 0, resourceInt, 20, 15, Type.BLOCK);

        gameObjects = new LinkedList<>();
    }

    @Override
    public void update() {
        setPosX(getPosX() + INCREMENT);

        for (GameObject obj : gameObjects) {
            obj.update();
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        for (GameObject obj : gameObjects) {
            obj.draw(canvas);
        }
    }

    public int getFinX(){
        return getPosX() + getSizeX();
    }

}
