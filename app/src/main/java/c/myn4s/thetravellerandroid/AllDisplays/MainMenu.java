package c.myn4s.thetravellerandroid.AllDisplays;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import c.myn4s.thetravellerandroid.GameEngine.Score;
import c.myn4s.thetravellerandroid.GameEngine.TableScore;
import c.myn4s.thetravellerandroid.R;


public class MainMenu extends AppCompatActivity {
    public static MediaPlayer song;
    private static final int PLAY_A_GAME_REQUEST = 145;
    private TableScore tableScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        song = MediaPlayer.create(MainMenu.this, R.raw.menusong);
        song.start();
        song.setLooping(true);
        tableScore = new TableScore(getApplicationContext());
        tableScore.chargeScore();
        setContentView(R.layout.activity_main_menu);
    }

    public void playGame(View view) {
        Intent intent = new Intent(MainMenu.this,PlayActivity.class);
        startActivityForResult(intent, PLAY_A_GAME_REQUEST); //permet de récupérer le score à la fin de la partie
    }

    public void showScores(View view) {
        Intent intent = new Intent(MainMenu.this,ScoreActivity.class);
        intent.putExtra("tableScore",tableScore);
        startActivity(intent);
    }

    public void settings(View view) {
        Intent intent = new Intent(MainMenu.this,SettingActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PLAY_A_GAME_REQUEST) {
            if(resultCode == Activity.RESULT_OK){
                Score s = (Score)data.getSerializableExtra("score");
                if(s!=null){
                    tableScore.save(s);
                }
            }
        }
    }
}
