import java.util.*;

public class GameBoardManager<playerMills> {
    //Stores the location of mills formed by both player
    public HashMap<Integer, Set> playerMills;

    // Creates and stores a GameBoard instance, and processes Player moves on the GameBoard
    private final GameBoard gb = new GameBoard();

    public GameBoardManager() {

        playerMills = new HashMap<>();
        //Stores the mills for player 1
        HashSet<List<String>> player1Mills = new HashSet<>();
        //Stores the mills for player 2
        HashSet<List<String>> player2Mills = new HashSet<>();
        playerMills.put(1, player1Mills);
        playerMills.put(2, player2Mills);
    }


    public String getGameBoardState() {
        return gb.toString();
    }

    private String getItemInGameBoard(String targetPosition) {
        return gb.getTokenAtPosition(targetPosition);
    }

    private void insertToken(String token, String targetPosition) {
        gb.setToken(token, targetPosition);
    }

    /**
     * Process a Player's request to place their token on the GameBoard.
     * If Player-requested move is invalid, InvalidMoveException will be thrown
     *
     * @param token string representing a player's token to be placed
     * @param targetPosition string representing coordinates in gameBoard (ex: A8, C4) to place token
     */
    public void processPlayerMove(String token, String targetPosition) throws InvalidPositionException {
        String itemAtPosition = getItemInGameBoard(targetPosition);
        if (! itemAtPosition.matches(GameBoard.EMPTY_SLOT_PATTERN)) {
            // targetPosition is already occupied by another token, or an invalid position on the GameBoard was given
            throw new InvalidPositionException();
        } else {
            // insert the new player token into the desired position in GameBoard
            insertToken(token, targetPosition);
        }
    }

    public int getPlayer1Houses(){ return playerMills.get(1).size();}

    public int getPlayer2Houses(){ return playerMills.get(2).size();}


    public boolean checkPhaseOneEnd() {
        // phase 1 ends when both players have put down all of their six tokens on the board
        return gb.getGameBoardCapacity() == 18; // AL: This strategy actually won't work
    }

    public void millAdder(String position, String[] mill) {
        if (getItemInGameBoard(position).equals("W")){ playerMills.get(1).add(mill);}
        else { playerMills.get(2).add(mill);}

    }

    public void checkHouse() {
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
     *Checks if the token being removed is valid and then remove it if it is valid. TODO: add check for token being removed only being non mill token unless no other tokens available
     * @param playerNumber: which player is removing a token from the opponent's tokens
     * @param position: target position
     */
    public void processPlayerRemove(int playerNumber, String position) throws InvalidPositionException {
        String itemAtPosition = getItemInGameBoard(position);
        if ((itemAtPosition.matches("B") || itemAtPosition.matches(GameBoard.EMPTY_SLOT_PATTERN)) && playerNumber==2) {
            // targetPosition is already occupied by another token, or an invalid position on the GameBoard was given
            throw new InvalidPositionException();
        }
        if ((itemAtPosition.matches("W") || itemAtPosition.matches(GameBoard.EMPTY_SLOT_PATTERN)) && playerNumber==1) {
            // targetPosition is already occupied by another token, or an invalid position on the GameBoard was given
            throw new InvalidPositionException();
        }
        else {
            // remove the player token into the desired position in GameBoard
            gb.removeToken(position);

        }
    }


    // TODO:
    // 3) create more specific exception classes
    // 4) improve checkHouse(requires bit of rework of gameboard numbering)
}
