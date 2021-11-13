import javax.swing.*;
import java.awt.*;

public class TokenButton extends JButton {
    String colour;
    boolean clickable;

    public TokenButton(String colour){
        super();

        this.setPreferredSize(new Dimension(40, 40));
        this.colour = colour;
        this.clickable = true;
        setButtonVisual();
    }

    public void setClickable(boolean clickable){
        this.clickable = clickable;
    }

    public void setColour(String colour){
        this.colour = colour;
    }

    public void setButtonVisual(){
        if (colour.equals("B")){
            ImageIcon tokenImg = new ImageIcon("res/blackToken.png");
            tokenImg.setImage(tokenImg.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
            this.setIcon(tokenImg);
        }
        else if (colour.equals("W")){
            ImageIcon tokenImg = new ImageIcon("res/whiteToken.png");
            tokenImg.setImage(tokenImg.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
            this.setIcon(tokenImg);
        }
        else{
            ImageIcon tokenImg = new ImageIcon("res/emptyToken.png");
            tokenImg.setImage(tokenImg.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
            this.setIcon(tokenImg);
        }

        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setBackground(null);
        this.setBorder(null);

        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
}
