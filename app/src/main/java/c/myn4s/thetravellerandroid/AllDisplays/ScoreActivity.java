package c.myn4s.thetravellerandroid.AllDisplays;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import c.myn4s.thetravellerandroid.R;

public class ScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scores_display);
    }
    public void back(View view) {
        ScoreActivity.this.finish();
    }
}
