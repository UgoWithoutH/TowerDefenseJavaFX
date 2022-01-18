package view.creators;

import javafx.collections.ListChangeListener;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import model.characters.Character;
import model.characters.monster.Monster;
import model.characters.monster.Speed;
import model.gamelogic.GameManager;

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


    private void addListener() {
        gameManager.getGame().getCharactersAlive().addListener(new ListChangeListener<Character>() {
            @Override
            public void onChanged(Change<? extends Character> change) {
                var listCharacters = change.getList();
                if (!gameManager.getGame().isRemoveCharacter()) {
                    Character character = listCharacters.get(listCharacters.size() - 1);
                    if(character instanceof Monster monster){
                        createMonster(monster);
                    }
                }
            }
        });
    }

    private void createMonster(Monster monster){
        Circle monsterView;
        if(monster instanceof Speed){
            monsterView = new Circle(10, Color.GREEN);
        }
        else{
            monsterView = new Circle(10, Color.RED);
        }
        monsterView.centerXProperty().bind(monster.getCoords().xProperty());
        monsterView.centerYProperty().bind(monster.getCoords().yProperty());
        monsterView.visibleProperty().bind(monster.visibleProperty());
        tilemapGroup.getChildren().add(monsterView);
    }
}
