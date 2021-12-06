package Exceptions;

/**
 * A parent exception class for any exception raised when player tries to insert token into an invalid position
 */
public class InvalidPositionException extends Exception {
    public InvalidPositionException(String msg) {
        super(msg);
    }
}
