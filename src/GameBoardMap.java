import javax.lang.model.type.NullType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Keeps track of all the token information with respect to the current game board.
 */

public class GameBoardMap {

    //Keeps track of tokens stored at each position on the game board. Key is position and value is token.
    public HashMap<String, String> token_placement =  new HashMap<String, String>();

    //Keeps track of all positions a user has their tokens on. Key is username and value is array of positions.
    public HashMap<String, ArrayList<String>> username_positions = new HashMap<String, ArrayList<String>>();




    //Keeps track of mills formed by the players on the current game board. Key is username and value is array
    //with three values for where the mills are made in terms of position.
    public HashMap<String, ArrayList<String>>  username_mill_positions = new HashMap<String, ArrayList<String>>();

}
