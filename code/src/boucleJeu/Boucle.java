package boucleJeu;
import game_logic.GameManager;
import javafx.application.Platform;
import model.characters.monster.Monster;

import static java.lang.Thread.sleep;


public class Boucle extends Observable implements Runnable {
    private boolean running = true;
    private long milis  = 50;
    private int timer = 200;
    GameManager gameManager;

    public Boucle(GameManager gameManager){
        this.gameManager = gameManager;
        subscribe(gameManager);
    }

    public long getMilis(){return milis;}

    public void setMilis(long milis){
        this.milis = milis;
    }

    public void start() {
        running = true;
        run();
    }

    public boolean isRunning(){return running;}

    public void setRunning(boolean run){running = run;}

    @Override
    public void run() {
        while(true) {
            if(gameManager.getGame().isGameOver()){
                break;
            }
            if(gameManager.freeze){
                synchronized (this){
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            try {
                sleep(milis);
                timer--;
                beep(timer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void beep(int timer) {
        Platform.runLater(() -> notifier(timer));
    }

    public void attack() throws InterruptedException {
        gameManager.attacker();
        gameManager.getGameViewLogic().createProjectiles();

    }
}
