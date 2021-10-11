public class GameBoardManager {
    // Creates and stores a GameBoard instance, and processes Player moves on the GameBoard
    private GameBoard gb = new GameBoard();

    public String getGameBoardState() {
        return gb.toString();
    }


}
