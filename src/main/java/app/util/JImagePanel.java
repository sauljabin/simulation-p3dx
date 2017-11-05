/**
 * Copyright (c) 2014 Saúl Piña <sauljabin@gmail.com>, Jorge Parra <thejorgemylio@gmail.com>.
 * <p>
 * This file is part of SimulationP3DX.
 * <p>
 * SimulationP3DX is licensed under The MIT License.
 * For full copyright and license information please see the LICENSE file.
 */

package app.util;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.Serializable;

public class JImagePanel extends JPanel implements Serializable {

    private String imagePath;
    private Image image;

    public JImagePanel() {

    }

    public JImagePanel(String imagePath) {
        setImagePath(imagePath);
    }

    public JImagePanel(Image image) {
        setImage(image);
    }

    public ImageIcon getImageIcon() {
        return new ImageIcon(image);
    }

    public void setImageIcon(ImageIcon icon) {
        setImage(icon.getImage());
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
        repaint();
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
        File file = new File(imagePath);
        if (file.exists()) {
            try {
                setImage(new ImageIcon(imagePath).getImage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                setImage(new ImageIcon(ClassLoader.getSystemResource(imagePath)).getImage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        if (image != null)
            g.drawImage(image, 11, 16, getWidth() - 22, getHeight() - 22, null);
        setOpaque(false);
        super.paintComponent(g);
    }

}
