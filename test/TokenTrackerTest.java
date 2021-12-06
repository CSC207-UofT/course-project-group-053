import UseCases.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import UseCases.TokenTracker;
import UseCases.GameBoardManipulator;
import UseCases.GameBoardPlacer;
import UseCases.GameBoardRemover;
import UseCases.CheckMill;
import Entity.GameBoard;
import Entity.Token;

public class TokenTrackerTest {
    TokenTracker tracker;
    GameBoardManipulator manager;

    @BeforeEach
    void setUp() {
        this.tracker = new TokenTracker();
        this.manager = new GameBoardManipulator(new GameBoardPlacer(), new GameBoardRemover(), new CheckMill());
        manager.register(tracker);
    }

    @Test
    void isSelfToken() {
        // Cases:
        // 1) No token at position
        assertFalse(tracker.isSelfToken("dude", "C8"));

        // 2) Self token at position
        try {
            manager.placeToken("C8", new Token("dude", "W"), tracker.getGameBoard());
            assertTrue(tracker.isSelfToken("dude", "C8"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail("Unexpected exception caught for isSelfToken");
        }

        // 3) Other token at position
        try {
            manager.placeToken("B5", new Token("bro", "B"), tracker.getGameBoard());
            assertFalse(tracker.isSelfToken("dude", "B5"));
            assertTrue(tracker.isSelfToken("bro", "B5"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail("Unexpected exception caught for isSelfToken");
        }
    }

    @Test
    void getToken() {
        // Cases:
        // 1) Empty slot
        assertNull(tracker.getToken("B7"));

        // 2) Token was placed by GBM
        try {
            manager.placeToken("B7", new Token("broski", "W"), tracker.getGameBoard());
            Token actualToken = tracker.getToken("B7");
            assertNotNull(actualToken);
            assertEquals("broski", actualToken.getPlayer());
            assertEquals("W", actualToken.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail("Unexpected exception caught for getToken case 1)");
        }

        // 3) Token was removed by GBM, so position should be null again
        try {
            manager.removeToken("B7", "buddy", "B", tracker);
            assertNull(tracker.getToken("B7"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail("Unexpected exception caught for getToken case 1)");
        }
    }

    @Test
    // test both update methods indirectly, seeing if tracker is correctly updated whenever manager makes a move
    void update() {
        // Cases (for update, added token)
        // 1) GBM adds something to gameboard
        try {
            manager.placeToken("A2", new Token("bro", "W"), tracker.getGameBoard());
            manager.placeToken("B3", new Token("man", "B"), tracker.getGameBoard());
            manager.placeToken("C4", new Token("bro", "W"), tracker.getGameBoard());

            Token token1 = tracker.getToken("A2");
            Token token2 = tracker.getToken("B3");
            Token token3 = tracker.getToken("C4");


            assertEquals("bro", token1.getPlayer());
            assertEquals("W", token1.toString());

            assertEquals("man", token2.getPlayer());
            assertEquals("B", token2.toString());

            assertEquals("bro", token3.getPlayer());
            assertEquals("W", token3.toString());
        } catch (Exception e) {
            fail(e.getMessage());
        }


        // Cases (for update, removed token)
        // 1) GBM removes something from gameboard
        try {
            manager.removeToken("B3", "bro", "W", tracker);
            manager.removeToken("A2", "man", "B", tracker);
            assertNull(tracker.getToken("B3"));
            assertNull(tracker.getToken("A2"));
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}
