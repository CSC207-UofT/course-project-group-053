/**
 * Initializes and stores an instance of GameBoard, processing requests to place and remove tokens from the GameBoard.
 */
// This class is both a Facade and a Subject class (as part of Observer design pattern)
public class GameBoardManipulator {
    // Design GameBoardManipulator as a facade class for manipulating tokens on a GameBoard (add, remove, slide tokens)
    private final GameBoard gameboard;
    private final GameBoardPlacer placer;
    private final GameBoardRemover remover;

    public GameBoardManipulator(GameBoardPlacer placer, GameBoardRemover remover) {
        this.gameboard = new GameBoard();
        this.placer = placer;
        this.remover = remover;
    }

    public void placeToken(Token token, String position) throws OccupiedSlotException,
            NonexistentPositionException {
        // TODO: make sure exceptions are caught properly upstream
        placer.place(gameboard, token, position);
    }

    public void removeToken(String position) throws RemoveEmptySlotException, InvalidPositionException {
        // TODO: make sure exceptions are caught properly upstream
        remover.remove(gameboard, position);
    }

/*
    @Override
    public void register(Observer o) {
        observers.add(o);
    }
*/

/*    @Override
    public void unregister(Observer o) {
        int observerIndex = observers.indexOf(o);
        observers.remove(observerIndex);
    }

    @Override
    public void notifyObserver(String playerName, String position, boolean addedToken) {
        for (Observer o : observers) {
            o.update(playerName, position, addedToken);
        }
    }*/
}