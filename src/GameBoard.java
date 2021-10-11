import java.text.MessageFormat;
import java.util.Arrays;

public class GameBoard {
    // Defines the game board used for Nine Men Morris, which holds tokens from each player
    private String[][] gameBoard;
    public static String emptyGameBoardSlot = "X";

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

    public void setToken(String token, int boxNumber, int boxPosition) {
        // token: string for token to put on board
        // boxNumber: 1 - 3, 1 = outer box, 2 = middle box, 3 = inner box
        // boxPosition: position within box to place token

        // stuff TODO
        // 1) in GameBoardManager/Placer/etc classes, raise error when token isn't valid
        // 2) in Gameboard/Placer/etc classes, raise error when boxNumber or boxPosition is invalid
        gameBoard[boxNumber - 1][boxPosition - 1] = token;
    }

    public void removeToken(int boxNumber, int boxPosition) {
        // remove a token at specified box number and box position, by setting it to emptyGameBoardSlot string
        // offset by 1 to account for zero indexing
        gameBoard[boxNumber - 1][boxPosition - 1] = GameBoard.emptyGameBoardSlot;
    }

    public String getTokenAtPosition(int boxNumber, int boxPosition) {
        // returns the string of the token at box number and position within box
        // if no token has been placed at specified location in gameBoard, emptyGameBoardSlot will be returned
        return gameBoard[boxNumber - 1][boxPosition - 1];
    }
}
