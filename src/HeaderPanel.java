import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class HeaderPanel extends JPanel {
    JPanel header;
    JLabel player1, player2, gameState;
    private final int PIXELS = 5;
    private static final long serialVersionUID = 1L;

    public HeaderPanel(String player1Name, String player2Name){
        super();

        createDropShadowPanel();

        header = new JPanel();
        header.setLayout(new BorderLayout());
        header.setBackground(Color.decode("#FF1B3A"));

        player1 = new JLabel("  " + player1Name);
        player2 = new JLabel(player2Name + "  ");
        gameState = new JLabel(player1Name + "'s turn to add a token");

        setLabelSettings();

        header.add(player1, BorderLayout.WEST);
        header.add(player2, BorderLayout.EAST);
        header.add(gameState, BorderLayout.CENTER);
        header.add(Box.createRigidArea(new Dimension(0, 30)), BorderLayout.NORTH);
        header.add(Box.createRigidArea(new Dimension(0, 30)), BorderLayout.SOUTH);

        this.add(header);
    }

    private void setLabelSettings(){
        player1.setFont(new Font("Segoe UI Light", Font.PLAIN, 38));
        player2.setFont(new Font("Segoe UI Light", Font.PLAIN, 38));
        gameState.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 45));

        player1.setForeground(Color.white);
        player2.setForeground(Color.white);
        gameState.setForeground(Color.white);

        gameState.setHorizontalAlignment(JLabel.CENTER);
    }

    public void setGameState(String state){
        gameState.setText(state);
    }

    //the following methods to create a shadow effect were adapted from
    //stackoverflow.com/questions/13368103/jpanel-drop-shadow
    public void createDropShadowPanel() {
        Border border = BorderFactory.createEmptyBorder(0, 0, PIXELS, 0);
        this.setBorder(BorderFactory.createCompoundBorder(this.getBorder(), border));
        this.setLayout(new BorderLayout());
        this.setBackground(Color.decode("#FF1B3A"));
    }

    @Override
    protected void paintComponent(Graphics g) {
        int shade = 80;
        int topOpacity = 100;

        g.setColor(new Color(10, 10, 10));
        g.drawRect(0, 0, this.getWidth() - 1, this.getHeight() - 1);

        g.setColor(new Color(70, 6, 15));
        g.drawRect(1, 1, this.getWidth() - 3, this.getHeight() - 3);

        g.setColor(new Color(130, 12, 30));
        g.drawRect(2, 2, this.getWidth() - 5, this.getHeight() - 5);

        g.setColor(new Color(190, 20, 48));
        g.drawRect(3, 3, this.getWidth() - 7, this.getHeight() - 7);

        g.setColor(new Color(255, 27, 58));
        g.drawRect(4, 4, this.getWidth() - 9, this.getHeight() - 9);
    }
}