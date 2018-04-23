package c.myn4s.thetravellerandroid.GameEngine.gameObjects;


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

        posY = Grid.getBlocksSize();
        posX = Grid.getBlocksSize();
        resourceInt = R.mipmap.player;
    }

    @Override
    public void update(){
        if (!isGrounded()) //si le joueur ne touche pas le sol
            movement += gravity;
        else if (movement > 0) { //si il touche le sol mais qu'il continue de bouger
            movement = 0;
            setPosY(getMaxDescente()-this.getSizeY());
        }

        if (getEndY() + movement > getMaxDescente()) //s'il peu descendre sans passer a travers le sol
            setPosY(getPosY() + movement);
        else { //si son déplacement va lui faire traverser le sol
            setPosY(getMaxDescente() - this.getSizeY());
            setGrounded(true);
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
//        if (first) {
//            super.setPosX(posX);
//            first = false;
//        }
    }
}
