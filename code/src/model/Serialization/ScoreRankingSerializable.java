package model.Serialization;


import java.util.ArrayList;
import java.util.List;

public class ScoreRankingSerializable {

    private final List<StateSerializable> list = new ArrayList<>();

    public List<StateSerializable> getList() {
        return list;
    }
}
