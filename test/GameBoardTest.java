import Entity.GameBoard;
import Entity.Player;
import Entity.Token;
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
    }

    @Test
    void setToken() {
        Player p = new Player("dude", "R");
        Token t = new Token(p.get_username(), p.get_tokencolour());
        gameBoard.setToken(t.toString(),"A1");
        assertEquals(t.toString(), gameBoard.getTokenAtPosition("A1"), "setToken: True");
        assertEquals(23, gameBoard.getGameBoardCapacity(), "successfully updated capacity");
    }

    @Test
    void removeToken() {
        //removeTokenTest for nonempty position
        Player p = new Player("dude", "R");
        Token t = new Token(p.get_username(), p.get_tokencolour());
        gameBoard.setToken(t.toString(),"A2");
        assertEquals(t.toString(), gameBoard.getTokenAtPosition("A2"), "setToken: True");
        assertEquals(23, gameBoard.getGameBoardCapacity());

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
        Player p = new Player("dude", "R");
        Token t = new Token(p.get_username(), p.get_tokencolour());
        gameBoard.setToken(t.toString(),"A3");
        assertEquals(t.toString(), gameBoard.getTokenAtPosition("A3"), "getTokenAtPosition: True");

        // get token at empty position
        assertNull(gameBoard.getTokenAtPosition("C8"));
    }

    @Test
    void getGameBoardCapacity() {
        assertEquals(24, gameBoard.getGameBoardCapacity(),"getGameBoardCapacity: True");

        //after gameBoard--
        Player p = new Player("dude", "R");
        Token t = new Token(p.get_username(), p.get_tokencolour());
        gameBoard.setToken(t.toString(),"B1");
        assertEquals(23, gameBoard.getGameBoardCapacity(),"getGameBoardCapacity: True");

        //after gameBoard++
        gameBoard.removeToken("B1");
        assertEquals(24, gameBoard.getGameBoardCapacity(),"getGameBoardCapacity: True");
    }
}