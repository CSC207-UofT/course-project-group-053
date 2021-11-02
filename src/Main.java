import java.util.*;


public class Main {
    public static String Black = "B";
    public static String White = "W";
    public static Player player1;
    public static Player player2;


    public static void main(String[] args) throws InvalidPositionException, RemoveEmptySlotException, RemoveSelfTokenException, RemoveMillException {
        List<Player> playerList = Start();
        GamePlay1 gamePlay1 = new GamePlay1(playerList, true);

        player1 = new Player(playerList.get(0).get_username(), playerList.get(0).get_tokencolour());
        player2 = new Player(playerList.get(1).get_username(), playerList.get(1).get_tokencolour());

        String player1_username = player1.get_username();
        String player2_username = player2.get_username();
        String player1_tokencolour = player1.get_tokencolour();
        String player2_tokencolour = player2.get_tokencolour();

        System.out.println("Starting Game between " + player1_username + " and " + player2_username);

        // while loop to run phase 1 of game, where players lay all their chips on the board
        while (!gamePlay1.getBoolean_end_of_p1()) {
            gamePlay1.move_token(player1);
            // Then let the player1 remove player2's token
            // Do it until the move is valid. If not valid pass
            // Then show the current state again
            gamePlay1.remove_token(player1);

            // Now check if the player2 has created a mill
            // Then let the player1 remove player2's token
            // Do it until the move is valid. If not valid pass
            gamePlay1.move_token(player2);

            gamePlay1.remove_token(player2);

            gamePlay1.setBoolean_end_of_p1(player1.get_numchipsleft() == 0 & player2.get_numchipsleft() == 0);
        }


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
            player1 = new Player(name_firstinput, White);
            player2 = new Player(name_secondinput, Black);
        }else {
            color_secondinput = White;
            player1 = new Player(name_secondinput, White);
            player2 = new Player(name_firstinput, Black);
        }

        System.out.print("The colour for " + name_secondinput + " is " + color_secondinput + ". ");
        System.out.print(player1.get_username()+" will be playing first.\n\n");

        return List.of(new Player[]{player1, player2});
    }

}

//TODO: Fix output strings for copied blocks
