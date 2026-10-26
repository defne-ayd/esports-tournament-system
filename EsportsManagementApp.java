import java.io.IOException;

/**
 * Main class — runs the whole tournament.
 * It loads files, generates matches, computes, and prints queries.
 */

public final class EsportsManagementApp {

    private static final String GAMES_CSV = "games.csv";
    private static final String GAMERS_CSV = "gamers.csv";

    private EsportsManagementApp() {
    }

    public static void main(String[] args) {
        try {
            executeTournament();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(0);  //Exists at any error
        }
    }

    private static void executeTournament() throws IOException {
        //Load games and gamers from CSV
        FileIO fileIO = new FileIO();
        Game[] games = fileIO.loadGames(GAMES_CSV);
        Gamer[] gamers = fileIO.loadGamers(GAMERS_CSV);

        //Generate 15 matches for each gamer
        MatchManagement matchMgmt = new MatchManagement(games, gamers);
        matchMgmt.generateMatches();
        Match[][] matches = matchMgmt.getMatches();

        //Calculate season totals and medals
        PointsBoard board = new PointsBoard(gamers, matches);
        
        //Run all 6 queries then print
        Query query = new Query(matches, board);

        System.out.println("Query Results:");
        query.printHighestScoringMatch();
        query.printLowestScoringMatchWithContribution();
        query.printLowestBonusMatch();
        query.printHighestScoringGamer();
        query.printTotalTournamentPoints();
        query.printMedalDistribution();
    }
}