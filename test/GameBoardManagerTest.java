import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameBoardManagerTest {
    GameBoardManager gameBoardManager;
    GameBoard gameBoard;

    @BeforeEach
    void setUp() {
        gameBoardManager = new GameBoardManager();
        gameBoard = new GameBoard();
    }

    @AfterEach
    void tearDown() {
        gameBoardManager = null;
    }

    @Test
    void getGameBoardState() {
    }

    @Test
    void processPlayerMove() throws InvalidPositionException, OccupiedSlotException {
        gameBoardManager.processPlayerMove("W", "A1");

        //when placing on occupied spot
        gameBoardManager.processPlayerMove("B","A1");
    }

    @Test
    void getPlayer1Houses() {
    }

    @Test
    void getPlayer2Houses() {
    }

    @Test
    void checkPhaseOneEnd() {
    }

    @Test
    void millAdder() {
    }

    @Test
    void checkHouse() {
    }

    @Test
    void processPlayerRemove() {
    }
}