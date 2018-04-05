package c.myn4s.thetravellerandroid.AllDisplays;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;

import c.myn4s.thetravellerandroid.GameEngine.gameObjects.GameObject;
import c.myn4s.thetravellerandroid.R;

public class PlayActivity extends AppCompatActivity {

    private static int SCREEN_WIDTH;
    private static int SCREEN_HEIGHT;

    public static int getScreenWidth() {
        return SCREEN_WIDTH;
    }

    public static int getScreenHeight() {
        return SCREEN_HEIGHT;
    }

    public static void setScreenWidth(int screenWidth) {
        SCREEN_WIDTH = screenWidth;
    }

    public static void setScreenHeight(int screenHeight) {
        SCREEN_HEIGHT = screenHeight;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        setScreenWidth(displayMetrics.widthPixels);
        setScreenHeight(displayMetrics.heightPixels);

        GameObject.setScreenSize(getScreenWidth(), getScreenHeight());
        setContentView(R.layout.play_display);
    }

    public void back(View view) {
        PlayActivity.this.finish();
    }

}
