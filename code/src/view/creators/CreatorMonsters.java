package view.creators;

import javafx.collections.ListChangeListener;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import model.characters.monster.Monster;
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
                    Monster aa = listMonsters.get(listMonsters.size() - 1);
                    Circle dd = new Circle(aa.getX(), aa.getY(), aa.getRadius(), Color.RED);
                    tilemapGroup.getChildren().add(dd);
                }
            }
        });

    }
}
