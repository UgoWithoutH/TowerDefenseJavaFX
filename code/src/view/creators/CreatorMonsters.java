package view.creators;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ListChangeListener;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import model.characters.monster.Basic;
import model.characters.monster.Monster;
import model.characters.monster.Speed;
import model.game_logic.GameManager;

public class CreatorMonsters {

    private GameManager gameManager;
    private Group tilemapGroup;

    /**
     * Create Monster By observable list
     *
     * @param gameManager
     * @param tilemapGroup
     */
    public CreatorMonsters(GameManager gameManager, Group tilemapGroup) {
        this.gameManager = gameManager;
        this.tilemapGroup = tilemapGroup;
        addListener();
    }

    /**
     * Add Monster in View
     * todo
     *  Terminer la nouvelle création de monstre et le déplacement et la couleur par BONHOME
     */
    public void addListener() {
        gameManager.getGame().getMonstersAlive().addListener(new ListChangeListener<Monster>() {
            @Override
            public void onChanged(Change<? extends Monster> change) {
                var listMonsters = change.getList();
                if (!gameManager.getGame().isRemoveMonster()) {
                    Monster monster = listMonsters.get(listMonsters.size() - 1);
                    Circle monsterView;
                    if(monster instanceof Speed){
                        monsterView = new Circle(monster.getRadius(), Color.GREEN);
                    }
                    else{
                        monsterView = new Circle(monster.getRadius(), Color.RED);
                    }
                    monsterView.centerXProperty().bind(monster.getCoords().xProperty());
                    monsterView.centerYProperty().bind(monster.getCoords().yProperty());
                    monsterView.visibleProperty().bind(monster.visibleProperty());
                    tilemapGroup.getChildren().add(monsterView);
                }
            }
        });

    }
}
