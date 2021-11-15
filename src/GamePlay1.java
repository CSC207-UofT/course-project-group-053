public class GamePlay1 {
    GameBoardPlacer placer;
    GameBoardRemover remover;
    CheckMill checkMill;
    GameBoardManipulator gameBoardManipulator;
    GameBoardManager gameBoardManager;
    WinnerCalculator winnerCalculator;
    boolean endOfP1;
    Player player1, player2;

    public GamePlay1(String player1Name, String player2Name) throws ArrayIndexOutOfBoundsException, NullPointerException {
        placer = new GameBoardPlacer();
        remover = new GameBoardRemover();
        checkMill = new CheckMill();
        gameBoardManipulator = new GameBoardManipulator(placer, remover, checkMill);
        gameBoardManager = new GameBoardManager();
        endOfP1 = false;
        player1 = new Player(player1Name, "W");
        player2 = new Player(player2Name, "B");
        winnerCalculator = new WinnerCalculator(this, player1, player2);
    }

    public int getPlayerHouses(int playerNum){
        return checkMill.getPlayerHouses(playerNum);
    }

    public boolean playerMadeMill(int playerHousesBeforeMove, int playerNum){
        return checkMill.getPlayerHouses(playerNum) > playerHousesBeforeMove;
    }

    public String getWinner(){
        return winnerCalculator.who_won();
    }

    public String getPlayerName(int playerNum){
        if(playerNum == 1){
            return player1.get_username();
        }
        else if(playerNum == 2){
            return player2.get_username();
        }
        else{
            return "";
        }
    }

    public void move_token(int playerNum, String setTokenPosition) throws ArrayIndexOutOfBoundsException, NullPointerException {
        Player player;
        if (playerNum == 1){player = player1;}
        else {player = player2;}

        while (true){
            try {
                if (setTokenPosition.equals("save")){
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
                if (setTokenPosition.equals("load")) {
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
                gameBoardManipulator.placeToken(token, setTokenPosition);

                // reduce player 1's chips by 1
                player.dec_numchipsleft();
                // player 1 has successfully placed down a token, so break out of the while loop

                checkMill.checkMill(setTokenPosition, player.get_tokencolour(), gameBoardManipulator.getGameboard());
                break;

            } catch (LoadedSuccessfully | SavedSuccessfully |InvalidPositionException | ArrayIndexOutOfBoundsException | NullPointerException e) {
                System.out.println(e.getMessage());
                // skip the invalid token and ask for prompt again
            }
        }
        // Now check if the player1 has created a mill
        //gameBoardManager.checkHouse();
    }

    public void updateEndOfP1(){
        endOfP1 = player1.int_player_numchipsonboard == 0 & player2.int_player_numchipsleft == 0;
    }

    public void remove_token(int playerNum, String removeTokenPosition) {
        Player player;
        if (playerNum == 1){player = player1;}
        else {player = player2;}

        while (true) {
            try {
                if (removeTokenPosition.equals("save")){
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
                if (removeTokenPosition.equals("load")) {
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
                gameBoardManipulator.removeToken(removeTokenPosition, player.get_tokencolour());
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
