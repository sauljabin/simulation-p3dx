package app.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingUtilities;

import app.Config;
import app.Log;
import app.Translate;
import app.com.ClassW;
import app.util.UtilClass;
import simulation.interfaces.Architecture;

public class ControllerViewApp extends WindowAdapter implements ActionListener {
	private ViewApp viewApp;
	private DefaultComboBoxModel<ClassW> modelCmbArch;

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
		viewApp.getBtnDisconnect().setEnabled(true);
		viewApp.getBtnConnect().setEnabled(false);
		viewApp.getTxtPort().setEnabled(false);
		viewApp.getTxtHost().setEnabled(false);

		Integer port = viewApp.getTxtPort().getValue();
		String host = viewApp.getTxtHost().getText();

		Config.set("HOST_SERVER", host);
		Config.set("HOST_PORT", port.toString());
		try {
			Config.save();
		} catch (Exception e) {
			Log.error(ControllerViewApp.class, Translate.get("ERROR_NOSAVECONFIG"), e);
		}

	}

	public void disconnect() {
		viewApp.getBtnDisconnect().setEnabled(false);
		viewApp.getBtnConnect().setEnabled(true);
		viewApp.getTxtPort().setEnabled(true);
		viewApp.getTxtHost().setEnabled(true);

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
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new ViewAbout();
			}
		});
	}

	public void showConfig() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new ViewConfig();
			}
		});
	}

}
