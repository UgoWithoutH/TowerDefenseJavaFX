package model.boucleJeu;
import model.game_logic.GameManager;
import javafx.application.Platform;

import static java.lang.Thread.sleep;


public class Boucle extends Observable implements Runnable {
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
        gameManager.getGame().setRunning(true);
        run();
    }

    @Override
    public void run() {
        while(gameManager.getGame().isRunning()) {
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
}
