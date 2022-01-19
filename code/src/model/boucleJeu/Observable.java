package model.boucleJeu;

import java.util.LinkedList;

public abstract class Observable {
    private LinkedList<IObservateur> observatory = new LinkedList<>();

    public void subscribe(IObservateur listener){
        observatory.add(listener);
    }

    public void unsubscribe(IObservateur listener){
        observatory.remove(listener);
    }

    public void notifier(int timer){
        for(var observer : observatory){
            observer.update(timer);
        }
    }
}