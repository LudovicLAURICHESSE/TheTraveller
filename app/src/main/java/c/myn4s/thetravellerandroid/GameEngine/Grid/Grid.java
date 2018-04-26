package c.myn4s.thetravellerandroid.GameEngine.Grid;

import android.util.Log;

/**
 * Created by Myn4s on 12/04/2018.
 */

public abstract class Grid {
    private static int SCREEN_WIDTH;
    private static int SCREEN_HEIGHT;

    private static int BLOCKS_ON_WIDTH = 70;
    private static int BLOCKS_ON_HEIGHT;
    private static int BLOCKS_SIZE;

    private static int GENERATION_POINT;
    private static int DESTRUCTION_POINT = 0;

    private static int INCREMENT = -20;

    public static void initialize(int screenWidth, int screenHeight){
        SCREEN_WIDTH = screenWidth;
        SCREEN_HEIGHT = screenHeight;

        BLOCKS_SIZE = getScreenWidth() / getBlocksOnWidth();
        BLOCKS_ON_HEIGHT = getScreenHeight() / getBlocksSize();

        GENERATION_POINT = (getBlocksOnWidth() + 5) * getBlocksSize();

    }

    public static int getScreenWidth() {
        return SCREEN_WIDTH;
    }

    public static int getScreenHeight() {
        return SCREEN_HEIGHT;
    }

    public static int getBlocksOnWidth() {
        return BLOCKS_ON_WIDTH;
    }

    public static int getBlocksOnHeight() {
        return BLOCKS_ON_HEIGHT;
    }

    public static int getBlocksSize() {
        return BLOCKS_SIZE;
    }

    public static int getGenerationPoint() {
        return GENERATION_POINT;
    }

    public static int getDestructionPoint() {
        return DESTRUCTION_POINT;
    }

    public static int getIncrement() {
        return INCREMENT;
    }
}
