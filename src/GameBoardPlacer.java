public class GameBoardPlacer {
    /**
     * Inserts a token of type T into a specified position on GameBoard
     *
     * @param gb An instance of a GameBoard
     * @param token String representation of a token to place on the GameBoard
     * @param position String representation of gameboard coordinates to place token, in form [ABC][1-8]
     */
    public static void InsertToken(GameBoard gb, String token, String position) throws NonexistentPositionException,
            OccupiedSlotException {
        if (! position.matches(GameBoard.EMPTY_SLOT_PATTERN)) {
            // non-existent coordinate was given
            throw new NonexistentPositionException();
        } else if (! gb.getTokenAtPosition(position).matches(GameBoard.EMPTY_SLOT_PATTERN)) {
            // tried to insert token into gameboard position taken up by another token
            throw new OccupiedSlotException();
        } else {
            // insert token at specified position into gameboard
            gb.setToken(token, position);
        }
    }
}
