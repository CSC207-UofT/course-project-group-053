import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class TokenPanel extends JPanel {
    ArrayList<JLabel> tokens;
    String tokenColour;

    public TokenPanel(String colour) {
        super();

        tokenColour = colour;

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setBackground(Color.decode("#FF1B3A"));

        tokens = new ArrayList<>();
        initializeTokens();
        addTokensToPanel();
    }
    private void initializeTokens() {
        String tokenFile;
        if (tokenColour.equals("B")){
            tokenFile = "res/blackToken.png";
        }
        else{
            tokenFile = "res/whiteToken.png";
        }

        ImageIcon tokenImg = new ImageIcon(tokenFile);
        tokenImg.setImage(tokenImg.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));

        for (int i = 0; i < 9; i++) {
            tokens.add(new JLabel(tokenImg));
            tokens.get(tokens.size() - 1).setAlignmentX(Component.CENTER_ALIGNMENT);
        }
    }

    private void addTokensToPanel(){
        this.add(Box.createRigidArea(new Dimension(150, 40)));
        for (JLabel token : tokens){
            this.add(token);
            this.add(Box.createRigidArea(new Dimension(0, 15)));
        }
    }

    public void removeToken(){
        this.remove(tokens.remove(tokens.size() - 1));
        this.revalidate();
        this.repaint();
    }
}
