package c.myn4s.thetravellerandroid.GameEngine.blocks;

import android.content.Context;
import android.util.Log;

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

    private List<GameObject> gameObjects;

    private Block lastCreated;

    public BlockFactory (Context context, int width, int height){
        this.context = context;

        lastCreated = null;
        gameObjects = new LinkedList<>();
    }

    public Block creationBlock(int blockId){
        if (lastCreated == null)
            return creationStartBlock();

        Log.i("Myn4s", "Appel blockid "+blockId);

        int startX = lastCreated.getFinX();

        Log.i("Myn4s", "startX "+startX);

        switch (blockId){
            case 1:
                lastCreated = new Block(startX, R.mipmap.player);
            case 2:
                lastCreated =  new Block(startX, R.mipmap.foe);

            default:
                lastCreated =  new Block(startX, R.mipmap.brick_tile);
        }

        Log.i("Myn4s", "Last created position "+ lastCreated.getPosX());

        return lastCreated;
    }

    private Block creationStartBlock() {
        Block b = new Block(0, R.mipmap.foe);
        lastCreated = b;
        return lastCreated;
    }
}
