package c.myn4s.thetravellerandroid.AllDisplays;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Spinner;

import c.myn4s.thetravellerandroid.GameEngine.Score;
import c.myn4s.thetravellerandroid.GameEngine.TableScore;
import c.myn4s.thetravellerandroid.R;


public class MainMenu extends AppCompatActivity {
    public static MediaPlayer song;
    private static final int PLAY_A_GAME_REQUEST = 145;
    private static final int SHOW_SCORE_REQUEST = 285;
    private TableScore tableScore;
    private Spinner world, character;
    public static boolean isSong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        song = MediaPlayer.create(MainMenu.this, R.raw.menusong);
        song.start();
        isSong = true;
        song.setLooping(true);
        tableScore = new TableScore(getApplicationContext());
        tableScore.chargeScore();
        setContentView(R.layout.activity_main_menu);
        world = (Spinner) findViewById(R.id.spinnerWorld);
        character = (Spinner) findViewById(R.id.spinnerChar);
    }

    public void onStart() {
        super.onStart();
        if(isSong)song.start();
    }

    public void onRestart() {
        super.onRestart();
        if(isSong)song.start();
    }

    public void onResume() {
        super.onResume();
        if(isSong)song.start();

    }

    public void onPause() {
        super.onPause();
        song.pause();
    }

    public void onStop() {
        super.onStop();
       //song.pause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        song.stop();
    }

    public void playGame(View view) {
        Intent intent = new Intent(MainMenu.this,PlayActivity.class);
        intent.putExtra("worldChoise", String.valueOf(world.getSelectedItem()));
        intent.putExtra("charChoise", String.valueOf(world.getSelectedItem()));
        startActivityForResult(intent, PLAY_A_GAME_REQUEST); //permet de récupérer le score à la fin de la partie
    }

    public void showScores(View view) {
        Intent intent = new Intent(MainMenu.this,ScoreActivity.class);
        intent.putExtra("tableScore",tableScore);
        startActivityForResult(intent,SHOW_SCORE_REQUEST);
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
        if(requestCode == SHOW_SCORE_REQUEST){
            if(resultCode==Activity.RESULT_OK){
                tableScore = (TableScore)data.getSerializableExtra("tableScore");
            }
        }
    }
}
