package Entity;

import java.io.Serializable;

/**
 * Tokens placed on GameBoards
 */
public class Token implements Serializable {
    public final String owner;  // username of player who owns this token
    private final char color;  // character representing the token (ex: B, W, R, etc...)

    public Token(String owner, String color) {
        this.owner = owner;
        this.color = color.charAt(0);  // input string for appearance should be single letter, coerce to char
    }

    @Override
    public String toString() {
        return Character.toString(this.color);
    }

    public String getPlayer() {
        return this.owner;
    }

}