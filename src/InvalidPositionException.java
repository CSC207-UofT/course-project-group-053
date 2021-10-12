/**
 * Exception class for when player requests an invalid gameboard slot to place a token in.
 * An invalid gameboard slot is one that is already occupied by another token, or does not exist on the gameboard
 */
public class InvalidPositionException extends Exception {
    public InvalidPositionException() {
        super("Invalid gameboard position. Please enter an available coordinate on the gameboard.");
    }
}
