import java.io.*;

/** 
 * Class for reading Game and Gamer data from CSV files.
 * Provides methods to load objects into arrays.
 */
public class FileIO {

    /** Loads Game objects from games.csv
     * @param filePath Relative path to games.csv
     * @return An Array of Game objects
     */ 
    public Game[] loadGames(String filePath) throws IOException{

        validateFiles(filePath);
        
        int gameCount = countLines(filePath);

        if (gameCount <= 0) {
            throw new IllegalArgumentException (filePath + " contains no data rows. (Found: " + gameCount+1 + " lines.)");
        }

        Game[] games = new Game[gameCount];

        // Parse lines into words (fields)
        try (BufferedReader gamesFile = new BufferedReader (new FileReader(filePath))){

            String line;
            gamesFile.readLine(); // Skip the header

            int index = 0;
            while ((line = gamesFile.readLine()) != null) {

                String[] tokens = line.split(",");

                if (tokens.length < 3) {
                    throw new IOException("Malformed line in " + filePath + ". Line: " + line);
                } 

                try {
                    int id = Integer.parseInt(tokens[0].trim());
                    String gameName = tokens[1].trim();
                    int basePointPerRound = Integer.parseInt(tokens[2].trim());

                    games[index] = new Game(id, gameName, basePointPerRound);
                    index++;
                } 
                catch (NumberFormatException e) {
                    throw new IOException("Invalid number format in " + filePath + ". Line " + line, e);
                }
            }
        }
        return games;
    }

    /** Loads Gamer objects from gamers.csv
     * @param filePath Relative path to gamers.csv
     * @return An Array of Gamer objects
     */ 
    public Gamer[] loadGamers(String filePath) throws IOException{

        validateFiles(filePath);

        int gamerCount = countLines(filePath);

        if (gamerCount <= 0) {
            throw new IllegalArgumentException (filePath + " contains no data rows. (Found: " + gamerCount+1 + " lines.)");
        }

        Gamer[] gamers = new Gamer[gamerCount];
        
        // Parse lines into words (fields)
        try (BufferedReader gamersFile = new BufferedReader (new FileReader(filePath))){

            String line;
            gamersFile.readLine(); // Skip the header

            int index = 0;
            while ((line = gamersFile.readLine()) != null) {

                String[] tokens = line.split(",");

                if (tokens.length < 5) {
                    throw new IOException("Malformed line in " + filePath + ". Line: " + line);
                }

                try {
                    int id = Integer.parseInt(tokens[0].trim());
                    String nickname = tokens[1].trim();
                    String name = tokens[2].trim();
                    String phoneNumber = tokens[3].trim();
                    int experienceYears = Integer.parseInt(tokens[4].trim());

                    gamers[index] = new Gamer(id, nickname, name, phoneNumber, experienceYears);
                    index++;
                }

                catch(NumberFormatException e) {
                    throw new IOException("Invalid number format in " + filePath + ". Line " + line, e);
                }
            }
        }
        return gamers;
    }

    // HELPERS
    private void validateFiles(String filePath) throws IOException {
        
        File file = new File(filePath);
        if (!file.exists()) {
            throw new IOException("File does not exist: " + filePath);
        }
        if (!file.canRead()) {
            throw new IOException("File cannot be read: " + filePath);
        }

    }

    /** Counts the number of data lines in a CSV file
     * @param filePath Relative path to the CSV file 
     * @return The number of lines without the header
     */
    private int countLines (String filePath) throws IOException{

        int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String header = reader.readLine(); // skip header
            if (header == null) {
                throw new IOException("CSV file is empty or missing header: " + filePath);
            }

            while (reader.readLine() != null) {
                count++;
            }
        }
        return count;
    }

}
