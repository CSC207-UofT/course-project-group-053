package data;

import java.sql.*;

/**
 * Test to see if SQLite and SQLite JDBC driver are working correctly.
 * This class has nothing to do with our project, but you can run it to see if SQLite works properly on your end.
 */
public class SQLiteTest {
    public static void connect() {
        Connection conn = null;
        try {
            // open connection to example sqlite database
            String url = "jdbc:sqlite:src/data/users_test.db";
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite established.");

            String sqlCommand = "SELECT * FROM users;";
            Statement statement = conn.createStatement();

            ResultSet result = statement.executeQuery(sqlCommand);
            ResultSetMetaData rsmd = result.getMetaData();

            // print the user table from users.db
            System.out.println("querying SELECT * FROM user\n---------------------------------------------------\n");
            int colNum = rsmd.getColumnCount();
            while (result.next()) {
                for (int i = 1; i <= colNum; i++) {
                    if (i > 1) System.out.print(",  ");
                    String columnValue = result.getString(i);
                    System.out.print(columnValue + " " + rsmd.getColumnName(i));
                }
                System.out.println("\n");
            }



        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        connect();
    }
}
