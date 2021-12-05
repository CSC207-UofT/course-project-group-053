package UserInterface;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class GamePanel extends JPanel {
    TokenButton[] tokens;
    GridBagLayout layout;
    GridBagConstraints gbc;
    Image gridImage;

    public GamePanel(){
        super();

        layout = new GridBagLayout();
        gbc = new GridBagConstraints();

        this.setMaximumSize(new Dimension(550, 550));
        this.setLayout(layout);

        ImageIcon img = new ImageIcon("res/gridImage.jpg");
        img.setImage(img.getImage().getScaledInstance(550, 550, Image.SCALE_DEFAULT));
        gridImage = img.getImage();

        tokens = new TokenButton[24];
        initializeTokens();
        addTokensToPanel();
    }

    public void initializeTokens(){
        for(int i = 0; i < tokens.length; i++){
            tokens[i] = new TokenButton("");
            tokens[i].setPreferredSize(new Dimension(40, 40));
        }
    }

    private void addTokensToPanel(){
        addComponents(tokens[0], 0, 0);
        addComponents(tokens[1], 3, 0);
        addComponents(tokens[2], 6, 0);

        addComponents(tokens[3], 1, 1);
        addComponents(tokens[4], 3, 1);
        addComponents(tokens[5], 5, 1);

        addComponents(tokens[6], 2, 2);
        addComponents(tokens[7], 3, 2);
        addComponents(tokens[8], 4, 2);

        addComponents(tokens[9], 0, 3);
        addComponents(tokens[10], 1, 3);
        addComponents(tokens[11], 2, 3);
        addComponents(tokens[12], 4, 3);
        addComponents(tokens[13], 5, 3);
        addComponents(tokens[14], 6, 3);

        addComponents(tokens[15], 2, 4);
        addComponents(tokens[16], 3, 4);
        addComponents(tokens[17], 4, 4);

        addComponents(tokens[18], 1, 5);
        addComponents(tokens[19], 3, 5);
        addComponents(tokens[20], 5, 5);

        addComponents(tokens[21], 0, 6);
        addComponents(tokens[22], 3, 6);
        addComponents(tokens[23], 6, 6);
    }

    public TokenButton[] getTokenButtons(){
        return tokens;
    }

    public ArrayList<TokenButton> getPlayerTokenButtons(int playerNum){
        ArrayList<TokenButton> playerTokenButtons = new ArrayList<>();
        String colour;
        if (playerNum == 1){ colour = "W"; }
        else { colour = "B"; }

        for (TokenButton token : tokens) {
            if (token.getColour().equals(colour)) {
                playerTokenButtons.add(token);
            }
        }
        return playerTokenButtons;
    }

    private void addComponents(Component component, int gridx, int gridy){
        gbc.gridx = gridx;
        gbc.gridy = gridy;

        gbc.gridwidth = 1;
        gbc.gridheight = 1;

        gbc.weightx = 1;
        gbc.weighty = 1;

        layout.setConstraints(component, gbc);
        this.add(component);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(gridImage, 0, 0, null);
    }
}
