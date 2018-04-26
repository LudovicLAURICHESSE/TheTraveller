package c.myn4s.thetravellerandroid.GameEngine.gameObjects;

import c.myn4s.thetravellerandroid.R;

/**
 * Created by pereiraloann on 25/04/2018.
 */

public class Projectile extends GameObject {
    private int movement = 20;
    private boolean isActive = true;

    public Projectile(int posX, int posY) {
        super(posX, posY, 1, 1);
        resourceInt = R.mipmap.player;
    }

    @Override
    public void update(){
        setPosX(getPosX()+movement);
    }

    public boolean killFoe(Foe f){
        //si le projectile n'est pas actif -> false
        //si le bas du proj est en dessus du haut de l'ennemi -> false
        //si le haut du proj est en dessous du bas de l'ennemi -> false
        //si la droite du proj est derrière l'ennemi -> false
        //si la gauche du proj est devant l'ennemi -> false

        return (isActive() &&
                (getEndY() > f.getPosY()) &&
                (getPosY() < f.getEndY()) &&
                (getPosX() < f.getEndX()) &&
                (getEndX() > f.getPosX()));
    }

    public boolean isActive() {
        return isActive;
    }
}
