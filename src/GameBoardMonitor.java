import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
/**
 * Observer class for monitoring current state of a GameBoard object, empty GameBoard slots and slots that players
 * can remove tokens from.
 */
public class GameBoardMonitor<T extends Player> implements Observer {
    private ArrayList<String> emptySlots;
    private HashMap<T, ArrayList<String>> playerRemovableSlots;
    private String gameBoardState;

    public GameBoardMonitor(T player1, T player2, String gameBoardState) {
        this.gameBoardState = gameBoardState;

        // Initialize emptySlots as ArrayList containing all possible coordinates in a GameBoard
        this.emptySlots = new ArrayList<String>();
        Collections.addAll(emptySlots, GameBoard.GAMEBOARD_COORDINATES);

        // initialize playerRemovableSlots as HashMap of players to empty ArrayLists
        this.playerRemovableSlots = new HashMap<T, ArrayList<String>>();
        playerRemovableSlots.put(player1, new ArrayList<String>());
        playerRemovableSlots.put(player2, new ArrayList<String>());
    }


    @Override
    public void update() {
    }
}