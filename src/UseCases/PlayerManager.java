package UseCases;

import Entity.Player;

public class PlayerManager {
    private Player player1, player2;
    public PlayerManager(String player1Username, String player1TokenColour,
                         String player2Username, String player2TokenColour){
        player1 = new Player(player1Username, player1TokenColour);
        player2 = new Player(player2Username, player2TokenColour);
    }

    public void decreasePlayerTokensLeft(int playerNum){
        if(playerNum == 1){
            player1.int_player_numchipsleft--;
        }
        else{
            player2.int_player_numchipsleft--;
        }
    }

    public String getPlayerTokenColour(int playerNum){
        if(playerNum == 1){
            return player1.get_tokencolour();
        }
        else{
            return player2.get_tokencolour();
        }
    }

    public String getPlayerUsername(int playerNum){
        if(playerNum == 1){
            return player1.get_username();
        }
        else{
            return player2.get_username();
        }
    }

    public Player getPlayer(int playerNum){
        if(playerNum == 1){
            return player1;
        }
        else{
            return player2;
        }
    }

    public int getTokensRemaining(int playerNum){
        if(playerNum == 1){
            return player1.int_player_numchipsleft;
        }
        else{
            return player2.int_player_numchipsleft;
        }
    }

    public void setPlayer(int playerNum, String playerUsername, int tokensRemaining){
        if(playerNum == 1){
            player1.set_username(playerUsername);
            player1.int_player_numchipsleft = tokensRemaining;
        }
        else{
            player2.set_username(playerUsername);
            player2.int_player_numchipsleft = tokensRemaining;
        }
    }

    public boolean playersHaveTokensLeft(){
        return !(player1.int_player_numchipsonboard == 0 & player2.int_player_numchipsleft == 0);
    }
}
