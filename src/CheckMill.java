import java.util.*;

public class CheckMill {
    // Stores the location of mills formed by both players
    public static HashMap<Integer, Set<List<String>>> playerMills = new HashMap<>();

    public CheckMill() {
        // Stores the mills for player 1
        // represent player mills as list of strings (ex: A1 A2 A3), and store the lists in the HashSets
        HashSet<List<String>> player1Mills = new HashSet<>();

        // Stores the mills for player 2
        HashSet<List<String>> player2Mills = new HashSet<>();

        // playerMills ends up as a hashmap, mapping player number to HashSets containing each player's mills
        playerMills.put(1, player1Mills);
        playerMills.put(2, player2Mills);
    }


    private String getItemInGameBoard(String targetPosition, GameBoard gameboard) throws NonexistentPositionException {
        if (! targetPosition.matches(GameBoard.EMPTY_SLOT_PATTERN)) {
            // position given isn't formatted properly/doesn't exist on gameboard
            throw new NonexistentPositionException();
        } else {
            return gameboard.getTokenAtPosition(targetPosition);
        }
    }

    public void millAdder(String position, String[] mill, GameBoard gameboard) throws InvalidPositionException {
        // checks which player the mill belongs to, and add the mill to the player's mills
        if (getItemInGameBoard(position, gameboard).equals("W")) {
            playerMills.get(1).add(List.of(mill));
        } else {
            playerMills.get(2).add(List.of(mill));
        }

    }

    public void checkMill(String position, String colour, GameBoard gameboard) throws InvalidPositionException {
        List<List<String>> combinations = new ArrayList<List<String>>();
        List<String> innerList1 = new ArrayList<>();
        innerList1.add("A1");
        innerList1.add("A2");
        innerList1.add("A3");
        combinations.add(innerList1);

        List<String> innerList2 = new ArrayList<>();
        innerList2.add("B1");
        innerList2.add("B2");
        innerList2.add("B3");
        combinations.add(innerList2);

        List<String> innerList3 = new ArrayList<>();
        innerList3.add("C1");
        innerList3.add("C2");
        innerList3.add("C3");
        combinations.add(innerList3);

        List<String> innerList4 = new ArrayList<>();
        innerList4.add("A1");
        innerList4.add("A4");
        innerList4.add("A6");
        combinations.add(innerList4);

        List<String> innerList5 = new ArrayList<>();
        innerList5.add("B1");
        innerList5.add("B4");
        innerList5.add("B6");
        combinations.add(innerList5);

        List<String> innerList6 = new ArrayList<>();
        innerList6.add("C1");
        innerList6.add("C4");
        innerList6.add("C6");
        combinations.add(innerList6);

        List<String> innerList7 = new ArrayList<>();
        innerList7.add("A3");
        innerList7.add("A5");
        innerList7.add("A8");
        combinations.add(innerList7);

        List<String> innerList8 = new ArrayList<>();
        innerList8.add("B3");
        innerList8.add("B5");
        innerList8.add("B8");
        combinations.add(innerList8);

        List<String> innerList9 = new ArrayList<>();
        innerList9.add("C3");
        innerList9.add("C5");
        innerList9.add("C8");
        combinations.add(innerList9);

        List<String> innerList10 = new ArrayList<>();
        innerList10.add("A6");
        innerList10.add("A7");
        innerList10.add("A8");
        combinations.add(innerList10);

        List<String> innerList11 = new ArrayList<>();
        innerList11.add("B6");
        innerList11.add("B7");
        innerList11.add("B8");
        combinations.add(innerList11);

        List<String> innerList12 = new ArrayList<>();
        innerList12.add("C6");
        innerList12.add("C7");
        innerList12.add("C8");
        combinations.add(innerList12);

        List<String> innerList13 = new ArrayList<>();
        innerList13.add("A2");
        innerList13.add("B2");
        innerList13.add("C2");
        combinations.add(innerList13);

        List<String> innerList14 = new ArrayList<>();
        innerList14.add("A4");
        innerList14.add("B4");
        innerList14.add("C4");
        combinations.add(innerList14);

        List<String> innerList15 = new ArrayList<>();
        innerList15.add("A7");
        innerList15.add("B7");
        innerList15.add("C7");
        combinations.add(innerList15);

        List<String> innerList16 = new ArrayList<>();
        innerList16.add("A5");
        innerList16.add("B5");
        innerList16.add("C5");
        combinations.add(innerList16);

        for (List<String> lo : combinations) {
            if (lo.contains(position)) {
                Boolean found = false;
                for (String o : lo) {
                    if (getItemInGameBoard(o, gameboard) == null) {
                        found = false;
                        break;
                    } else if (!getItemInGameBoard(o, gameboard).equals(colour)) {
                        found = false;
                        break;
                    } else {
                        found = true;
                    }
                }
                if (found) {
                    millAdder(lo.get(0), new String[]{lo.get(0), lo.get(1), lo.get(2)}, gameboard);
                }
            }

        }
    }

    public Boolean checkMill2(String position, String colour, GameBoard gameboard) throws InvalidPositionException {
        List<List<String>> combinations = new ArrayList<List<String>>();
        List<String> innerList1 = new ArrayList<>();
        innerList1.add("A1");
        innerList1.add("A2");
        innerList1.add("A3");
        combinations.add(innerList1);

        List<String> innerList2 = new ArrayList<>();
        innerList2.add("B1");
        innerList2.add("B2");
        innerList2.add("B3");
        combinations.add(innerList2);

        List<String> innerList3 = new ArrayList<>();
        innerList3.add("C1");
        innerList3.add("C2");
        innerList3.add("C3");
        combinations.add(innerList3);

        List<String> innerList4 = new ArrayList<>();
        innerList4.add("A1");
        innerList4.add("A4");
        innerList4.add("A6");
        combinations.add(innerList4);

        List<String> innerList5 = new ArrayList<>();
        innerList5.add("B1");
        innerList5.add("B4");
        innerList5.add("B6");
        combinations.add(innerList5);

        List<String> innerList6 = new ArrayList<>();
        innerList6.add("C1");
        innerList6.add("C4");
        innerList6.add("C6");
        combinations.add(innerList6);

        List<String> innerList7 = new ArrayList<>();
        innerList7.add("A3");
        innerList7.add("A5");
        innerList7.add("A8");
        combinations.add(innerList7);

        List<String> innerList8 = new ArrayList<>();
        innerList8.add("B3");
        innerList8.add("B5");
        innerList8.add("B8");
        combinations.add(innerList8);

        List<String> innerList9 = new ArrayList<>();
        innerList9.add("C3");
        innerList9.add("C5");
        innerList9.add("C8");
        combinations.add(innerList9);

        List<String> innerList10 = new ArrayList<>();
        innerList10.add("A6");
        innerList10.add("A7");
        innerList10.add("A8");
        combinations.add(innerList10);

        List<String> innerList11 = new ArrayList<>();
        innerList11.add("B6");
        innerList11.add("B7");
        innerList11.add("B8");
        combinations.add(innerList11);

        List<String> innerList12 = new ArrayList<>();
        innerList12.add("C6");
        innerList12.add("C7");
        innerList12.add("C8");
        combinations.add(innerList12);

        List<String> innerList13 = new ArrayList<>();
        innerList13.add("A2");
        innerList13.add("B2");
        innerList13.add("C2");
        combinations.add(innerList13);

        List<String> innerList14 = new ArrayList<>();
        innerList14.add("A4");
        innerList14.add("B4");
        innerList14.add("C4");
        combinations.add(innerList14);

        List<String> innerList15 = new ArrayList<>();
        innerList15.add("A7");
        innerList15.add("B7");
        innerList15.add("C7");
        combinations.add(innerList15);

        List<String> innerList16 = new ArrayList<>();
        innerList16.add("A5");
        innerList16.add("B5");
        innerList16.add("C5");
        combinations.add(innerList16);

        for (List<String> lo : combinations) {
            if (lo.contains(position)) {
                Boolean found = false;
                for (String o : lo) {
                    if (getItemInGameBoard(o, gameboard) == null) {
                        found = false;
                        break;
                    } else if (!getItemInGameBoard(o, gameboard).equals(colour)) {
                        found = false;
                        break;
                    } else {
                        found = true;
                    }
                }
                if (found) {
                    return found;
                }
            }

        }
        return false;
    }

    public static int getPlayerHouses(int player_number) {
        return playerMills.get(player_number).size();
    }

}
