import java.text.MessageFormat;
import java.util.Arrays;

public class GameBoard {
    // Defines the game board used for Nine Men Morris, which holds tokens from each player
    private String[][] gameBoard;
    public static String EMPTY_GAMEBOARD_SLOT = "X";

    /**
     * Initializes an empty Nine Men Morris gameboard.
     *
     * Gameboard layout:
     *
     *
     *
     */
    public GameBoard() {
        // set up arrays representing each "box" in the Nine Men Morris gameboard
        String[] outerBox = new String[8];
        String[] middleBox = new String[8];
        String[] innerBox = new String[8];

        // fill each array with 8 empty slots, representing places where tokens can be placed in each box
        Arrays.fill(outerBox, GameBoard.EMPTY_GAMEBOARD_SLOT);
        Arrays.fill(middleBox, GameBoard.EMPTY_GAMEBOARD_SLOT);
        Arrays.fill(innerBox, GameBoard.EMPTY_GAMEBOARD_SLOT);

        gameBoard = new String[][]{outerBox, middleBox, innerBox};
    }

    public static void main(String[] args) {
        // sample main method, in case you want to see how the board looks like
        GameBoard gb = new GameBoard();
        System.out.println(gb);
        System.out.println("---------------------------------------------------------------");
        System.out.println(gb.getTokenAtPosition(2, 8));
        gb.setToken("A", 2, 4);
        System.out.println(gb.getTokenAtPosition(2, 4));
        System.out.println("---------------------------------------------------------------");
        System.out.println(gb);
        gb.removeToken(2, 4);
        System.out.println("---------------------------------------------------------------");
        System.out.println(gb);
    }

    @Override
    public String toString() {
        // create array of string values of tokens in each box in gameBoard
        String[] gameBoardTokens = new String[24];

        int i = 0;
        for (String[] box: gameBoard) {
            for (String boxItem: box) {
                gameBoardTokens[i] = boxItem;
                i++;
            }
        }

        // This string is merked af rn
        return MessageFormat.format(
                "{0}\t\t\t\t\t{1}\t\t\t\t\t{2}\n" +
                "\n\n\n" +
                "\t\t{8}\t\t\t{9}\t\t\t{10}\n\n\n" +
                "\t\t\t\t{16}\t{17}\t{18}\n\n\n" +
                "{3}\t\t{11}\t\t{19}\t\t{20}\t\t{12}\t\t{4}\n\n\n" +
                "\t\t\t\t{21}\t{22}\t{23}\n\n\n" +
                "\t\t{13}\t\t\t{14}\t\t\t{15}\n\n\n\n" +
                "{5}\t\t\t\t\t{6}\t\t\t\t\t{7}",
                gameBoardTokens);
    }

    /**
     * Place a Player's token in a specified box and box position in GameBoard
     *
     * @param token string representing the colored token the player will place on the GameBoard
     * @param boxNumber int representing which GameBoard box the token belongs to (1 = outer box, 2 = middle, 3 = inner)
     * @param boxPosition int representing which index within the box the token is to be placed
     */
    public void setToken(String token, int boxNumber, int boxPosition) {
        // stuff TODO
        // 1) in GameBoardManager/Placer/etc classes, raise error when token isn't valid
        // 2) in Gameboard/Placer/etc classes, raise error when boxNumber or boxPosition is invalid
        gameBoard[boxNumber - 1][boxPosition - 1] = token;
    }

    /**
     * Remove a Player's token from a specified box and box position in GameBoard
     *
     * @param boxNumber int representing which GameBoard box the token belongs to (1 = outer box, 2 = middle, 3 = inner)
     * @param boxPosition int representing which index within the box the token is to be placed
     */
    public void removeToken(int boxNumber, int boxPosition) {
        // remove a token at specified box number and box position, by setting it to emptyGameBoardSlot string
        // offset by 1 to account for zero indexing
        gameBoard[boxNumber - 1][boxPosition - 1] = GameBoard.EMPTY_GAMEBOARD_SLOT;
    }

    /**
     * Retrieve the string of the player token placed in a particular box, at a particular position in GameBoard.
     * Return EMPTY_GAMEBOARD_SLOT if no player token is placed at specified location
     *
     * @param boxNumber int representing which GameBoard box the token belongs to (1 = outer box, 2 = middle, 3 = inner)
     * @param boxPosition int representing which index within the box the token is to be placed
     */
    public String getTokenAtPosition(int boxNumber, int boxPosition) {
        // returns the string of the token at box number and position within box
        // if no token has been placed at specified location in gameBoard, emptyGameBoardSlot will be returned
        return gameBoard[boxNumber - 1][boxPosition - 1];
    }
}
