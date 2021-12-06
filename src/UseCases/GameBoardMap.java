package UseCases;

import Entity.Token;

import javax.lang.model.type.NullType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Keeps track of all the token information with respect to the current game board.
 */

public class GameBoardMap {

    //private Entity.GameBoard gameBoard
    //private Entity.Token token;

    //Keeps track of tokens stored at each position on the game board. Key is position and value is token.
    public HashMap<String, Token> token_placement = new HashMap<String, Token>();

    //Keeps track of all positions a user has their tokens on. Key is username and value is array of positions.
    public HashMap<String, ArrayList<String>> username_positions = new HashMap<String, ArrayList<String>>();

    //Keeps track of mills formed by the players on the current game board. Key is username and value is array
    //with three values for where the mills are made in terms of position.
    public HashMap<String, ArrayList<String>> username_mill_positions = new HashMap<String, ArrayList<String>>();
}

    //public UseCases.GameBoardMap() {
        //this.gameBoard = new Entity.GameBoard();
        //this.token = new Entity.Token();

        // Once we get the token in Entity.GameBoard to be an instance of the Entity.Token Class, then the
        // following code can be implemented.

        //for(Map.Entry<String, String> entry: gameBoard.gameBoard.entrySet()) {
            //if(entry.getValue() != null); {
                //token = gameBoard.getTokenAtPosition(entry.getKey());
                //username_positions.put(token.owner, entry.getValue())

