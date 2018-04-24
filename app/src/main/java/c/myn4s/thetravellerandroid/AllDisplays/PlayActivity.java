package c.myn4s.thetravellerandroid.AllDisplays;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

import c.myn4s.thetravellerandroid.GameEngine.GameView;
import c.myn4s.thetravellerandroid.GameEngine.Score;
import c.myn4s.thetravellerandroid.GameEngine.gameObjects.GameObject;
import c.myn4s.thetravellerandroid.R;

public class PlayActivity extends AppCompatActivity {

    private static DisplayMetrics metrics = new DisplayMetrics();

    public static int getScreenWidth(){
        return metrics.widthPixels;
    }

    public static int getScreenHeight(){
        return metrics.heightPixels;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowManager windowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(metrics);

        setContentView(R.layout.play_display);
    }

    public void back(View view) {

        PlayActivity.this.finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Score score = ((GameView)findViewById(R.id.gameview)).onGameOver();
        data.putExtra("score", score);
    }

    public void endGame(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        EndGameFragment end = new EndGameFragment();
        fragmentTransaction.add(R.id.fragment_container, end);
        fragmentTransaction.commit();
    }
}
