package Exceptions;

/**
 * Exception class raised when player requests to place a token in an occupied slot on the gameboard.
 * A slot on the gameboard is considered occupied if it already holds the token (regardless of what player it belongs to
 */
public class NonexistentPositionException extends InvalidPositionException {
    public NonexistentPositionException() {
        super("Position does not exist on gameboard. Please enter a coordinate as [ABC][1-8].");
    }
}