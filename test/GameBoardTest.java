import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameBoardTest {
    GameBoard<PlayerToken> gameBoard;

    @BeforeEach
    void setUp() { gameBoard = new GameBoard<>(); }

    @AfterEach
    void tearDown() {
        gameBoard = null;
    }

    @Test
    void setToken() {
        HumanPlayer1 p = new HumanPlayer1("dude", "red");
        PlayerToken t = new PlayerToken(p, "red");
        gameBoard.setToken(t,"A1");
        assertEquals(t, gameBoard.getTokenAtPosition("A1"), "setToken: True");
        assertEquals(23, gameBoard.getGameBoardCapacity(), "successfully updated capacity");
    }

    @Test
    void removeToken() {
        //removeTokenTest for nonempty position
        HumanPlayer1 p = new HumanPlayer1("dude", "red");
        PlayerToken t = new PlayerToken(p, "red");
        gameBoard.setToken(t,"A2");
        assertEquals(t, gameBoard.getTokenAtPosition("A2"), "setToken: True");
        assertEquals(24, gameBoard.getGameBoardCapacity());

        // remove the token we just added
        gameBoard.removeToken("A2");
        assertNull(gameBoard.getTokenAtPosition("A2"));
        assertEquals(24, gameBoard.getGameBoardCapacity());

        // no test case for removing token from empty position, as use cases should deal with that logic
        // (and not the gameboard entity class itself)
    }


    @Test
    void getTokenAtPosition() {
        // get token at occupied position
        HumanPlayer1 p = new HumanPlayer1("dude", "red");
        PlayerToken t = new PlayerToken(p, "red");
        gameBoard.setToken(t,"A3");
        assertEquals(t, gameBoard.getTokenAtPosition("A3"), "getTokenAtPosition: True");

        // get token at empty position
        assertNull(gameBoard.getTokenAtPosition("C8"));
    }

    @Test
    void getGameBoardCapacity() {
        assertEquals(24, gameBoard.getGameBoardCapacity(),"getGameBoardCapacity: True");

        //after gameBoard--
        HumanPlayer1 p = new HumanPlayer1("dude", "red");
        PlayerToken t = new PlayerToken(p, "red");
        gameBoard.setToken(t,"B1");
        assertEquals(23, gameBoard.getGameBoardCapacity(),"getGameBoardCapacity: True");

        //after gameBoard++
        gameBoard.removeToken("B1");
        assertEquals(24, gameBoard.getGameBoardCapacity(),"getGameBoardCapacity: True");
    }
}
