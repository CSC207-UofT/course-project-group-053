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

    public GameSaveData(GameBoard savedGameboard, TokenTracker tracker){
        this.savedGameboard = savedGameboard;
        this.savedTracker = tracker;
    }

    public GameSaveData(Player player1saved, Player player2saved, GameBoard savedGameboard, TokenTracker tracker) {
        this.player1saved = player1saved;
        this.player2saved = player2saved;
        this.savedGameboard = savedGameboard;
        this.savedTracker = tracker;
    }
}
