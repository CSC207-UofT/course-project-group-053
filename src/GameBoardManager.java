public class GameBoardManager {
    // Creates and stores a GameBoard instance, and processes Player moves on the GameBoard
    private final GameBoard gb = new GameBoard();

    public String getGameBoardState() {
        return gb.toString();
    }

    private String getItemInGameBoard(int boxNumber, int boxPosition) {
        return gb.getTokenAtPosition(boxNumber, boxPosition);
    }

    private void insertToken(String token, int boxNumber, int boxPosition) {
        gb.setToken(token, boxNumber, boxPosition);
    }

    /**
     * Process a Player's request to place their token on the GameBoard.
     * If Player-requested move is invalid, InvalidMoveException will be thrown
     *
     * @param token string representing a player's token to be placed
     * @param boxNumber int representing which box the token is to be placed (1 = outer, 2 = middle, 3 = inner)
     * @param boxPosition int representing index within box to place token
     */
    public void processPlayerMove(String token, int boxNumber, int boxPosition) {
        String itemAtPosition = getItemInGameBoard(boxNumber, boxPosition);
        if (!itemAtPosition.equals(GameBoard.EMPTY_GAMEBOARD_SLOT)) {
            // TODO: make this throw a custom Exception instead
            System.out.println("Cannot place token in slot occupied by another token");
        } else {
            // insert the new player token into the desired position in GameBoard
            insertToken(token, boxNumber, boxPosition);
        }
    }

    // place new method for end of phase 1
    // when all players are out of tokens, the game ends
    // check if players have max number of tokens on gameboard
}
