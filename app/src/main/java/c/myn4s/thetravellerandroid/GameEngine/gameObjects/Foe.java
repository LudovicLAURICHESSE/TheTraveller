package c.myn4s.thetravellerandroid.GameEngine.gameObjects;

import c.myn4s.thetravellerandroid.R;

/**
 * Created by Myn4s on 03/04/2018.
 */

public class Foe extends GameObject {
    public Foe(int posX, int posY) {
        super(posX, posY, 1, 1);
        resourceInt = R.mipmap.foe;
    }
}
