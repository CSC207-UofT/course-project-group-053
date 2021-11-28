package UseCases;

import Entity.GameBoard;
import Entity.Token;
import Interfaces.Observer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * Stores a gameboard and keeps track of where tokens are on the gameboard, tokens owned by each player, and where mills
 * are formed by each player
 */

public class TokenTracker implements Observer, Serializable {

    // store and keep track of a GameBoard
    private GameBoard gameBoard = new GameBoard();

    // keeps track of where player tokens are on the gameboard
    private final HashMap<String, Token> playerTokenMap = new HashMap<>();

    // keep track of positions occupied by each player
    private final HashMap<String, ArrayList<String>> playerPositions = new HashMap<>();

    public TokenTracker() {
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

    /**
     * Set gameBoard to a preconfigured GameBoard object
     *
     * @param loadedBoard Saved GameBoard from a previous game
     */
    public void setGameBoard(GameBoard loadedBoard) {
        this.gameBoard = loadedBoard;
    }

    /**
     * Return whether player is trying to remove one of their own tokens
     *
     * @param username String for username of the player making the move
     * @param position Position on gameboard where player is trying to remove a token
     * @return boolean that is true if specified position has player's own token
     */
    public boolean isSelfToken(String username, String position) {
        // NOTE TO SELF: make GameBoardRemover call this method later
        ArrayList<String> playerPositions = this.playerPositions.get(username);
        return playerPositions.contains(position);
    }

    @Override
    // add token
    public void update(String position, String username, Token playerToken) {
        // add token to token map
        // add token for player in player tokens
        addTokenMap(position, playerToken);
        addPlayerPosition(username, position);
    }

    private void addPlayerPosition(String username, String position) {
        if (!playerPositions.containsKey(username)) {
            // initialize new array list of occupied positions for this player
            playerPositions.put(username, new ArrayList<>());
        }
        // add newly occupied position by player to playerPositions
        playerPositions.get(username).add(position);
    }

    private void addTokenMap(String position, Token playerToken) {
        playerTokenMap.put(position, playerToken);
    }

    @Override
    // remove token
    public void update(String position, String username) {
        removeTokenMap(position);
        removePlayerPosition(username, position);
    }

    /* By this time this method will be called, both players will already be set as keys in playerPositions
     */
    private void removePlayerPosition(String username, String position) {
        Set<String> playerUsernames = playerPositions.keySet();

        for (String player: playerUsernames) {
            // remove the token from the opponent player
            if (!player.equals(username)) {
                // remove token from opponent
                playerPositions.get(player).remove(position);
                break;
            }
        }
    }

    private void removeTokenMap(String position) {
        // remove token from position in playerTokenMap by setting associated value to null
        playerTokenMap.put(position, null);
    }

    @Override
    // slide token
    public void update(String[] oldToNewPosition) {
        slideTokenMap(oldToNewPosition);
    }

    private void slideTokenMap(String[] oldToNewPosition) {
        String oldPosition = oldToNewPosition[0];
        String newPosition = oldToNewPosition[1];

        // remove old token from playerTokenMap, where it was before sliding
        Token oldToken = playerTokenMap.get(oldPosition);
        playerTokenMap.put(oldPosition, null);

        // move player token to new position after sliding
        playerTokenMap.put(newPosition, oldToken);
    }
}