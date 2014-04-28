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

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.net.URI;

import javax.swing.JLabel;

public class JUrlLinkLabel extends JLabel implements Serializable {

	private static final long serialVersionUID = 7594812715384885615L;

	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setText(String text, String url) {
		setUrl(url);
		setText(text);
	}

	public JUrlLinkLabel(String text, String url) {
		setText(text);
		setUrl(url);
		setForeground(Color.BLUE);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				goUrl();
			}

		});
	}

	public JUrlLinkLabel(String url) {
		this(url, url);
	}

	public JUrlLinkLabel() {
		this("URL", "");
	}

	public void goUrl() {
		try {
			Desktop.getDesktop().browse(new URI(url));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
