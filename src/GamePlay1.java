import java.util.List;
import java.util.Scanner;

public class GamePlay1 {
    GameBoardManager gameBoardManager = new GameBoardManager();
    Scanner sc = new Scanner(System.in);
    Boolean boolean_end_of_p1;

    public GamePlay1(List<Player> playerList, Boolean boolean_end_of_p1) throws InvalidPositionException, ArrayIndexOutOfBoundsException, NullPointerException {
        Player player1 = new Player(playerList.get(0).get_username(), playerList.get(0).get_tokencolour());
        Player player2 = new Player(playerList.get(1).get_username(), playerList.get(1).get_tokencolour());

        // keep track of whether both players have run out of chips/tokens to place
        // when they do, phase 1 of the game ends
        this.boolean_end_of_p1 = (player1.get_numchipsleft() == 0 & player2.get_numchipsleft() == 0);

        // print the initial gameboard state, before players make any moves
        System.out.println(gameBoardManager.getGameBoardState());
    }

    public void move_token(Player player) throws InvalidPositionException, ArrayIndexOutOfBoundsException, NullPointerException {
        while (true){
            try {
                System.out.println(player.get_username() + "'s turn. Place " + player.get_tokencolour() + " token. Choose an empty slot");
                // player 1 inputs gameboard position to place their token in
                String t1 = sc.nextLine();
                gameBoardManager.processPlayerMove(player.get_tokencolour(), t1);

                // player 1 has successfully placed down a token, so break out of the while loop
                break;

            } catch (InvalidPositionException | ArrayIndexOutOfBoundsException | NullPointerException e) {
                System.out.println(e.getMessage());
                // skip the invalid token and ask for prompt again
            }
            // reduce player 1's chips by 1
            player.dec_numchipsleft();

            // print gameboard state after player 1 places down a chip
            System.out.println(gameBoardManager.getGameBoardState());
        }
        // Now check if the player1 has created a mill
        gameBoardManager.checkHouse();

    }

    public void remove_token(Player player) {
        int player1Houses = gameBoardManager.getPlayer1Houses();
        int player2Houses = gameBoardManager.getPlayer2Houses();

        if (gameBoardManager.getPlayer1Houses() > player1Houses) {
            player1Houses = gameBoardManager.getPlayer1Houses();
            while (true) {
                System.out.println(player.get_username() + "'s turn. Choose a token to remove");
                // in gameboard manager add a function that returns a playerList of positions available
                String r1 = sc.nextLine();
                try {
                    gameBoardManager.processPlayerRemove(1, r1);
                    break;
                } catch (InvalidPositionException | ArrayIndexOutOfBoundsException | NullPointerException | InvalidRemovalException e) {
                    System.out.println(e.getMessage());
                    // skip the invalid token and ask for prompt again
                }

            }
            //shows state after removing opponents token
            System.out.println(gameBoardManager.getGameBoardState());
        }
    }

    public void setBoolean_end_of_p1(Boolean end_of_p1) {
        this.boolean_end_of_p1 = end_of_p1;
    }
    public boolean getBoolean_end_of_p1() {
        return this.boolean_end_of_p1;
    }
}
