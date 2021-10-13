/**
 * Exception class raised when player requests to place a token in an occupied slot on the gameboard.
 * A slot on the gameboard is considered occupied if it already holds the token (regardless of what player it belongs to
 */
public class OccupiedSlotException extends Exception{
    public OccupiedSlotException() {
        super("Position already occupied by a token. Please choose an empty position on the gameboard");
    }
}
