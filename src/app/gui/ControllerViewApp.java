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

package app.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import coppelia.CharWA;
import coppelia.IntWA;
import app.Config;
import app.Log;
import app.Main;
import app.Translate;
import app.com.ClassW;
import app.util.UtilClass;
import app.vrep.Client;
import app.vrep.Robot;
import simulation.Simulation;
import simulation.interfaces.Architecture;

public class ControllerViewApp extends WindowAdapter implements ActionListener, ChangeListener {
	private ViewApp viewApp;
	private DefaultComboBoxModel<ClassW> modelCmbArch;
	private Robot robot;
	private IntWA resolution;
	private CharWA pixels;
	private Simulation simulation;

	public ControllerViewApp() {
		viewApp = new ViewApp();
		viewApp.setController(this);
		initState();

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
		else if (source.equals(viewApp.getMenuItemAbout()))
			about();
		else if (source.equals(viewApp.getMenuItemShowConfig()))
			showConfig();
		else if (source.equals(viewApp.getBtnConnect()))
			connect();
		else if (source.equals(viewApp.getBtnDisconnect()))
			disconnect();
		else if (source.equals(viewApp.getBtnStopSimulation()))
			stopSimulation();
		else if (source.equals(viewApp.getBtnStartSimulation()))
			startSimulation();
		else if (source.equals(viewApp.getBtnStop()))
			stop();
		else if (source.equals(viewApp.getBtnForward()))
			forward();
		else if (source.equals(viewApp.getBtnBackward()))
			backward();
		else if (source.equals(viewApp.getBtnRotateL()))
			rotateL();
		else if (source.equals(viewApp.getBtnRotateR()))
			rotateR();
	}

	private void rotateR() {
		viewApp.getSldMotorR().setValue(Robot.intStop);
		robot.setMotorRightSpeed(Robot.intStop);

		viewApp.getSldMotorL().setValue(Robot.intMeanSpeed);
		robot.setMotorLeftSpeed(Robot.intMeanSpeed);
	}

	private void rotateL() {
		viewApp.getSldMotorL().setValue(Robot.intStop);
		robot.setMotorLeftSpeed(Robot.intStop);

		viewApp.getSldMotorR().setValue(Robot.intMeanSpeed);
		robot.setMotorRightSpeed(Robot.intMeanSpeed);
	}

	private void backward() {
		viewApp.getSldMotorLR().setValue(Robot.intMeanSpeedBack);
		viewApp.getSldMotorR().setValue(Robot.intMeanSpeedBack);
		robot.setMotorRightSpeed(Robot.intMeanSpeedBack);

		viewApp.getSldMotorL().setValue(Robot.intMeanSpeedBack);
		robot.setMotorLeftSpeed(Robot.intMeanSpeedBack);
	}

	private void forward() {
		viewApp.getSldMotorLR().setValue(Robot.intMeanSpeed);
		viewApp.getSldMotorR().setValue(Robot.intMeanSpeed);
		robot.setMotorRightSpeed(Robot.intMeanSpeed);

		viewApp.getSldMotorL().setValue(Robot.intMeanSpeed);
		robot.setMotorLeftSpeed(Robot.intMeanSpeed);
	}

	private void stop() {
		viewApp.getSldMotorLR().setValue(Robot.intStop);
		viewApp.getSldMotorR().setValue(Robot.intStop);
		robot.setMotorRightSpeed(Robot.intStop);

		viewApp.getSldMotorL().setValue(Robot.intStop);
		robot.setMotorLeftSpeed(Robot.intStop);
	}

	public void startSimulation() {

		Architecture arch = null;
		try {
			arch = (Architecture) ((ClassW) viewApp.getCmbArch().getSelectedItem()).getValue().newInstance();
		} catch (Exception e) {
			Log.error(Main.class, Translate.get("ERROR_NOARCHINSTANCE"), e);
			e.printStackTrace();
		}
		if (Client.isConnect() && arch != null) {

			viewApp.getBtnStopSimulation().setEnabled(true);
			viewApp.getBtnStartSimulation().setEnabled(false);
			viewApp.getCmbArch().setEnabled(false);

			viewApp.getSldMotorL().setEnabled(false);
			viewApp.getTxtMotorL().setEnabled(false);
			viewApp.getSldMotorR().setEnabled(false);
			viewApp.getTxtMotorR().setEnabled(false);
			viewApp.getSldMotorLR().setEnabled(false);
			viewApp.getTxtMotorLR().setEnabled(false);

			viewApp.getBtnBackward().setEnabled(false);
			viewApp.getBtnForward().setEnabled(false);
			viewApp.getBtnRotateL().setEnabled(false);
			viewApp.getBtnRotateR().setEnabled(false);
			viewApp.getBtnStop().setEnabled(false);

			simulation = new Simulation(((Integer) viewApp.getSpnSimDelay().getValue()).longValue(), arch);
			simulation.start();
		}
	}

	public void stopSimulation() {
		if (simulation == null)
			return;

		simulation.stopSimulation();
		stop();
		viewApp.getBtnStopSimulation().setEnabled(false);
		viewApp.getBtnStartSimulation().setEnabled(true);
		viewApp.getCmbArch().setEnabled(true);

		viewApp.getSldMotorL().setEnabled(true);
		viewApp.getTxtMotorL().setEnabled(true);
		viewApp.getSldMotorR().setEnabled(true);
		viewApp.getTxtMotorR().setEnabled(true);
		viewApp.getSldMotorLR().setEnabled(true);
		viewApp.getTxtMotorLR().setEnabled(true);

		viewApp.getBtnBackward().setEnabled(true);
		viewApp.getBtnForward().setEnabled(true);
		viewApp.getBtnRotateL().setEnabled(true);
		viewApp.getBtnRotateR().setEnabled(true);
		viewApp.getBtnStop().setEnabled(true);

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
		viewApp.getBtnStopSimulation().setEnabled(false);
		viewApp.getBtnStartSimulation().setEnabled(true);
		viewApp.getCmbArch().setEnabled(true);

		viewApp.getSldMotorL().setEnabled(true);
		viewApp.getTxtMotorL().setEnabled(true);
		viewApp.getSldMotorR().setEnabled(true);
		viewApp.getTxtMotorR().setEnabled(true);
		viewApp.getSldMotorLR().setEnabled(true);
		viewApp.getTxtMotorLR().setEnabled(true);

		viewApp.getBtnBackward().setEnabled(true);
		viewApp.getBtnForward().setEnabled(true);
		viewApp.getBtnRotateL().setEnabled(true);
		viewApp.getBtnRotateR().setEnabled(true);
		viewApp.getBtnStop().setEnabled(true);

		robot = Client.getRobot();

		resolution = new IntWA(0);
		pixels = new CharWA(0);
		robot.getCamImageStreaming(resolution, pixels);

		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				while (Client.isConnect()) {

					if (!viewApp.getChbCamera().isSelected())
						continue;

					if (robot.getCamImageBuffer(resolution, pixels)) {
						char[] chars = pixels.getArray();
						int x = resolution.getArray()[0];
						int y = resolution.getArray()[1];

						if (x == 0 || y == 0)
							continue;

						BufferedImage bi = new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);
						for (int i = 0; i < y; i++) {
							for (int j = 0; j < x; j++) {
								int color = chars[((i * x) + j)];
								bi.setRGB(j, y - 1 - i, new Color(color, color, color).getRGB());
							}
						}
						viewApp.getPnlCam().setImage(bi);
					}

					try {
						Thread.sleep(((Integer) viewApp.getSpnCamDelay().getValue()).longValue());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
				disconnect();
			}
		});
		thread.start();
	}

	public void initState() {
		viewApp.getSpnCamDelay().setModel(new SpinnerNumberModel((int) Simulation.STANDARD_DELAY, 1, 1000, 1));
		viewApp.getSpnSimDelay().setModel(new SpinnerNumberModel((int) Simulation.STANDARD_DELAY, 1, 1000, 1));
		viewApp.getPnlCam().setImagePath("img/logo200x200.png");
		viewApp.getBtnDisconnect().setEnabled(false);
		viewApp.getBtnStopSimulation().setEnabled(false);
		viewApp.getBtnStartSimulation().setEnabled(false);
		viewApp.getBtnConnect().setEnabled(true);
		viewApp.getTxtPort().setEnabled(true);
		viewApp.getTxtHost().setEnabled(true);
		viewApp.getTxtMotorL().setEditable(false);
		viewApp.getTxtMotorR().setEditable(false);
		viewApp.getTxtMotorLR().setEditable(false);
		viewApp.getCmbArch().setEnabled(false);

		viewApp.getSldMotorL().setEnabled(false);
		viewApp.getTxtMotorL().setEnabled(false);
		viewApp.getSldMotorR().setEnabled(false);
		viewApp.getTxtMotorR().setEnabled(false);
		viewApp.getSldMotorLR().setEnabled(false);
		viewApp.getTxtMotorLR().setEnabled(false);

		viewApp.getBtnBackward().setEnabled(false);
		viewApp.getBtnForward().setEnabled(false);
		viewApp.getBtnRotateL().setEnabled(false);
		viewApp.getBtnRotateR().setEnabled(false);
		viewApp.getBtnStop().setEnabled(false);

		int maxValue = Robot.intMaxSpeed;
		int minValue = Robot.intMaxSpeedBack;

		viewApp.getSldMotorL().setMaximum(maxValue);
		viewApp.getSldMotorL().setMinimum(minValue);
		viewApp.getSldMotorL().setValue(0);

		viewApp.getSldMotorR().setMaximum(maxValue);
		viewApp.getSldMotorR().setMinimum(minValue);
		viewApp.getSldMotorR().setValue(0);

		viewApp.getSldMotorLR().setMaximum(maxValue);
		viewApp.getSldMotorLR().setMinimum(minValue);
		viewApp.getSldMotorLR().setValue(0);
	}

	public void disconnect() {
		try {
			stopSimulation();
			stop();
			Thread.sleep(500);
		} catch (Exception e) {
			e.printStackTrace();
		}
		initState();
		Client.close();
	}

	@Override
	public void windowClosing(WindowEvent e) {
		close();
	}

	public void close() {
		try {
			stopSimulation();
			stop();
			Thread.sleep(500);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Client.close();
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

	@Override
	public void stateChanged(ChangeEvent e) {
		Object source = e.getSource();
		if (source.equals(viewApp.getSldMotorL())) {
			int valueL = viewApp.getSldMotorL().getValue();
			viewApp.getTxtMotorL().setText(String.valueOf(valueL));
			if (robot != null)
				robot.setMotorLeftSpeed(valueL);
		} else if (source.equals(viewApp.getSldMotorR())) {
			int valueR = viewApp.getSldMotorR().getValue();
			viewApp.getTxtMotorR().setText(String.valueOf(valueR));
			if (robot != null)
				robot.setMotorRightSpeed(valueR);
		} else if (source.equals(viewApp.getSldMotorLR())) {
			viewApp.getTxtMotorLR().setText(String.valueOf(viewApp.getSldMotorLR().getValue()));
			int value = viewApp.getSldMotorLR().getValue();
			viewApp.getSldMotorR().setValue(value);
			viewApp.getSldMotorL().setValue(value);
		} else if (source.equals(viewApp.getSpnSimDelay())) {
			if (simulation != null)
				simulation.setDelay(((Integer) viewApp.getSpnSimDelay().getValue()).longValue());
		}
	}

}
