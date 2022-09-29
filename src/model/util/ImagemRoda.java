package model.util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

import static javax.imageio.ImageIO.*;

public class ImagemRoda extends JPanel {




    public static BufferedImage rotateImage(BufferedImage imageToRotate, int angulo) {
        int widthOfImage = imageToRotate.getWidth();
        int heightOfImage = imageToRotate.getHeight();
        int typeOfImage = imageToRotate.getType();

        BufferedImage newImageFromBuffer = new BufferedImage(widthOfImage, heightOfImage, typeOfImage);

        Graphics2D graphics2D = newImageFromBuffer.createGraphics();

        graphics2D.rotate(Math.toRadians(angulo), widthOfImage / 2, heightOfImage / 2);
        graphics2D.drawImage(imageToRotate, null, 0, 0);

        return newImageFromBuffer;
    }


    public static ImageIcon giraAviao(int angulo){
        ImageIcon img = null;
        try{


        BufferedImage imgoriginal = ImageIO.read(new File("src\\model\\media\\aviao.png"));
        var imagemRodada = rotateImage(imgoriginal, angulo);
         img = new ImageIcon(imagemRodada);
        }catch (IOException e){
            System.out.println(e);
        }
        return img;
    }


}
