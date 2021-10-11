/* Below is the PlayerManager class.
 * (through csv files) keeps records of player game history (wins, losses) and creates user
 * accounts and give them unique usernames so that we can can calculate the
 * probability of winning/losing as per the users.
 * Keep track of player’s chips on board
 * Allows players to make moves on a game board -> slide or place chip
 *
 * AS OF NOW, PlayerManager is being designed to control a single player, meaning that in a multiplayer game,
 * each player would have different PlayerManager objects.
 *
 * There will be a csv file for each player. Those files will have dates as rows, each consisting
 * of the player's scores and whether they won/lost/drew the games at that specific date in yyyy/mm/dd. E.g.
 * 2021/10/11, D230.5, W300.5, L190.84, L32.5
 * 2021/10/12, W453.33, L230.4
 *
 * Note that what will be used to calculate high scores is still not defined (is it time, moves, etc.?).
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class PlayerManager {
    private final String USERNAME;
    private final char TOKEN_COLOUR;
    private final File PLAYER_DATA_FILE;
    private Map<Date, String[]> userdata;
    private Map<Date, double[]> userscore;
    private Map<Date, char[]> userwin;

    /**
     * Construct a PlayerManager, giving it the username and token colour of the player to be managed
     *
     * @param name username for the player this PlayerManager is managing
     * @param colour token colour ('B' or 'W') for the player this PlayerManager is managing
     */
    public PlayerManager(String name, char colour) throws FileNotFoundException, ParseException {
        USERNAME = name;
        TOKEN_COLOUR = colour;
        PLAYER_DATA_FILE = new File("UserData/" + this.USERNAME + ".csv");
        createUserFile();
        userdata = getUserData();
        userscore = getUserScore();
        userwin = getUserWin();
    }

    private void createUserFile() {
        // create a data file for the player; if the player already has one, createNewFile will not overwrite it.
        try {
            PLAYER_DATA_FILE.createNewFile();
        }
        catch (java.io.IOException e){
            e.printStackTrace();
        }
    }

    private HashMap<Date, String[]> getUserData() throws FileNotFoundException, ParseException {
        // store row's scores in a dict, where the key is the date and the value
        // is an array of values of win/draw/loss plus score.
        HashMap<Date, String[]> userdata = new HashMap<>();
        // specify the format of the String used to store the date
        DateFormat format = new SimpleDateFormat("yyyy,MM,dd");

        Scanner scanner = new Scanner(PLAYER_DATA_FILE);
        scanner.useDelimiter(",");

        while (scanner.hasNext()) {
            String[] row = scanner.next().split(","); // transform row into an array
            Date date = format.parse(row[0]); //get first column and store it as a date
            String[] rowScores = Arrays.copyOfRange(row, 1, row.length); // slice row to get all but the first value
            userdata.put(date, rowScores);
        }
        return userdata;
    }

    private HashMap<Date, double[]> getUserScore(){
        // store row's scores in a dict, where the key is the date and the value is an array of scores.
        // copy the values from userdata
        HashMap<Date, double[]> userscores = new HashMap<>();

        for(Map.Entry<Date, String[]> row : userdata.entrySet()) {
            Date date = row.getKey();
            String[] rowValues = row.getValue();

            // remove the first character ("W", "D", "L") from the row data and store it as a double
            double[] scores = new double[rowValues.length];
            for(int i = 0; i < rowValues.length; i++){
                scores[i] = Double.parseDouble(rowValues[i].substring(1));
            }
            userscores.put(date, scores);
        }
        return userscores;
    }

    private HashMap<Date, char[]> getUserWin(){
        // store row's scores in a dict, where the key is the date and the 
        // value is a char storing whether the user Won, Drew, or Lost.
        // copy the values from userdata
        HashMap<Date, char[]> userwins = new HashMap<>();

        for(Map.Entry<Date, String[]> row : userdata.entrySet()) {
            Date date = row.getKey();
            String[] rowValues = row.getValue();

            // keep only the first character ("W", "D", "L") from the row data and store it as a char
            char[] wins = new char[rowValues.length];
            for(int i = 0; i < rowValues.length; i++){
                wins[i] = (rowValues[i].substring(0, 1)).charAt(0);
            }
            userwins.put(date, wins);
        }
        return userwins;
    }

    public double getAverageScore(){
        double totalScore = 0.0;
        int numOfScores = 0;
        //iterate over every score in userscore
        for(double[] rowScores : userscore.values()){
            totalScore += Arrays.stream(rowScores).sum();
            numOfScores += rowScores.length;
        }

        return totalScore / numOfScores;
    }

    /* NOTE: The methods dealing with max/min runtime might not be the best, but because we expect the user to play
     * less than, say, 5000 times, the runtime won't be of great importance
     */
    public double getHighestScore(){
        double maxScoreSoFar = 0.0;
        //iterate over every score in userscore
        for(double[] rowScores : userscore.values()){
            //get the row's max value and store it as a double
            double rowHighestScore = Arrays.stream(rowScores).max().getAsDouble();

            if(maxScoreSoFar < rowHighestScore){ maxScoreSoFar = rowHighestScore; }
        }

        return maxScoreSoFar;
    }

    public double getDayHighestScore(Date date){
        double maxScoreSoFar = 0.0;
        //get row corresponding to date and return its max as a double
        return Arrays.stream(userscore.get(date)).max().getAsDouble();
    }

    public double getLowestScore(){
        double mixScoreSoFar = 0.0;
        //iterate over every score in userscore
        for(double[] rowScores : userscore.values()){
            //get the row's max value and store it as a double
            double rowLowestScore = Arrays.stream(rowScores).min().getAsDouble();

            if(mixScoreSoFar > rowLowestScore){ mixScoreSoFar = rowLowestScore; }
        }

        return mixScoreSoFar;
    }

    public double getDayLowestScore(Date date){
        double maxScoreSoFar = 0.0;
        //get row corresponding to date and return its max as a double
        return Arrays.stream(userscore.get(date)).min().getAsDouble();
    }

    public int getLongestStreak(char streakType){
        int longestStreak = 0;
        //iterate over every score in userscore
        for(char[] row : userwin.values()){
            //get the row's max streakType (w, d, l)
            int rowStreak = getRowStreak(row, streakType);
            if(longestStreak < rowStreak) { longestStreak = rowStreak; }
        }

        return longestStreak;
    }

    public int getRowStreak(char[] row, char streakType){
        int longestStreak = 0;
        int streakCounter = 0;
        int streakStoppedAt = 0;
        for(int i = 0; i < row.length; i++){
            for(int j = i; j < row.length; j++){
                if(row[j] == streakType) { streakCounter++; }
                else {
                    streakStoppedAt = j;
                    break;
                }
                i = streakStoppedAt;
            }
            if(longestStreak < streakCounter) { longestStreak = streakCounter; }
        }
        return longestStreak;
    }
}
