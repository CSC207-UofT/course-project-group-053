package UserInterface;/*
 * GUI is a subclass of JFrame where the game is displayed. An object of this class is created to run the game.
 */

import Controller.GamePlay1;
import Exceptions.InvalidPositionException;
import Exceptions.LoadedSuccessfully;
import Exceptions.SavedSuccessfully;
import Gateways.LeaderboardDataGateway;
import Interfaces.DataAdapter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;


public class GUI extends JFrame implements ActionListener, DataAdapter<String, Integer> {
    JPanel loginPanel, welcomePanel, whiteTokenPanel, blackTokenPanel, gamePanel, gamePanelWrapper, headerPanel, leaderboardPanel;
    DefaultButton saveButton;
    JFrame tutorialPopup;
    GamePlay1 gamePlay;

    public GUI() {
        initiateGUI();
    }

    /**
     * Initiates all instance attributes of GUI except gamePlay, headerPanel,
     * and tutorial Popup, since the first two require information that the user/gateways will give later,
     * while the latter should only be initialized when the user chooses to watch the tutorial.
     * Calls helper method to set the preferred JFrame settings.
     * Adds the panels for the starting part (welcomePanel and loginPanel).
     */
    private void initiateGUI() {
        loginPanel = new LoginPanel();
        welcomePanel = new WelcomePanel();
        whiteTokenPanel = new TokenPanel("W");
        blackTokenPanel = new TokenPanel("B");
        gamePanel = new GamePanel();
        leaderboardPanel = new LeaderboardPanel();
        //gamePlay and headerPanel must be initialized later

        //gamePanelWrapper is used to fix a size for GamePanel because if
        //it was added directly into the frame it would not have a fixed size.
        gamePanelWrapper = new JPanel();
        gamePanelWrapper.setLayout(new BoxLayout(gamePanelWrapper, BoxLayout.PAGE_AXIS));
        gamePanelWrapper.setBackground(Color.white);

        gamePlay = new GamePlay1();

        saveButton = new DefaultButton(Color.decode("#FF1B3A"),20, 65,
                250, "SAVE PROGRESS");

        setSettings();
        this.add(welcomePanel, BorderLayout.WEST);
        this.add(loginPanel, BorderLayout.CENTER);
        this.setVisible(true);

        addActionEvent();//calling addActionEvent() method
    }

    /**
     * Helper method for initiateGUI. Set the title, bounds, background, layout
     * and closing operation for the GUI JFrame and makes sure it cannot be resized.
     */
    private void setSettings(){
        this.setTitle("Nine Men's Morris");
        this.setBounds(10, 10, 1500, 900);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(1500, 900));
        this.setResizable(true);
        this.getContentPane().setBackground(Color.white);
        this.setLayout(new BorderLayout());
    }

    /**
     * Defines a specific action listener for each button.
     * It does so by overriding actionPerformer for each button
     * Inside actionPerformed it calls a helper method.
     */
    public void addActionEvent() {
        ((LoginPanel) loginPanel).continueButton.addActionListener(e -> confirmButtonAction());

        ((LoginPanel) loginPanel).loadButton.addActionListener(e -> loadButtonAction());

        saveButton.addActionListener(e -> JOptionPane.showMessageDialog(null,
                gamePlay.saveGame(((HeaderPanel) headerPanel).gameState.getText())));

        ((WelcomePanel) welcomePanel).tutorialButton.addActionListener(e -> tutorialButtonAction());

        TokenButton[] tokenButtons = ((GamePanel) gamePanel).getTokenButtons();
        for(int i = 0; i < tokenButtons.length; i++){
            int finalI = i; //needs to make i final to add action listener to the ith tokenButton
            tokenButtons[i].addActionListener(e -> {
                try {
                    tokenButtonAction(finalI);
                } catch (SavedSuccessfully | LoadedSuccessfully | InvalidPositionException | IOException ex) {
                    ex.printStackTrace();
                }
            });
        }

        ((LeaderboardPanel) leaderboardPanel).exitGameButton.addActionListener(e ->
                this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING))); //close GUI;
        ((LeaderboardPanel) leaderboardPanel).newGameButton.addActionListener(e -> restart());
    }

    /**
     * Helper method for confirmButton actionPerformed.
     * Get the text in both text fields and check if any of them are empty.
     * If so, pop a message dialog saying that they cannot be blank.
     * Otherwise, update the frame to show the actual game.
     *
     */
    private void confirmButtonAction() {
        if(((LoginPanel) loginPanel).player1TextField.getText().equals("") ||
                ((LoginPanel) loginPanel).player2TextField.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Usernames cannot be blank!");
        }
        else{
            gamePlay.setPlayers(((LoginPanel) loginPanel).getPlayerUsername(1),
                    ((LoginPanel) loginPanel).getPlayerUsername(2));
            headerPanel = new HeaderPanel(gamePlay.getPlayerName(1), gamePlay.getPlayerName(2));
            goToGameFrame();
        }
    }

    private void loadButtonAction(){
        String[] gamePlayLoadedResult = gamePlay.loadGame();
        if (gamePlayLoadedResult.length == 1){
            JOptionPane.showMessageDialog(null, gamePlayLoadedResult);
        }

        else {
            ((LoginPanel) loginPanel).setPlayersUsername(gamePlayLoadedResult[0], gamePlayLoadedResult[1]);
            //update the available tokens to be placed
            for (int i = 9; i > Integer.parseInt(gamePlayLoadedResult[2]); i--){
                ((TokenPanel) whiteTokenPanel).removeToken();
            }
            for (int i = 9; i > Integer.parseInt(gamePlayLoadedResult[3]); i--){
                ((TokenPanel) blackTokenPanel).removeToken();
            }

            headerPanel = new HeaderPanel(gamePlay.getPlayerName(1), gamePlay.getPlayerName(2));
            ((HeaderPanel) headerPanel).setGameState(gamePlayLoadedResult[4]);
            setLoadedGameBoard();
            goToGameFrame();
        }
    }

    private void tutorialButtonAction(){
        tutorialPopup = new JFrame();
        tutorialPopup.setContentPane(new JLabel(new ImageIcon("res/tutorial1.gif")));
        tutorialPopup.setTitle("Tutorial");
        tutorialPopup.setBounds(10, 10, 1280, 720);
        tutorialPopup.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        tutorialPopup.setResizable(false);
        tutorialPopup.setVisible(true);
    }

    /**
     * Helper method for tokenButton actionPerformed.
     * Get the text in both text fields and check if any of them are empty.
     * If so, pop a message dialog saying that they cannot be blank.
     * Otherwise, update the frame to show the actual game.
     *
     * @param tokenIndex    the index of the desired tokenButton in the array
     *                      returned by getTokenButtons
     */
    private void tokenButtonAction(int tokenIndex) throws SavedSuccessfully, LoadedSuccessfully, InvalidPositionException, IOException {
        String gameState = ((HeaderPanel) headerPanel).gameState.getText();
        TokenButton tokenButton = ((GamePanel) gamePanel).getTokenButtons()[tokenIndex];

        if(gameState.equals(gamePlay.getPlayerName(1) + "'s turn to add a token") & tokenButton.addable){
            tokenButton.setColour("W");
            ((TokenPanel) whiteTokenPanel).removeToken();

            int currentPlayerHouses = gamePlay.getPlayerHouses(1);
            gamePlay.move_token(1, adaptData(tokenIndex));
            if(gamePlay.playerMadeMill(currentPlayerHouses, 1)){
                ((HeaderPanel) headerPanel).setGameState(gamePlay.getPlayerName(1) + "'s turn to remove a token");
            }
            else{
                ((HeaderPanel) headerPanel).setGameState(gamePlay.getPlayerName(2) + "'s turn to add a token");
            }
            tokenButton.setAddable(false);
            tokenButton.setRemovable(true);
            tokenButton.setButtonVisual();
        }

        else if(gameState.equals(gamePlay.getPlayerName(2) + "'s turn to add a token") & tokenButton.addable){
            tokenButton.setColour("B");
            ((TokenPanel) blackTokenPanel).removeToken();

            int currentPlayerHouses = gamePlay.getPlayerHouses(2);
            gamePlay.move_token(2, adaptData(tokenIndex));
            if(gamePlay.playerMadeMill(currentPlayerHouses, 2)){
                ((HeaderPanel) headerPanel).setGameState(gamePlay.getPlayerName(2) + "'s turn to remove a token");
            }
            else{
                ((HeaderPanel) headerPanel).setGameState(gamePlay.getPlayerName(1) + "'s turn to add a token");
            }
            tokenButton.setAddable(false);
            tokenButton.setRemovable(true);
            tokenButton.setButtonVisual();
        }

        if(gameState.equals(gamePlay.getPlayerName(1) + "'s turn to remove a token")
                & tokenButton.removable & tokenButton.colour.equals("B")){
            tokenButton.setColour("");

            if(gamePlay.remove_token(2, adaptData(tokenIndex)).equals("")){
                ((HeaderPanel) headerPanel).setGameState(gamePlay.getPlayerName(2) + "'s turn to add a token");
                tokenButton.setAddable(true);
                tokenButton.setRemovable(false);
                tokenButton.setButtonVisual();
            }
            else{
                JOptionPane.showMessageDialog(null, gamePlay.remove_token(2, adaptData(tokenIndex)));
            }
        }
        else if(gameState.equals(gamePlay.getPlayerName(2) + "'s turn to remove a token")
                & tokenButton.removable & tokenButton.colour.equals("W")){
            tokenButton.setColour("");

            if(gamePlay.remove_token(1, adaptData(tokenIndex)).equals("")){
                ((HeaderPanel) headerPanel).setGameState(gamePlay.getPlayerName(1) + "'s turn to add a token");
                tokenButton.setAddable(true);
                tokenButton.setRemovable(false);
                tokenButton.setButtonVisual();
            }
            else{
                JOptionPane.showMessageDialog(null, gamePlay.remove_token(1, adaptData(tokenIndex)));
            }
        }

        gamePanel.revalidate();
        gamePanel.repaint();

        gamePanelWrapper.revalidate();
        gamePanelWrapper.repaint();

        this.revalidate();
        this.repaint();

        gamePlay.updateEndOfP1();
        if(gamePlay.endOfP1 & (tokenButton.addable | tokenButton.removable)){
            endGame();
        }
    }

    private void endGame() throws IOException {
        TokenButton[] tokenButtons = ((GamePanel) gamePanel).getTokenButtons();
        for (TokenButton button : tokenButtons) {
            button.setAddable(false);
            button.setRemovable(false);
            button.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
        String winner = gamePlay.getWinner();
        ((HeaderPanel) headerPanel).setGameState(winner);

        if(!winner.equals("It's a Tie")){
            LeaderboardDataGateway.addWin(winner.substring(0, winner.length() - 4));
        }

        ((LeaderboardPanel) leaderboardPanel).setTopPlayers(LeaderboardDataGateway.getTopPlayers());
        goToLeaderboardFrame();
    }


    /**
     * Helper method for confirmButtonAction. Deletes all current JPanels in the frame.
     * add the JPanels of the actual game and changes the frame's background colour.
     * Updates the frame to show the changes.
     */
    private void goToGameFrame(){
        this.remove(loginPanel);
        this.remove(welcomePanel);

        gamePanelWrapper.add(Box.createRigidArea(new Dimension(0, 50)));
        gamePanelWrapper.add(gamePanel);
        gamePanelWrapper.add(Box.createRigidArea(new Dimension(0, 50)));
        gamePanelWrapper.add(saveButton);
        gamePanelWrapper.add(Box.createRigidArea(new Dimension(0, 10)));

        this.add(whiteTokenPanel, BorderLayout.WEST);
        this.add(blackTokenPanel, BorderLayout.EAST);
        this.add(gamePanelWrapper, BorderLayout.CENTER);
        this.add(headerPanel, BorderLayout.NORTH);

        this.setBackground(Color.decode("#FF1B3A"));

        this.revalidate();
        this.repaint();
    }

    public void goToLeaderboardFrame() {
        this.remove(whiteTokenPanel);
        this.remove(blackTokenPanel);
        this.remove(gamePanelWrapper);

        gamePanelWrapper.remove(saveButton);

        this.add(gamePanelWrapper, BorderLayout.CENTER);
        this.add(leaderboardPanel, BorderLayout.EAST);

        this.revalidate();
        this.repaint();
    }

    public void setLoadedGameBoard(){
        ArrayList<String> whiteTokenCoord = gamePlay.getTokenCoordinates("W");
        ArrayList<String> blackTokenCoord = gamePlay.getTokenCoordinates("B");

        TokenButton[] tokenButtons = ((GamePanel) gamePanel).getTokenButtons();

        for(int i = 0; i < 24; i++){
            if(whiteTokenCoord.contains(adaptData(i))){
                tokenButtons[i].setColour("W");
                tokenButtons[i].setAddable(false);
                tokenButtons[i].setRemovable(true);
                tokenButtons[i].setButtonVisual();
            }
            else if(blackTokenCoord.contains(adaptData(i))){
                tokenButtons[i].setColour("B");
                tokenButtons[i].setAddable(false);
                tokenButtons[i].setRemovable(true);
                tokenButtons[i].setButtonVisual();
            }
        }
    }

    /**
     * Removes all current frames and initiates the GUI again by calling the same helper method
     * used in the constructor.
     */
    private void restart() {
        this.remove(leaderboardPanel);
        this.remove(gamePanelWrapper);
        this.remove(headerPanel);

        initiateGUI();

        this.revalidate();
        this.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    /**
     * Convert between the button indexing format used in GUI ([0-23])
     * to the one used in gamePlay ([A-C][1-8]).
     *
     * @param data index in the list of button stored in gamePlay
     * @return String of the button's position in the [A-C][1-8] format
     */
    @Override
    public String adaptData(Integer data) {
        HashMap<Integer, String> guiDataToGameData = new HashMap<>();
        guiDataToGameData.put(0, "A1");
        guiDataToGameData.put(1, "A2");
        guiDataToGameData.put(2, "A3");
        guiDataToGameData.put(9, "A4");
        guiDataToGameData.put(14, "A5");
        guiDataToGameData.put(21, "A6");
        guiDataToGameData.put(22, "A7");
        guiDataToGameData.put(23, "A8");

        guiDataToGameData.put(3, "B1");
        guiDataToGameData.put(4, "B2");
        guiDataToGameData.put(5, "B3");
        guiDataToGameData.put(10, "B4");
        guiDataToGameData.put(13, "B5");
        guiDataToGameData.put(18, "B6");
        guiDataToGameData.put(19, "B7");
        guiDataToGameData.put(20, "B8");

        guiDataToGameData.put(6, "C1");
        guiDataToGameData.put(7, "C2");
        guiDataToGameData.put(8, "C3");
        guiDataToGameData.put(11, "C4");
        guiDataToGameData.put(12, "C5");
        guiDataToGameData.put(15, "C6");
        guiDataToGameData.put(16, "C7");
        guiDataToGameData.put(17, "C8");

        return guiDataToGameData.get(data);
    }
}