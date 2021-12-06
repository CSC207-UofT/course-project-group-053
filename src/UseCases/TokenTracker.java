
package UseCases;

import Entity.GameBoard;
import Entity.Token;
import Interfaces.Observer;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Set;

/**
 * Stores a gameboard and keeps track of where player tokens are on the gameboard.
 * Is used to store game progress and allow games to be saved/loaded
 */
public class TokenTracker implements Observer, Serializable {

    // store and keep track of a GameBoard
    private GameBoard gameBoard;

    // store all valid gameboard positions
    private final Set<String> gbPositions;

    // keeps track of where player tokens are on the gameboard
    private final HashMap<String, Token> playerTokenMap;

    public TokenTracker() {
        gameBoard = new GameBoard();
        gbPositions = gameBoard.getGameBoardPositions();
        playerTokenMap = new HashMap<>();
        Set<String> gameBoardPositions = gameBoard.getGameBoardPositions();
        for (String position : gameBoardPositions) {
            // initialize all positions as null, until tokens are placed in them
            playerTokenMap.put(position, null);
        }
    }

    /**
     * Return the GameBoard stored by TokenTracker
     *
     * @return GameBoard that is being used for this Nine Men Morris game
     */
    public GameBoard getGameBoard() {
        return gameBoard;
    }

    // TODO - maybe can stop saving gameboards, and instead only have to save tracker, since tracker stores gameboard
    /**
     * Set gameBoard to a preconfigured GameBoard object
     *
     * @param loadedBoard Saved GameBoard from a previous game
     */
    public void setGameBoard(GameBoard loadedBoard) {
        this.gameBoard = loadedBoard;
    }

    /**
     * Return a set of all legal positions in the gameboard
     * @return Set of legal positions, as strings
     */
    public Set<String> getGameBoardPositions() {
        return gbPositions;
    }

    /**
     * Return whether player is trying to remove one of their own tokens
     *
     * @param username String for username of the player making the move
     * @param position Position on gameboard where player is trying to remove a token
     * @return boolean True if specified position has one of player's own token
     */
    public boolean isSelfToken(String username, String position) {
        Token tokenAtPosition = playerTokenMap.get(position);

        if (tokenAtPosition == null) {
            // no token placed at position, so cannot be a self token
            return false;
        } else {
            return tokenAtPosition.getPlayer().equals(username);
        }
    }

    /**
     * Retrieves a Token from a specified gameboard position. Returns null if no Token is placed there.
     * @param position GameBoard coordinates, in form [ABC][1-8
     * @return Token that was placed at position in gameboard, or null
     */
    public Token getToken(String position) { return playerTokenMap.get(position); }

    @Override
    // player added token to gameboard
    public void update(String position, Token playerToken) {
        // add token to token map
        // add token for player in player tokens
        addTokenMap(position, playerToken);
    }

    private void addTokenMap(String position, Token playerToken) {
        playerTokenMap.put(position, playerToken);
    }

    @Override
    // player removed token from gameboard
    public void update(String position) {
        removeTokenMap(position);
    }

    private void removeTokenMap(String position) {
        // remove token from position in playerTokenMap by setting associated value to null
        playerTokenMap.put(position, null);
    }
}