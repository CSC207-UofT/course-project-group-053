package UseCases;

import Entity.GameBoard;
import Entity.Token;
import Exceptions.*;
import Interfaces.Observer;
import Interfaces.Subject;

import java.util.ArrayList;

/**
 * Processes requests to place and remove tokens from a GameBoard
 */
// This class is both a Facade and a Subject class (as part of the Observer design pattern)
public class GameBoardManipulator implements Subject {
    // Design UseCases.GameBoardManipulator as a facade class for manipulating tokens on a Entity.GameBoard (add, remove, slide tokens)
    private GameBoard gameboard;
    private final GameBoardPlacer placer;
    private final GameBoardRemover remover;
    private final CheckMill millChecker;
    private final ArrayList<Observer> observers = new ArrayList<>();

    public GameBoardManipulator(GameBoardPlacer placer, GameBoardRemover remover,
                                CheckMill millChecker) {
        this.gameboard = new GameBoard();
        this.placer = placer;
        this.remover = remover;
        this.millChecker = millChecker;
    }

    @Override
    public void register(Observer o) {
        observers.add(o);
    }

    public void unregister(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers(String position, Token playerToken) {
        for (Observer o : observers) {
            o.update(position, playerToken);
        }
    }

    @Override
    public void notifyObservers(String position) {
        for (Observer o : observers) {
            o.update(position);
        }
    }

    public void placeToken(String position, Token playerToken, GameBoard gb) throws OccupiedSlotException,
            NonexistentPositionException {
        placer.place(gb, playerToken, position);
        notifyObservers(position, playerToken);
    }

    public void removeToken(String position, String playerUserName, String playerColor, TokenTracker tracker) throws RemoveEmptySlotException,
            InvalidPositionException, RemoveMillException, RemoveSelfTokenException {
        remover.remove(position, playerColor, playerUserName, tracker, millChecker);
        notifyObservers(position);
    }
}