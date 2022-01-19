package model.serialization;

import model.ScoreRanking;

public abstract class AdministratorPersistence implements SaveStates, LoadStates{
    @Override
    public abstract void save(ScoreRanking scoreRanking);

    @Override
    public abstract void load(ScoreRanking scoreRanking);
}
