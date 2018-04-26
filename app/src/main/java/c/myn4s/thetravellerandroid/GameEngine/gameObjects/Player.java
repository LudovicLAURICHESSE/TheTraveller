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
    private int jumpForce = -60;
    private int gravity = 5;

    private boolean grounded = true;
    private boolean kill = false;
    private boolean isShot = false;
    private int maxDescente=0;

    public Player(int posX, int posY) {
        super(posX, posY, 10, 10);

        this.posY = Grid.getBlocksSize();
        this.posX = Grid.getBlocksSize();
        resourceInt = R.mipmap.player;
    }

    @Override
    public void update(){
        //si le joueur est passé a travers du sol (ou va passer) + vérification qu'il n'est pas tombé précédement
        if ((getPosY() < getMaxDescente())&&(getEndY() + movement > getMaxDescente())){
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

    public boolean getKill(){return kill;}

    public void setKill(boolean val){kill = val;}

    public boolean getIsShot(){return isShot;}

    public void setIsShot(boolean val){isShot = val;}

    @Override
    public void setPosX(int posX){

    }

    public boolean isKilledBy(GameObject other){
        if (movement <= 0 && !(this.getPosX() >= other.getEndX())) { //élimination du cas ou le joueur est tué par une plateforme qui est passée derrière lui
            if (this.getEndX() >= other.getPosX()) {     //contact à droite
                if (this.getEndY() > other.getPosY()) {  //
                    Log.i("Myn4s", "Player is dead");
                    return true;
                }
            }
        }
        return false;
    }

}
