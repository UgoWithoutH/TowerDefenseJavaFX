package boucleJeu;
import game_logic.GameManager;
import javafx.application.Platform;

import static java.lang.Thread.sleep;

// TODO: 17/12/2021
//  commpleter la Boucle de jeu (voir reccomencer)
public class Boucle extends Observable implements Runnable {
    private boolean running = true;
    private long milis  = 50;
    private int timer = 200;
    GameManager gameManager;

    public Boucle(GameManager gameManager){
        this.gameManager = gameManager;
    }

    public boolean isRunning() {
        return running;
    }

    public long getMilis(){return milis;}

    public void setMilis(long milis){
        this.milis = milis;
    }

    public void start() {
        running = true;
        run();
    }

    public void switchRunning() {
        if(running)
            running = false;
        else
            running = true;
    }

    @Override
    public void run() {
            while (running) {
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
