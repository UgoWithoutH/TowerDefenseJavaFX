package model.gameloop;

import java.util.LinkedList;

public abstract class Observable {
    private LinkedList<Observer> observateurs = new LinkedList<>();

    public void subscribe(Observer listener){
        observateurs.add(listener);
    }

    public void unsubscribe(Observer listener){
        observateurs.remove(listener);
    }

    public void notifier(int timer){
        for(var observateur : observateurs){
            observateur.update(timer);
        }
    }
}