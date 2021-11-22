import Controller.GamePlay1;
import Entity.Player;
import Exceptions.InvalidPositionException;
import Exceptions.LoadedSuccessfully;
import Exceptions.SavedSuccessfully;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class GamePlay1Test {
    GamePlay1 gamePlay1;
    Player player1, player2;
    List<Player> playerList;

    @BeforeEach
    void setUp() throws InvalidPositionException, SavedSuccessfully, LoadedSuccessfully {
        player1 = new Player("Player1", "W");
        player2 = new Player("Player2", "B");

        playerList = List.of(new Player[]{player1, player2});
        gamePlay1 = new GamePlay1();
    }

    @AfterEach
    void tearDown() {
        player1 = null;
        player2 = null;
        playerList = null;
        gamePlay1 = null;
    }

    @Test
    void move_token() {
    }

    @Test
    void remove_token() {
    }
}