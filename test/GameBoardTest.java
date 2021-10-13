import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameBoardTest {
    GameBoard gameBoard;

    @BeforeEach
    void setUp() { gameBoard = new GameBoard(); }

    @AfterEach
    void tearDown() {
        gameBoard = null;
        System.out.println("Tear down: "+ gameBoard);
    }

    @Test
    void main() {
    }

    @Test
    void testToString() {
    }

    @Test
    void setToken() {
        gameBoard.setToken("W","A1");
        assertEquals("W", gameBoard.getTokenAtPosition("A1"), "setToken: True");
    }

    @Test
    void removeToken() {
        //removeTokenTest for nonempty position
        gameBoard.setToken("W","A2");
        gameBoard.removeToken("A2");
        /* TODO: solve below
        at an empty tokenposition, gb.getTokenAtPosition() returns the position
        */
        //assertEquals("", gb.getTokenAtPosition("B4"), "removeToken: True");

        //removeTokenTest for empty position
        //TODO: test for empty token
        gameBoard.removeToken("C1");

    }


    @Test
    void getTokenAtPosition() {
        gameBoard.setToken("W","A3");
        assertEquals("W", gameBoard.getTokenAtPosition("A3"), "getTokenAtPosition: True");
    }

    @Test
    void getGameBoardCapacity() {
        assertEquals(24, gameBoard.getGameBoardCapacity(),"getGameBoardCapacity: True");
    }
}