package Controller;

import Entity.Player;
import Entity.Token;
import Exceptions.InvalidPositionException;
import Exceptions.InvalidRemovalException;
import Exceptions.LoadedSuccessfully;
import Exceptions.SavedSuccessfully;
import Gateways.data.GameSaveData;
import Gateways.data.GameState;
import UseCases.*;
import UserInterface.GamePanel;
import UserInterface.TokenButton;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class GamePlay1 {
    GameBoardPlacer placer;
    GameBoardRemover remover;
    public CheckMill checkMill;
    public GameBoardManipulator gameBoardManipulator;
    WinnerCalculator winnerCalculator;
    public boolean endOfP1;
    public PlayerManager playerManager;

    public GamePlay1(String player1Username, String player2Username) throws ArrayIndexOutOfBoundsException, NullPointerException {
        placer = new GameBoardPlacer();
        remover = new GameBoardRemover();
        checkMill = new CheckMill();
        gameBoardManipulator = new GameBoardManipulator(placer, remover, checkMill);
        endOfP1 = false;

        setPlayers(player1Username, player2Username);
    }

    public GamePlay1(){
        placer = new GameBoardPlacer();
        remover = new GameBoardRemover();
        checkMill = new CheckMill();
        gameBoardManipulator = new GameBoardManipulator(placer, remover, checkMill);
        endOfP1 = false;
        setPlayers("", "");
    }

    public void setPlayers(String player1Username, String player2Username){
        playerManager = new PlayerManager(player1Username, "W", player2Username, "B");
        winnerCalculator = new WinnerCalculator(this, playerManager.getPlayer(1), playerManager.getPlayer(2));
    }

    public int getPlayerHouses(int playerNum) {
        return checkMill.getPlayerHouses(playerNum);
    }

    public boolean playerMadeMill(int playerHousesBeforeMove, int playerNum) {
        return checkMill.getPlayerHouses(playerNum) > playerHousesBeforeMove;
    }

    public String getWinner() {
        return winnerCalculator.who_won();
    }

    public String getPlayerName(int playerNum) {
        if (playerNum == 1) {
            return playerManager.getPlayerUsername(1);
        } else if (playerNum == 2) {
            return playerManager.getPlayerUsername(2);
        } else {
            return "";
        }
    }

    public ArrayList<String> getTokenCoordinates(String colour){
        ArrayList<String> tokenCoordinates = new ArrayList<>();
        ArrayList<String> gameboardKeys = gameBoardManipulator.getKeys();
        for(String key : gameboardKeys) {
            if (gameBoardManipulator.getCorrespondentValue(key) != null){
                if(gameBoardManipulator.getCorrespondentValue(key).equals(colour)){
                    tokenCoordinates.add(key);
                }
            }
        }
        return tokenCoordinates;
    }

    public void move_token(int playerNum, String setTokenPosition) throws ArrayIndexOutOfBoundsException, NullPointerException {
        while (true) {
            try {
                Token token = new Token(playerManager.getPlayerUsername(playerNum), playerManager.getPlayerTokenColour(playerNum));
                gameBoardManipulator.placeToken(token, setTokenPosition);

                // reduce player 1's chips by 1 and update chips on board
                playerManager.decreasePlayerTokensLeft(playerNum);
                playerManager.updateNumPlayerTokensOnBoard(playerNum, 1);

                checkMill.checkMill(setTokenPosition, playerManager.getPlayerTokenColour(playerNum), gameBoardManipulator.getGameBoard());

                // player has successfully placed down a token, so break out of the while loop
                break;
            } catch (InvalidPositionException | ArrayIndexOutOfBoundsException | NullPointerException e) {
                System.out.println(e.getMessage());
                // skip the invalid token and ask for prompt again
            }
        }
    }

    public void updateEndOfP1() {
        endOfP1 = !playerManager.playersHaveTokensLeft();
    }

    public String saveGame(String gameState){
        GameSaveData save_file = new GameSaveData(playerManager.getPlayer(1),
                playerManager.getPlayer(2), gameBoardManipulator.getGameBoard(), gameState);
        try {
            GameState.save(save_file, "gamestate1.save");
            return "Game saved successfully";
        } catch (Exception e) {
            return "Couldn't save:" + e.getMessage();
        }
    }

    public String[] loadGame(){
        try {
            GameSaveData saveData = (GameSaveData) GameState.load("gamestate1.save");
            gameBoardManipulator.setGameBoard(saveData.savedGameboard);

            playerManager.setPlayer(1, saveData.getSavedPlayerUsername(1), saveData.getSavedPlayerTokensRemaining(1));
            playerManager.setPlayer(2, saveData.getSavedPlayerUsername(2), saveData.getSavedPlayerTokensRemaining(2));
            winnerCalculator = new WinnerCalculator(this, playerManager.getPlayer(1), playerManager.getPlayer(2));

            return new String[]{playerManager.getPlayerUsername(1),
                    playerManager.getPlayerUsername(2),
                    Integer.toString(playerManager.getTokensRemaining(1)),
                    Integer.toString(playerManager.getTokensRemaining(2)),
                    saveData.getSavedGameState()};

        } catch (Exception e) {
            return new String[]{"Couldn't load:" + e.getMessage()};
        }
    }

    /**
     * Removes token at position removeTokenPosition.
     *
     * @param playerNum indicates whether it is player1 or player2
     * @param removeTokenPosition position in the form of [A-C][1-8] of the token to be removed
     * @return empty string if token was removed; excpetion message otherwise.
     */
    public String remove_token(int playerNum, String removeTokenPosition) {
        try {
            if (removeTokenPosition.equals("save")) {
                boolean saved_data = false;
                GameSaveData save_file = new GameSaveData(gameBoardManipulator.getGameBoard());
                try {
                    GameState.save(save_file, "gamestate1.save");
                    saved_data = true;
                } catch (Exception e) {
                    System.out.println("Couldn't save:" + e.getMessage());
                }
                if (saved_data) {
                    throw new SavedSuccessfully("Game saved successfully");
                }
            }
            if (removeTokenPosition.equals("load")) {
                boolean loaded_data = false;
                try {
                    GameSaveData saveData = (GameSaveData) GameState.load("gamestate1.save");
                    gameBoardManipulator.setGameBoard(saveData.savedGameboard);
                    loaded_data = true;
                } catch (Exception e) {
                    System.out.println("Couldn't load:" + e.getMessage());
                }
                if (loaded_data) {
                    throw new LoadedSuccessfully("Game loaded successfully");
                }
            }
            gameBoardManipulator.removeToken(removeTokenPosition, playerManager.getPlayerTokenColour(playerNum), isSpecialCase(playerNum));
            if(isSpecialCase(playerNum)){ checkMill.removeTokenFromMill(removeTokenPosition, playerNum); }
            playerManager.updateNumPlayerTokensOnBoard(playerNum, -1);

        } catch (SavedSuccessfully | LoadedSuccessfully | InvalidPositionException | ArrayIndexOutOfBoundsException | NullPointerException | InvalidRemovalException e) {
            return e.getMessage();
            // skip the invalid token and ask for prompt again
        }
        return "";
    }

    private boolean isSpecialCase(int playerNum){
        String colour;
        if(playerNum == 1){ colour = "W"; }
        else { colour = "B"; }
        return playerManager.getPlayerNumOfTokens(playerNum) == checkMill.getPlayerHousesIndexes(colour).size();
    }
}
