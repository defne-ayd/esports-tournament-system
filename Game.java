public class Game {
    
    //Fields (final for safety)
    private final int id;
    private final String gameName;
    private final int basePointPerRound;

    //Default Constructor
    public Game() {
        this.id = 0;
        this.gameName = "";
        this.basePointPerRound = 0;
    }

    //Loaded Constructor
    public Game(int id, String gameName, int basePointPerRound) {
        this.id = id;
        this.gameName = gameName;
        this.basePointPerRound = basePointPerRound;
    }

    //Copy constructor
    public Game(Game other) {
        this.id = other.id;
        this.gameName = other.gameName;
        this.basePointPerRound = other.basePointPerRound;
    }

    //Getters (no setters because fields are immutable)
    public int getId() {
        return id;
    }

    public String getGameName() {
        return gameName;
    }

    public int getBasePointPerRound() {
        return basePointPerRound;
    }

    @Override
    public String toString() {

        return ("Game: " + gameName);
    }
}
