package Gateways;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerDataGateway{
    public static String DATABASE_URL = "jdbc:sqlite:src/Gateways.data/users_test.db";

    /**
     * Updates database entry for existing player logging into a game, or creates a new entry for a new player logging
     * into the game.
     *
     * @param username String username of player of interest
     * @return boolean for whether the username was for a new player or not
     * @throws SQLException For when something sketchy happens with the SQL commands
     */
    public boolean logInUser(String username) throws SQLException {
        Connection conn = DriverManager.getConnection(DATABASE_URL);
        String checkUserCmd = String.format("SELECT * FROM users WHERE username = \"%s\";", username);
        PreparedStatement statement = conn.prepareStatement(checkUserCmd);
        ResultSet result = statement.executeQuery();

        boolean isNewUser = false;
        if (!result.next()) { // no such username exists in database
            logInNewUser(username, conn);
            isNewUser = true;
        } else { // username exists in database
            logInExistingUser(username, conn);
        }

        conn.close();
        statement.close();
        result.close();

        return isNewUser;
    }

    /**
     * Update database entry for a user after a round of Nine Men Morris.
     * If the user won, add one to their number of wins, and update win rate for all users.
     *
     * @param username String for user's username in the database
     * @param wonRound boolean indicating if this user won the match
     */
    public void updateUserAfterGame(String username, boolean wonRound) throws SQLException {
        Connection conn = DriverManager.getConnection(DATABASE_URL);

        if (wonRound) {
            String updateWinCommand = "UPDATE users\n" +
                    "SET\n" +
                    "num_wins = num_wins + 1,\n" +
                    "win_rate = cast((num_wins + 1) as REAL) / num_games\n" +
                    "WHERE\n" +
                    String.format("username = \"%s\";", username);

            PreparedStatement statement = conn.prepareStatement(updateWinCommand);
            statement.executeUpdate();
            statement.close();

        } else {
            String updateLossCommand = "UPDATE users\n" +
                    "SET\n" +
                    "win_rate = cast(num_wins as REAL) / num_games\n" +
                    "WHERE\n" +
                    String.format("username = \"%s\";", username);

            PreparedStatement statement = conn.prepareStatement(updateLossCommand);
            statement.executeUpdate();
            statement.close();
        }

        conn.close();
    }

    /**
     * Retrieves a hashmap, representing all columns of Gateways.data for a user in the database.
     *
     * Precondition: username is already registered in the database
     *
     * @param username String for username of the user of interest
     * @throws SQLException Something sketchy happened with the SQL commands
     */
    public Map<String, Object> getUserStats(String username) throws SQLException {
        Connection conn = DriverManager.getConnection(DATABASE_URL);
        String userLookup = String.format("SELECT * FROM users WHERE username = \"%s\";", username);
        PreparedStatement statement = conn.prepareStatement(userLookup);
        ResultSet userStats = statement.executeQuery();

        Map<String, Object> resultsHash = resultSetToList(userStats).get(0);

        conn.close();
        statement.close();
        userStats.close();

        return resultsHash;
    }

    /**
     * Helper method for logging in new user into the game, creating a new entry for the new user in the SQL database.
     */
    private void logInNewUser(String username, Connection conn) throws SQLException {
        String insertUserCommand = String.format("INSERT INTO users (username) VALUES(\"%s\")", username);
        PreparedStatement statement = conn.prepareStatement(insertUserCommand);
        statement.executeUpdate();
        statement.close();
    }

    /**
     * Helper method for logging in an existing user into the game, increasing num_games by 1 and updating last_login
     * to current timestamp for the user
     */
    private void logInExistingUser(String username, Connection conn) throws SQLException {
        String updateUserCommand = "UPDATE users\n" +
                "SET num_games = num_games + 1,\n" +
                "last_login = CURRENT_TIMESTAMP\n" +
                "WHERE\n" +
                String.format("username = \"%s\";", username);

        PreparedStatement statement = conn.prepareStatement(updateUserCommand);
        statement.executeUpdate();
        statement.close();
    }

    /**
     * Converts a ResultSet of a SQL query result to a list of hashmaps, with each hashmap being a row, with column names
     * as keys and column values as values.
     *
     * This function was copied from https://gist.github.com/cworks/4175942
     *
     */
    private List<Map<String, Object>> resultSetToList(ResultSet rs) throws SQLException {
        ResultSetMetaData md = rs.getMetaData();
        int columns = md.getColumnCount();
        List<Map<String, Object>> rows = new ArrayList<>();
        while (rs.next()){
            Map<String, Object> row = new HashMap<>(columns);
            for(int i = 1; i <= columns; ++i){
                row.put(md.getColumnName(i), rs.getObject(i));
            }
            rows.add(row);
        }
        return rows;
    }

    /*
    Struggling to write proper unit tests for this class, so I'm using this main method as a crude sanity check for my
    code.
     */
    public static void main(String[] args) {

    }
}
