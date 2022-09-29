package model.util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

public class JPanelCartesiano extends JPanel {

    @Override
    protected void paintComponent(Graphics graphics){
        try{
            Graphics2D g2d = (Graphics2D) graphics;


            BufferedImage bfI = ImageIO.read(new File("src\\model\\media\\plano.png"));
            Image iR = bfI.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT);
            g2d.drawImage(iR, 0, 0, this);

        }catch (Exception e){
            System.out.println(e);
        }
    }
}
