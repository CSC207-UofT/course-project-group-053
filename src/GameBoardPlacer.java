public class GameBoardPlacer {
    // class for GameBoardManipulator facade, placing tokens on a GameBoard instance

    /**
     * Inserts a Token into a specified position on GameBoard
     *
     * @param gb GameBoard instance to place token on
     * @param token A Token instance to place on the gameboard
     * @param position String representation of gameboard coordinates to place token, in form [ABC][1-8]
     */
    public void place(GameBoard gb, Token token, String position) throws NonexistentPositionException,
            OccupiedSlotException {
        if (! checkValidPosition(position)) {
            throw new NonexistentPositionException();
        } else if (! checkPositionUnoccupied(gb, position)) {
            throw new OccupiedSlotException();
        } else {
            // position is valid and not occupied, so player may place token at that position in gameboard
            gb.setToken(token.toString(), position);
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