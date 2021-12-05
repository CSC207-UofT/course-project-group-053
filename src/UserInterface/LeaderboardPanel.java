package UserInterface;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class LeaderboardPanel extends JPanel {
    JLabel[] topPlayersListLabel;
    JLabel leaderboardLabel;
    DefaultButton newGameButton, exitGameButton;

    public LeaderboardPanel() {
        super();

        topPlayersListLabel = new JLabel[10];

        for (int i = 0; i < 10; i++) {
            topPlayersListLabel[i] = new JLabel();
            setJLabelDesignAndSize(topPlayersListLabel[i]);
        }

        leaderboardLabel = new JLabel(" LEADERBOARD");
        leaderboardLabel.setBounds(50, 150, 100, 25);
        leaderboardLabel.setFont(new Font("Segoe UI Bold", Font.PLAIN, 65));
        leaderboardLabel.setForeground(Color.decode("#FF1B3A"));
        leaderboardLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        ImageIcon tokenImg = new ImageIcon("res/leaderboardIcon.png");
        tokenImg.setImage(tokenImg.getImage().getScaledInstance(55, 55, Image.SCALE_DEFAULT));
        leaderboardLabel.setIcon(tokenImg);

        newGameButton = new DefaultButton(Color.decode("#FF1B3A"), 20, 65, 250, "NEW GAME");
        exitGameButton = new DefaultButton(Color.decode("#FF1B3A"), 20, 65, 250, "EXIT GAME");

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setBackground(Color.white);
    }

    public void setTopPlayers(ArrayList<String> topPlayers){
        for (int i = 0; i < topPlayers.size(); i++) {
            topPlayersListLabel[i].setText((i+1) + " - " + topPlayers.get(i));
        }
        addComponentsToPanel();
    }

    private void setJLabelDesignAndSize(JLabel label) {
        label.setBounds(50, 150, 100, 25);
        label.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 25));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    private void addComponentsToPanel() {
        this.add(Box.createRigidArea(new Dimension(800, 15)));
        this.add(leaderboardLabel);
        this.add(Box.createRigidArea(new Dimension(0, 22)));

        for (JLabel jLabel : topPlayersListLabel) {
            this.add(jLabel);
            this.add(Box.createRigidArea(new Dimension(0, 8)));
        }
        this.add(Box.createRigidArea(new Dimension(0, 15)));
        this.add(newGameButton);
        this.add(Box.createRigidArea(new Dimension(0, 3)));
        this.add(exitGameButton);
    }
}