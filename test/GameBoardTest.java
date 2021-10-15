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
    void setToken() {
        gameBoard.setToken("W","A1");
        assertEquals("W", gameBoard.getTokenAtPosition("A1"), "setToken: True");
        assertEquals(23, gameBoard.getGameBoardCapacity(), "successfully updated capacity");
    }

    @Test
    void removeToken() {
        //removeTokenTest for nonempty position
        gameBoard.setToken("W","A2");
        assertEquals("W", gameBoard.getTokenAtPosition("A2"), "setToken: True");
        gameBoard.removeToken("A2");
        assertEquals("A2", gameBoard.getTokenAtPosition("A2"));
        assertEquals(24, gameBoard.getGameBoardCapacity());

        // if you try to remove a token from an empty slot, you will "replace" the item at the slot with the same
        // empty token, since empty spots hold "tokens" with the pattern [ABC][1-8]

        // however, capacity will go up, even though no token was really removed

        // GameBoardManager will never call removeToken on an empty slot, and will throw a RemoveEmptySlotException
        // when a player tries to do so

        // - Jason

        //removeTokenTest for empty position
        gameBoard.removeToken("C1");
        assertEquals("C1", gameBoard.getTokenAtPosition("C1"));
        assertEquals(25, gameBoard.getGameBoardCapacity());


    }


    @Test
    void getTokenAtPosition() {
        gameBoard.setToken("W","A3");
        assertEquals("W", gameBoard.getTokenAtPosition("A3"), "getTokenAtPosition: True");
    }

    @Test
    void getGameBoardCapacity() {
        assertEquals(24, gameBoard.getGameBoardCapacity(),"getGameBoardCapacity: True");

        //after gameBoard--
        gameBoard.setToken("W","B1");
        assertEquals(23, gameBoard.getGameBoardCapacity(),"getGameBoardCapacity: True");

        //after gameBoard++
        gameBoard.removeToken("B1");
        assertEquals(24, gameBoard.getGameBoardCapacity(),"getGameBoardCapacity: True");
    }
}
