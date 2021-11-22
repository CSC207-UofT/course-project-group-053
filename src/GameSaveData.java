import java.io.Serializable;

public class GameSaveData implements Serializable {
    private static final long  serialVersionUID = 1L;
    public Player player1saved, player2saved;
    public GameBoard savedGameboard;

    public GameSaveData(GameBoard savedGameboard){
        this.savedGameboard = savedGameboard;
    }

    public GameSaveData(Player player1saved, Player player2saved, GameBoard savedGameboard){
        this.player1saved = player1saved;
        this.player2saved = player2saved;
        this.savedGameboard = savedGameboard;
    }

    public String getSavedPlayerUsername(int playerNum){
        if(playerNum == 1){
            return player1saved.get_username();
        }
        else{ return player2saved.get_username(); }
    }
}
