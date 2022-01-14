package model.Serialization;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ScoreRankingSerializable implements Serializable {

    private  static  final  long serialVersionUID =  1350092881346723535L;
    private final List<StateSerializable> ranking = new ArrayList<>();

    public List<StateSerializable> getRanking() {
        return ranking;
    }
}
