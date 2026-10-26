
import java.text.DecimalFormat;

/**
 * Prints all 6 required outputs in the exact format from the sample.
 * Getters from Match.
 */

public class Query {

    private static final DecimalFormat DF = new DecimalFormat("0.00");
    private final Match[][] matches;
    private final PointsBoard board;

    /**
     * Takes matches and board, checks for null.
     * No need to copy arrays since PointsBoard already deep-copied them.
     */
    public Query(Match[][] matches, PointsBoard board) {
        if (matches == null || board == null) {
            throw new IllegalArgumentException("Inputs cannot be null");
        }
        this.matches = matches;
        this.board = board;
    }

    //1. Highest Scoring Match
    public void printHighestScoringMatch() {
        Match best = null;
        int maxPoints = Integer.MIN_VALUE;

        for (Match[] row : matches) {
            for (Match m : row) {
                if (m.getMatchPoints() > maxPoints) {
                    maxPoints = m.getMatchPoints();
                    best = m;
                }
            }
        }

        System.out.println("1. Highest-Scoring Match");
        printMatchDetails(best, maxPoints);
        System.out.println();
    }

    //2. Lowest Scoring Match and Most Contributing Game
    public void printLowestScoringMatchWithContribution() {
        Match worst = null;
        int minPoints = Integer.MAX_VALUE;

        for (Match[] row : matches) {
            for (Match m : row) {
                if (m.getMatchPoints() < minPoints) {
                    minPoints = m.getMatchPoints();
                    worst = m;
                }
            }
        }

        System.out.println("2. Lowest-Scoring Match & Most Contributing Game");
        printMatchDetails(worst, minPoints);

        //Find which game gave the most points in this match
        Game contrib = worst.getMostContributingGame();
        int contribValue = worst.getMaxGameContribution();

        System.out.println("Most Contributing Game in this Match:");
        System.out.println("Game: " + contrib.getGameName());
        System.out.println("Contribution: " + 
            getRoundsForGame(worst, contrib) + " rounds × " + 
            contrib.getBasePointPerRound() + " points = " + contribValue);
        System.out.println();
    }

    //3. Match with Lowest Bonus Points
    public void printLowestBonusMatch() {
        Match lowestBonus = null;
        int minBonus = Integer.MAX_VALUE;

        for (Match[] row : matches) {
            for (Match m : row) {
                if (m.getBonusPoints() < minBonus) {
                    minBonus = m.getBonusPoints();
                    lowestBonus = m;
                }
            }
        }

        System.out.println("3. Match with the Lowest Bonus Points");
        System.out.println("Match with Lowest Bonus Points:");
        printMatchDetails(lowestBonus, lowestBonus.getMatchPoints());
        System.out.println();
    }

    //4. Highest Scoring Gamer
    public void printHighestScoringGamer() {
        int maxTotal = -1;
        int bestIdx = -1;
        for (int i = 0; i < board.getGamerCount(); i++) {
            int total = board.getTotalPoints(i);
            if (total > maxTotal) {
                maxTotal = total;
                bestIdx = i;
            }
        }

        Gamer g = board.getGamer(bestIdx);
        System.out.println("4. Highest-Scoring Gamer");
        System.out.println("Nickname: " + g.getNickname());
        System.out.println("Name: " + g.getName());
        System.out.println("Total Points: " + maxTotal);
        System.out.println("Average Per Match: " + DF.format(board.getAveragePerMatch(bestIdx)));
        System.out.println("Medal: " + board.getMedal(bestIdx));
        System.out.println();
    }

    //5. Total Tournament Points
    public void printTotalTournamentPoints() {
        long total = 0;
        for (int i = 0; i < board.getGamerCount(); i++) {
            total += board.getTotalPoints(i);
        }
        System.out.println("5. Total Tournament Points");
        System.out.println("Total Tournament Points across 1500 matches: " + total);
        System.out.println();
    }

    //6. Medal Distribution
    public void printMedalDistribution() {
        int gold = 0, silver = 0, bronze = 0, none = 0;
        int n = board.getGamerCount();

        for (int i = 0; i < n; i++) {
            String m = board.getMedal(i);
            if ("GOLD".equals(m)) gold++;
            else if ("SILVER".equals(m)) silver++;
            else if ("BRONZE".equals(m)) bronze++;
            else none++;
        }

        System.out.println("6. Medal Distribution");
        System.out.println("Medal Distribution:");
        System.out.println("GOLD: " + gold + " gamers (" + DF.format(100.0 * gold / n) + "%)");
        System.out.println("SILVER: " + silver + " gamers (" + DF.format(100.0 * silver / n) + "%)");
        System.out.println("BRONZE: " + bronze + " gamers (" + DF.format(100.0 * bronze / n) + "%)");
        System.out.println("NONE: " + none + " gamers (" + DF.format(100.0 * none / n) + "%)");
    }

    private void printMatchDetails(Match m, int matchPoints) {
        Game[] games = m.getGames();
        int[] rounds = m.getRounds();

        //Match ID
        System.out.println("Match ID: " + System.identityHashCode(m));
        
        //Games
        System.out.print("Games: [");
        for (int i = 0; i < games.length; i++) {
            System.out.print(games[i].getGameName());
            if (i < games.length - 1) System.out.print(", ");
        }
        System.out.println("]");

        //Rounds
        System.out.print("Rounds: [");
        for (int i = 0; i < rounds.length; i++) {
            System.out.print(rounds[i]);
            if (i < rounds.length - 1) System.out.print(", ");
        }
        System.out.println("]");

        //Raw, Skill, Bonus, Total points
        System.out.println("Raw Points: " + m.getRawPoints());
        System.out.println("Skill Points: " + m.getSkillPoints());
        System.out.println("Bonus Points: " + m.getBonusPoints());
        System.out.println("Match Points: " + matchPoints);
    }

    /**
     * Finds how many rounds were played for a game in a match.
     * Used for contribution calculation.
     */
    private int getRoundsForGame(Match m, Game target) {
        Game[] games = m.getGames();
        int[] rounds = m.getRounds();
        for (int i = 0; i < games.length; i++) {
            if (games[i].getId() == target.getId()) {
                return rounds[i];
            }
        }
        return 0;
    }
}