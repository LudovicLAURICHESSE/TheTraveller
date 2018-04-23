package c.myn4s.thetravellerandroid.GameEngine.gameObjects;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import c.myn4s.thetravellerandroid.GameEngine.Grid.Grid;

/**
 * Created by Myn4s on 02/04/2018.
 */

public abstract class GameObject {
    //position
    protected int posX;
    protected int posY;
    //nombre de blocks occupés
    private int blocksOnX;
    private int blocksOnY;
    //taille (en pixels)
    private int sizeX;
    private int sizeY;
    //sprite
    protected BitmapDrawable img=null;
    protected int resourceInt;
    //contexte (pour les sprites)
    protected static Context context;

    public GameObject(int posX, int posY, int nbBlocsX, int nbBlocksOnY){
        setPosX(posX);
        setPosY(posY);
        setBlocksOnX(nbBlocsX);
        setBlocksOnY(1); //la hauteur d'un objet n'a pas besoin de dépasser un block

        setSize(); //définition des tailles à l'écran
    }

    public static void setContext(Context c){
        context = c;
    }


    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        if (posY <= 0) posY = 0; //vérification pour ne pas dépasser le plafond
        this.posY = posY;
    }

    public int getSizeX() {
        return sizeX;
    }

    public void setSize() { //défini les tailles en pixels en fonction de la taille d'un block
        sizeX = getBlocksOnX() * Grid.getBlocksSize();
        sizeY = getBlocksOnY() * Grid.getBlocksSize();
    }

    public int getSizeY() {
        return sizeY;
    }

    protected void setResourceInt(int res){
        resourceInt = res;
    }

    public void update() {
        posX += Grid.getIncrement();
    } //mouvement des objets (changé juste pour le joueur)

    public void doDraw(Canvas canvas) {
        if(img==null) {resize();} //
        if(img!=null) {canvas.drawBitmap(img.getBitmap(), getPosX(), getPosY(), null);}
    }

    public void  resize() {
        setSize();
        img = setImage(context, resourceInt, getSizeX(), getSizeY());
    }

    public int getBlocksOnX() {
        return blocksOnX;
    }

    public void setBlocksOnX(int blocksOnX) {
        this.blocksOnX = blocksOnX;
    }

    public int getBlocksOnY() {
        return blocksOnY;
    }

    public void setBlocksOnY(int blocksOnY) {
        this.blocksOnY = blocksOnY;
    }

    private BitmapDrawable setImage(final Context c, final int resource, final int w, final int h)
    {
        Drawable dr = c.getResources().getDrawable(resource,null);
        Bitmap bitmap = ((BitmapDrawable) dr).getBitmap();
        return new BitmapDrawable(c.getResources(), Bitmap.createScaledBitmap(bitmap, w, h, true));
    }

    public void setSizeX(int sizeX) {
        this.sizeX = sizeX;
    }

    public int getEndX(){
        return posX + sizeX;
    }

    public int getEndY(){
        return posY + sizeY;
    }

    public boolean collisionFromRight (GameObject other){
        boolean testX = this.getEndX() >= other.getPosX();
        boolean testY1 = other.getPosY() <= this.getPosY() && this.getPosY() <= other.getEndY();
        boolean testY2 = other.getPosY() <= this.getEndY() && this.getEndY() <= other.getEndY();

        if (testX && (testY1 || testY2)) {
            //Log.i("Myn4s", "test collision droite");
        }
        return testX && (testY1 || testY2);
    }

    public boolean collisionFromBottom (GameObject other){
        boolean testY = this.getEndY() >= other.getPosY();
        boolean testX1 = other.getPosX() <= this.getPosX() && this.getPosX() <= other.getEndX();
        boolean testX2 = other.getPosX() <= this.getEndX() && this.getEndX() <= other.getEndX();

        if (testY && (testX1 || testX2)) {
            //Log.i("Myn4s", "test collision bas");
        }
        return testY && (testX1 || testX2);
    }
}
