/**
 * Exception to be thrown when player tries to remove chip from opponent's mill
 */
public class RemoveMillException extends Exception {
    public RemoveMillException() {
        super("Cannot remove chip that is part of opponent's mill");
    }
}
