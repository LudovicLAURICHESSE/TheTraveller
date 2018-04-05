package c.myn4s.thetravellerandroid.GameEngine.gameObjects;

/**
 * Created by Myn4s on 03/04/2018.
 */

public class Foe extends GameObject {
    public Foe(int startX, int startY, int resourceInt) {
        super(startX, startY, resourceInt, 1, 1, Type.FOE);
    }

    @Override
    public void update() {
        applyMovement();
        applyForce();
    }
}
