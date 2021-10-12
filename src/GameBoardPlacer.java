public class GameBoardPlacer {
    // collection of static methods for manipulating GameBoard by adding/removing tokens
    // use class for GameBoard, used in conjunction with GameBoardManager?

    /**
     * Places a Player's token at a location specified by box number and box position, within a gameboard
     *
     * @param gb a GameBoard instance for Player's to play Nine Men Morris
     * @param token string representing the colored token the player will place on the GameBoard
     * @param targetPosition string representing coordinates in gameBoard (ex: A8, C4) to place token
     */
    public static void placePlayerToken(GameBoard gb, String token, String targetPosition) {
        // check if value stored at targetPosition in gameboard matches empty slot pattern
        if (!gb.getTokenAtPosition(targetPosition).matches(GameBoard.EMPTY_SLOT_PATTERN)) {
            gb.setToken(token, targetPosition);

        } else {  // cannot place token at occupied slot on GameBoard
            // TODO - have this raise an exception (GameBoardSlotOccupied exception?)
            System.out.println("can't place token at occupied slot");
        }
    }

    /**
     * Removes a Player's token from a location specified by box number and box position, within a gameboard
     *
     * @param gb a GameBoard instance for Player's to play Nine Men Morris
     * @param targetPosition string representing coordinates in gameBoard (ex: A8, C4) to place token
     */
    public static void removePlayerToken(GameBoard gb, String targetPosition) {
        if (gb.getTokenAtPosition(targetPosition).matches(GameBoard.EMPTY_SLOT_PATTERN)) {
            // TODO - have this raise exception (RemoveEmptySlot exception?)
            System.out.println("cannot remove token from empty slot");

        } else {
            gb.removeToken(targetPosition);
        }
    }


}
