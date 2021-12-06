package Exceptions;

/**
 * Exception to be thrown when player tries to remove own token from the gameboard
 */
public class RemoveSelfTokenException extends InvalidRemovalException {
    public RemoveSelfTokenException() {
        super("Entity.Player cannot remove your own token from the gameboard.");
    }
}
