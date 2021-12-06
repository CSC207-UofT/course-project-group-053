package Exceptions;

/**
 * A parent exception class for any exception raised when player tries to remove a token from an illegal position on
 * the gameboard
 */
public class InvalidRemovalException extends Exception {
    public InvalidRemovalException(String msg) {
        super(msg);
    }
}
