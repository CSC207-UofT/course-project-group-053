package Interfaces;
import Entity.Token;

public interface Subject {
    /**
     * Add an Observer for this Subject to update
     * @param o Observer object to be updated
     */
    void register(Observer o);

    /**
     * Update Observers about token being added to gameboard
     * @param position Gameboard position where token was added
     * @param playerToken Token that was added
     */
    void notifyObservers(String position, Token playerToken);

    /**
     * Update Observers about token being removed from gameboard
     * @param position Gameboard position from which token was removed
     */
    void notifyObservers(String position);
}