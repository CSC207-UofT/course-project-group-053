public abstract class Player {
    String str_player_username;

    //Keep track of player’s chips
    Integer int_player_numchipsleft;
    Integer int_player_numchipsonboard;

    public Player(String str_player_username) {
        this.str_player_username = str_player_username;

        //initialize with 9 chips left and 0 on board
        this.int_player_numchipsleft = 9;
        this.int_player_numchipsonboard = 0;
    }

    private void reset() {
        this.int_player_numchipsleft = 9;
        this.int_player_numchipsonboard = 9;
    }

    private String get_username() {
        return this.str_player_username;
    }

    private int get_numchipsleft() {
        return this.int_player_numchipsleft;
    }

    private int get_numchipsonboard() {
        return this.int_player_numchipsonboard;
    }

    //decrease or increase number of chips
    private void dec_numchipsleft() {
        //used when other player gets house. with dec_numchipsonboard()
        --this.int_player_numchipsleft;
    }

    private void inc_numchipsonboard() {
        ++this.int_player_numchipsonboard;
    }

    private void dec_numchipsonboard() {
        --this.int_player_numchipsonboard;
    }

    public abstract boolean isHuman();
}
