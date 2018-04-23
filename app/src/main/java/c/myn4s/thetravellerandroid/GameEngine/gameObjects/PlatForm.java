package c.myn4s.thetravellerandroid.GameEngine.gameObjects;

import c.myn4s.thetravellerandroid.R;

/**
 * Created by Myn4s on 04/04/2018.
 */

public class PlatForm extends GameObject {

    public PlatForm(int posX, int posY, int nbBlocsX, int nbBlocsY) {
        super(posX, posY, nbBlocsX, nbBlocsY);
        resourceInt = R.mipmap.floor;
    }
}
