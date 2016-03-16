/**
 * Copyright (c) 2014 Saúl Piña <sauljabin@gmail.com>, Jorge Parra <thejorgemylio@gmail.com>.
 * 
 * This file is part of SimulationP3DX.
 * 
 * SimulationP3DX is licensed under The MIT License.
 * For full copyright and license information please see the LICENSE file.
 */

package app.util;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.Serializable;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class JImagePanel extends JPanel implements Serializable {

	private static final long serialVersionUID = -4037621125161878010L;
	private String imagePath;
	private Image image;

	public void setImageIcon(ImageIcon icon) {
		setImage(icon.getImage());
	}

	public ImageIcon getImageIcon() {
		return new ImageIcon(image);
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
				setImage(new ImageIcon(getClass().getResource(imagePath)).getImage());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public JImagePanel() {

	}

	public JImagePanel(String imagePath) {
		setImagePath(imagePath);
	}

	public JImagePanel(Image image) {
		setImage(image);
	}

	@Override
	public void paintComponent(Graphics g) {
		if (image != null)
			g.drawImage(image, 11, 16, getWidth()-22, getHeight()-22, null);
		setOpaque(false);
		super.paintComponent(g);
	}

}
