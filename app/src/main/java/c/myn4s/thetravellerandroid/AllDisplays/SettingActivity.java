package c.myn4s.thetravellerandroid.AllDisplays;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import c.myn4s.thetravellerandroid.R;

public class SettingActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_display);
        Switch songswitch=findViewById(R.id.switchSong);
        if(MainMenu.song.isPlaying()){
            songswitch.setChecked(true);
        }
        else{
            songswitch.setChecked(false);
        }
        songswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean check) {
                if(compoundButton.isChecked()){
                    MainMenu.song.start();
                }
                else{
                    MainMenu.song.pause();
                }
            }
        });
    }

    public void back(View view) {
        SettingActivity.this.finish();
    }

}
