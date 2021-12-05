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
    private final GameBoardPlacer placer;
    private final GameBoardRemover remover;
    private final CheckMill millChecker;
    private final ArrayList<Observer> observers = new ArrayList<>();

    public GameBoardManipulator(GameBoardPlacer placer, GameBoardRemover remover,
                                CheckMill millChecker) {
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

    /**
     * Place a Token on a gameboard, as requested by a player
     * @param position Gameboard coordinates, in form [ABC][1-8], to place the token
     * @param playerToken Token that player wants to place
     * @param gb GameBoard for inserting the token into
     * @throws OccupiedSlotException If player tries to place token in non-empty position
     * @throws NonexistentPositionException If player specifies non-existent position in gameboard
     */
    public void placeToken(String position, Token playerToken, GameBoard gb) throws OccupiedSlotException,
            NonexistentPositionException {
        placer.place(gb, playerToken, position);
        notifyObservers(position, playerToken);
    }

    /**
     * Removes a Token from a specified position on a gameboard, as requested by a player
     * @param position Gameboard coordinates, in form [ABC][1-8], to remove a token from
     * @param playerUserName Username of the requesting player
     * @param playerColor Token color/string of the requesting player (i.e: "B" or "W")
     * @param tracker TokenTracker to check for self tokens a player tries to remove
     * @throws RemoveEmptySlotException If player tries to remove token from empty position
     * @throws InvalidPositionException If player tries to remove token from non-existent position
     * @throws RemoveMillException If player tries to remove token that belongs in an opponent's mill
     * @throws RemoveSelfTokenException If player tries to remove their own token
     */
    public void removeToken(String position, String playerUserName, String playerColor, TokenTracker tracker) throws RemoveEmptySlotException,
            InvalidPositionException, RemoveMillException, RemoveSelfTokenException {
        remover.remove(position, playerColor, playerUserName, tracker, millChecker);
        notifyObservers(position);
    }
}