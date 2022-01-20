package model.serialization;

import model.ScoreRanking;

/**
 * Gère la persistance des données
 */
public abstract class AdministratorPersistence implements ISaveStates, ILoadStates {
    @Override
    public abstract void save(ScoreRanking scoreRanking);

    @Override
    public abstract void load(ScoreRanking scoreRanking);
}
