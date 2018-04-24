package c.myn4s.thetravellerandroid.GameEngine;

import android.graphics.Canvas;

import c.myn4s.thetravellerandroid.GameEngine.gameObjects.PlayerIsDeadException;

/**
 * Created by Myn4s on 02/04/2018.
 */

public class GameLoopThread extends Thread {
    private final static int FRAMES_PER_SECOND = 30; //the number of frames per sec
    private final static int SKIP_TICKS = 1000 / FRAMES_PER_SECOND; //the time between to refresh of the screen

    private final GameView view; //the view of the game
    private boolean running = false;

    public GameLoopThread(GameView view) {
        this.view = view;
    }

    // set the state of the thread, running or not
    public void setRunning(boolean run) {
        running = run;
    }

    @Override
    public void run(){
        //the times of start and pause
        long startTime;
        long sleepTime;

        while (running){

            //getting the starting time of the iteration
            startTime = System.currentTimeMillis();

            //updating the objects inside the view
            synchronized (view.getHolder()) {
                view.update();
            }

            //rendering the image
            //we need to lock the canvas since we access it from a different thread
            Canvas c = null;
            try {
                c = view.getHolder().lockCanvas();
                synchronized (view.getHolder()) {
                    view.doDraw(c);
                }
            }
            finally
            {
                if (c != null) {view.getHolder().unlockCanvasAndPost(c);}
            }

            //pause time if needed
            sleepTime = SKIP_TICKS-(System.currentTimeMillis() - startTime);
            try{
                if (sleepTime >= 0) {sleep(sleepTime);}
            }
            catch (Exception e){} //the loop keeps on going
        } //while(running)
    }// run()
}//class GameLoopThread
