package view.creators;

import javafx.collections.ListChangeListener;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import model.characters.Character;
import model.characters.monster.Monster;
import model.characters.monster.Speed;
import model.gamelogic.GameManager;

/**
 * Créé visuellement les monstres
 */
public class CreatorMonsters {

    private GameManager gameManager;
    private Group tilemapGroup;

    /**
     * Create Monster par une observable list dans GameManager
     *
     * @param gameManager   GameManager
     * @param tilemapGroup  Group
     */
    public CreatorMonsters(GameManager gameManager, Group tilemapGroup) {
        this.gameManager = gameManager;
        this.tilemapGroup = tilemapGroup;
        addListener();
    }

    /**
     * Ecoute l'Observable list pour tout changement
     */
    private void addListener() {
        gameManager.getGame().getCharactersAlive().addListener((ListChangeListener<Character>) change -> {
            var listCharacters = change.getList();
            if (!gameManager.getGame().isRemoveCharacter()) {
                Character character = listCharacters.get(listCharacters.size() - 1);
                if (character instanceof Monster monster) {
                    createMonster(monster);
                }
            }
        });
    }

    /**
     * Créé la Vue du Monstre
     * @param monster   Monster
     */
    private void createMonster(Monster monster){
        Circle monsterView;
        if(monster instanceof Speed){
            monsterView = new Circle(10, Color.GREEN);
        }
        else{
            monsterView = new Circle(10, Color.RED);
        }
        monsterView.centerXProperty().bind(monster.getCoordinate().xProperty());
        monsterView.centerYProperty().bind(monster.getCoordinate().yProperty());
        monsterView.visibleProperty().bind(monster.visibleProperty());
        tilemapGroup.getChildren().add(monsterView);
    }
}
