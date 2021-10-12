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
    public void processPlayerMove(String token, String targetPosition) {
        String itemAtPosition = getItemInGameBoard(targetPosition);
        if (!itemAtPosition.matches(GameBoard.EMPTY_SLOT_PATTERN)) {
            // TODO: make this throw a custom Exception instead
            System.out.println("Cannot place token in slot occupied by another token");
        } else {
            // insert the new player token into the desired position in GameBoard
            insertToken(token, targetPosition);
        }
    }

    // place new method for end of phase 1
    // when all players are out of tokens, the game ends
    // check if players have max number of tokens on gameboard
}
