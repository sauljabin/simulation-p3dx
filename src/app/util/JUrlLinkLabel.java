/**
 * Copyright (c) 2014 Saúl Piña <sauljabin@gmail.com>, Jorge Parra <thejorgemylio@gmail.com>.
 * 
 * This file is part of SimulationP3DX.
 * 
 * SimulationP3DX is licensed under The MIT License.
 * For full copyright and license information please see the LICENSE file.
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
