/**
 * Class for initializing a new gameboard, and keeping track of current state of said gameboard
 */
public class GameBoardTracker {
    private final GameBoard gb = new GameBoard();
    public String getGameBoardState() { return gb.toString(); }
    public GameBoard getGameBoard() { return gb; }
}