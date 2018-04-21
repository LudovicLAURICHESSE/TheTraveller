package c.myn4s.thetravellerandroid.GameEngine;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ludod on 31/03/2018.
 */

public class Score implements Serializable {

    private  int point;
    private transient final double FACTEUR_POINT=10;
    private  String date;

    public Score() {
        this.point = 0;
        Date dateActuelle = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        this.setDate(dateFormat.format(dateActuelle));
    }
    public Score(String date, int score){
        this.date = date;
        this.point = score;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public  int getPoint() {
        return point;
    }

    public  void malusPoint(int penalite) {
        point-=penalite;
    }

    public  void winPoint(int pointWin) {
        point+=pointWin;
    }

    public  void addScore(double d) {
        point+= d*FACTEUR_POINT;
    }
}
