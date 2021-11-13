import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class CheckMill {
    // store an empty gameboard at first
    private final GameBoard gb = new GameBoard();


    // Stores the location of mills formed by both players
    public HashMap<Integer, Set<List<String>>> playerMills;

    private String getItemInGameBoard(String targetPosition) throws NonexistentPositionException {
        if (! targetPosition.matches(GameBoard.EMPTY_SLOT_PATTERN)) {
            // position given isn't formatted properly/doesn't exist on gameboard
            throw new NonexistentPositionException();
        } else {
            return gb.getTokenAtPosition(targetPosition);
        }
    }

    public void millAdder(String position, String[] mill) throws InvalidPositionException {
        // checks which player the mill belongs to, and add the mill to the player's mills
        if (getItemInGameBoard(position).equals("W")) {
            playerMills.get(1).add(List.of(mill));
        } else {
            playerMills.get(2).add(List.of(mill));
        }

    }

    public void checkMill() throws InvalidPositionException {
        if (getItemInGameBoard("A1").equals(getItemInGameBoard("A2")))
            if (getItemInGameBoard("A2").equals(getItemInGameBoard("A3")) && !getItemInGameBoard("A1").equals(GameBoard.EMPTY_SLOT_PATTERN)) {
                millAdder("A1", new String[]{"A1", "A2", "A3"});
            }
        if (getItemInGameBoard("B1").equals(getItemInGameBoard("B2")) && getItemInGameBoard("B2").equals(getItemInGameBoard("B3")) && !getItemInGameBoard("B1").equals(GameBoard.EMPTY_SLOT_PATTERN)){
            millAdder("B1", new String[] {"B1", "B2", "B3"});
        }
        if (getItemInGameBoard("C1").equals(getItemInGameBoard("C2")) && getItemInGameBoard("C2").equals(getItemInGameBoard("C3")) && !getItemInGameBoard("C1").equals(GameBoard.EMPTY_SLOT_PATTERN)){
            millAdder("C1", new String[] {"C1", "C2", "C3"});
        }
        if (getItemInGameBoard("A1").equals(getItemInGameBoard("A4")) && getItemInGameBoard("A4").equals(getItemInGameBoard("A6")) && !getItemInGameBoard("A1").equals(GameBoard.EMPTY_SLOT_PATTERN)){
            millAdder("A1", new String[] {"A1", "A4", "A6"});
        }
        if (getItemInGameBoard("B1").equals(getItemInGameBoard("B4")) && getItemInGameBoard("B4").equals(getItemInGameBoard("B6")) && !getItemInGameBoard("B1").equals(GameBoard.EMPTY_SLOT_PATTERN)){
            millAdder("B1", new String[] {"B1", "B4", "B6"});
        }
        if (getItemInGameBoard("C1").equals(getItemInGameBoard("C4")) && getItemInGameBoard("C4").equals(getItemInGameBoard("C6")) && !getItemInGameBoard("C1").equals(GameBoard.EMPTY_SLOT_PATTERN)){
            millAdder("C1", new String[] {"C1", "C4", "C6"});
        }
        if (getItemInGameBoard("A3").equals(getItemInGameBoard("A5")) && getItemInGameBoard("A5").equals(getItemInGameBoard("A8")) && !getItemInGameBoard("A3").equals(GameBoard.EMPTY_SLOT_PATTERN)){
            millAdder("A3", new String[] {"A3", "A5", "A8"});
        }
        if (getItemInGameBoard("B3").equals(getItemInGameBoard("B5")) && getItemInGameBoard("B5").equals(getItemInGameBoard("B8")) && !getItemInGameBoard("B3").equals(GameBoard.EMPTY_SLOT_PATTERN)){
            millAdder("B3", new String[] {"B3", "B5", "B8"});
        }
        if (getItemInGameBoard("C3").equals(getItemInGameBoard("C5")) && getItemInGameBoard("C5").equals(getItemInGameBoard("C8")) && !getItemInGameBoard("C3").equals(GameBoard.EMPTY_SLOT_PATTERN)){
            millAdder("C1", new String[] {"C3", "C5", "C8"});
        }
        if (getItemInGameBoard("A6").equals(getItemInGameBoard("A7")) && getItemInGameBoard("A7").equals(getItemInGameBoard("A8")) && !getItemInGameBoard("A6").equals(GameBoard.EMPTY_SLOT_PATTERN)){
            millAdder("A6", new String[] {"A6", "A7", "A8"});
        }
        if (getItemInGameBoard("B6").equals(getItemInGameBoard("B7")) && getItemInGameBoard("B7").equals(getItemInGameBoard("B8")) && !getItemInGameBoard("B6").equals(GameBoard.EMPTY_SLOT_PATTERN)){
            millAdder("B6", new String[] {"B6", "B7", "B8"});
        }
        if (getItemInGameBoard("C6").equals(getItemInGameBoard("C7")) && getItemInGameBoard("C7").equals(getItemInGameBoard("C8")) && !getItemInGameBoard("C6").equals(GameBoard.EMPTY_SLOT_PATTERN)){
            millAdder("C6", new String[] {"C6", "C7", "C8"});
        }



    }
}
