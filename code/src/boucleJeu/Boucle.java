package boucleJeu;


// TODO: 17/12/2021
//  commpleter la Boucle de jeu (voir reccomencer)
public class Boucle implements Thread {
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
            sleep(30);
            beep();
        }
    }


    public void beep() {

    }
}
