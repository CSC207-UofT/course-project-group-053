import javax.swing.*;
import java.awt.*;

public class WelcomePanel extends JPanel{
    JLabel welcomeLabel, welcomeImageLabel;

    public WelcomePanel(){
        super();
        welcomeLabel = new JLabel("WELCOME TO NINE MEN'S MORRIS!");

        ImageIcon welcomeGif = new ImageIcon("welcomeImg.gif");
        welcomeGif.setImage(welcomeGif.getImage().getScaledInstance(450, 450, Image.SCALE_DEFAULT));
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

        welcomeImageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcomeImageLabel.setSize(new Dimension(300, 300));
    }

    private void addComponentsToPanel() {
        this.add(Box.createRigidArea(new Dimension(900, 100)));
        this.add(welcomeLabel);
        this.add(Box.createRigidArea(new Dimension(0, 100)));
        this.add(welcomeImageLabel);
    }

}
