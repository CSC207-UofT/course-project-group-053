package UseCases;

import Entity.GameBoard;
import Entity.Token;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Stores a gameboard and keeps track of where tokens are on the gameboard, tokens owned by each player, and where mills
 * are formed by each player
 */

public class GameBoardMap {

    // store and keep track of a GameBoard
    public GameBoard gameBoard = new GameBoard();

    //Keeps track of tokens stored at each position on the game board. Key is position and value is token.
    public HashMap<String, Token> tokenMap = new HashMap<>();

    //Keeps track of all positions a user has their tokens on. Key is username and value is array of positions.
    public HashMap<String, ArrayList<String>> playerTokens = new HashMap<>();

    /**
     * Return the GameBoard stored by GameBoardMap
     * @return GameBoard that is being used for this Nine Men Morris game
     */
    public GameBoard getGameBoard() { return gameBoard; }

    /**
     * Set gameBoard to a preconfigured GameBoard object
     * @param loadedBoard Saved GameBoard from a previous game
     */
    public void setGameBoard(GameBoard loadedBoard) {
        this.gameBoard = loadedBoard;
    }

    private void addTokenToMap(String position, Token token) {
        tokenMap.put(position, token);
    }

    private void addPlayerToken(String username, String position) {
        if (!playerTokens.containsKey(username)) {
            // initialize new key for player username
            playerTokens.put(username, new ArrayList<>());
        }
        // add position occupied by player
        playerTokens.get(username).add(position);
    }
}



