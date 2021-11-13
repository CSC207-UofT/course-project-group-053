import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginPanel extends JPanel {
    JLabel player1Label, player2Label;
    JTextField player1TextField, player2TextField;
    JButton continueButton;

    public LoginPanel(){
        super();

        player1Label = new JLabel("Enter Player 1's username:");
        player1TextField = new JTextField();

        player2Label = new JLabel("Enter Player 2's username:");
        player2TextField = new JTextField();

        continueButton = new JButton("CONTINUE");

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setBackground(Color.white);

        setComponentsDesignAndSize();
        addComponentsToPanel();
    }


    private void setComponentsDesignAndSize() {
        player1Label.setBounds(50,150,100,25);
        player1Label.setFont(new Font("Segoe UI Light", Font.PLAIN, 33));
        player1Label.setAlignmentX(Component.CENTER_ALIGNMENT);

        player2Label.setBounds(50,150,100,25);
        player2Label.setFont(new Font("Segoe UI Light", Font.PLAIN, 33));
        player2Label.setAlignmentX(Component.CENTER_ALIGNMENT);

        player1TextField.setBounds(350,10,350,10);
        player1TextField.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 20));
        player1TextField.setMaximumSize(new Dimension(350, 30));
        player1TextField.setAlignmentX(Component.CENTER_ALIGNMENT);

        player2TextField.setBounds(350,10,350,10);
        player2TextField.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 20));
        player2TextField.setMaximumSize(new Dimension(350, 30));
        player2TextField.setAlignmentX(Component.CENTER_ALIGNMENT);

        continueButton.setBounds(200,300,200,30);
        continueButton.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
        continueButton.setForeground(Color.white);
        continueButton.setBackground(Color.decode("#FF1B3A"));
        continueButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    private void addComponentsToPanel() {
        this.add(Box.createRigidArea(new Dimension(0, 300)));
        this.add(player1Label);
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(player1TextField);
        this.add(Box.createRigidArea(new Dimension(0, 30)));
        this.add(player2Label);
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(player2TextField);
        this.add(Box.createRigidArea(new Dimension(0, 50)));
        this.add(continueButton);
        this.add(Box.createRigidArea(new Dimension(300, 200)));
    }
}
