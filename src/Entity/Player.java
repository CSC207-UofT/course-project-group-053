package Entity;

import java.io.Serializable;

public class Player implements Serializable {
    String str_player_username;
    String str_player_tokencolour;

    //Keep track of player’s chips
    Integer int_player_numchipsleft;
    Integer int_player_numchipsonboard;

    public Player(String str_player_username, String colour) {
        this.str_player_username = str_player_username;
        this.str_player_tokencolour = colour;

        //initialize with 9 chips left and 0 on board
        this.int_player_numchipsleft = 9;
        this.int_player_numchipsonboard = 0;
    }

    public void reset() {
        this.int_player_numchipsleft = 9;
        this.int_player_numchipsonboard = 9;
    }

    public String get_username() {
        return this.str_player_username;
    }

    public String get_tokencolour() { return this.str_player_tokencolour; }

    public int get_numchipsleft() {
        return this.int_player_numchipsleft;
    }

    public int get_numchipsonboard() {
        return this.int_player_numchipsonboard;
    }

    //decrease or increase number of chips
    public void dec_numchipsleft() {
        //used when other player gets house. with dec_numchipsonboard()
        --this.int_player_numchipsleft;
    }

    public void inc_numchipsonboard() {
        ++this.int_player_numchipsonboard;
    }

    private void dec_numchipsonboard() {
        --this.int_player_numchipsonboard;
    }

}
