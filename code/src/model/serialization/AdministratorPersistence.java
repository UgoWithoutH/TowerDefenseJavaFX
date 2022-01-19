package model.serialization;

import model.ScoreRanking;

public abstract class AdministratorPersistence implements ISaveStates, ILoadStates {
    @Override
    public abstract void save(ScoreRanking scoreRanking);

    @Override
    public abstract void load(ScoreRanking scoreRanking);
}
