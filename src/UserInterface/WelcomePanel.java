package UserInterface;

import javax.swing.*;
import java.awt.*;

public class WelcomePanel extends JPanel{
    JLabel welcomeLabel, welcomeImageLabel, tutorialLabel;
    DefaultButton tutorialButton;

    public WelcomePanel(){
        super();
        welcomeLabel = new JLabel("WELCOME TO NINE MEN'S MORRIS!");
        tutorialLabel = new JLabel("Don't know how to play?");

        tutorialButton = new DefaultButton(Color.white, 25, 80,
                355, "WATCH A TUTORIAL");

        ImageIcon welcomeGif = new ImageIcon("res/welcomeImg.gif");
        welcomeGif.setImage(welcomeGif.getImage().getScaledInstance(400, 400, Image.SCALE_DEFAULT));
        welcomeImageLabel = new JLabel(welcomeGif);

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setBackground(Color.decode("#FF1B3A"));

        setComponentsDesignAndSize();
        addComponentsToPanel();
    }

    private void setComponentsDesignAndSize() {
        welcomeLabel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 40));
        welcomeLabel.setForeground(Color.white);
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        tutorialLabel.setFont(new Font("Segoe UI Light", Font.PLAIN, 33));
        tutorialLabel.setForeground(Color.white);
        tutorialLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        welcomeImageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcomeImageLabel.setSize(new Dimension(300, 300));
    }

    private void addComponentsToPanel() {
        this.add(Box.createRigidArea(new Dimension(900, 60)));
        this.add(welcomeLabel);
        this.add(Box.createRigidArea(new Dimension(0, 80)));
        this.add(welcomeImageLabel);
        this.add(Box.createRigidArea(new Dimension(0, 70)));
        this.add(tutorialLabel);
        this.add(Box.createRigidArea(new Dimension(0, 15)));
        this.add(tutorialButton);
    }

}
