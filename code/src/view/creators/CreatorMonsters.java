package view.creators;

import javafx.collections.ListChangeListener;
import javafx.scene.Group;
import model.characters.monster.Monster;
import model.game_logic.GameManager;

public class CreatorMonsters {

    private GameManager gameManager;
    private Group tilemapGroup;

    public CreatorMonsters(GameManager gameManager, Group tilemapGroup) {
        this.gameManager = gameManager;
        this.tilemapGroup = tilemapGroup;
        addListener();
    }

    public void addListener() {
        gameManager.getGame().getMonstersAlive().addListener(new ListChangeListener<Monster>() {
            @Override
            public void onChanged(Change<? extends Monster> change) {
                var listMonsters = change.getList();
                if (!gameManager.getGame().isRemoveMonster()) {
                    tilemapGroup.getChildren().add(listMonsters.get(listMonsters.size() - 1).getView());
                }
            }
        });
    }
}