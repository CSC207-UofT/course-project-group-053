import java.util.*;

public class GameBoardManager<playerMills> {
    // Stores the location of mills formed by both players
    public HashMap<Integer, Set> playerMills;

    // Creates and stores a GameBoard instance, and processes Player moves on the GameBoard
    private final GameBoard gb = new GameBoard();

    public GameBoardManager() {
        playerMills = new HashMap<>();

        // Stores the mills for player 1
        // represent player mills as list of strings (ex: A1 A2 A3), and store the mills in HashSet
        HashSet<List<String>> player1Mills = new HashSet<>();

        // Stores the mills for player 2
        HashSet<List<String>> player2Mills = new HashSet<>();

        // playerMills ends up as a hashmap, mapping player number to HashSets containing each player's mills
        playerMills.put(1, player1Mills);
        playerMills.put(2, player2Mills);
    }


    public String getGameBoardState() {
        return gb.toString();
    }

    private String getItemInGameBoard(String targetPosition) throws InvalidPositionException {
        if (! targetPosition.matches(GameBoard.EMPTY_SLOT_PATTERN)) {
            // position given isn't formatted properly/doesn't exist on gameboard
            throw new InvalidPositionException();
        } else {
            return gb.getTokenAtPosition(targetPosition);
        }
    }

    private void insertToken(String token, String targetPosition) {
        gb.setToken(token, targetPosition);
    }

    // private helper method for checking if a position occurs in a player's mills
    private boolean checkIfPositionInMill(int playerNumber, String position) {
        HashSet<List<String>> mills = (HashSet<List<String>>) playerMills.get(playerNumber);

        // iterate over mills in mills, and check if position occurs in any of the mills
        for (List<String> m: mills) {
            if (m.contains(position)) {
                // found position in one of the player's mills
                return true;
            }
        }

        return false;
    }

    /**
     * Process a Player's request to place their token on the GameBoard.
     * If Player-requested move is invalid, InvalidMoveException will be thrown
     *
     * @param token string representing a player's token to be placed
     * @param targetPosition string representing coordinates in gameBoard (ex: A8, C4) to place token
     */
    public void processPlayerMove(String token, String targetPosition) throws InvalidPositionException,
            OccupiedSlotException {
        // if targetPosition was an invalid gameboard coordinate, getItemInGameBoard will throw InvalidPositionException
        String itemAtPosition = getItemInGameBoard(targetPosition);

        // check if gameboard slot already occupied by another token
        if (! itemAtPosition.matches(GameBoard.EMPTY_SLOT_PATTERN)) {
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
    public void processPlayerRemove(int playerNumber, String otherColor, String position) throws InvalidPositionException {
        // if an invalid gameboard position was given, getItemInGameBoard will throw InvalidPositionException
        String itemAtPosition = getItemInGameBoard(position);

        // cannot remove token from empty/unoccupied gameboard slot
        if (itemAtPosition.matches(GameBoard.EMPTY_SLOT_PATTERN)) {
            throw new RemoveEmptySlotException();
        }

        // player cannot remove an opponent's token from the gameboard
        else if (! itemAtPosition.equals(otherColor)) {
            throw new RemoveSelfTokenException();
        }

        else if (checkIfPositionInMill(playerNumber, position)) {
            throw new RemoveMillException();
        }

        // valid gameboard position was specified by player, occupied by an opponent's token, so go ahead and remove
        // the token
        else {
            gb.removeToken(position);
        }
    }


    // TODO:
    // 3) create more specific exception classes
    // 4) improve checkHouse(requires bit of rework of gameboard numbering)
}
