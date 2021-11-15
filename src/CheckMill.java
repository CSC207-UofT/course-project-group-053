import java.util.*;

public class CheckMill {
    private GameBoard gameBoard;

    // store an empty gameboard at first
    private final GameBoard gb = new GameBoard();
    // Stores the location of mills formed by both players
    public static HashMap<Integer, Set<List<String>>> playerMills = new HashMap<>();

    //Stores possible mill combinations
    private List<List<String>> mill_combinations = new ArrayList<List<String>>();

    public CheckMill(GameBoard gameboard) {
        this.gameBoard = gameboard;

        //List of possible mill combinations
        List<String> innerList1 = new ArrayList<>();
        innerList1.add("A1");
        innerList1.add("A2");
        innerList1.add("A3");
        mill_combinations.add(innerList1);

        List<String> innerList2 = new ArrayList<>();
        innerList2.add("B1");
        innerList2.add("B2");
        innerList2.add("B3");
        mill_combinations.add(innerList2);

        List<String> innerList3 = new ArrayList<>();
        innerList3.add("C1");
        innerList3.add("C2");
        innerList3.add("C3");
        mill_combinations.add(innerList3);

        List<String> innerList4 = new ArrayList<>();
        innerList4.add("A1");
        innerList4.add("A4");
        innerList4.add("A6");
        mill_combinations.add(innerList4);

        List<String> innerList5 = new ArrayList<>();
        innerList5.add("B1");
        innerList5.add("B4");
        innerList5.add("B6");
        mill_combinations.add(innerList5);

        List<String> innerList6 = new ArrayList<>();
        innerList6.add("C1");
        innerList6.add("C4");
        innerList6.add("C6");
        mill_combinations.add(innerList6);

        List<String> innerList7 = new ArrayList<>();
        innerList7.add("A3");
        innerList7.add("A5");
        innerList7.add("A8");
        mill_combinations.add(innerList7);

        List<String> innerList8 = new ArrayList<>();
        innerList8.add("B3");
        innerList8.add("B5");
        innerList8.add("B8");
        mill_combinations.add(innerList8);

        List<String> innerList9 = new ArrayList<>();
        innerList9.add("C3");
        innerList9.add("C5");
        innerList9.add("C8");
        mill_combinations.add(innerList9);

        List<String> innerList10 = new ArrayList<>();
        innerList10.add("A6");
        innerList10.add("A7");
        innerList10.add("A8");
        mill_combinations.add(innerList10);

        List<String> innerList11 = new ArrayList<>();
        innerList11.add("B6");
        innerList11.add("B7");
        innerList11.add("B8");
        mill_combinations.add(innerList11);

        List<String> innerList12 = new ArrayList<>();
        innerList12.add("C6");
        innerList12.add("C7");
        innerList12.add("C8");
        mill_combinations.add(innerList12);

        List<String> innerList13 = new ArrayList<>();
        innerList13.add("A2");
        innerList13.add("B2");
        innerList13.add("C2");
        mill_combinations.add(innerList13);

        List<String> innerList14 = new ArrayList<>();
        innerList14.add("A4");
        innerList14.add("B4");
        innerList14.add("C4");
        mill_combinations.add(innerList14);

        List<String> innerList15 = new ArrayList<>();
        innerList15.add("A7");
        innerList15.add("B7");
        innerList15.add("C7");
        mill_combinations.add(innerList15);

        List<String> innerList16 = new ArrayList<>();
        innerList16.add("A5");
        innerList16.add("B5");
        innerList16.add("C5");
        mill_combinations.add(innerList16);

        // Stores the mills for player 1
        // represent player mills as list of strings (ex: A1 A2 A3), and store the lists in the HashSets
        HashSet<List<String>> player1Mills = new HashSet<>();

        // Stores the mills for player 2
        HashSet<List<String>> player2Mills = new HashSet<>();

        // playerMills ends up as a hashmap, mapping player number to HashSets containing each player's mills
        playerMills.put(1, player1Mills);
        playerMills.put(2, player2Mills);
    }


    private String getItemInGameBoard(String targetPosition) throws NonexistentPositionException {
        if (! targetPosition.matches(GameBoard.EMPTY_SLOT_PATTERN)) {
            // position given isn't formatted properly/doesn't exist on gameboard
            throw new NonexistentPositionException();
        } else {
            return gameBoard.getTokenAtPosition(targetPosition);
        }
    }

    // Checks which player the mill belongs to, and add the mill to the player's mills
    public void millAdder(String position, String[] mill) throws InvalidPositionException {
        if (getItemInGameBoard(position).equals("W")) {
            playerMills.get(1).add(List.of(mill));
        } else {
            playerMills.get(2).add(List.of(mill));
        }

    }

    /**
     * If player adds a token that makes a new mill, add mill.
     * @param position String representation of a token to check, in format [ABC][1-8]
     * @param colour colour of the player who is placing the token
     * @return Boolean representing if the position is in a mill
     */

    public boolean check_addMill(String position, String colour) throws InvalidPositionException {
        Boolean found = false;

        for (List<String> lo : mill_combinations) {
            if (lo.contains(position)) {
                for (String o : lo) {
                    if (getItemInGameBoard(o) == null) {
                        found = false;
                        break;
                    } else if (!getItemInGameBoard(o).equals(colour)) {
                        found = false;
                        break;
                    } else {
                        found = true;
                    }
                }
                if (found) {
                    millAdder(lo.get(0), new String[]{lo.get(0), lo.get(1), lo.get(2)});
                }
            }

        }
        return found;
    }

    public static int getPlayerHouses(int player_number) {
        return playerMills.get(player_number).size();
    }

}
