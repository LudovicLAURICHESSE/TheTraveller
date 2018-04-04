package c.myn4s.thetravellerandroid.GameEngine.gameObjects;


/**
 * Created by Myn4s on 02/04/2018.
 */

public class Floor extends GameObject {
    /**
     * Constructor for the GameObject
     * @param resourceInt id for the sprite
     */
    public Floor(int resourceInt) {
        super(0, SCREEN_HEIGHT-BLOCK_SIZE, resourceInt, BLOCKS_ON_WIDTH,1 ,Type.FLOOR);
    }

    /**
     * Does nothing, we don't need our floor to move
     */
    @Override
    public void update() {

    }


}
