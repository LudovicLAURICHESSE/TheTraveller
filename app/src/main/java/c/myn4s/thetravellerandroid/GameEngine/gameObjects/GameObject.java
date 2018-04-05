package c.myn4s.thetravellerandroid.GameEngine.gameObjects;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import c.myn4s.thetravellerandroid.GameEngine.Vector;

/**
 * Created by Myn4s on 02/04/2018.
 */

public abstract class GameObject {
    protected static Context mContext; //the context for the gameObjects, used to access the resources

    protected BitmapDrawable img=null; //the sprite for the object
    protected int resourceInt; //the int that indicate the sprite in the resources
    private int posY; //the position on the width of the screen
    private int posX; //the position on the height of the screen

    static protected int BLOCK_SIZE; //the size of a block
    static protected int BLOCKS_ON_WIDTH = 7; //the number of blocks on the width of the screen
    static protected int SCREEN_WIDTH;
    static protected int SCREEN_HEIGHT;

    protected int blocksY; //the number of blocks in y
    protected int blocksX; //the number of blocks in x
    private int sizeY; //the size in pixels in y
    private int sizeX; //the size in pixels in x

    protected Vector force; //the current force applied to the object
    private Vector movement; //the movement, applied by the bocks
    private Type type;

    /**
     * Constructor for the GameObject
     * @param startX starting position in x
     * @param startY starting position in y
     * @param resourceInt id for the sprite
     * @param nbBlocksX number of blocks in x
     * @param nbBlocksY number of blocks in y
     */
    public GameObject(int startX, int startY, int resourceInt, int nbBlocksX, int nbBlocksY, Type type){
        this.resourceInt = resourceInt;
        setPosX(startX);
        setPosY(startY);
        blocksX = nbBlocksX;
        blocksY = nbBlocksY;

        setSizeX(blocksX * BLOCK_SIZE);
        setSizeY(blocksY * BLOCK_SIZE);

        force = new Vector(0,0);
        this.setType(type);
    }

    public static void setmContext (Context context){
        mContext = context;

    }

    public void draw(Canvas canvas) {
        if(img==null) {return;}
        canvas.drawBitmap(img.getBitmap(), getPosX(), getPosY(), null);
    }

    public void resize() {
        setSizeX(blocksX * BLOCK_SIZE);
        setSizeY(blocksY * BLOCK_SIZE);

        img = setImage(mContext, resourceInt, getSizeX(), getSizeY());
    }

    public static void setScreenSize(int width, int height) {
        SCREEN_HEIGHT = height;
        SCREEN_WIDTH = width;
        BLOCK_SIZE = SCREEN_WIDTH / BLOCKS_ON_WIDTH;
    }

    private BitmapDrawable setImage(final Context c, final int resource, final int w, final int h)
    {
        Drawable dr = c.getResources().getDrawable(resource,null);
        Bitmap bitmap = ((BitmapDrawable) dr).getBitmap();
        return new BitmapDrawable(c.getResources(), Bitmap.createScaledBitmap(bitmap, w, h, true));
    }

    protected void applyForce(){
        setPosX(getPosX() + force.getX());
        setPosY(getPosY() + force.getY());
    }

    protected void applyMovement(){
        setPosX(getPosX() + getMovement().getX());
        setPosY(getPosY() + getMovement().getY());
    }
    public void nullifyForce(){
        force = new Vector(0,0);
    }

    public boolean collision (GameObject obj){
        return (this.hasInside(obj.getPosX(), obj.getPosY()) ||
                this.hasInside(obj.getPosX(), obj.getPosY() + obj.getSizeY()) ||
                this.hasInside(obj.getPosX() + obj.getSizeY(), obj.getPosY()) ||
                this.hasInside(obj.getPosX() + obj.getSizeX(), obj.getPosY() + obj.getSizeY()));
    }

    private boolean hasInside (int x, int y){
        return (this.getPosX() <= x && x <= this.getPosX() + this.getSizeX()) && (this.getPosY() <= y && y <= this.getPosY() + this.getSizeY());
    }

    abstract public void update();

    public static int getBlockSize(){
        return BLOCK_SIZE;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getSizeY() {
        return sizeY;
    }

    protected void setSizeY(int sizeY) {
        this.sizeY = sizeY;
    }

    public int getSizeX() {
        return sizeX;
    }

    protected void setSizeX(int sizeX) {
        this.sizeX = sizeX;
    }

    public Type getType() {
        return type;
    }

    protected void setType(Type type) {
        this.type = type;
    }

    public void addForce (Vector v){
        force.add(v);
    }

    public Vector getForce (){
        return force;
    }

    public void setForce(Vector v){
        force = v;
    }


    public Vector getMovement() {
        return movement;
    }

    public void setMovement(Vector movement) {
        this.movement = movement;
    }
}
