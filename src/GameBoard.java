import java.io.Serializable;
import java.util.HashMap;
import java.util.Set;

public class GameBoard implements Serializable {
    // Defines the game board used for Nine Men Morris, which holds the strings representing tokens placed on the
    // gameboard
    public final HashMap<String, String> gameBoard;

    // keeps track of how many empty slots are currently on the gameboard
    private int gameBoardCapacity;

    // regex pattern for empty slots on board
    public static String EMPTY_SLOT_PATTERN = "[ABC][1-8]";

    /**
     * Initializes an empty Nine Men Morris gameboard.
     *
     * gameBoard is a hashmap, mapping coordinates to each position in each box of the gameboard
     *
     */
    public GameBoard() {
        // set up hash table, with keys as gameboard coordinates (ex: A1, C5, etc), and values as gameboard token
        // objects (of type T)
        HashMap<String, String> gbHash = new HashMap<>();
        String[] gbBoxes = {"A", "B", "C"};  // A = outer box, B = middle box, C = inner box

        for (String letter: gbBoxes) {
            // create keys for all gameboard coordinates, in form [ABC][1-8]
            for (int i = 1; i < 9; i++) {
                String coordinate = letter + i;
                gbHash.put(coordinate, null);
            }
        }

        gameBoard = gbHash;
        gameBoardCapacity = 24;
    }

    private void decreaseCapacity() { gameBoardCapacity++; }
    private void increaseCapacity() { gameBoardCapacity--; }

    /**
     * Place a Player's token in a specified box and box position in GameBoard
     *
     * @param token unique string representing a player's token to place on GameBoard
     * @param targetPosition string representing coordinates in gameBoard (ex: A8, C4) to place token
     */
    public void setToken(String token, String targetPosition) {
        // note: any use cases using setToken should ensure targetPosition is empty, before calling this method
        gameBoard.put(targetPosition, token);  // store string of token at target coordinate in gameboard
        decreaseCapacity();
    }

    /**
     * Remove a Player's token from a specified box and box position in GameBoard
     *
     * @param targetPosition string representing coordinates in gameBoard (ex: A8, C4) to place token
     *
     */
    public String removeToken(String targetPosition) {
        // note: any use cases using removeToken should ensure targetPosition is OCCUPIED, before calling this method
        String removedToken = getTokenAtPosition(targetPosition);
        gameBoard.put(targetPosition, null);  // remove token from target gameboard coordinate, by storing null
        increaseCapacity();
        return removedToken;  // return String of the token that was removed
    }

    /**
     * Retrieve the string of the token placed in a particular box, at a particular position in GameBoard.
     * Return null if there is not token stored at the specified position
     *
     * @param targetPosition string representing coordinates in gameBoard (ex: A8, C4) to retrieve token
     *
     */
    public String getTokenAtPosition(String targetPosition) {
        return gameBoard.get(targetPosition);
    }

    public int getGameBoardCapacity() {
        return gameBoardCapacity;
    }

}
