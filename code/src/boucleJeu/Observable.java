package boucleJeu;

import java.util.LinkedList;

public class Observable {
    private LinkedList<Observateur> observateurs = new LinkedList<>();

    public void subscribe(Observateur listener){
        observateurs.add(listener);
    }

    public void unsubscribe(Observateur listener){
        observateurs.remove(listener);
    }

    public void notifier(){
        for(var observateur : observateurs){
            observateur.update();
        }
    }
}