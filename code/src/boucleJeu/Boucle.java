package boucleJeu;
import game_logic.GameManager;
import javafx.application.Platform;
import model.characters.monster.Monster;

import static java.lang.Thread.sleep;


public class Boucle extends Observable implements Runnable {
    private long milis  = 50;
    private int timer = 200;
    private boolean running = false;
    private boolean speed = false;
    GameManager gameManager;

    public Boucle(GameManager gameManager){
        this.gameManager = gameManager;
        subscribe(gameManager);
    }

    public boolean isSpeed() {return speed;}

    public void setSpeed(boolean speed) {this.speed = speed;    }

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
        while(isRunning()) {
            if(gameManager.getGame().isGameOver()){
                break;
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
