package model.Serialization;

import model.ScoreRanking;
import model.game_logic.GameState;

import java.io.*;

public class GestionairePersistance {

    private static final File fileSerialization = new File(System.getProperty("user.dir") + "/code/ressources/serialization/test.ser");

    public static void saveStates(ScoreRanking scoreRanking) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileSerialization))) {
            try {
                ScoreRankingSerializable srs = new ScoreRankingSerializable();
                StateSerializable gameStateSerialization;
                var list = scoreRanking.getRanking();
                for (GameState game : scoreRanking.getRanking()) {
                    gameStateSerialization = new StateSerializable(
                            game.getPseudo(),
                            game.getLevel(),
                            game.getScore(),
                            game.getTimeSeconds(),
                            game.isVictory()
                    );
                    srs.getRanking().add(gameStateSerialization);
                }
                oos.writeObject(srs);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadStates(ScoreRanking scoreRanking) {
        if(fileSerialization.length() == 0) return;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileSerialization))) {
            ScoreRankingSerializable scoreRankingSerializable;
            scoreRankingSerializable = (ScoreRankingSerializable) ois.readObject();
            for (StateSerializable stateSerializable : scoreRankingSerializable.getRanking()) {
                GameState gameState = new GameState(stateSerializable.getPseudo());
                gameState.setLevel(stateSerializable.getLevel());
                gameState.setScore(stateSerializable.getScore());
                gameState.setTimeSeconds(stateSerializable.getTime());
                gameState.setVictory(stateSerializable.isVictory());
                scoreRanking.getRanking().add(gameState);
            }
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

}
