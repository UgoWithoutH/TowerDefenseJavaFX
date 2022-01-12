package model.Serialization;

import model.ScoreRanking;
import model.game_logic.GameState;

import java.io.*;

public class GestionairePersistance {

    private static final File fileSerialization = new File(System.getProperty("user.dir")+ "/code/ressources/serialization/test.ser");

    public static void saveStates(ScoreRanking scoreRanking){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileSerialization))) {
            try {
                for (GameState game : scoreRanking.getRanking()) {
                    StateSerializable gameStateSerialization = new StateSerializable(
                            game.getLevel(),
                            game.getScore(),
                            game.getTimeSeconds(),
                            game.isVictory()
                    );
                    oos.writeObject(gameStateSerialization);
                }
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
        boolean check = true;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileSerialization))) {
            try {
                StateSerializable stateSerializable;
                while (check) {
                    stateSerializable = (StateSerializable) ois.readObject();
                    GameState gameState = new GameState();
                    gameState.setLevel(stateSerializable.getLevel());
                    gameState.setScore(stateSerializable.getScore());
                    gameState.setTimeSeconds(stateSerializable.getTime());
                    gameState.setVictory(stateSerializable.isVictory());
                    scoreRanking.getRanking().add(gameState);
                }
            }
            catch(EOFException ex){
                check = false;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
