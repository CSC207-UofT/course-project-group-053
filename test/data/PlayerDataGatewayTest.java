package data;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

class PlayerDataGatewayTest {

    @BeforeEach
    void setUp() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:src/data/test_db.db");
            Statement statement = conn.createStatement();
            String setUpCommand = "CREATE TABLE users (\n" +
                "username TEXT NOT NULL PRIMARY KEY,\n" +
                "num_games INTEGER NOT NULL DEFAULT 1,\n" +
                "num_wins INTEGER NOT NULL DEFAULT 0,\n" +
                "win_rate REAL NOT NULL DEFAULT 0.0,\n" +
                "last_login DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP\n" +
                ");";
            statement.executeQuery(setUpCommand);
            conn.close();
            statement.close();

        } catch (SQLException e) {
            System.out.print(e.getMessage());
        }
    }

    @AfterEach
    void tearDown() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:src/data/test_db.db");
            Statement statement = conn.createStatement();
            statement.executeQuery("DROP TABLE users;");

        } catch (SQLException e) {
            System.out.print(e.getMessage());
        }
    }

    @Test
    void logInUser() {
        // login new user and check database
        // login existing user and check database
        // login another new user
        PlayerDataGateway gateway = new PlayerDataGateway();
    }

    @Test
    void updateUserAfterGame() {
        // login 2 new users
        // run update with wonGame = true for one player
        // run update with wonGame = false for other player
    }
}