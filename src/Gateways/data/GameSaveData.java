package Gateways.data;

import Entity.GameBoard;
import Entity.Player;
import UseCases.TokenTracker;

import java.io.Serializable;

public class GameSaveData implements Serializable {
    private static final long  serialVersionUID = 1L;
    public Player player1saved, player2saved;
    public GameBoard savedGameboard;
    public TokenTracker savedTracker;
    public String savedGameState;

    public GameSaveData(GameBoard savedGameboard){
        this.savedGameboard = savedGameboard;
    }

    public GameSaveData(Player player1saved, Player player2saved, GameBoard savedGameboard, TokenTracker tracker,
                        String savedGameState){
        this.player1saved = player1saved;
        this.player2saved = player2saved;
        this.savedGameboard = savedGameboard;
        this.savedTracker = tracker;
        this.savedGameState = savedGameState;
    }

    public String getSavedPlayerUsername(int playerNum){
        if(playerNum == 1){
            return player1saved.get_username();
        }
        else{ return player2saved.get_username(); }
    }

    public int getSavedPlayerTokensRemaining(int playerNum){
        if(playerNum == 1){ return player1saved.int_player_numchipsleft; }
        else { return player2saved.int_player_numchipsleft; }
    }

    public TokenTracker getTracker() {
        return savedTracker;
    }

    public GameBoard getGameBoard() {
        return savedGameboard;
    }

    public String getSavedGameState(){
        return savedGameState;
    }
}
