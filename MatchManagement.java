public class MatchManagement {
    
    private Match[][] matches;
    private Game[] allGames;
    private Gamer[] allGamers;

    public MatchManagement(Game[] allGames, Gamer[] allGamers) {
        if (allGames == null || allGames.length == 0) {
            throw new IllegalArgumentException("allGames cannot be null or empty");
        }
        
        if (allGamers == null || allGamers.length == 0) {
            throw new IllegalArgumentException("allGamers cannot be null or empty");
        }
        
        this.allGames = allGames.clone();
        this.allGamers = allGamers.clone();
    }

    public void generateMatches() {
        if (allGames == null || allGamers == null) {
            throw new IllegalStateException("allGames or allGamers cannot be null during match generation");
        }
        
        matches = new Match[allGamers.length][15];
        
        for (int gamerIndex = 0; gamerIndex < allGamers.length; gamerIndex++) {
            
            for (int matchIndex = 0; matchIndex < 15; matchIndex++) {
                Match match = new Match(allGames);
                match.calculatePoints(allGamers[gamerIndex]);
                matches[gamerIndex][matchIndex] = match;
            }
        }
    }

    public Match[][] getMatches() {
        if (matches == null) {
            throw new IllegalStateException("Matches have not been generated yet");
        }
        
        return matches.clone();
    }

    public Match[] getMatchesForGamer(int gamerIndex) {
        if (matches == null) {
            throw new IllegalStateException("Matches have not been generated yet");
        }
        
        if (gamerIndex < 0 || gamerIndex >= allGamers.length) {
            throw new IndexOutOfBoundsException("Invalid gamer index: " + gamerIndex);
        }
        
        if (matches[gamerIndex] == null) {
            throw new IllegalStateException("Matches for gamer index " + gamerIndex + " not initialized");
        }
        
        return matches[gamerIndex].clone();
    }
}
