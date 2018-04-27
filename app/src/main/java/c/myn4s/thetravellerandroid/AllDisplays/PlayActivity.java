package c.myn4s.thetravellerandroid.AllDisplays;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import c.myn4s.thetravellerandroid.GameEngine.Game;
import c.myn4s.thetravellerandroid.GameEngine.GameView;
import c.myn4s.thetravellerandroid.GameEngine.Score;
import c.myn4s.thetravellerandroid.R;

public class PlayActivity extends AppCompatActivity {

    private static DisplayMetrics metrics = new DisplayMetrics();

    public static int getScreenWidth(){
        return metrics.widthPixels;
    }

    public static int getScreenHeight(){
        return metrics.heightPixels;
    }

    private Score score;
    private TextView scoreDisplayer;
    private GameView inGame;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowManager windowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(metrics);
        setContentView(R.layout.play_display);
        scoreDisplayer = findViewById(R.id.textViewScore);
        scoreDisplayer.setText("" + 0);
        inGame = ((GameView) findViewById(R.id.gameview));
        inGame.onGameOver().addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent pce) {
                update((int) pce.getNewValue());
            }
        });
        inGame.endGame().addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent pce) {
                endGame();
            }
        });
        if(MainMenu.isSong){
            MainMenu.song.start();
        }
    }

    private void update(final int points){

        runOnUiThread(new Runnable() {
        @Override
        public void run() {
            scoreDisplayer.setText("" + points);
        }});

    }

    public void back(View view) {
        Intent data = new Intent();
        score = ((GameView)findViewById(R.id.gameview)).onGameOver();
        data.putExtra("score", score);
        setResult(Activity.RESULT_OK,data);
        PlayActivity.this.finish();
    }

    public void playGame(View view) {
            Intent intent = new Intent(this,PlayActivity.class);
            startActivity(intent);
            PlayActivity.this.finish();
    }

    public void endGame(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        EndGameFragment end = new EndGameFragment();
        fragmentTransaction.add(R.id.fragment_container, end);
        fragmentTransaction.commit();

    }

}
