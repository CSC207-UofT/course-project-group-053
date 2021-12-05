import Entity.GameBoard;
import Entity.Token;
import Exceptions.NonexistentPositionException;
import Exceptions.OccupiedSlotException;
import Exceptions.RemoveEmptySlotException;
import Exceptions.RemoveSelfTokenException;
import UseCases.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameBoardManipulatorTest {
    TokenTracker tracker;
    GameBoardPlacer placer;
    GameBoardRemover remover;
    CheckMill milLChecker;
    GameBoardManipulator gbm;

    @BeforeEach
    void setUp() {
        this.tracker = new TokenTracker();
        this.placer = new GameBoardPlacer();
        this.remover = new GameBoardRemover();
        this.milLChecker = new CheckMill();
        this.gbm = new GameBoardManipulator(placer, remover, milLChecker);
        this.gbm.register(tracker);
    }

    @Test
    void register(){
        TokenTracker tracker2 = new TokenTracker();
        gbm.register(tracker2);

        // try removing both tracker
        gbm.unregister(this.tracker);
        gbm.unregister(tracker2);
        gbm.unregister(tracker2);  // nothing should happen as all trackers removed
    }

    @Test
    void notifyObservers() {
        // no token initially at A3
        assertNull(tracker.getToken("A3"));

        // place new token at A3 and notify tracker
        Token token = new Token("bruh", "B");
        gbm.notifyObservers("A3", token);

        // see if token correctly stored in tracker
        Token expectedToken = tracker.getToken("A3");
        assertEquals(token.getPlayer(), expectedToken.getPlayer());
        assertEquals(token.toString(), expectedToken.toString());

        // now try updating after "removing" token
        gbm.notifyObservers("A3");
        assertNull(tracker.getToken("A3"));
    }

    @Test
    void placeToken() {
        // cases:
        // 1) Invalid position
        // 2) Valid token placement
        // 3) Occupied slot
        Token token = new Token("bruh", "B");

        // invalid position to place token
        try {
            gbm.placeToken("ASNDSANDISANIDSA", token, tracker.getGameBoard());
        } catch (Exception e) {
            NonexistentPositionException expectedException = new NonexistentPositionException();
            assertEquals(e.getClass(), expectedException.getClass());
        }

        // place token in valid position
        try {
            gbm.placeToken("C6", token, tracker.getGameBoard());

            Token placedToken = tracker.getToken("C6");
            assertEquals(token.toString(), placedToken.toString());
            assertEquals(token.getPlayer(), placedToken.getPlayer());
            assertEquals(tracker.getGameBoard().getTokenAtPosition("C6"), token.toString());
        } catch (Exception e) {
            fail("Unexpected exception for placeToken, first try block");
        }

        // try to place token in occupied position
        Token token2 = new Token("dude", "W");
        try {
            gbm.placeToken("C6", token2, tracker.getGameBoard());
        } catch (OccupiedSlotException e) {
            Token originalToken = tracker.getToken("C6");

            // make sure token at occupied position was unaltered
            assertEquals(token.toString(), originalToken.toString());
            assertEquals(token.getPlayer(), originalToken.getPlayer());
            assertEquals(tracker.getGameBoard().getTokenAtPosition("C6"), "B");
        } catch (Exception e) {
            fail("Unexpected exception for placeToken, second try block");
        }
    }

    @Test
        // removeToken(String position, String playerUserName, String playerColor, TokenTracker tracker)
    void removeToken() {
        // cases:
        // 1) Invalid position
        // 2) Remove empty position
        // 3) Remove self token
        // 4) remove mill token
        // 5) remove valid token (after token is placed properly)

        // remove from invalid position
        try {
            gbm.removeToken("ANDSANJDS", "buddy", "W", tracker);
        } catch (NonexistentPositionException e) {
            System.out.println("Successfully caught NonExistentPositionException for removeToken");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail("Unexpected exception thrown in removeToken case 1)");
        }

        // remove from empty position
        try {
            gbm.removeToken("B7", "buddy", "W", tracker);
        } catch (RemoveEmptySlotException e) {
            System.out.println("Successfully caught RemoveEmptySlotException for removeToken");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail("Unexpected exception thrown in removeToken case 2)");
        }

        // remove self token
        try {
            Token token = new Token("bruh", "B");
            gbm.placeToken("A4", token, tracker.getGameBoard());
            assertEquals("B", tracker.getGameBoard().getTokenAtPosition("A4"));
            assertEquals("bruh", tracker.getToken("A4").getPlayer());
            assertEquals("B", tracker.getToken("A4").toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail("Unexpected exception thrown in removeToken case 3)");
        }
    }
}