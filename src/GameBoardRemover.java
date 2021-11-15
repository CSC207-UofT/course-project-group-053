public class GameBoardRemover {
    // class for GameBoardManipulator facade, removing tokens from a GameBoard instance

    /**
     * Removes a token from position on gameboard
     * @param position String representation of a token to place on the GameBoard, in format [ABC][1-8]
     * @return String representing the token that was removed from gameboard
     */
    public String remove(GameBoard gb, String position) throws NonexistentPositionException, RemoveEmptySlotException {
        // 1) check if position is valid (InvalidPositionException)
        // 2) check if position is empty (RemoveEmptySlotException)
        // 3) if position is valid and non-empty, remove the token from gameboard
        if (! checkValidPosition(position)) {
            throw new NonexistentPositionException();
        }
        else if (checkPositionUnoccupied(gb, position)) {
            // cannot remove token from an empty position
            throw new RemoveEmptySlotException();
        }
        else {
            return gb.removeToken(position);
        }
    }

    private boolean checkValidPosition(String position) {
        // return True if gameboard position string matches [ABC][1-8] text pattern, matching pattern for empty slot
        return position.matches(GameBoard.EMPTY_SLOT_PATTERN);
    }

    private boolean checkPositionUnoccupied(GameBoard gb, String position) {
        // return True if position is not occupied in gameboard
        return gb.getTokenAtPosition(position) == null;
    }
}
