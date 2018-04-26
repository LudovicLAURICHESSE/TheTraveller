package c.myn4s.thetravellerandroid.GameEngine.gameObjects;

import android.util.Log;

import c.myn4s.thetravellerandroid.R;

/**
 * Created by Myn4s on 03/04/2018.
 */

public class Foe extends GameObject {
    boolean isKilled = false;
    public Foe(int posX, int posY) {
        super(posX, posY, 10, 10);
        resourceInt = R.mipmap.foe;
    }


    public boolean isKilledByPlayer(Player playerOne){ // si l'ennemi est tué par le joueur c-à-d le joueur lui suate dessus
        if(playerOne.getEndX()>=this.getPosX() && playerOne.getPosX()<=this.getEndX()&& playerOne.getEndY()>= this.getPosY() && !isKilled){
            isKilled=true;
            Log.i("isKilledByPlayer","mort du block");
        }
        return isKilled;
    }

    public void killPlayer(Player playerOne){
        if(playerOne.getEndY()>=this.getPosY()&& playerOne.getPosY()<=this.getEndY() && playerOne.getEndX()>=this.getPosX() && playerOne.getPosX()<=this.getEndX()  && !playerOne.getKill()){
            playerOne.setKill(true);
        }
    }
}
