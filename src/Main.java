import java.util.*;


public class Main {
    public static String Black = "B";
    public static String White = "W";
    public static HumanPlayer1 player1;
    public static HumanPlayer2 player2;

    public static void main(String[] args) throws InvalidPositionException, RemoveEmptySlotException, RemoveSelfTokenException, RemoveMillException {
        List<Player> lst = Start();
        GamePlay(lst);
    }



    /**
     * Setting name and color for players
     *
     * @return Player[]{player1, player2}
     */
    public static List<Player> Start(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Initializing Nine Men Morris \r\n");

        //Setting for player
        System.out.println("1. Type name of the Human Player: \r\n");
        String name_firstinput = sc.nextLine();
        System.out.print("Choose Colour for: " + name_firstinput + ". Type B for Black or W for white \r\n");
        String color_firstinput = sc.nextLine();

        //if user types wrong form
        while (!color_firstinput.equals(Black) & !color_firstinput.equals(White)){
            System.out.print("Choose Colour for: " + name_firstinput + ". Type B for Black or W for white \r\n");
            color_firstinput = sc.nextLine();
        }

        //Setting for other player
        System.out.print("2. Type name of another Human Player: \r\n");
        String name_secondinput = sc.nextLine();

        String color_secondinput;

        //user w/ white is player1
        if (color_firstinput.equals(White)) {
            color_secondinput = Black;
            // done initializing player 1
            player1 = new HumanPlayer1(name_firstinput, White);
            player2 = new HumanPlayer2(name_secondinput, Black);
        }else {
            color_secondinput = White;
            player1 = new HumanPlayer1(name_secondinput, White);
            player2 = new HumanPlayer2(name_firstinput, Black);
        }

        System.out.print("The colour for " + name_secondinput + " is " + color_secondinput + ". ");
        System.out.print(player1.get_username()+" will be playing first.\n\n");

        return List.of(new Player[]{player1, player2});
    }


    /**
     *
     *
     * throws InvalidPositionException for checkHouse()
     *
     * @param playerList list of player1, player2 setted by start()
     */
    public static void GamePlay(List<Player> playerList) throws InvalidPositionException, ArrayIndexOutOfBoundsException, NullPointerException, RemoveEmptySlotException, RemoveMillException, RemoveSelfTokenException {
        GameBoardManager gbManager = new GameBoardManager();
        Scanner sc = new Scanner(System.in);

        HumanPlayer1 player1 = new HumanPlayer1(playerList.get(0).get_username(), playerList.get(0).get_tokencolour());
        HumanPlayer2 player2 = new HumanPlayer2(playerList.get(1).get_username(), playerList.get(1).get_tokencolour());

        String name1 = player1.get_username();
        String name2 = player2.get_username();
        String col1 = player1.get_tokencolour();
        String col2 = player2.get_tokencolour();

        System.out.println("Starting Game between " + name1 + " and " + name2);

        // print the initial gameboard state, before players make any moves
        System.out.println(gbManager.getGameBoardState());

        // keep track of whether both players have run out of chips/tokens to place
        // when they do, phase 1 of the game ends
        boolean end_of_p1 = (player1.get_numchipsleft() == 0 & player2.get_numchipsleft() == 0);

        // while loop to run phase 1 of game, where players lay all their chips on the board
        while (!end_of_p1) {

            int player1Houses, player2Houses;
            player1Houses = gbManager.getPlayer1Houses();
            player2Houses = gbManager.getPlayer2Houses();

            while(true) {
                try {
                    System.out.println(name1 + "'s turn. Place " + col1 + " token. Choose an empty slot");
                    // TODO: in gameboard manager add a function that returns a playerList of positions available

                    // player 1 inputs gameboard position to place their token in
                    String t1 = sc.nextLine();
                    gbManager.processPlayerMove(player1.get_tokencolour(), t1);

                    // player 1 has successfully placed down a token, so break out of the while loop
                    break;

                } catch(InvalidPositionException | ArrayIndexOutOfBoundsException | NullPointerException e){
                    System.out.println(e.getMessage());
                    // skip the invalid token and ask for prompt again

                }
            }

            // reduce player 1's chips by 1
            player1.dec_numchipsleft();

            // print gameboard state after player 1 places down a chip
            System.out.println(gbManager.getGameBoardState());


            // Now check if the player1 has created a mill
            // Then let the player1 remove player2's token
            // Do it until the move is valid. If not valid pass
            // Then show the current state again
            gbManager.checkHouse();
            if (gbManager.getPlayer1Houses() > player1Houses){
                player1Houses = gbManager.getPlayer1Houses();
                while (true){
                    try {
                        System.out.println(name1 + "'s turn. Choose a token to remove");
                        // in gameboard manager add a function that returns a playerList of positions available
                        String r1 = sc.nextLine();

                        // TODO - whenever processPlayerRemove called, have a catch block to deal with InvalidRemovalException
                        gbManager.processPlayerRemove(1, r1);
                        break;
                    } catch(InvalidPositionException | ArrayIndexOutOfBoundsException | NullPointerException | InvalidRemovalException e){
                        System.out.println(e.getMessage());
                        // skip the invalid token and ask for prompt again
                    }

                }
                //shows state after removing opponents token
                System.out.println(gbManager.getGameBoardState());
            }



            while(true){
                try{
                    System.out.println(name2 + "'s turn. Place " + col2 + " token. Choose an empty place");
                    // in gameboard manager add a function that returns a playerList of positions available
                    String t2 = sc.nextLine();
                    gbManager.processPlayerMove(player2.get_tokencolour(), t2);
                    break;
                }catch(InvalidPositionException | ArrayIndexOutOfBoundsException | NullPointerException e){
                    System.out.println(e.getMessage());
                    // skip the invalid token
                }
            }

            player2.dec_numchipsleft();
            System.out.println(gbManager.getGameBoardState());

            // Now check if the player2 has created a mill
            // Then let the player1 remove player2's token
            // Do it until the move is valid. If not valid pass
            gbManager.checkHouse();
            if (gbManager.getPlayer2Houses() > player2Houses){
                player2Houses = gbManager.getPlayer2Houses();
                while (true){
                    try{
                        System.out.println(name2 + "'s turn. Choose a token to remove");
                        // in gameboard manager add a function that returns a playerList of positions available
                        String r2 = sc.nextLine();
                        gbManager.processPlayerRemove(2, r2);
                        break;
                    }catch(InvalidPositionException | ArrayIndexOutOfBoundsException | NullPointerException | InvalidRemovalException e){
                        System.out.println(e.getMessage());
                        // t1 = sc.next(); // skip the invalid token
                        // continue; is not required
                    }

                }
                //shows state after removing opponents token
                System.out.println(gbManager.getGameBoardState());
            }
            end_of_p1 = (player1.get_numchipsleft() == 0 & player2.get_numchipsleft() == 0);
        }
    }
}

//TODO: Fix output strings for copied blocks
