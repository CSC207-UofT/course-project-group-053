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
 * of the player's scores at that specific date in yyyy/mm/dd. E.g.
 * 2021/10/11, 230.5, 300.5, 190.84, 32.5
 * 2021/10/12, 453.33, 230.4
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
    private final Map<Date, double[]> USERDATA;

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
        USERDATA = getUserData();
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

    private HashMap<Date, double[]> getUserData() throws FileNotFoundException, ParseException {
        // store row's scores in a dict, where the key is the date and the value is an array of scores.
        HashMap<Date, double[]> userdata = new HashMap<>();
        // specify the format of the String used to store the date
        DateFormat format = new SimpleDateFormat("yyyy,MM,dd");

        Scanner scanner = new Scanner(PLAYER_DATA_FILE);
        scanner.useDelimiter(",");

        while (scanner.hasNext()) {
            String[] row = scanner.next().split(","); // transform row into an array
            Date date = format.parse(row[0]); //get first column and store it as a date
            // slice row to get all but the first value while parsing all Strings to double
            double[] rowScores = new double[row.length - 1];
            for(int i = 1; i < row.length; i++){
                rowScores[i] = Double.parseDouble(row[i]);
            }
            userdata.put(date, rowScores);
        }
        return userdata;
    }
}
