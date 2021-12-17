package boucleJeu;
import static java.lang.Thread.sleep;

// TODO: 17/12/2021
//  commpleter la Boucle de jeu (voir reccomencer)
public class Boucle extends Observable implements Runnable {
    public boolean running = false;
    public int tickCount = 0;
    Thread th;
    public Boucle(){
        start();
    }

    public void start() {
        new Thread().start();
        running = true;
    }

    public void stopBoucle() {
        running = false;
    }

    @Override
    public void run() {

        while(running) {
            try {
                sleep(30);
                beep();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public void beep() {
        notifier();
    }
}
