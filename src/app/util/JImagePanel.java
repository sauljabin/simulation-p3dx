/**
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 * 
 *		SAUL PIÑA - SAULJP07@GMAIL.COM
 *		JORGE PARRA - THEJORGEMYLIO@GMAIL.COM
 *		2014
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