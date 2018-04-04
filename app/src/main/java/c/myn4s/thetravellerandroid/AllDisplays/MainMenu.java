package c.myn4s.thetravellerandroid.AllDisplays;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import c.myn4s.thetravellerandroid.R;


public class MainMenu extends AppCompatActivity {
    public static MediaPlayer song;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        song = MediaPlayer.create(MainMenu.this, R.raw.menusong);
        song.start();
        song.setLooping(true);

        setContentView(R.layout.activity_main_menu);
    }

    public void playGame(View view) {
        Intent intent = new Intent(MainMenu.this,PlayActivity.class);
        startActivity(intent);
    }

    public void showScores(View view) {
        Intent intent = new Intent(MainMenu.this,ScoreActivity.class);
        startActivity(intent);
    }

    public void settings(View view) {
        Intent intent = new Intent(MainMenu.this,SettingActivity.class);
        startActivity(intent);
    }
}
