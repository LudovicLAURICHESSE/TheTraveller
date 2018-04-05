package c.myn4s.thetravellerandroid.GameEngine;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import c.myn4s.thetravellerandroid.AllDisplays.PlayActivity;
import c.myn4s.thetravellerandroid.GameEngine.blocks.BlockFactory;
import c.myn4s.thetravellerandroid.GameEngine.gameObjects.GameObject;


/**
 * Created by Myn4s on 02/04/2018.
 */

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private GameLoopThread gameLoopThread;
    private Game game;

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(this);

        GameObject.setmContext(context);

        gameLoopThread = new GameLoopThread(this);

        game = new Game(new BlockFactory(context, PlayActivity.getScreenWidth(), PlayActivity.getScreenHeight()));
    }

    /**
     * method that render all the objects on the given canvas
     * @param canvas
     */
    public void doDraw(Canvas canvas){
        if(canvas==null) {return;}

        canvas.drawColor(Color.WHITE); //clearing the canvas

        game.doDraw(canvas);
    }

    /**
     * method that applies an update on all the gameobjects
     */
    public void update(){
        game.update();
    }

    /**
     * method of the SurfaceView class
     * called after the object creation
     * @param surfaceHolder
     */
    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        //we create the thread if needed
        if(gameLoopThread.getState()==Thread.State.TERMINATED) {
            gameLoopThread = new GameLoopThread(this);
        }

        gameLoopThread.setRunning(true); //starting the thread
        gameLoopThread.start();
    }

    /**
     * method of the SurfaceView class
     * called after the object creation or modification or when onResumed() is called
     * @param surfaceHolder
     * @param i
     * @param width the width of the screen
     * @param height the height of the screen
     */
    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int width, int height) {
        GameObject.setScreenSize(width,height);
        PlayActivity.setScreenHeight(height);
        PlayActivity.setScreenWidth(width);
        game.resize();
    }

    /**
     * method of the SurfaceView class
     * called before the object destruction
     * @param surfaceHolder
     */
    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        boolean retry = true;
        gameLoopThread.setRunning(false);
        while (retry) {
            try {
                gameLoopThread.join(); //we try to stop the running thread
                retry = false;
            }
            catch (InterruptedException e) {}
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        int currentX = (int)event.getX();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if(currentX < PlayActivity.getScreenWidth()/2) {
                    game.playerJump();
                    Log.i("Myn4s", "Contact gauche");
                }

                else {
                    Log.i("Myn4s", "Contact droite");
                }
                break;
        }

        return true;
    }
}
