package model.gameloop;

import java.util.LinkedList;

/**
 * Classe Observable
 */
public abstract class Observable {
    private LinkedList<IObserver> observatory = new LinkedList<>();

    public void subscribe(IObserver listener){
        observatory.add(listener);
    }

    public void unsubscribe(IObserver listener){
        observatory.remove(listener);
    }

    /**
     * Notification de tout Observer sur la boucle
     * @param timer
     */
    protected void notify(int timer){
        for(var observer : observatory){
            observer.update(timer);
        }
    }
}