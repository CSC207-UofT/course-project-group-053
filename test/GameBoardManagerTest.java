import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class GameBoardManagerTest {
    GameBoardManager gbm;

    @BeforeEach
    void setUp() {
        gbm = new GameBoardManager();

    }

    @AfterEach
    void tearDown() {
        gbm = null;
    }

    @Test
    void processPlayerMove() {
        // player places token in valid spot
        try {
            gbm.processPlayerMove("W", "B1");
        } catch (InvalidPositionException e) {
            e.printStackTrace();
        }

        // player places token in occupied slot
        try {
            gbm.processPlayerMove("B", "B1");
        } catch (InvalidPositionException e) {
            assertEquals("Position already occupied by a token. Please choose an empty position on the gameboard",e.getMessage());
        }

        // player specifies nonexistent coordinate to place token
        try {
            gbm.processPlayerMove("B", "3");
        } catch (InvalidPositionException e) {
            assertEquals("Position does not exist on gameboard. Please enter a coordinate as [ABC][1-8].",e.getMessage());
        }
    }

    @Test
    void processPlayerRemove() {
        try {
            //TODO: mill not created properly -> RemoveMillException is not checked properly
            gbm.processPlayerMove("W", "A1");
            gbm.processPlayerMove("W", "A2");
            gbm.processPlayerMove("W", "A3");
            gbm.processPlayerMove("W", "A4");
            gbm.processPlayerMove("B", "B1");

            // player places token in valid spot
            try {
                gbm.processPlayerRemove(2, "A4");
            } catch (InvalidRemovalException e) {
                e.printStackTrace();
            }

            // player removes token from empty gameboard slot
            try {
                gbm.processPlayerRemove(2, "C1");
            } catch (InvalidRemovalException e) {
                assertEquals("Cannot remove token from an empty slot.", e.getMessage());
            }

            // player removes own token from the gameboard
            try {
                gbm.processPlayerRemove(2, "B1");
            } catch (InvalidRemovalException e) {
                assertEquals("Player cannot remove your own token from the gameboard.", e.getMessage());
            }

            // player removes opponents token if it's in a mill, and the opponent has tokens outside of mills
            try {
                gbm.processPlayerRemove(2, "A1");
            } catch (InvalidRemovalException e) {
                assertEquals("Cannot remove chip that is part of opponent mill", e.getMessage());
                System.out.println(e.getMessage());
            }
        }
        catch (InvalidPositionException e) {
            e.printStackTrace();
        }
    }
}