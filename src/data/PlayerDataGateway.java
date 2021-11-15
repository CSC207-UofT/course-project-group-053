package data;

import java.sql.*;

public class PlayerDataGateway{
    public static String DATABASE_URL = "jdbc:sqlite:src/data/users_test.db";

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
     * Retrieves a hashmap, representing all columns of data for a user in the database.
     * Precondition: username is already registered in the database
     * @param username String for username of the user of interest
     * @throws SQLException Something sketchy happened with the SQL commands
     */
    public void getUserStats(String username) throws SQLException {
        // open connection to database
        // create statement object
        // run query to get ResultSet for user entry in database
        // return hashmap of user data
        Connection conn = DriverManager.getConnection(DATABASE_URL);
        String userLookup = String.format("SELECT * FROM users WHERE username \"%s\";", username);
        PreparedStatement statement = conn.prepareStatement(userLookup);
        ResultSet userStats = statement.executeQuery();

        conn.close();
        statement.close();
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

    /*
    Struggling to write proper unit tests for this class, so I'm using this main method as a crude sanity check for my
    code.
     */
    public static void main(String[] args) {
        // setting up test database
        try {
            Connection conn = DriverManager.getConnection(DATABASE_URL);
            Statement statement = conn.createStatement();
            String setUpCommand = "CREATE TABLE users (\n" +
                "username TEXT NOT NULL PRIMARY KEY,\n" +
                "num_games INTEGER NOT NULL DEFAULT 1,\n" +
                "num_wins INTEGER NOT NULL DEFAULT 0,\n" +
                "win_rate REAL NOT NULL DEFAULT 0.0,\n" +
                "last_login DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP\n" +
                ");";
            statement.executeUpdate(setUpCommand);
            conn.close();
            statement.close();

        } catch (SQLException e) {
            System.out.print(e.getMessage());
        }


        // try logging in new and existing users
        PlayerDataGateway gateway = new PlayerDataGateway();

        try {
            gateway.logInUser("bruh");
            gateway.logInUser("joe");
            gateway.logInUser("bruh");
            gateway.logInUser("bruh");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


        // try updating player stats after wins and losses of games
        try {
            gateway.updateUserAfterGame("bruh", false);  // win rate still 0
            gateway.updateUserAfterGame("bruh", true);  // win rate now 1/3
            gateway.updateUserAfterGame("bruh", true);  // win rate now 2/3
            gateway.updateUserAfterGame("joe", true);  // win rate is 1

            gateway.logInUser("joe");
            gateway.updateUserAfterGame("joe", false);  // win rate is now  1/2

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        // reset testing database
        try {
            Connection conn = DriverManager.getConnection(DATABASE_URL);
            PreparedStatement statement = conn.prepareStatement("DROP TABLE users;");
            statement.executeUpdate();
            conn.close();
            statement.close();

        } catch (SQLException e) {
            System.out.print(e.getMessage());
        }
    }
}
