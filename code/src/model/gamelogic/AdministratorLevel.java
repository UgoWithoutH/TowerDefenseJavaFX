package model.gamelogic;

import model.gamelogic.action.ILevel;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AdministratorLevel implements ILevel{
    private GameState game;
    private int level;
    private Scanner levelFile;

    public AdministratorLevel(GameState game) {
        this.game = game;
        this.level = game.getLevel();
        setFileLevel(level);
    }

    public Scanner getLevelFile() {
        return levelFile;
    }

    /**
     * Set le Scanner du fichier d'ennemie si disponible
     * Sinon il n'y a pas plus de manche
     * @param level int Level a charger
     * @return  boolean true si disponible
     */
    public boolean setFileLevel(int level){
        Scanner scannerMonster = null;
        try {
            scannerMonster = new Scanner(new File(System.getProperty("user.dir") + "/code/ressources/levels/level"+level+".txt"));
        } catch (FileNotFoundException e) {
            return false;
        }
        this.levelFile = scannerMonster;
        return true;
    }

    /**
     *  Charge le prochain Niveau (Vague) si disponible
     * @return  boolean si Level trouvé
     */
    @Override
    public boolean nextLevel() {
        this.level++;
        if(setFileLevel(this.level)){
            this.game.setLevel(this.level);
            return true;
        }
        return false;
    }
}
