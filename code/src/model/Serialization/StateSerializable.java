package model.Serialization;

import java.io.Serializable;

public class StateSerializable implements Serializable {

    private  static  final  long serialVersionUID =  1350092881346723535L;
    private int level;
    private int score;
    private int time;
    private boolean victory;

    public StateSerializable(int level, int score, int time, boolean victory){
        this.level = level;
        this.score = score;
        this.time = time;
        this.victory = victory;
    }

    public int getLevel() {return level;}

    public int getScore() {return score;}

    public int getTime() {return time;}

    public boolean isVictory() {return victory;}
}
