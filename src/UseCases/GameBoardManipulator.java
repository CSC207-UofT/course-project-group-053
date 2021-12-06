package UseCases;

import Entity.GameBoard;
import Entity.Token;
import Exceptions.*;

/**
 * Initializes and stores an instance of Entity.GameBoard, processing requests to place and remove tokens from the Entity.GameBoard.
 */
// This class is both a Facade and a Interfaces.Subject class (as part of Interfaces.Observer design pattern)
public class GameBoardManipulator {
    // Design UseCases.GameBoardManipulator as a facade class for manipulating tokens on a Entity.GameBoard (add, remove, slide tokens)
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

    public void setGameboard(GameBoard loadedBoard) {this.gameboard = loadedBoard;}
}