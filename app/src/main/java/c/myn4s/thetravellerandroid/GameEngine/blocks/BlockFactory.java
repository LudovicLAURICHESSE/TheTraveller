package c.myn4s.thetravellerandroid.GameEngine.blocks;

import android.content.Context;

import java.util.LinkedList;
import java.util.List;

import c.myn4s.thetravellerandroid.GameEngine.gameObjects.GameObject;
import c.myn4s.thetravellerandroid.GameEngine.gameObjects.Floor;
import c.myn4s.thetravellerandroid.R;

/**
 * Created by Myn4s on 04/04/2018.
 */

public class BlockFactory {
    private Context context;
    private int width;
    private int height;

    private List<GameObject> gameObjects;

    public BlockFactory (Context context, int width, int height){
        this.context = context;
        this.height = height;
        this.width = width*3;

        gameObjects = new LinkedList<>();
    }

    public Block creationBlock(int blockId){
        gameObjects.removeAll(gameObjects);
        switch (blockId){
            case 1:
                gameObjects.add(new Floor(R.mipmap.brick_tile));
                return new Block(this.context, gameObjects, width, "b1");
            case 2:
                gameObjects.add(new Floor(R.mipmap.brick_tile));
                return new Block(this.context, gameObjects, width, "b2");
            case 3:
                gameObjects.add(new Floor(R.mipmap.brick_tile));
                return new Block(this.context, gameObjects, width, "b3");

            default:
                gameObjects.add(new Floor(R.mipmap.brick_tile));
                return new Block(this.context, gameObjects, width, "start");
        }
    }

    public Block creationStartBlock() {
        gameObjects.add(new Floor(R.mipmap.brick_tile));
        Block b = new Block(this.context, gameObjects, width, "start");
        b.setPosX(0);
        return b;
    }
}
