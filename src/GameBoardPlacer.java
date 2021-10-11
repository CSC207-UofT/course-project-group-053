public class GameBoardPlacer {
    // collection of static methods for manipulating GameBoard by adding/removing tokens
    // use class for GameBoard, used in conjunction with GameBoardManager?
    public static void placePlayerToken(GameBoard gb, String token, int boxNumber, int boxPosition) {
        if (! gb.getTokenAtPosition(boxNumber, boxPosition).equals(GameBoard.emptyGameBoardSlot)) {
            gb.setToken(token, boxNumber, boxPosition);
        } else {  // cannot place token at occupied slot on GameBoard
            // TODO - have this raise an exception (GameBoardSlotOccupied exception?)
            System.out.println("can't place token at occupied slot");
        }
    }

    public static void removePlayerToken(GameBoard gb, String token, int boxNumber, int boxPosition) {
        if (gb.getTokenAtPosition(boxNumber, boxPosition).equals(GameBoard.emptyGameBoardSlot)) {
            // TODO - have this raise exception (RemoveEmptySlot exception?)
            System.out.println("cannot remove token from empty slot");
        } else {
            gb.removeToken(boxNumber, boxPosition);
        }
    }


}
