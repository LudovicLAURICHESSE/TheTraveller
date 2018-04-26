package c.myn4s.thetravellerandroid.GameEngine.gameObjects;

import android.util.Log;

import c.myn4s.thetravellerandroid.GameEngine.Grid.Grid;
import c.myn4s.thetravellerandroid.R;

/**
 * Created by pereiraloann on 25/04/2018.
 */

public class Projectile extends GameObject {
    private boolean isLeaving = false;
    private int movement = 10;
    public Projectile(int posX, int posY) {
        super(posX, posY, 1, 1);
        this.posY = Grid.getBlocksSize();
        this.posX = Grid.getBlocksSize();
        resourceInt = R.mipmap.player;
    }

    public void update(Player player){
        if(!isLeaving) {
            Log.i("updateProj","je ne tire pas");
            setPosX(player.getPosX());
            setPosY(player.getPosY());
        }
        else{
            Log.i("updateProj","je suis en train de tirer");
            setPosX(getPosX()+movement);
        }
    }

    public void shoot(){
        this.isLeaving=true;
    }

}
