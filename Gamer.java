public class Gamer {
    
    //Fields (final for safety)
    private final int id;
    private final String nickname;
    private final String name;
    private final String phoneNumber;
    private final int experienceYears;
    
    //Default Constructor
    public Gamer() {
        this.id = 0;
        this.nickname = "";
        this.name = "";
        this.phoneNumber = "";
        this.experienceYears = 0;
    }

    //Loaded Constructor
    public Gamer(int id, String nickname, String name, String phoneNumber, int experienceYears) {
        this.id = id;
        this.nickname = nickname;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.experienceYears = experienceYears;
    }

    //Copy Constructor
    public Gamer(Gamer other) {
        this.id = other.id;
        this.nickname = other.nickname;
        this.name = other.name;
        this.phoneNumber = other.phoneNumber;
        this.experienceYears = other.experienceYears;
    }

    // Getters (no setters because the fields are immutable)
    public int getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getExperienceYears() {
        return experienceYears;
    }

    @Override
    public String toString() {
        return ("ID: " + id + ", Nickname: " + nickname + ", Name: " + name + ", Phone: " + phoneNumber + ", Experience: " + experienceYears + " years");
    }
}
