public final class PointsBoard {

/**
 * Keeps track of each gamer's total season points,
 * average per match, and what medal they get.
 * Final so no one changes it after it is built.
 */

    private static final int MATCHES_PER_GAMER = 15;
    private static final int GOLD_THRESHOLD = 4400;
    private static final int SILVER_MIN = 3800;
    private static final int SILVER_MAX = 4399;
    private static final int BRONZE_MIN = 3500;
    private static final int BRONZE_MAX = 3799;

    //Arrays hold the results for every gamer
    private final Gamer[] gamers;
    private final Match[][] matches;
    private final int[] totalPoints;
    private final double[] averagePerMatch;
    private final String[] medals;

    /**
     * Takes gamers and their matches, then calculates everything.
     * Deep-copy both arrays so the original data can't be changed later.
     */
    public PointsBoard(Gamer[] gamers, Match[][] matches) {
        validateInputs(gamers, matches);

        this.gamers = new Gamer[gamers.length];
        for (int i = 0; i < gamers.length; i++) {
            this.gamers[i] = new Gamer(gamers[i]);
        }

        this.matches = new Match[matches.length][];
        for (int i = 0; i < matches.length; i++) {
            this.matches[i] = new Match[matches[i].length];
            for (int j = 0; j < matches[i].length; j++) {
                this.matches[i][j] = new Match(matches[i][j]);
            }
        }
        
        //Initialize result arrays
        this.totalPoints = new int[gamers.length];
        this.averagePerMatch = new double[gamers.length];
        this.medals = new String[gamers.length];
        computeAll();
    }

    /**
     * Validates input arrays before using them.
     * Throws clear errors if something is wrong.
     */
    private void validateInputs(Gamer[] gamers, Match[][] matches) {
        if (gamers == null) {
            throw new IllegalArgumentException("Gamers array cannot be null");
        }
        if (matches == null) {
            throw new IllegalArgumentException("Matches array cannot be null");
        }
        if (gamers.length == 0) {
            throw new IllegalArgumentException("Gamers array cannot be empty");
        }
        if (matches.length != gamers.length) {
            throw new IllegalArgumentException("Matches rows must equal number of gamers");
        }
        for (int i = 0; i < matches.length; i++) {
            if (matches[i] == null || matches[i].length != MATCHES_PER_GAMER) {
                throw new IllegalArgumentException(
                    "Gamer " + i + " must have exactly " + MATCHES_PER_GAMER + " matches"
                );
            }
        }
    }

    //Sum every gamer's 15 matches
    private void computeAll() {
        for (int i = 0; i < gamers.length; i++) {
            int sum = 0;
            for (int j = 0; j < MATCHES_PER_GAMER; j++) {
                sum += matches[i][j].getMatchPoints();
            }
            totalPoints[i] = sum;
            averagePerMatch[i] = (double) sum / MATCHES_PER_GAMER;
            medals[i] = assignMedal(sum);
        }
    }

    private String assignMedal(int total) {
        if (total >= GOLD_THRESHOLD) return "GOLD";
        if (total >= SILVER_MIN && total <= SILVER_MAX) return "SILVER";
        if (total >= BRONZE_MIN && total <= BRONZE_MAX) return "BRONZE";
        return "NONE";
    }

    //Getters
    public Gamer getGamer(int index) {
        validateIndex(index);
        return gamers[index];
    }
    public int getTotalPoints(int index) {
        validateIndex(index);
        return totalPoints[index];
    }
    public double getAveragePerMatch(int index) {
        validateIndex(index);
        return averagePerMatch[index];
    }
    public String getMedal(int index) {
        validateIndex(index);
        return medals[index];
    }
    public int getGamerCount() {
        return gamers.length;
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= gamers.length) {
            throw new IndexOutOfBoundsException("Gamer index " + index + " is invalid");
        }
    }
}