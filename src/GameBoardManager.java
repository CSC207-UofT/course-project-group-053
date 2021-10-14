import java.util.*;

public class GameBoardManager {
    // Creates and stores a GameBoard instance, and processes Player moves on the GameBoard
    private final GameBoard gb = new GameBoard();

    // Stores the positions of each player's tokens on the gameboard
    public HashMap<Integer, Set<String>> playerTokens;

    // Stores the location of mills formed by both players
    public HashMap<Integer, Set> playerMills;

    // Hash to map player colors to player numbers
    // white ("W" token) = player 1, black ("B" token) = player 2, as defined in Main.java
    private Map<String, Integer> playerColorsToNumbers = Map.of("W", 1, "B", 2);

    public GameBoardManager() {
        // initialize playerTokens
        playerTokens = new HashMap<Integer, Set<String>>();
        HashSet<String> player1Tokens = new HashSet<>();  // initialize set of each player's tokens as empty sets
        HashSet<String> player2Tokens = new HashSet<>();
        playerTokens.put(1, player1Tokens);  // map player numbers to their token sets
        playerTokens.put(2, player2Tokens);

        // initialize playerMills to keep track of mills formed by each player
        playerMills = new HashMap<>();

        // Stores the mills for player 1
        // represent player mills as list of strings (ex: A1 A2 A3), and store the lists in the HashSets
        HashSet<List<String>> player1Mills = new HashSet<>();

        // Stores the mills for player 2
        HashSet<List<String>> player2Mills = new HashSet<>();

        // playerMills ends up as a hashmap, mapping player number to HashSets containing each player's mills
        playerMills.put(1, player1Mills);
        playerMills.put(2, player2Mills);
    }

    // PRIVATE HELPER METHODS - CAN IGNORE

    /**
     * Private helper to return which player number owns a token, based on token's color
     * @param token a String being either "W" (white, p1) or "B" (black, p2)
     * @return int for player number
     */
    private int getPlayerFromToken(String token) {
        return playerColorsToNumbers.get(token);
    }

    /**
     * Private helper to return string of the token occupying a specified position on the gameboard
     * @param targetPosition coordinates on the gameboard to retrieve a token from.
     *                       throw InvalidPositionException if the position is empty
     * @return String for token occupying targetPosition on gameboard
     * @throws NonexistentPositionException
     */
    private String getItemInGameBoard(String targetPosition) throws NonexistentPositionException {
        if (! targetPosition.matches(GameBoard.EMPTY_SLOT_PATTERN)) {
            // position given isn't formatted properly/doesn't exist on gameboard
            throw new NonexistentPositionException();
        } else {
            return gb.getTokenAtPosition(targetPosition);
        }
    }

    /**
     * Private helper for inserting token into specified position on gameboard
     * @param token string representing token to be inserted at target position
     * @param targetPosition coordinates on board to place the token
     */
    private void insertToken(String token, String targetPosition) {
        gb.setToken(token, targetPosition);

        // using value of token, update playerTokens entry for the player the token belongs to, to include this newly
        // added token for the player
        playerTokens.get(getPlayerFromToken(token)).add(targetPosition);
    }

    /**
     * Private helper for removing token from
     * @param otherPlayerNumber int for player number whose token is to be removed
     * @param targetPosition position of otherPlayerNumber's token to remove
     */
    private void removeOpponentToken(int otherPlayerNumber, String targetPosition) {
        gb.removeToken(targetPosition);

        // remove token from token set of otherPlayerNumber
        playerTokens.get(otherPlayerNumber).remove(targetPosition);
    }

    /**
     * Returns whether a particular position on the gameboard falls into a mill formed by a player
     * @param playerNumber player that we are checking whether the position falls into their mills
     * @param position string for position on the board to check
     * @return boolean indicating whether position falls into a player's mills
     */
    private boolean checkIfPositionInMill(int playerNumber, String position) {
        Set mills = playerMills.get(playerNumber);

        // iterate over mills in mills, and check if position occurs in any of the mills
        for (Object m: mills) {
            if (((List) m).contains(position)) {
                // found position in one of the player's mills
                return true;
            }
        }

        return false;
    }

    /**
     * Private helper to check whether all of a player's tokens are in mills
     * @param playerNumber int representing player number to check
     * @return boolean indicating whether player's tokens are all in a mill
     */
    private boolean checkAllTokensInMill(int playerNumber) {
        Set<String> playerTokenSet = playerTokens.get(playerNumber);

        for (String tokenPosition: playerTokenSet) {
            if (! checkIfPositionInMill(playerNumber, tokenPosition)) {
                // if any of player's tokens not in a mill, return false
                return false;
            }
        }

        // all of the player's tokens were in a mill
        return true;
    }

    // END OF PRIVATE HELPER METHODS

    //

    // PUBLIC METHODS

    public String getGameBoardState() {
        return gb.toString();
    }

    /**
     * Process a Player's request to place their token on the GameBoard.
     * If Player-requested move is invalid, InvalidMoveException will be thrown
     *
     * @param token string representing a player's token to be placed
     * @param targetPosition string representing coordinates in gameBoard (ex: A8, C4) to place token
     */
    public void processPlayerMove(String token, String targetPosition) throws InvalidPositionException {
        // if targetPosition was an invalid gameboard coordinate, getItemInGameBoard will throw NonexistentPositionException
        String itemAtPosition = getItemInGameBoard(targetPosition);

        // check if gameboard slot already occupied by another token
        if (! itemAtPosition.matches(GameBoard.EMPTY_SLOT_PATTERN)) {
            // child class of InvalidPositionException, so it works with method signature
            throw new OccupiedSlotException();

        } else {
            insertToken(token, targetPosition);
        }
    }

    public int getPlayer1Houses(){ return playerMills.get(1).size();}

    public int getPlayer2Houses(){ return playerMills.get(2).size();}

    public void millAdder(String position, String[] mill) throws InvalidPositionException {
        // checks which player the mill belongs to, and add the mill to the player's mills
        if (getItemInGameBoard(position).equals("W")) { playerMills.get(1).add(mill); }
        else { playerMills.get(2).add(mill);}

    }

    public void checkHouse() throws InvalidPositionException {
        if (getItemInGameBoard("A1").equals(getItemInGameBoard("A2")))
            if (getItemInGameBoard("A2").equals(getItemInGameBoard("A3")) && !getItemInGameBoard("A1").equals(GameBoard.EMPTY_SLOT_PATTERN)) {
                millAdder("A1", new String[]{"A1", "A2", "A3"});
            }
        if (getItemInGameBoard("B1").equals(getItemInGameBoard("B2")) && getItemInGameBoard("B2").equals(getItemInGameBoard("B3")) && !getItemInGameBoard("B1").equals(GameBoard.EMPTY_SLOT_PATTERN)){
            millAdder("B1", new String[] {"B1", "B2", "B3"});
        }
        if (getItemInGameBoard("C1").equals(getItemInGameBoard("C2")) && getItemInGameBoard("C2").equals(getItemInGameBoard("C3")) && !getItemInGameBoard("C1").equals(GameBoard.EMPTY_SLOT_PATTERN)){
            millAdder("C1", new String[] {"C1", "C2", "C3"});
        }
        if (getItemInGameBoard("A1").equals(getItemInGameBoard("A4")) && getItemInGameBoard("A4").equals(getItemInGameBoard("A6")) && !getItemInGameBoard("A1").equals(GameBoard.EMPTY_SLOT_PATTERN)){
            millAdder("A1", new String[] {"A1", "A4", "A6"});
        }
        if (getItemInGameBoard("B1").equals(getItemInGameBoard("B4")) && getItemInGameBoard("B4").equals(getItemInGameBoard("B6")) && !getItemInGameBoard("B1").equals(GameBoard.EMPTY_SLOT_PATTERN)){
            millAdder("B1", new String[] {"B1", "B4", "B6"});
        }
        if (getItemInGameBoard("C1").equals(getItemInGameBoard("C4")) && getItemInGameBoard("C4").equals(getItemInGameBoard("C6")) && !getItemInGameBoard("C1").equals(GameBoard.EMPTY_SLOT_PATTERN)){
            millAdder("C1", new String[] {"C1", "C4", "C6"});
        }
        if (getItemInGameBoard("A3").equals(getItemInGameBoard("A5")) && getItemInGameBoard("A5").equals(getItemInGameBoard("A8")) && !getItemInGameBoard("A3").equals(GameBoard.EMPTY_SLOT_PATTERN)){
            millAdder("A3", new String[] {"A3", "A5", "A8"});
        }
        if (getItemInGameBoard("B3").equals(getItemInGameBoard("B5")) && getItemInGameBoard("B5").equals(getItemInGameBoard("B8")) && !getItemInGameBoard("B3").equals(GameBoard.EMPTY_SLOT_PATTERN)){
            millAdder("B3", new String[] {"B3", "B5", "B8"});
        }
        if (getItemInGameBoard("C3").equals(getItemInGameBoard("C5")) && getItemInGameBoard("C5").equals(getItemInGameBoard("C8")) && !getItemInGameBoard("C3").equals(GameBoard.EMPTY_SLOT_PATTERN)){
            millAdder("C1", new String[] {"C3", "C5", "C8"});
        }
        if (getItemInGameBoard("A6").equals(getItemInGameBoard("A7")) && getItemInGameBoard("A7").equals(getItemInGameBoard("A8")) && !getItemInGameBoard("A6").equals(GameBoard.EMPTY_SLOT_PATTERN)){
            millAdder("A6", new String[] {"A6", "A7", "A8"});
        }
        if (getItemInGameBoard("B6").equals(getItemInGameBoard("B7")) && getItemInGameBoard("B7").equals(getItemInGameBoard("B8")) && !getItemInGameBoard("B6").equals(GameBoard.EMPTY_SLOT_PATTERN)){
            millAdder("B6", new String[] {"B6", "B7", "B8"});
        }
        if (getItemInGameBoard("C6").equals(getItemInGameBoard("C7")) && getItemInGameBoard("C7").equals(getItemInGameBoard("C8")) && !getItemInGameBoard("C6").equals(GameBoard.EMPTY_SLOT_PATTERN)){
            millAdder("C6", new String[] {"C6", "C7", "C8"});
        }

    }

    /***
     *Checks if the token being removed is valid and then remove it if it is valid.
     * TODO: add check for token being removed only being non mill token unless no other tokens available
     * @param playerNumber: int representing the player (1 or 3) requesting to remove a token
     * @param position: coordinate (in format [A-C][1-8]) on gameboard to remove token from
     */
    public void processPlayerRemove(int playerNumber, String position) throws InvalidPositionException,
            InvalidRemovalException {
        // if an invalid gameboard position was given, getItemInGameBoard will throw InvalidPositionException
        String itemAtPosition = getItemInGameBoard(position);

        // determine number of playerNumber's opponent
        int otherPlayerNumber;
        if (playerNumber == 1) {
            otherPlayerNumber = 2;
        } else {
            otherPlayerNumber = 1;
        }

        // cannot remove token from empty/unoccupied gameboard slot
        if (itemAtPosition.matches(GameBoard.EMPTY_SLOT_PATTERN)) {
            throw new RemoveEmptySlotException();
        }

        // player cannot remove own token from the gameboard
        else if (playerColorsToNumbers.get(getItemInGameBoard(position)).equals(playerNumber)) {
            throw new RemoveSelfTokenException();
        }

        // player cannot remove opponents token if it's in a mill, and the opponent has tokens outside of mills
        else if (checkIfPositionInMill(otherPlayerNumber, position) && ! checkAllTokensInMill(otherPlayerNumber)) {
            throw new RemoveMillException();
        }

        // valid gameboard position was specified by player, occupied by an opponent's token, so go ahead and remove
        // the token
        else {
            removeOpponentToken(otherPlayerNumber, position);
        }
    }
}
