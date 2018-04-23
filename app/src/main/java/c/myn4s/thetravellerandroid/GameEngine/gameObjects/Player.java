package c.myn4s.thetravellerandroid.GameEngine.gameObjects;


import android.util.Log;

import c.myn4s.thetravellerandroid.GameEngine.Grid.Grid;
import c.myn4s.thetravellerandroid.R;

/**
 * Created by Myn4s on 02/04/2018.
 */

public class Player extends GameObject {
    private boolean first = true;
    private int movement = 0;
    private int jumpForce = -30;
    private int gravity = 5;

    private boolean grounded = true;

    private int maxDescente=0;

    public Player(int posX, int posY) {
        super(posX, posY, 1, 1);

        this.posY = Grid.getBlocksSize();
        this.posX = 0;
        resourceInt = R.mipmap.player;
    }

    @Override
    public void update(){
        if (getEndY() + movement < getMaxDescente()){   //si le joueur est passé a travers du sol (ou va passer)
            setPosY(getMaxDescente() - this.getSizeY());//on le pose au sol
            movement = 0;                               //on stoppe son mouvement
            setGrounded(true);                          //on le déclare comme étant au sol
        }
        else{
            setPosY(getPosY() + movement); //sinon on le déplace
            movement += gravity;           //et on incrémente la gravité
        }
    }

    public void jump(){
        movement = jumpForce;
        setGrounded(false);
    }

    public void setGrounded(boolean grounded){
        this.grounded = grounded;
    }

    public boolean isGrounded() {
        return grounded;
    }

    public int getMaxDescente() {
        return maxDescente;
    }

    public void setMaxDescente(int maxDescente) {
        this.maxDescente = maxDescente;
    }

    @Override
    public void setPosX(int posX){

    }

}
