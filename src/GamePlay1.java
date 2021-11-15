import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class GamePlay1 {
    GameBoardPlacer placer = new GameBoardPlacer();
    GameBoardRemover remover = new GameBoardRemover();
    CheckMill checkMill = new CheckMill();
    GameBoardManipulator gameBoardManipulator = new GameBoardManipulator(placer, remover, checkMill);
    Scanner sc = new Scanner(System.in);

    public GamePlay1(List<Player> playerList) throws SavedSuccessfully, LoadedSuccessfully, InvalidPositionException, ArrayIndexOutOfBoundsException,
            NullPointerException {
        Player player1 = new Player(playerList.get(0).get_username(), playerList.get(0).get_tokencolour());
        Player player2 = new Player(playerList.get(1).get_username(), playerList.get(1).get_tokencolour());
        System.out.println("Starting Game between " + player1.get_username() + " and " + player2.get_username());

        // print the initial gameboard state, before players make any moves
        //TODO: Print GameBoard

        // keep track of whether both players have run out of chips/tokens to place
        // when they do, phase 1 of the game ends
        boolean end_of_p1 = false;

        // while loop to run phase 1 of game, where players lay all their chips on the board
        while (!end_of_p1) {
            int player1Houses = checkMill.getPlayerHouses(1);
            int player2Houses = checkMill.getPlayerHouses(2);

            move_token(player1);
            //if player1 has made a mill; process remove
            if (checkMill.getPlayerHouses(1) > player1Houses) {
                player1Houses = CheckMill.getPlayerHouses(1);
                remove_token(player1);
            }


            move_token(player2);
            //if player2 has made a mill; process remove
            if (checkMill.getPlayerHouses(2) > player2Houses) {
                player2Houses = checkMill.getPlayerHouses(2);
                remove_token(player2);
            }
            end_of_p1 = (player1.get_numchipsleft() == 0 & player2.get_numchipsleft() == 0);
        }

    }

    public void move_token(Player player) throws SavedSuccessfully, LoadedSuccessfully, InvalidPositionException, ArrayIndexOutOfBoundsException, NullPointerException {
        while (true){
            try {
                System.out.println(player.get_username() + "'s turn. Place " + player.get_tokencolour() + " token. Choose an empty slot");
                // player 1 inputs gameboard position to place their token in
                String setToken_position = sc.nextLine();
                if (setToken_position.equals("save")){
                    boolean saved_data = false;
                    GameSaveData save_file = new GameSaveData(gameBoardManipulator.getGameboard());
                    try{
                        GameState.save(save_file, "gamestate1.save");
                        saved_data = true;
                    } catch (Exception e) {
                        System.out.println("Couldn't save:" + e.getMessage());
                    }
                    if (saved_data){throw new SavedSuccessfully("Game saved successfully");}
                }
                if (setToken_position.equals("load")) {
                    boolean loaded_data = false;
                        try {
                            GameSaveData saveData = (GameSaveData) GameState.load("gamestate1.save");
                            gameBoardManipulator.setGameboard(saveData.savedGameboard);
                            loaded_data = true;
                        } catch (Exception e) {
                            System.out.println("Couldn't load:" + e.getMessage());
                        }
                        if (loaded_data){throw new LoadedSuccessfully("Game loaded successfully");
                        }
                    }


                Token token = new Token(player.get_username(), player.get_tokencolour());

                //TODO: make a Token
                //InsertToken(token, position)
                gameBoardManipulator.placeToken(token, setToken_position);

                // reduce player 1's chips by 1
                player.dec_numchipsleft();
                // player 1 has successfully placed down a token, so break out of the while loop


                checkMill.checkMill(setToken_position, player.get_tokencolour(), gameBoardManipulator.getGameboard());
                break;

            } catch (LoadedSuccessfully | SavedSuccessfully |InvalidPositionException | ArrayIndexOutOfBoundsException | NullPointerException e) {
                System.out.println(e.getMessage());
                // skip the invalid token and ask for prompt again
            }

            // print gameboard state after player 1 places down a chip
            //TODO: print gameboard
        }
        // Now check if the player1 has created a mill
        //gameBoardManager.checkHouse();

    }

    public void remove_token(Player player) throws LoadedSuccessfully, SavedSuccessfully {
        while (true) {
            System.out.println(player.get_username() + "'s turn. Choose a token to remove");
            // in gameboard manager add a function that returns a playerList of positions available
            String removeToken_position = sc.nextLine();
            try {
                if (removeToken_position.equals("save")){
                    boolean saved_data = false;
                    GameSaveData save_file = new GameSaveData(gameBoardManipulator.getGameboard());
                    try{
                        GameState.save(save_file, "gamestate1.save");
                        saved_data = true;
                    } catch (Exception e) {
                        System.out.println("Couldn't save:" + e.getMessage());
                    }
                    if (saved_data){throw new SavedSuccessfully("Game saved successfully");}
                }
                if (removeToken_position.equals("load")) {
                    boolean loaded_data = false;
                    try {
                        GameSaveData saveData = (GameSaveData) GameState.load("gamestate1.save");
                        gameBoardManipulator.setGameboard(saveData.savedGameboard);
                        loaded_data = true;
                    } catch (Exception e) {
                        System.out.println("Couldn't load:" + e.getMessage());
                    }
                    if (loaded_data){throw new LoadedSuccessfully("Game loaded successfully");
                    }
                }
                gameBoardManipulator.removeToken(removeToken_position, player.get_tokencolour());
                break;
            } catch (SavedSuccessfully | LoadedSuccessfully |InvalidPositionException | ArrayIndexOutOfBoundsException | NullPointerException | InvalidRemovalException e) {
                System.out.println(e.getMessage());
                // skip the invalid token and ask for prompt again
            }

        }
        //shows state after removing opponents token
        //TODO: print gameboard
        //TODO: change duplicate code to suitable method
        //TODO: add loading at start of game and add loading of player names etc as well
        //TODO: save which player's turn it was and start from that player's turn when gamestate is loaded. Might have to rework gameplay1 slightly
    }
}
