package c.myn4s.thetravellerandroid.AllDisplays;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.List;

import c.myn4s.thetravellerandroid.GameEngine.Score;
import c.myn4s.thetravellerandroid.GameEngine.TableScore;
import c.myn4s.thetravellerandroid.R;

public class ScoreActivity extends AppCompatActivity {

    private TableScore tableScore;
    private List<Score> leaderBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scores_display);

        Intent intent = getIntent();
        if(intent != null){
            tableScore= (TableScore)intent.getSerializableExtra("tableScore");
            leaderBoard=tableScore.getTableScore();
        }
        TableLayout scoreTableLayout = findViewById(R.id.tableLayoutScore);
        TableRow row;
        TextView date,points;
        for(int i=0;i<10;i++){
            if(i>=leaderBoard.size())break;
            row = new TableRow(getApplicationContext());
            date = new TextView(getApplicationContext());
            points = new TextView(getApplicationContext());

            date.setText(leaderBoard.get(i).getDate());
            points.setText(""+leaderBoard.get(i).getPoint());
            date.setGravity(Gravity.CENTER);
            points.setGravity(Gravity.CENTER);

            row.addView(date);
            row.addView(points);
            scoreTableLayout.addView(row);
        }
        Button rebootLeaderBoard = findViewById(R.id.rebootLeaderBoard);
        /*rebootLeaderBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reinitialize(view);
                setTableLayout(leaderBoard);
            }
        });*/


    }
    public void back(View view) {
        ScoreActivity.this.finish();
    }

    private void setTableLayout(List<Score> leaderBoard){

    }

    /*private void reinitialize(View view) {
        tableScore.deleteFileScore();
        tableScore.chargeScore();
        leaderBoard=tableScore.getTableScore();
    }*/
}
