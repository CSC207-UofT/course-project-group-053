package Interfaces;
import Entity.Token;

public interface Observer {
    /**
     * Update after player adds token to gameboard, assuming valid token placement
     * @param position gameboard position, in form [ABC][1-8], that token was placed in
     * @param playerToken Token that was placed
     */
    void update(String position, Token playerToken);

    /**
     * Update after player removes token from gameboard, assuming valid token removal
     * @param position gameboard position from which a Token was removed
     */
    void update(String position);
}