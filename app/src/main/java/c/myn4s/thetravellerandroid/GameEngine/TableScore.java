package c.myn4s.thetravellerandroid.GameEngine;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

/**
 * Created by ludod on 07/04/2018.
 */

public class TableScore{

    private static List<Score> tableScore;
    private transient static Context appcontext;

    public TableScore(Context context){
        tableScore = new ArrayList<>(10);
        appcontext=context;
    }
    public List<Score> getTableScore() {
        return tableScore;
    }

    public void save(Score actualScore) {
        for (int i = 0; i < 10; i++) {
            if (tableScore.get(i).getPoint() <= actualScore.getPoint()) {
                tableScore.add(i, actualScore);
                break;
            }
        }
        File f = new File(appcontext.getExternalFilesDir(null)+"/allscore.txt");
        f.delete();
        try (
                BufferedWriter scoreWritter = new BufferedWriter(new FileWriter(appcontext.getExternalFilesDir(null)+"/allscore.txt",false));)
        {

            for(int i=0;i<tableScore.size();i++){
                scoreWritter.write(tableScore.get(i).getDate()+"\n"+tableScore.get(i).getPoint()+"\n");
            }
        }
        catch (IOException e) {
            Log.i("GDEBUG","ERREUR ECRITURE : "+e.getMessage());
            e.printStackTrace();
        }
        catch(Exception e){
            Log.i("GDEBUG","ERREUR SAVE :"+e.getMessage());
            e.printStackTrace();
        }
    }

    public void chargeScore() {
        try (BufferedReader scoreReader = new BufferedReader(new FileReader(appcontext.getExternalFilesDir(null) + "/allscore.txt"));){
            String date;
            while((date = scoreReader.readLine())!=null){
                int points = parseInt(scoreReader.readLine());
                tableScore.add(new Score(date,points));
            }

        } catch (IOException e) {
            Log.i("GDEBUG", "ERREUR LECTURE :" + e.getMessage());
            e.printStackTrace();
            tableScore.add(new Score("-",0));
        } catch (Exception e) {
            Log.i("GDEBUG", "ERREUR CHARGE :" + e.getMessage());
            e.printStackTrace();
            tableScore.add(new Score("-",0));
        }
    }
}
