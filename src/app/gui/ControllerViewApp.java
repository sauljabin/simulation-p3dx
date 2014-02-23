package app.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;

import coppelia.CharWA;
import coppelia.IntWA;
import app.Config;
import app.Log;
import app.Translate;
import app.com.ClassW;
import app.util.UtilClass;
import app.vrep.Client;
import app.vrep.Robot;
import simulation.interfaces.Architecture;

public class ControllerViewApp extends WindowAdapter implements ActionListener {
	private ViewApp viewApp;
	private DefaultComboBoxModel<ClassW> modelCmbArch;
	private Robot robot;
	private IntWA resolution;
	private CharWA pixels;

	public ControllerViewApp() {
		viewApp = new ViewApp();
		viewApp.setController(this);
		viewApp.getBtnDisconnect().setEnabled(false);
		Log.setLogTextArea(viewApp.getTarConsole());
		Vector<ClassW> architectures = UtilClass.getSubClass(Architecture.class);
		modelCmbArch = new DefaultComboBoxModel<ClassW>(architectures);
		viewApp.getCmbArch().setModel(modelCmbArch);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source.equals(viewApp.getMenuItemClose()))
			close();
		if (source.equals(viewApp.getMenuItemAbout()))
			about();
		if (source.equals(viewApp.getMenuItemShowConfig()))
			showConfig();
		if (source.equals(viewApp.getBtnConnect()))
			connect();
		if (source.equals(viewApp.getBtnDisconnect()))
			disconnect();
	}

	public void connect() {

		Integer port = viewApp.getTxtPort().getValue();
		String host = viewApp.getTxtHost().getText();

		Config.set("HOST_SERVER", host);
		Config.set("HOST_PORT", port.toString());
		try {
			Config.save();
		} catch (Exception e) {
			Log.error(ControllerViewApp.class, Translate.get("ERROR_NOSAVECONFIG"), e);
		}
		if (!Client.connect(host, port))
			return;

		viewApp.getBtnDisconnect().setEnabled(true);
		viewApp.getBtnConnect().setEnabled(false);
		viewApp.getTxtPort().setEnabled(false);
		viewApp.getTxtHost().setEnabled(false);

		robot = new Robot();
		resolution = new IntWA(0);
		pixels = new CharWA(0);
		robot.getCamImageStrimming(resolution, pixels);

		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				while (Client.isConnect()) {
					if (robot.getCamImageBuffer(resolution, pixels)) {
						char[] chars = pixels.getArray();
						int x = resolution.getArray()[0];
						int y = resolution.getArray()[1];

						BufferedImage bi = new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);
						for (int i = 0; i < y; i++) {
							for (int j = 0; j < x; j++) {
								int color = chars[((i * x) + j)];
								bi.setRGB(j, y - 1 - i, new Color(color, color, color).getRGB());
							}
						}
						viewApp.getPnlCam().setImage(bi);
						viewApp.getPnlCam().repaint();
					}
				}
				disconnect();
			}
		});
		thread.start();
	}

	public void disconnect() {
		viewApp.getBtnDisconnect().setEnabled(false);
		viewApp.getBtnConnect().setEnabled(true);
		viewApp.getTxtPort().setEnabled(true);
		viewApp.getTxtHost().setEnabled(true);
		Client.close();
	}

	@Override
	public void windowClosing(WindowEvent e) {
		close();
	}

	public void close() {
		viewApp.dispose();
		System.exit(0);
	}

	public void about() {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				new ViewAbout();
			}
		});
		thread.start();
	}

	public void showConfig() {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				new ViewConfig();
			}
		});
		thread.start();
	}

}
