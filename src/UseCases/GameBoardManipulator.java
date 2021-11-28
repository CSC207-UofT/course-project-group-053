package UseCases;

import Entity.Token;
import Exceptions.*;
import Interfaces.Observer;
import Interfaces.Subject;

import java.util.ArrayList;

/**
 * Initializes and stores an instance of Entity.GameBoard, processing requests to place and remove tokens from the Entity.GameBoard.
 */
// This class is both a Facade and a Subject class (as part of the Observer design pattern)
public class GameBoardManipulator implements Subject {
    // Design UseCases.GameBoardManipulator as a facade class for manipulating tokens on a Entity.GameBoard (add, remove, slide tokens)
    private TokenTracker tokenTracker;
    private final GameBoardPlacer placer;
    private final GameBoardRemover remover;
    private final CheckMill millChecker;
    private final ArrayList<Observer> observers = new ArrayList<>();

    public GameBoardManipulator(TokenTracker tracker, GameBoardPlacer placer, GameBoardRemover remover,
                                CheckMill millChecker) {
        this.tokenTracker = tracker;
        this.placer = placer;
        this.remover = remover;
        this.millChecker = millChecker;
    }

    public void placeToken(String position, Token playerToken) throws OccupiedSlotException,
            NonexistentPositionException {
        String playerUserName = playerToken.getPlayer();
        placer.place(tokenTracker.getGameBoard(), playerToken, position);
        notifyObservers(position, playerUserName, playerToken);
    }

    // TODO - remove game logic from here, and put into remover class instead
    public void removeToken(String position, String playerUserName, String playerColor) throws RemoveEmptySlotException,
            InvalidPositionException, RemoveMillException, RemoveSelfTokenException {
        remover.remove(position, playerColor, playerUserName, tokenTracker, millChecker);
        notifyObservers(position, playerUserName);
    }

    public void slideToken(String[] oldToNewPositions) { notifyObservers(oldToNewPositions); }

    @Override
    public void register(Observer o) {
        observers.add(o);
    }

    @Override
    public void unregister(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers(String position, String username, Token playerToken) {
        for (Observer o : observers) {
            o.update(position, username, playerToken);
        }
    }

    @Override
    public void notifyObservers(String position, String username) {
        for (Observer o : observers) {
            o.update(position, username);
        }
    }

    @Override
    public void notifyObservers(String[] oldToNewPositions) {
        for (Observer o : observers) {
            o.update(oldToNewPositions);
        }
    }

    /**
     * Set token tracker to one from a previously saved Nine Men Morris game
     * @param tracker Saved TokenTracker from a previous game, so to restore previous game progress
     */
    public void setTokenTracker(TokenTracker tracker) { this.tokenTracker = tracker; }
}