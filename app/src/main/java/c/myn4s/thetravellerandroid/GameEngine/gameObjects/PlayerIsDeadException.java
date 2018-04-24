package c.myn4s.thetravellerandroid.GameEngine.gameObjects;

/**
 * Created by Myn4s on 24/04/2018.
 */

public class PlayerIsDeadException extends Exception {

    public PlayerIsDeadException (){
        super("Player is dead - game over");
    }
}
