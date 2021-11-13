import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CheckMill {
    private GameBoard gameBoard;


    // store an empty gameboard at first
    private final GameBoard gb = new GameBoard();
    // Stores the location of mills formed by both players
    public static HashMap<Integer, Set<List<String>>> playerMills = new HashMap<>();

    public CheckMill(GameBoard gameboard) {
        this.gameBoard = gameboard;

        // Stores the mills for player 1
        // represent player mills as list of strings (ex: A1 A2 A3), and store the lists in the HashSets
        HashSet<List<String>> player1Mills = new HashSet<>();

        // Stores the mills for player 2
        HashSet<List<String>> player2Mills = new HashSet<>();

        // playerMills ends up as a hashmap, mapping player number to HashSets containing each player's mills
        playerMills.put(1, player1Mills);
        playerMills.put(2, player2Mills);
    }


    private String getItemInGameBoard(String targetPosition, GameBoard gameBoard) throws NonexistentPositionException {
        if (! targetPosition.matches(GameBoard.EMPTY_SLOT_PATTERN)) {
            // position given isn't formatted properly/doesn't exist on gameboard
            throw new NonexistentPositionException();
        } else {
            return gameBoard.getTokenAtPosition(targetPosition);
        }
    }

    public void millAdder(String position, String[] mill) throws InvalidPositionException {
        // checks which player the mill belongs to, and add the mill to the player's mills
        if (getItemInGameBoard(position, this.gameBoard).equals("W")) {
            playerMills.get(1).add(List.of(mill));
        } else {
            playerMills.get(2).add(List.of(mill));
        }

    }

    public void checkMill() throws InvalidPositionException {
        if (getItemInGameBoard("A1", this.gameBoard).equals(getItemInGameBoard("A2", this.gameBoard)))
            if (getItemInGameBoard("A2", this.gameBoard).equals(getItemInGameBoard("A3", this.gameBoard)) && !getItemInGameBoard("A1", this.gameBoard).equals(GameBoard.EMPTY_SLOT_PATTERN)) {
                millAdder("A1", new String[]{"A1", "A2", "A3"});
                System.out.println("CheckMill_A1-A2-A3 made");
            }

        if (!(getItemInGameBoard("B1", this.gameBoard)==null)){
            if (getItemInGameBoard("B1", this.gameBoard).equals(getItemInGameBoard("B2", this.gameBoard)) && getItemInGameBoard("B2", this.gameBoard).equals(getItemInGameBoard("B3", this.gameBoard)) && !getItemInGameBoard("B1", this.gameBoard).equals(GameBoard.EMPTY_SLOT_PATTERN)) {
                millAdder("B1", new String[]{"B1", "B2", "B3"});
            }
        }
        if (getItemInGameBoard("C1", this.gameBoard).equals(getItemInGameBoard("C2", this.gameBoard)) && getItemInGameBoard("C2", this.gameBoard).equals(getItemInGameBoard("C3", this.gameBoard)) && !getItemInGameBoard("C1", this.gameBoard).equals(GameBoard.EMPTY_SLOT_PATTERN)){
            millAdder("C1", new String[] {"C1", "C2", "C3"});
        }
        if (getItemInGameBoard("A1", this.gameBoard).equals(getItemInGameBoard("A4", this.gameBoard)) && getItemInGameBoard("A4", this.gameBoard).equals(getItemInGameBoard("A6", this.gameBoard)) && !getItemInGameBoard("A1", this.gameBoard).equals(GameBoard.EMPTY_SLOT_PATTERN)){
            millAdder("A1", new String[] {"A1", "A4", "A6"});
        }
        if (getItemInGameBoard("B1", this.gameBoard).equals(getItemInGameBoard("B4", this.gameBoard)) && getItemInGameBoard("B4", this.gameBoard).equals(getItemInGameBoard("B6", this.gameBoard)) && !getItemInGameBoard("B1", this.gameBoard).equals(GameBoard.EMPTY_SLOT_PATTERN)){
            millAdder("B1", new String[] {"B1", "B4", "B6"});
        }
        if (getItemInGameBoard("C1", this.gameBoard).equals(getItemInGameBoard("C4", this.gameBoard)) && getItemInGameBoard("C4", this.gameBoard).equals(getItemInGameBoard("C6", this.gameBoard)) && !getItemInGameBoard("C1", this.gameBoard).equals(GameBoard.EMPTY_SLOT_PATTERN)){
            millAdder("C1", new String[] {"C1", "C4", "C6"});
        }
        if (getItemInGameBoard("A3", this.gameBoard).equals(getItemInGameBoard("A5", this.gameBoard)) && getItemInGameBoard("A5", this.gameBoard).equals(getItemInGameBoard("A8", this.gameBoard)) && !getItemInGameBoard("A3", this.gameBoard).equals(GameBoard.EMPTY_SLOT_PATTERN)){
            millAdder("A3", new String[] {"A3", "A5", "A8"});
        }
        if (getItemInGameBoard("B3", this.gameBoard).equals(getItemInGameBoard("B5", this.gameBoard)) && getItemInGameBoard("B5", this.gameBoard).equals(getItemInGameBoard("B8", this.gameBoard)) && !getItemInGameBoard("B3", this.gameBoard).equals(GameBoard.EMPTY_SLOT_PATTERN)){
            millAdder("B3", new String[] {"B3", "B5", "B8"});
        }
        if (getItemInGameBoard("C3", this.gameBoard).equals(getItemInGameBoard("C5", this.gameBoard)) && getItemInGameBoard("C5", this.gameBoard).equals(getItemInGameBoard("C8", this.gameBoard)) && !getItemInGameBoard("C3", this.gameBoard).equals(GameBoard.EMPTY_SLOT_PATTERN)){
            millAdder("C1", new String[] {"C3", "C5", "C8"});
        }
        if (getItemInGameBoard("A6", this.gameBoard).equals(getItemInGameBoard("A7", this.gameBoard)) && getItemInGameBoard("A7", this.gameBoard).equals(getItemInGameBoard("A8", this.gameBoard)) && !getItemInGameBoard("A6", this.gameBoard).equals(GameBoard.EMPTY_SLOT_PATTERN)){
            millAdder("A6", new String[] {"A6", "A7", "A8"});
        }
        if (getItemInGameBoard("B6", this.gameBoard).equals(getItemInGameBoard("B7", this.gameBoard)) && getItemInGameBoard("B7", this.gameBoard).equals(getItemInGameBoard("B8", this.gameBoard)) && !getItemInGameBoard("B6", this.gameBoard).equals(GameBoard.EMPTY_SLOT_PATTERN)){
            millAdder("B6", new String[] {"B6", "B7", "B8"});
        }
        if (getItemInGameBoard("C6", this.gameBoard).equals(getItemInGameBoard("C7", this.gameBoard)) && getItemInGameBoard("C7", this.gameBoard).equals(getItemInGameBoard("C8", this.gameBoard)) && !getItemInGameBoard("C6", this.gameBoard).equals(GameBoard.EMPTY_SLOT_PATTERN)){
            millAdder("C6", new String[] {"C6", "C7", "C8"});
        }
    }

    public static int getPlayerHouses(int player_number) {
        System.out.println("CheckMIll_recent mill numb: " + playerMills.get(player_number).size());
        return playerMills.get(player_number).size();
    }

}
