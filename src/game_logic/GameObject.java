package game_logic;

public abstract class GameObject {

    private String name;
    private Vector2 position;

    public GameObject(String name, Vector2 position){
        this.name = name;
        this.position = position;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Vector2 getPosition() { return position; }
    public void setPosition(Vector2 position) { this.position = position; }
}
