/**
 * Abstract parent class for any object we can place on a GameBoard
 */
public abstract class AbstractToken<T extends Player> {
    private final T owner;
    private final String appearance;
    private boolean inMill;

    public AbstractToken(T owner, String appearance) {
        this.owner = owner;
        this.appearance = appearance;
        this.inMill = false;
    }

    @Override
    public abstract String toString();

    public T getPlayer() {
        return this.owner;
    }

    public String getAppearance() {
        return this.appearance;
    }

    public boolean inMill() {
        return this.inMill;
    }

    public void changeMillStatus(boolean newInMill) {
        this.inMill = newInMill;
    }
}
