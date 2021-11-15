import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GamePlay1Test {
    GamePlay1 gamePlay1;
    Player player1, player2;
    List<Player> playerList;

    @BeforeEach
    void setUp() throws InvalidPositionException {
        player1 = new Player("Player1", "W");
        player2 = new Player("Player2", "B");

        playerList = List.of(new Player[]{player1, player2});
        gamePlay1 = new GamePlay1(playerList);
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