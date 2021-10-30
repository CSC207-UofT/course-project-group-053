/**
 * Class for colored tokens that players may place on GameBoard
 */
public class PlayerToken extends AbstractToken {
    public PlayerToken(Player owner, String appearance) {
        super(owner, appearance);
    }

    @Override
    public String toString() {
        return this.getAppearance();
    }
}
