public class GameBoardManager {
    // Creates and stores a GameBoard instance, and processes Player moves on the GameBoard
    private final GameBoard gb = new GameBoard();

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

    public boolean checkPhaseOneEnd() {
        // phase 1 ends when both players have put down all of their six tokens on the board
        return gb.getGameBoardCapacity() == 0; // AL: we want the player to place all of their tokens
    }

    // TODO:
    // 1) figure out check houses method
    // 2) keep track of player tokens
    // 3) create more specific exception classes
}
