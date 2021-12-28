package boucleJeu;
import static java.lang.Thread.sleep;

// TODO: 17/12/2021
//  commpleter la Boucle de jeu (voir reccomencer)
public class Boucle extends Observable implements Runnable {
    public boolean running = false;
    public int tickCount = 0;
    Thread th;

    public void start() {
        new Thread().run();
        running = true;
    }

    public void stopBoucle() {
        running = false;
    }

    @Override
    public void run() {
        int timer = 0;
        while(running) {
            System.out.println("aaa");
            try {
                sleep(30);
                timer++;
                beep(timer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public void beep(int timer) {
        notifier(timer);
    }
}
