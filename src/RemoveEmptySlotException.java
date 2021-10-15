/**
 * Exception to be thrown when player tries to remove token from an unoccupied gameboard position.
 */
public class RemoveEmptySlotException extends InvalidRemovalException {
    public RemoveEmptySlotException() {
        super("Cannot remove token from an empty slot.");
    }
}
