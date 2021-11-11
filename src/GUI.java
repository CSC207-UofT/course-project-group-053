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


    private void setSettings(){
        this.setTitle("Nine Men's Morris");
        this.setBounds(10, 10, 1500, 900);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.white);
        this.setLayout(new BorderLayout());
    }

    public void addActionEvent() {
        //adding Action listener to components
        ((LoginPanel) loginPanel).continueButton.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(((LoginPanel) loginPanel).player1TextField.getText().equals("") ||
                ((LoginPanel) loginPanel).player2TextField.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Usernames cannot be blank!");
        }
        // else if for when the username already exists; to be implemented after
        else{
            goToGameFrame();
        }
    }

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
}

class b {
    public static void main(String[] args) {
        GUI frame = new GUI();
    }
}