import java.util.List;
import java.util.Scanner;

public class GamePlay1 {

    public GamePlay1(List<Player> playerList) throws InvalidPositionException, ArrayIndexOutOfBoundsException, NullPointerException, RemoveEmptySlotException, RemoveMillException, RemoveSelfTokenException {
        GameBoardManager gameBoardManager = new GameBoardManager();
        Scanner sc = new Scanner(System.in);

        Player player1 = new Player(playerList.get(0).get_username(), playerList.get(0).get_tokencolour());
        Player player2 = new Player(playerList.get(1).get_username(), playerList.get(1).get_tokencolour());

        String player1_username = player1.get_username();
        String player2_username = player2.get_username();
        String player1_tokencolour = player1.get_tokencolour();
        String player2_tokencolour = player2.get_tokencolour();

        System.out.println("Starting Game between " + player1_username + " and " + player2_username);

        // print the initial gameboard state, before players make any moves
        System.out.println(gameBoardManager.getGameBoardState());

        // keep track of whether both players have run out of chips/tokens to place
        // when they do, phase 1 of the game ends
        boolean end_of_p1 = (player1.get_numchipsleft() == 0 & player2.get_numchipsleft() == 0);

        // while loop to run phase 1 of game, where players lay all their chips on the board
        while (!end_of_p1) {

            int player1Houses = gameBoardManager.getPlayer1Houses();
            int player2Houses = gameBoardManager.getPlayer2Houses();

            while (true) {
                try {
                    System.out.println(player1_username + "'s turn. Place " + player1_tokencolour + " token. Choose an empty slot");
                    // TODO: in gameboard manager add a function that returns a playerList of positions available

                    // player 1 inputs gameboard position to place their token in
                    String t1 = sc.nextLine();
                    gameBoardManager.processPlayerMove(player1.get_tokencolour(), t1);

                    // player 1 has successfully placed down a token, so break out of the while loop
                    break;

                } catch (InvalidPositionException | ArrayIndexOutOfBoundsException | NullPointerException e) {
                    System.out.println(e.getMessage());
                    // skip the invalid token and ask for prompt again

                }
            }

            // reduce player 1's chips by 1
            player1.dec_numchipsleft();

            // print gameboard state after player 1 places down a chip
            System.out.println(gameBoardManager.getGameBoardState());


            // Now check if the player1 has created a mill
            // Then let the player1 remove player2's token
            // Do it until the move is valid. If not valid pass
            // Then show the current state again
            gameBoardManager.checkHouse();
            if (gameBoardManager.getPlayer1Houses() > player1Houses) {
                player1Houses = gameBoardManager.getPlayer1Houses();
                while (true) {
                    try {
                        System.out.println(player1_username + "'s turn. Choose a token to remove");
                        // in gameboard manager add a function that returns a playerList of positions available
                        String r1 = sc.nextLine();

                        // TODO - whenever processPlayerRemove called, have a catch block to deal with InvalidRemovalException
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


            while (true) {
                try {
                    System.out.println(player2_username + "'s turn. Place " + player2_tokencolour + " token. Choose an empty place");
                    // in gameboard manager add a function that returns a playerList of positions available
                    String t2 = sc.nextLine();
                    gameBoardManager.processPlayerMove(player2.get_tokencolour(), t2);
                    break;
                } catch (InvalidPositionException | ArrayIndexOutOfBoundsException | NullPointerException e) {
                    System.out.println(e.getMessage());
                    // skip the invalid token
                }
            }

            player2.dec_numchipsleft();
            System.out.println(gameBoardManager.getGameBoardState());

            // Now check if the player2 has created a mill
            // Then let the player1 remove player2's token
            // Do it until the move is valid. If not valid pass
            gameBoardManager.checkHouse();
            if (gameBoardManager.getPlayer2Houses() > player2Houses) {
                player2Houses = gameBoardManager.getPlayer2Houses();
                while (true) {
                    try {
                        System.out.println(player2_username + "'s turn. Choose a token to remove");
                        // in gameboard manager add a function that returns a playerList of positions available
                        String r2 = sc.nextLine();
                        gameBoardManager.processPlayerRemove(2, r2);
                        break;
                    } catch (InvalidPositionException | ArrayIndexOutOfBoundsException | NullPointerException | InvalidRemovalException e) {
                        System.out.println(e.getMessage());
                        // t1 = sc.next(); // skip the invalid token
                        // continue; is not required
                    }

                }
                //shows state after removing opponents token
                System.out.println(gameBoardManager.getGameBoardState());
            }
            end_of_p1 = (player1.get_numchipsleft() == 0 & player2.get_numchipsleft() == 0);

        }
    }
}
