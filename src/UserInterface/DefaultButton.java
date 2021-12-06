package UserInterface;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class DefaultButton extends JButton {
    private final Color buttonColour;
    private final int buttonHeight, buttonWidth;

    public DefaultButton(Color buttonColor, int textSize, int buttonHeight, int buttonWidth, String buttonText){
        super(buttonText);

        this.buttonColour = buttonColor;
        this.buttonHeight = buttonHeight;
        this.buttonWidth = buttonWidth;

        this.setFont(new Font("Segoe UI Semibold", Font.PLAIN, textSize));
        this.setBounds(200,300,200,30);
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.setAlignmentX(Component.CENTER_ALIGNMENT);

        // all buttons will either be white with pink text or pink with white text
        if(buttonColor.equals(Color.white)){
            this.setForeground(Color.decode("#FF1B3A"));
            setButtonBackgroundImage("res/whiteButton.png");
        }
        else{
            this.setForeground(Color.white);
            setButtonBackgroundImage("res/pinkButton.png");
        }

        //mkae image be the entire button background
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setBackground(null);
        this.setBorder(null);

        //text on top of the background image
        this.setHorizontalTextPosition(JButton.CENTER);
        this.setVerticalTextPosition(JButton.CENTER);

        this.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                updateVisualMouseEntered();
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                updateVisualMouseExit();
            }
        });
    }

    private void updateVisualMouseEntered(){
        if(this.buttonColour.equals(Color.white)){
            setButtonBackgroundImage("res/whiteButtonHover.png"); //shadows
        }
        else{
            setButtonBackgroundImage("res/pinkButtonHover.png"); //shadows
        }
    }

    private void updateVisualMouseExit(){
        if(this.buttonColour.equals(Color.white)){
            setButtonBackgroundImage("res/whiteButton.png");
        }
        else{
            setButtonBackgroundImage("res/pinkButton.png");
        }
    }

    private void setButtonBackgroundImage(String filename){
        ImageIcon tokenImg = new ImageIcon(filename);
        tokenImg.setImage(tokenImg.getImage().getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_DEFAULT));
        this.setIcon(tokenImg);
    }
}
