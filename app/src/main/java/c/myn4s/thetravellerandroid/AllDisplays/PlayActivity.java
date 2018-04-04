package c.myn4s.thetravellerandroid.AllDisplays;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;

import c.myn4s.thetravellerandroid.GameEngine.gameObjects.GameObject;
import c.myn4s.thetravellerandroid.R;

public class PlayActivity extends AppCompatActivity {

    public static DisplayMetrics metrics = new DisplayMetrics();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x/100;
        int height = size.y-500;

        /*
        Log.i("Myn4s", ""+metrics.widthPixels);
        Log.i("Myn4s", ""+width);

        Log.i("Myn4s", ""+metrics.heightPixels);
        Log.i("Myn4s", ""+height);
        */

        //GameObject.setScreenSize(metrics.widthPixels,metrics.heightPixels); //getting the size of the screen
        GameObject.setScreenSize(width,height);
        setContentView(R.layout.play_display);
    }

    public void back(View view) {
        PlayActivity.this.finish();
    }

}
