import java.util.Random;

public class Match {
    private Game[] games;
    private int[] rounds;
    private int rawPoints;
    private int skillPoints;
    private int bonusPoints;
    private int matchPoints;

    //Loaded constructor
    public Match(Game[] allGames) {
        if (allGames == null || allGames.length == 0) {
            throw new IllegalArgumentException("allGames cannot be null or empty");
        }
        
        if (allGames.length < 3) {
            throw new IllegalArgumentException("At least 3 games are required to create a match");
        }

        games = selectDistinctGames(allGames);
        rounds = generateRandomRounds();
        rawPoints = calculateRawPoints();
    }

    //Copy constructor
    public Match(Match other) {
        
        if (other == null) {
            throw new IllegalArgumentException("Cannot copy a null Match.");
        }

        this.games = new Game[other.games.length];
        for (int i = 0; i < other.games.length; i++) {
            this.games[i] = new Game(other.games[i]);
        }

        this.rounds = other.rounds.clone();
        this.rawPoints = other.rawPoints;
        this.skillPoints = other.skillPoints;
        this.bonusPoints = other.bonusPoints;
        this.matchPoints = other.matchPoints;
    }

    private Game[] selectDistinctGames(Game[] allGames) {
        Random random = new Random();
        Game[] selected = new Game[3];
        boolean[] selectedIndices = new boolean[allGames.length];
        int count = 0;

        while (count < 3) {
            int index = random.nextInt(allGames.length);
            
            if (!selectedIndices[index]) {
                selected[count] = allGames[index];
                selectedIndices[index] = true;
                count++;
            }
        }
        
        return selected;
    }

    private int[] generateRandomRounds() {
        Random random = new Random();
        int[] roundsArray = new int[3];
        
        for (int i = 0; i < 3; i++) {
            roundsArray[i] = random.nextInt(10) + 1;
        }
        
        return roundsArray;
    }

    private int calculateRawPoints() {
        int sum = 0;
        
        for (int i = 0; i < 3; i++) {
            sum += rounds[i] * games[i].getBasePointPerRound();
        }
        
        return sum;
    }

    public void calculatePoints(Gamer gamer) {
        if (gamer == null) {
            throw new IllegalArgumentException("Gamer cannot be null");
        }
        
        if (rawPoints < 0) {
            throw new IllegalStateException("Raw points must be calculated before calling this method");
        }

        int effectiveExperience = Math.min(gamer.getExperienceYears(), 10);
        skillPoints = (int) Math.floor(rawPoints * (1 + effectiveExperience * 0.02));

        if (rawPoints <= 199) {
            bonusPoints = 10;
        } 
        
        else if (rawPoints <= 399) {
            bonusPoints = 25;
        } 
        
        else if (rawPoints <= 599) {
            bonusPoints = 50;
        } 
        
        else {
            bonusPoints = 100;
        }

        matchPoints = skillPoints + bonusPoints;
    }

    public Game[] getGames() {
        return games.clone();
    }

    public int[] getRounds() {
        return rounds.clone();
    }

    public int getRawPoints() {
        return rawPoints;
    }

    public int getSkillPoints() {
        return skillPoints;
    }

    public int getBonusPoints() {
        return bonusPoints;
    }

    public int getMatchPoints() {
        return matchPoints;
    }

    public int getMaxGameContribution() {
        int max = 0;
        
        for (int i = 0; i < 3; i++) {
            int contrib = rounds[i] * games[i].getBasePointPerRound();
            
            if (contrib > max) {
                max = contrib;
            }
        }
        
        return max;
    }

    public Game getMostContributingGame() {
        int max = 0;
        Game mostContributing = null;
        
        for (int i = 0; i < 3; i++) {
            int contrib = rounds[i] * games[i].getBasePointPerRound();
            
            if (contrib > max) {
                max = contrib;
                mostContributing = games[i];
            }
        }
        
        return mostContributing;
    }
}
