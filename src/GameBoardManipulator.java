import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Initializes and stores an instance of GameBoard, processing requests to place and remove tokens from the GameBoard.
 */
// This class is both a Facade and a Subject class (as part of Observer design pattern)
public class GameBoardManipulator {
    // Design GameBoardManipulator as a facade class for manipulating tokens on a GameBoard (add, remove, slide tokens)
    private GameBoard gameboard;
    private final GameBoardPlacer placer;
    private final GameBoardRemover remover;
    private final CheckMill millChecker;

    public GameBoardManipulator(GameBoardPlacer placer, GameBoardRemover remover,
                                CheckMill millChecker) {
        this.gameboard = new GameBoard();
        this.placer = placer;
        this.remover = remover;
        this.millChecker = millChecker;
    }

    public void placeToken(Token token, String position) throws OccupiedSlotException,
            NonexistentPositionException {
        // TODO: make sure exceptions are caught properly upstream
        placer.place(gameboard, token, position);
    }

    public void removeToken(String position, String playerColor) throws RemoveEmptySlotException,
            InvalidPositionException, RemoveMillException {
        // TODO: make sure exceptions are caught properly upstream
        // check for remove self token first
        if (!millChecker.checkMill2(position, playerColor, gameboard)) {
            remover.remove(gameboard, position);
        } else {
            throw new RemoveMillException();
        }
    }

    public GameBoard getGameboard() { return this.gameboard; }

    public void setGameboard(GameBoard loadedBoard) { this.gameboard = loadedBoard; }

    public ArrayList<String> getKeys() { return ((ArrayList<String>)gameboard.gameBoard.keySet()); }

    public String getCorrespondendValue(String key) { return gameboard.gameBoard.get(key); }
}