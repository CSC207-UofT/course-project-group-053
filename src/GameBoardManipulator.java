/**
 * Initializes and stores an instance of GameBoard, processing requests to place and remove tokens from the GameBoard.
 */
// TODO - make this class a Subject class, that reports to some Observer class keeping track of GameBoard state?
public class GameBoardManipulator {
    // store an empty gameboard at first
    private final GameBoard gb = new GameBoard();

    private boolean checkInvalidPosition(String position) {
        // valid position coordinate in gameboard matches [ABC][1-8] text pattern
        return !position.matches(GameBoard.EMPTY_SLOT_PATTERN);
    }

    /**
     * Inserts a Token into a specified position on GameBoard
     *
     * @param token A token instance to place on the gameboard
     * @param position String representation of gameboard coordinates to place token, in form [ABC][1-8]
     */
    public void InsertToken(Token token, String position) throws NonexistentPositionException,
            OccupiedSlotException {
        if (checkInvalidPosition(position)) {
            // non-existent coordinate was given; position string should match [ABC][1-8] pattern
            throw new NonexistentPositionException();
        } else if (gb.getTokenAtPosition(position) != null) {
            // tried to insert token into gameboard position taken up by another token
            throw new OccupiedSlotException();
        } else {
            // insert token at specified position into gameboard
            gb.setToken(token.toString(), position);
        }
    }

    /**
     * Removes a token from position on gameboard
     * @param position String representation of a token to place on the GameBoard, in format [ABC][1-8]
     * @return String representing the token that was removed from gameboard
     */
    public String RemoveToken(String position) throws InvalidPositionException, RemoveEmptySlotException {
        // 1) check if position is valid (InvalidPositionException)
        // 2) check if position is empty (RemoveEmptySlotException)
        // 3) if position is valid and non-empty, remove the token from gameboard
        if (checkInvalidPosition(position)) {
            throw new InvalidPositionException("Non-existent position. Please enter valid coordinate.");
        }
        else if (gb.getTokenAtPosition(position) == null) {
            // cannot remove token from an empty position
            throw new RemoveEmptySlotException();
        }
        else {
            return gb.removeToken(position);  // remove token and return its string
            }
        }
}