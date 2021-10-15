import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameBoardManagerTest {
    GameBoardManager gbm;

    @BeforeEach
    void setUp() { gbm = new GameBoardManager(); }

    @Test
    void processPlayerMove() {
        // player places token in valid spot

        // player places token in occupied slot

        // player specifies nonexistent coordinate to place token
    }

    @Test
    void processPlayerRemove() {

}