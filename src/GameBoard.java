import java.text.MessageFormat;
import java.util.Arrays;

public class GameBoard {
    // Defines the game board used for Nine Men Morris, which holds tokens from each player
    private String[][] gameBoard;
    public static String emptyGameBoardSlot = "[]";

    public GameBoard() {
        // set up arrays representing each "box" in the Nine Men Morris gameboard
        String[] outerBox = new String[8];
        String[] middleBox = new String[8];
        String[] innerBox = new String[8];

        // fill each array with 8 empty slots, representing places where tokens can be placed in each box
        Arrays.fill(outerBox, GameBoard.emptyGameBoardSlot);
        Arrays.fill(middleBox, GameBoard.emptyGameBoardSlot);
        Arrays.fill(innerBox, GameBoard.emptyGameBoardSlot);

        gameBoard = new String[][]{outerBox, middleBox, innerBox};
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

        return MessageFormat.format("""
                {0} ------------------ {1} ------------------ {2}
                |                      |                      |
                |       {8} ---------- {9} ---------  {10}    |
                |       |              |               |      |
                |       |       {16} - {17} - {18}     |      |
                |       |       |             |        |      |
                {3} --- {11} -- {19}          {20} -- {12} -- {4}
                |       |       |             |       |       |
                |       |       {21} - {22} - {23}    |       |
                |       |              |              |       |
                |       {13} --------- {14} --------- {15}    |
                |                      |                      |
                {5} ------------------ {6} ------------------ {7}
                """, gameBoardTokens);
    }

    public void setToken(String token, int boxNumber, int boxPosition) {
        // token: string for token to put on board
        // boxNumber: 1 - 3, 1 = outer box, 2 = middle box, 3 = inner box
        // boxPosition: position within box to place token

        // stuff TODO
        // 1) in GameBoardManager/Placer/etc classes, raise error when token isn't valid
        // 2) in Gameboard/Placer/etc classes, raise error when boxNumber or boxPosition is invalid
        gameBoard[boxNumber][boxPosition] = token;
    }

    public void removeToken(int boxNumber, int boxPosition) {
        // remove a token at specified box number and box position, by setting it to emptyGameBoardSlot string
        gameBoard[boxNumber][boxPosition] = GameBoard.emptyGameBoardSlot;
    }

    public String getTokenAtPosition(int boxNumber, int boxPosition) {
        // returns the string of the token at box number and position within box
        // if no token has been placed at specified location in gameBoard, emptyGameBoardSlot will be returned
        return gameBoard[boxNumber][boxPosition];
    }
}
