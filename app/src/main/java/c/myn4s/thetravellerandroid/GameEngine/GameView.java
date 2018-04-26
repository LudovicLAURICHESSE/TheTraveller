package c.myn4s.thetravellerandroid.GameEngine;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import c.myn4s.thetravellerandroid.AllDisplays.GameOverDialog;
import c.myn4s.thetravellerandroid.AllDisplays.PlayActivity;
import c.myn4s.thetravellerandroid.GameEngine.Grid.Grid;
import c.myn4s.thetravellerandroid.GameEngine.gameObjects.GameObject;
import c.myn4s.thetravellerandroid.GameEngine.gameObjects.PlayerIsDeadException;


/**
 * Created by Myn4s on 02/04/2018.
 */

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private GameLoopThread gameLoopThread;
    private Game game;

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(this);

        gameLoopThread = new GameLoopThread(this);

        Grid.initialize(PlayActivity.getScreenWidth(), PlayActivity.getScreenHeight());

        game = new Game();

        GameObject.setContext(context);
    }

    /**
     * method that render all the objects on the given canvas
     * @param canvas
     */
    public void doDraw(Canvas canvas){
        if(canvas==null) {return;}

        canvas.drawColor(Color.WHITE);
        game.doDraw(canvas);
    }

    /**
     * method that applies an update on all the gameobjects
     */
    public void update() {
        try {
            game.update();
        } catch (PlayerIsDeadException e) {
            e.printStackTrace();

        }
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
        Grid.initialize(width,height);
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
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        int currentX = (int)event.getX();
        //Log.i("Myn4s", "Contact X " + (int)event.getX() + " Contact Y "  + (int)event.getY());
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if(currentX < PlayActivity.getScreenWidth()/2) {
                    game.playerJump();
                }

                else {
                    game.playerShot();
                }
                break;
        }

        return true;
    }

    public Score onGameOver(){
        return game.onGameOver();
    }
}
