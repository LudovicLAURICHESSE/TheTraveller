package c.myn4s.thetravellerandroid.GameEngine.gameObjects;


/**
 * Created by Myn4s on 02/04/2018.
 */

public class Player extends GameObject {
    /**
     * Constructor for the GameObject
     * @param resourceInt id for the sprite
     */
    public Player(int resourceInt) {
        super(0, 0, resourceInt, 1, 1,Type.PLAYER);
    }

    @Override
    public void update() {
        setPosX(getPosX());
        //applyForce();
    }
}
