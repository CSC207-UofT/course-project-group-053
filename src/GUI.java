/*
 * GUI is a subclass of JFrame where the game is displayed. An object of this class is created to run the game.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;


public class GUI extends JFrame implements ActionListener, DataAdapter<String, Integer> {
    JPanel loginPanel, welcomePanel, whiteTokenPanel, blackTokenPanel, gamePanel, gamePanelWrapper, headerPanel;
    GamePlay1 gamePlay;

    public GUI() {
        loginPanel = new LoginPanel();
        welcomePanel = new WelcomePanel();
        whiteTokenPanel = new TokenPanel("W");
        blackTokenPanel = new TokenPanel("B");
        gamePanel = new GamePanel();
        //gamePlay and headerPanel must be initialized later

        //gamePanelWrapper is used to fix a size for GamePanel because if
        //it was added directly into the frame it would not have a fixed size.
        gamePanelWrapper = new JPanel();
        gamePanelWrapper.setLayout(new BoxLayout(gamePanelWrapper, BoxLayout.PAGE_AXIS));
        gamePanelWrapper.setBackground(Color.white);

        setSettings();
        this.add(welcomePanel, BorderLayout.WEST);
        this.add(loginPanel, BorderLayout.CENTER);
        this.setVisible(true);

        addActionEvent();//calling addActionEvent() method
    }

    /**
     * Helper method for constructor. Set the title, bounds, background, layout
     * and closing operation for the GUI JFrame and makes sure it cannot be resized.
     */
    private void setSettings(){
        this.setTitle("Nine Men's Morris");
        this.setBounds(10, 10, 1500, 900);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.white);
        this.setLayout(new BorderLayout());
    }

    /**
     * Defines a specific action listener for each button.
     * It does so by overriding actionPerformer for each button
     * Inside actionPerformed it calls a helper method.
     */
    public void addActionEvent() {
        ((LoginPanel) loginPanel).continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               confirmButtonAction(e);
            }
        });

        TokenButton[] tokenButtons = ((GamePanel) gamePanel).getTokenButtons();
        for(int i = 0; i < tokenButtons.length; i++){
            int finalI = i; //needs to make i final to add action listener to the ith tokenButton
            tokenButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    tokenButtonAction(e, finalI);
                }
            });
        }
    }

    /**
     * Helper method for confirmButton actionPerformed.
     * Get the text in both text fields and check if any of them are empty.
     * If so, pop a message dialog saying that they cannot be blank.
     * Otherwise, update the frame to show the actual game.
     *
     * @param e   the ActionEvent object from actionPerformed
     */
    public void confirmButtonAction(ActionEvent e) {
        if(((LoginPanel) loginPanel).player1TextField.getText().equals("") ||
                ((LoginPanel) loginPanel).player2TextField.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Usernames cannot be blank!");
        }
        else{
            goToGameFrame();
        }
    }

    /**
     * Helper method for tokenButton actionPerformed.
     * Get the text in both text fields and check if any of them are empty.
     * If so, pop a message dialog saying that they cannot be blank.
     * Otherwise, update the frame to show the actual game.
     *
     * @param e   the ActionEvent object from actionPerformed
     * @param tokenIndex    the index of the desired tokenButton in the array
     *                      returned by getTokenButtons
     */
    public void tokenButtonAction(ActionEvent e, int tokenIndex) {
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

            if(gameState.equals(gamePlay.getPlayerName(1) + "'s turn to remove a token") & tokenButton.removable){
                tokenButton.setColour("");

                gamePlay.remove_token(2, adaptData(tokenIndex));

                ((HeaderPanel) headerPanel).setGameState(gamePlay.getPlayerName(2) + "'s turn to add a token");
                tokenButton.setAddable(true);
                tokenButton.setRemovable(false);
                tokenButton.setButtonVisual();
            }
            else if(gameState.equals(gamePlay.getPlayerName(2) + "'s turn to remove a token") & tokenButton.removable){
                tokenButton.setColour("");

                gamePlay.remove_token(1, adaptData(tokenIndex));

                ((HeaderPanel) headerPanel).setGameState(gamePlay.getPlayerName(1) + "'s turn to add a token");
                tokenButton.setAddable(true);
                tokenButton.setRemovable(false);
                tokenButton.setButtonVisual();
            }

        gamePlay.updateEndOfP1();

        if(gamePlay.endOfP1){
            tokenButton.setAddable(false);
            tokenButton.setRemovable(false);
            ((HeaderPanel) headerPanel).setGameState(gamePlay.getWinner());
        }

        gamePanel.revalidate();
        gamePanel.repaint();

        gamePanelWrapper.revalidate();
        gamePanelWrapper.repaint();

        this.revalidate();
        this.repaint();
        System.out.println(gamePlay.player1.get_numchipsleft() + "  " + gamePlay.player2.get_numchipsleft());
    }

    /**
     * Helper method for confirmButtonAction. Deletes all current JPanels in the frame.
     * add the JPanels of the actual game and changes the frame's background colour.
     * Updates the frame to show the changes.
     */
    private void goToGameFrame(){
        gamePlay = new GamePlay1(((LoginPanel) loginPanel).getPlayerUsername(1),
                ((LoginPanel) loginPanel).getPlayerUsername(2));

        headerPanel = new HeaderPanel(gamePlay.getPlayerName(1), gamePlay.getPlayerName(2));

        this.remove(loginPanel);
        this.remove(welcomePanel);

        gamePanelWrapper.add(Box.createRigidArea(new Dimension(0, 80)));
        gamePanelWrapper.add(gamePanel);

        this.add(whiteTokenPanel, BorderLayout.WEST);
        this.add(blackTokenPanel, BorderLayout.EAST);
        this.add(gamePanelWrapper, BorderLayout.CENTER);
        this.add(headerPanel, BorderLayout.NORTH);

        this.setBackground(Color.decode("#FF1B3A"));

        this.revalidate();
        this.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public String adaptData(Integer data) {
        HashMap<Integer, String> guiDataToGameData = new HashMap();
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