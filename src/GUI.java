/*
 * GUI is a subclass of JFrame where the game is displayed. An object of this class is created to run the game.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GUI extends JFrame implements ActionListener {
    JPanel loginPanel, welcomePanel, whiteTokenPanel, blackTokenPanel, gamePanel, gamePanelWrapper, headerPanel;

    public GUI() {
        loginPanel = new LoginPanel();
        welcomePanel = new WelcomePanel();
        whiteTokenPanel = new TokenPanel("W");
        blackTokenPanel = new TokenPanel("B");
        gamePanel = new GamePanel();
        headerPanel = new HeaderPanel();

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

        if (tokenButton.clickable){
            if(gameState.equals("Player 1's turn")){
                tokenButton.setColour("W");
                ((TokenPanel) whiteTokenPanel).removeToken();
                ((HeaderPanel) headerPanel).gameState.setText("Player 2's turn");
            }
            else if(gameState.equals("Player 2's turn")){
                tokenButton.setColour("B");
                ((TokenPanel) blackTokenPanel).removeToken();
                ((HeaderPanel) headerPanel).gameState.setText("Player 1's turn");
            }
            tokenButton.setClickable(false);
            tokenButton.setButtonVisual();
            tokenButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }

        gamePanel.revalidate();
        gamePanel.repaint();

        gamePanelWrapper.revalidate();
        gamePanelWrapper.repaint();

        this.revalidate();
        this.repaint();
    }

    /**
     * Helper method for confirmButtonAction. Deletes all current JPanels in the frame.
     * add the JPanels of the actual game and changes the frame's background colour.
     * Updates the frame to show the changes.
     */
    private void goToGameFrame(){
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
}

class b {
    public static void main(String[] args) {
        GUI frame = new GUI();
    }
}