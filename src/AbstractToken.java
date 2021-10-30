/**
 * Abstract parent class for any object we can place on a GameBoard
 */
public abstract class AbstractToken {
    private final Player owner;
    private final String appearance;
    private boolean inMill;

    public AbstractToken(Player owner, String appearance) {
        this.owner = owner;
        this.appearance = appearance;
        this.inMill = false;
    }

    @Override
    public abstract String toString();

    public Player getPlayer() {
        return this.owner;
    }

    public String getAppearance() {
        return this.appearance;
    }

    public boolean checkInMill() {
        return this.inMill;
    }

    public void changeMillStatus(boolean newInMill) {
        this.inMill = newInMill;
    }
}
