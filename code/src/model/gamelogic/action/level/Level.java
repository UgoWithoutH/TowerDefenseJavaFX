package model.gamelogic.action.level;

import model.gamelogic.GameState;
import model.gamelogic.action.ILevel;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Level  {
    private GameState game;
    private int level;
    private Scanner levelFile;

    public Level(GameState game) {
        this.game = game;
        this.level = game.getLevel();
        setFileLevel(level);
    }

    public Scanner getLevelFile() {
        return levelFile;
    }
    public boolean setFileLevel(int level){
        Scanner scannerMonster = null;
        try {
            scannerMonster = new Scanner(new File(System.getProperty("user.dir") + "/code/ressources/levels/level"+level+".txt"));
            System.out.println(level);
        } catch (FileNotFoundException e) {
            return false;
        }
        this.levelFile = scannerMonster;
        return true;
    }


    public boolean nextLevel() {
        this.level++;
        if(setFileLevel(this.level)){
            this.game.setLevel(this.level);
            return true;
        }
        return false;
    }


}
