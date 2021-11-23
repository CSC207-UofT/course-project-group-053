import Entity.GameBoard;
import Entity.Token;
import Exceptions.NonexistentPositionException;
import Exceptions.OccupiedSlotException;
import Exceptions.RemoveEmptySlotException;
import UseCases.CheckMill;
import UseCases.GameBoardManipulator;
import UseCases.GameBoardPlacer;
import UseCases.GameBoardRemover;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameBoardManipulatorTest {
    GameBoardManipulator gbm = new GameBoardManipulator(new GameBoardPlacer(), new GameBoardRemover(), new CheckMill());

    @BeforeEach
    void setUp() {
        this.gbm = new GameBoardManipulator(new GameBoardPlacer(), new GameBoardRemover(), new CheckMill());
    }

    @Test
    void placeToken() {
        // test cases:
        // 1) place token in empty position
        // 2) place token in non-existent position
        // 3) place token in occupied position
        Token token1 = new Token("bruh", "B");
        Token token2 = new Token("hi", "W");
        Token token3 = new Token("bruh", "B");

        try {
            gbm.placeToken(token1, "A1");
            assertEquals("B", gbm.getGameBoard().getTokenAtPosition("A1"));
        } catch (Exception e) {
            System.out.println("Something went wrong...");
        }

        try {
            gbm.placeToken(token2, "SNADIUOEWNN");
        } catch (NonexistentPositionException e) {
            System.out.println("Exceptions.NonexistentPositionException caught properly");
        } catch (Exception e) {
            System.out.println("Caught unexpected exception");
            System.out.println(e.getMessage());
        }

        try {
            gbm.placeToken(token3, "A1");
        } catch (OccupiedSlotException e) {
            System.out.println("Exceptions.OccupiedSlotException caught properly");
        } catch (Exception e) {
            System.out.println("Caught unexpected exception");
            System.out.println(e.getMessage());
        }
    }

    @Test
    void removeToken() {
        // test cases:
        // 1) remove existing token from gameboard
        // 2) remove from non-existent position
        // 3) remove from empty position
        Token token1 = new Token("bruh", "B");

        try {
            gbm.placeToken(token1, "A1");
            assertEquals("B", gbm.getGameBoard().getTokenAtPosition("A1"));
        } catch (Exception e) {
            System.out.println("Something went wrong...");
        }

        // remove existing token from gameboard
        try {
            gbm.removeToken("A1", "B");
            GameBoard gb = gbm.getGameBoard();
            assertNull(gb.getTokenAtPosition("A1"));
        } catch (Exception e) {
            System.out.println("Something went wrong...");
            System.out.println(e.getMessage());
        }

        // remove token from non-existent position
        try {
            gbm.removeToken("BABABABABABA", "W");
        } catch (NonexistentPositionException e) {
            System.out.println("Exceptions.NonexistentPositionException caught properly");
        } catch (Exception e) {
            System.out.println("Something went wrong...");
            System.out.println(e.getMessage());
        }

        // remove token from empty position
        try {
            gbm.removeToken("A1", "B");
        } catch (RemoveEmptySlotException e) {
            System.out.println("Exceptions.RemoveEmptySlotException caught properly");
        } catch (Exception e) {
            System.out.println("Something went wrong...");
            System.out.println(e.getMessage());
        }
    }
}