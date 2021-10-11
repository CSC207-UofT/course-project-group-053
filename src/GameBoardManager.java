public class GameBoardManager {
    // Initializes new instances of GameBoard, returns state of GameBoard and processes Player requests to place/slide
    // tokens on GameBoard

    // This class is use case class, consisting of static methods for other classes to interact with GameBoard

    /**
     * Initializes a new instance of GameBoard
     * @return new GameBoard instance, with all slots empty
     */
    public static GameBoard makeNewGameBoard() {
        return new GameBoard();
    }

    /**
     * Returns the string representation of a GameBoard's current state, with positions of all Player tokens
     * @param gb GameBoard object of interest
     * @return string representation of GameBoard's state, as specified by GameBoard's toString method
     */
    public static String getGameBoardState(GameBoard gb) {
        return gb.toString();
    }
}
