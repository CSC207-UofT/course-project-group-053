package UseCases;

import Entity.GameBoard;
import Exceptions.NonexistentPositionException;
import Exceptions.RemoveEmptySlotException;
import Exceptions.RemoveMillException;
import Exceptions.RemoveSelfTokenException;

public class GameBoardRemover {
    // class for UseCases.GameBoardManipulator facade, removing tokens from a Entity.GameBoard instance

    /**
     * Removes a token from position on gameboard
     * @param position String representation of a token to place on the Entity.GameBoard, in format [ABC][1-8]
     */
    protected void remove(String position, String playerColor, String playerUserName, TokenTracker tracker,
                            CheckMill millChecker)
            throws NonexistentPositionException, RemoveEmptySlotException, RemoveSelfTokenException, RemoveMillException {
        GameBoard gb = tracker.getGameBoard();

        if (!checkValidPosition(position)) {
            // non-existent gameboard coordinates given
            throw new NonexistentPositionException();
        } else if (checkPositionUnoccupied(gb, position)) {
            // tried to remove token from slot with no tokens
            throw new RemoveEmptySlotException();
        } else if (tracker.isSelfToken(playerUserName, position)) {
            // player tried to remove their own token
            throw new RemoveSelfTokenException();
        } else if (millChecker.checkMill2(position, playerColor, gb)) {
            // player tried to remove token in opponent's mills
            throw new RemoveMillException();
        } else {
            gb.removeToken(position);
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
