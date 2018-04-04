package c.myn4s.thetravellerandroid.GameEngine;

/**
 * Created by Myn4s on 03/04/2018.
 */

public class Vector {
    private int x;
    private int y;

    public static final Vector GRAVITY = new Vector(0,10);
    public static final Vector PROGRESSION = new Vector(10,0);
    public static int SPEED = 1;

    public Vector (int x, int y){
        setX(x);
        setY(y);
    }

    public int getX() {
        return x;
    }

    private void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    private void setY(int y) {
        this.y = y;
    }

    public void add (Vector v){
        setX(v.getX()+getX());
        setY(v.getY()+getY());
    }


}
