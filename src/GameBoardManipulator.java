/**
 * Initializes and stores an instance of GameBoard, processing requests to place and remove tokens from the GameBoard.
 * This class takes in two generics:
 *      P: Expected class for Player objects
 *      T: Expected class for Token objects
 */
// TODO - make this class a Subject class, that reports to some Observer class keeping track of GameBoard state?
public class GameBoardManipulator<P extends Player, T extends AbstractToken<P>> {
    // store an empty gameboard at first, holding type T token objects
    private final GameBoard<T> gb = new GameBoard<>();

    private boolean checkInvalidPosition(String position) {
        // valid position coordinate in gameboard matches [ABC][1-8] text pattern
        return !position.matches(GameBoard.EMPTY_SLOT_PATTERN);
    }

    /**
     * Inserts a token of type T into a specified position on GameBoard
     *
     * @param token String representation of a token to place on the GameBoard, in format [ABC][1-8]
     * @param position String representation of gameboard coordinates to place token, in form [ABC][1-8]
     */
    public void InsertToken(T token, String position) throws NonexistentPositionException,
            OccupiedSlotException {
        if (checkInvalidPosition(position)) {
            // non-existent coordinate was given; position string should match [ABC][1-8] pattern
            throw new NonexistentPositionException();
        } else if (gb.getTokenAtPosition(position) != null) {
            // tried to insert token into gameboard position taken up by another token
            throw new OccupiedSlotException();
        } else {
            // insert token at specified position into gameboard
            gb.setToken(token, position);
        }
    }

    /**
     * Removes a token from position on gameboard
     * @param position String representation of a token to place on the GameBoard, in format [ABC][1-8]
     * @param player Player requesting to remove
     */
    public void RemoveToken(String position, Player player) throws InvalidPositionException,
                                                                   RemoveEmptySlotException,
                                                                   RemoveSelfTokenException,
                                                                   RemoveMillException {
        // 1) check if position is valid (InvalidPositionException)
        // 2) check if position is empty (RemoveEmptySlotException)
        // 3) remove token, then check if token belongs to player (RemoveSelfTokenException)
        // 4) check if token is in another player's mill (RemoveMillException)
        //     TODO - extend 4) check to see if all of other player's tokens are in mills
        if (checkInvalidPosition(position)) {
            throw new InvalidPositionException();
        }
        else if (gb.getTokenAtPosition(position) == null) {
            // cannot remove token from an empty position
            throw new RemoveEmptySlotException();
        }
        else {
            T removedToken = gb.removeToken(position);
            if (removedToken.getPlayer() == player) {
                // player assigned to token should be aliased to original player
                // player tried to remove own token, which isn't acceptable
                gb.setToken(removedToken, position);  // add back in removed self token
                throw new RemoveSelfTokenException();
            }
            else if (removedToken.inMill()) {
                // if token isn't player's own, and is in mill, then the token is one of opponent's mills
                throw new RemoveMillException();
            } else {
                // TODO - maybe move this print statement to a controller class
                String msg = "Token at " + position + " removed by " + removedToken.getPlayer().get_username();
                System.out.println(msg);
            }
        }
    }
}