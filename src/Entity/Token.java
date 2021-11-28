package Entity;

/**
 * Tokens placed on GameBoards
 */
public class Token {
    public final String owner;  // username of player who owns this token
    private final char color;  // character representing the token (ex: B, W, R, etc...)
    private boolean inMill;

    public Token(String owner, String color) {
        this.owner = owner;
        this.color = color.charAt(0);  // input string for appearance should be single letter, coerce to char
        this.inMill = false;
    }

    @Override
    public String toString() { return Character.toString(this.color); }

    public String getPlayer() {
        return this.owner;
    }

    public boolean inMill() {
        return this.inMill;
    }

    public void changeMillStatus(boolean newInMill) {
        this.inMill = newInMill;
    }
}
