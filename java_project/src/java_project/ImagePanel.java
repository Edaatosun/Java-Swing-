package java_project;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class ImagePanel extends JPanel {
    private ImageIcon imageIcon;

    public ImagePanel(String imagePath) {
        File file = new File(imagePath);
        if (file.exists()) {
            this.imageIcon = new ImageIcon(imagePath);
        } else {
            System.err.println("Dosya bulunamadı: " + imagePath);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imageIcon != null) {
            Image image = imageIcon.getImage();
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        }
    }
}