import java.text.MessageFormat;
import java.util.HashMap;

public class GameBoard {
    // Defines the game board used for Nine Men Morris, which holds tokens from each player
    private final HashMap<String, String[]> gameBoard;

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
        // set up hash table, with keys as boxes (A = outer box, B = middle box, C = inner box) and values as
        // arrays representing slots in each box
        HashMap<String, String[]> gbHash = new HashMap<>();
        gbHash.put("A", new String[8]);  // A = outer box
        gbHash.put("B", new String[8]);  // B = middle box
        gbHash.put("C", new String[8]);  // C = inner box

        // populate each array for key in hash table, with items [LETTER][1-8]
        for (String key: gbHash.keySet()) {
            String[] boxArr = gbHash.get(key);
            for (int i = 1; i <= boxArr.length; i++) {
                // -1 offset for boxArr indexing, to account for array indexing starting from zero
                boxArr[i - 1] = key + i;
            }
        }

        gameBoard = gbHash;
        gameBoardCapacity = 24;
    }

    public static void main(String[] args) {
        // sample main method, in case you want to see how the board looks like
        GameBoard gb = new GameBoard();
        System.out.println(gb);
        System.out.println("---------------------------------------------------------------");
        System.out.println(gb.getTokenAtPosition("B8"));
        gb.setToken("B", "B4");
        System.out.println(gb.getTokenAtPosition("B4"));
        System.out.println("---------------------------------------------------------------");
        System.out.println(gb);
        gb.removeToken("B4");
        System.out.println("---------------------------------------------------------------");
        System.out.println(gb);
    }

    @Override
    public String toString() {
        // create array of string values of tokens in each box in gameBoard
        String[] gameBoardTokens = new String[24];

        // populate array with all tokens in gameBoard
        int i = 0;
        for (String key: gameBoard.keySet()) {
            String[] boxArr = gameBoard.get(key);
            for (String boxItem: boxArr) {
                gameBoardTokens[i] = boxItem;
                i++;
            }
        }

        return MessageFormat.format(
                "{0}----------------------{1}----------------------{2}\n"+
                        "|                                                |\n"+
                "|                                                |\n"+
                "|     {8}----------------{9}----------------{10}     |\n"+
                "|     |                 |                  |     |\n"+
                "|     |                 |                  |     |\n"+
                "|     |     {16}----------{17}----------{18}     |     |\n"+
                "|     |     |                        |     |     |\n"+
        "{3}    {11}    {19}                      {20}    {12}     {4}\n"+
                "|     |     |                        |     |     |\n"+
                "|     |     {21}----------{22}----------{23}     |     |\n"+
                "|     |                 |                  |     |\n"+
                "|     |                 |                  |     |\n"+
                "|     {13}----------------{14}----------------{15}     |\n"+
                "|                                                |\n"+
                "|                                                |\n"+
        "{5}----------------------{6}----------------------{7}\n",
                gameBoardTokens);
    }

    private String[] splitCoordinates(String targetPosition) {
        return targetPosition.split("(?<=\\D)(?=\\d+\\b)");
    }

    /**
     * Place a Player's token in a specified box and box position in GameBoard
     *
     * @param token string representing the colored token the player will place on the GameBoard
     * @param targetPosition string representing coordinates in gameBoard (ex: A8, C4) to place token
     */
    public void setToken(String token, String targetPosition) {
        // stuff TODO

        // split targetPosition string into letter and integer index within box
        String[] coordinates = splitCoordinates(targetPosition);

        // get array of box elements corresponding to box of specified letter
        // then, set token to given numerical index in box
        // offset index within box by 1, to account for indexing starting from zero
        gameBoard.get(coordinates[0])[Integer.parseInt(coordinates[1]) - 1] = token;
        gameBoardCapacity--;
    }

    /**
     * Remove a Player's token from a specified box and box position in GameBoard
     *
     * @param targetPosition string representing coordinates in gameBoard (ex: A8, C4) to place token
     *
     */
    public void removeToken(String targetPosition) {
        // split targetPosition string into letter and integer index within box
        String[] coordinates = splitCoordinates(targetPosition);

        // get array of box elements corresponding to box of specified letter
        // then, set item in box back to default coordinate values (i.e: the slot is now free)
        // offset index within box by 1, to account for indexing starting from zero
        gameBoard.get(coordinates[0])[Integer.parseInt(coordinates[1]) - 1] = targetPosition;
        gameBoardCapacity++;
    }

    /**
     * Retrieve the string of the player token placed in a particular box, at a particular position in GameBoard.
     * Return EMPTY_GAMEBOARD_SLOT if no player token is placed at specified location
     *
     * @param targetPosition string representing coordinates in gameBoard (ex: A8, C4) to place token
     *
     */
    public String getTokenAtPosition(String targetPosition) {
        // split targetPosition string into letter and integer index within box
        String[] coordinates = splitCoordinates(targetPosition);

        return gameBoard.get(coordinates[0])[Integer.parseInt(coordinates[1]) - 1];
    }

    /**
     * Returns the current number of empty slots on the GameBoard, an int between 0 - 24
     *
     * @return int representing number of empty slots on GameBoard
     */
    public int getGameBoardCapacity() { return gameBoardCapacity; }
}
