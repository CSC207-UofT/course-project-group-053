public class GameBoardPlacer {
    // collection of static methods for manipulating GameBoard by adding/removing tokens
    // use class for GameBoard, used in conjunction with GameBoardManager?

    /**
     * Places a Player's token at a location specified by box number and box position, within a gameboard
     *
     * @param gb a GameBoard instance for Player's to play Nine Men Morris
     * @param token string representing the colored token the player will place on the GameBoard
     * @param boxNumber int representing which GameBoard box the token belongs to (1 = outer box, 2 = middle, 3 = inner)
     * @param boxPosition int representing which index within the box the token is to be placed
     */
    public static void placePlayerToken(GameBoard gb, String token, int boxNumber, int boxPosition) {
        if (! gb.getTokenAtPosition(boxNumber, boxPosition).equals(GameBoard.EMPTY_GAMEBOARD_SLOT)) {
            gb.setToken(token, boxNumber, boxPosition);
        } else {  // cannot place token at occupied slot on GameBoard
            // TODO - have this raise an exception (GameBoardSlotOccupied exception?)
            System.out.println("can't place token at occupied slot");
        }
    }

    /**
     * Removes a Player's token from a location specified by box number and box position, within a gameboard
     *
     * @param gb a GameBoard instance for Player's to play Nine Men Morris
     * @param token string representing the colored token the player will place on the GameBoard
     * @param boxNumber int representing which GameBoard box the token belongs to (1 = outer box, 2 = middle, 3 = inner)
     * @param boxPosition int representing which index within the box the token is to be placed
     */
    public static void removePlayerToken(GameBoard gb, String token, int boxNumber, int boxPosition) {
        if (gb.getTokenAtPosition(boxNumber, boxPosition).equals(GameBoard.EMPTY_GAMEBOARD_SLOT)) {
            // TODO - have this raise exception (RemoveEmptySlot exception?)
            System.out.println("cannot remove token from empty slot");
        } else {
            gb.removeToken(boxNumber, boxPosition);
        }
    }


}
