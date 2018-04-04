package c.myn4s.thetravellerandroid.GameEngine.gameObjects;

/**
 * Created by Myn4s on 04/04/2018.
 */

public class Hole extends GameObject {
    /**
     * Constructor for the GameObject
     *
     * @param startX      starting position in x
     * @param startY      starting position in y
     * @param resourceInt id for the sprite
     * @param nbBlocksX   number of blocks in x
     * @param nbBlocksY   number of blocks in y
     * @param type
     */
    public Hole(int startX, int startY, int resourceInt, int nbBlocksX, int nbBlocksY, Type type) {
        super(startX, startY, resourceInt, nbBlocksX, nbBlocksY, type);
    }

    @Override
    public void update() {

    }
}
