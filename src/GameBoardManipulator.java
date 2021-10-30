/**
 * Initializes and stores an instance of GameBoard, processing requests to place and remove tokens from the GameBoard
 */
public class GameBoardManipulator<T extends AbstractToken> {
    private final GameBoard gb = new GameBoard();

    /**
     * Inserts a token of type T into a specified position on GameBoard
     *
     * @param token String representation of a token to place on the GameBoard
     * @param position String representation of gameboard coordinates to place token, in form [ABC][1-8]
     */
    public void InsertToken(T token, String position) throws NonexistentPositionException,
            OccupiedSlotException {
        if (! position.matches(GameBoard.EMPTY_SLOT_PATTERN)) {
            // non-existent coordinate was given
            throw new NonexistentPositionException();
        } else if (! gb.getTokenAtPosition(position).matches(GameBoard.EMPTY_SLOT_PATTERN)) {
            // tried to insert token into gameboard position taken up by another token
            throw new OccupiedSlotException();
        } else {
            // insert token at specified position into gameboard
            gb.setToken(token.toString(), position);
        }
    }

    /**
     * Removes a token from position on gameboard
     * @param position
     */
    public void RemoveToken(GameBoard gb, String position) {

    }
}