package view.informer;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ListChangeListener;
import model.characters.monster.Monster;
import model.game_logic.GameManager;
import model.game_logic.GameState;

public class InformerMonsters {

    private ObjectProperty<Monster> monster = new SimpleObjectProperty<>();
        public Monster getMonster() {return monster.get();}
        public ObjectProperty<Monster> monsterProperty() {return monster;}
        public void setMonster(Monster monster) {this.monster.set(monster);}
    private GameManager gameManager;

    public InformerMonsters(GameManager gameManager) {
        this.gameManager = gameManager;
        addListener();
    }

    public void addListener() {
        gameManager.getGame().getMonstersAlive().addListener(new ListChangeListener<Monster>() {
            @Override
            public void onChanged(Change<? extends Monster> change) {
                var listMonsters = change.getList();
                if (!gameManager.getGame().isRemoveMonster()) {
                    setMonster(listMonsters.get(listMonsters.size() - 1));
                }
            }
        });
    }
}
