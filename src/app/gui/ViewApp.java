package app.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;

import app.Config;
import app.Translate;
import app.com.ClassW;
import net.miginfocom.swing.MigLayout;

public class ViewApp extends JFrame {

	private static final long serialVersionUID = 4806248606059318670L;

	private ControllerViewApp controller;
	private Vector<JMenuItem> menuItems;
	private Vector<JButton> buttons;
	private JMenuBar menuBar;
	private JMenu menuOptions;
	private JMenuItem menuItemShowConfig;
	private JMenuItem menuItemClose;
	private JMenu menuHelp;
	private JMenuItem menuItemAbout;
	private JPanel pnlConnection;
	private JLabel lblHost;
	private JLabel lblPort;
	private JTextField txtHost;
	private JIntegerField txtPort;
	private JPanel pnlWest;
	private JButton btnConnect;
	private JButton btnDisconnect;
	private JPanel pnlSouth;
	private JTextArea tarConsole;
	private JPanel pnlCenter;
	private JPanel pnlSimulation;
	private JPanel pnlTeleoperation;
	private JLabel lblArch;
	private JComboBox<ClassW> cmbArch;
	private JButton btnStarSimulation;
	private JButton btnStopSimulation;
	private JSlider sldMotorL;
	private JSlider sldMotorR;
	private JSlider sldMotorLR;
	private JButton btnForward;
	private JButton btnBackward;
	private JButton btnRotateL;
	private JButton btnRotateR;
	private JButton btnStop;
	private JPanel pnlButtonsTeleoperation;
	private JPanel pnlMotorsTeleoperation;
	private JTextField txtMotorL;
	private JTextField txtMotorR;
	private JTextField txtMotorLR;

	private JImagePanel pnlCam;

	public ViewApp() {
		menuItems = new Vector<JMenuItem>();
		buttons = new Vector<JButton>();
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		init();
		setLocationRelativeTo(this);
		setVisible(true);
	}

	public ControllerViewApp getController() {
		return controller;
	}

	public void setController(ControllerViewApp controller) {
		this.controller = controller;
		for (int i = 0; i < menuItems.size(); i++) {
			menuItems.elementAt(i).addActionListener(controller);
		}
		for (int i = 0; i < buttons.size(); i++) {
			buttons.elementAt(i).addActionListener(controller);
		}
		this.addWindowListener(controller);
	}

	private void init() {
		setLayout(new BorderLayout());
		setSize(600, 650);
		setTitle(Config.get("APP_NAME"));

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		menuOptions = new JMenu(Translate.get("GUI_OPTIONS"));
		menuBar.add(menuOptions);

		menuItemShowConfig = new JMenuItem(Translate.get("GUI_SHOWCONFIG"));
		menuOptions.add(menuItemShowConfig);

		menuItemClose = new JMenuItem(Translate.get("GUI_CLOSE"));
		menuOptions.add(menuItemClose);

		menuHelp = new JMenu(Translate.get("GUI_HELP"));
		menuBar.add(menuHelp);

		menuItemAbout = new JMenuItem(Translate.get("GUI_ABOUT"));
		menuHelp.add(menuItemAbout);

		menuItems.add(menuItemShowConfig);
		menuItems.add(menuItemClose);
		menuItems.add(menuItemAbout);

		pnlConnection = new JPanel();
		pnlConnection.setLayout(new MigLayout());
		pnlConnection.setBorder(BorderFactory.createTitledBorder(Translate.get("GUI_CONNECTION")));

		lblHost = new JLabel(Translate.get("GUI_HOST"));
		lblPort = new JLabel(Translate.get("GUI_PORT"));

		txtHost = new JTextField(Config.get("HOST_SERVER"));
		txtPort = new JIntegerField(new Integer(Config.get("HOST_PORT")));

		btnConnect = new JButton(Translate.get("GUI_CONNECT"));
		btnDisconnect = new JButton(Translate.get("GUI_DISCONNECT"));
		buttons.add(btnConnect);
		buttons.add(btnDisconnect);

		pnlConnection.add(lblHost, "width 50%");
		pnlConnection.add(txtHost, "width 50%, wrap");
		pnlConnection.add(lblPort, "grow");
		pnlConnection.add(txtPort, "grow, wrap 10");
		pnlConnection.add(btnConnect, "grow, height 25");
		pnlConnection.add(btnDisconnect, "grow, height 25, wrap");

		pnlSimulation = new JPanel();
		pnlSimulation.setLayout(new MigLayout());
		pnlSimulation.setBorder(BorderFactory.createTitledBorder(Translate.get("GUI_SIMULATION")));

		lblArch = new JLabel(Translate.get("GUI_ARCH"));
		cmbArch = new JComboBox<ClassW>();

		btnStarSimulation = new JButton(Translate.get("GUI_STARTSIMULATION"));
		btnStopSimulation = new JButton(Translate.get("GUI_STOPSIMULATION"));
		buttons.add(btnStarSimulation);
		buttons.add(btnStopSimulation);

		pnlSimulation.add(lblArch, "width 50%");
		pnlSimulation.add(cmbArch, "width 50%, wrap 10");
		pnlSimulation.add(btnStarSimulation, "grow, height 25");
		pnlSimulation.add(btnStopSimulation, "grow, height 25, wrap");
		
		pnlCam=new JImagePanel();
		pnlCam.setLayout(new MigLayout());
		pnlCam.setBorder(BorderFactory.createTitledBorder(Translate.get("GUI_CAM")));
		pnlCam.setImagePath("img/logo200x200.png");

		pnlWest = new JPanel();
		pnlWest.setLayout(new MigLayout());
		add(pnlWest, BorderLayout.WEST);
		pnlWest.add(pnlConnection, "width 200, wrap 10");
		pnlWest.add(pnlSimulation, "width 200, wrap 10");
		pnlWest.add(pnlCam, "width 200, height 200, wrap 10");

		pnlCenter = new JPanel();
		pnlCenter.setLayout(new MigLayout());
		add(pnlCenter, BorderLayout.CENTER);

		pnlTeleoperation = new JPanel();
		pnlTeleoperation.setLayout(new MigLayout());
		pnlTeleoperation.setBorder(BorderFactory.createTitledBorder(Translate.get("GUI_TELEOPERATION")));

		btnForward = new JButton(Translate.get("GUI_FORWARD"));
		btnBackward = new JButton(Translate.get("GUI_BACKWARD"));
		btnRotateL = new JButton(Translate.get("GUI_ROTATEL"));
		btnRotateR = new JButton(Translate.get("GUI_ROTATER"));
		btnStop = new JButton(Translate.get("GUI_STOP"));
		btnStop.setForeground(Color.RED);

		pnlButtonsTeleoperation = new JPanel();
		pnlButtonsTeleoperation.setLayout(new MigLayout());
		pnlButtonsTeleoperation.setBorder(BorderFactory.createTitledBorder(Translate.get("GUI_ACTION")));

		pnlButtonsTeleoperation.add(btnForward, "width 100, height 25, wrap 10");
		pnlButtonsTeleoperation.add(btnBackward, "grow, height 25, wrap 10");
		pnlButtonsTeleoperation.add(btnRotateL, "grow, height 25, wrap 10");
		pnlButtonsTeleoperation.add(btnRotateR, "grow, height 25, wrap 10");
		pnlButtonsTeleoperation.add(btnStop, "grow, height 25, wrap 10");

		sldMotorL = new JSlider(JSlider.VERTICAL);
		sldMotorL.setBorder(BorderFactory.createTitledBorder(null, Translate.get("GUI_MOTORL"), TitledBorder.CENTER, TitledBorder.TOP));

		sldMotorR = new JSlider(JSlider.VERTICAL);
		sldMotorR.setBorder(BorderFactory.createTitledBorder(null, Translate.get("GUI_MOTORR"), TitledBorder.CENTER, TitledBorder.TOP));

		sldMotorLR = new JSlider(JSlider.VERTICAL);
		sldMotorLR.setBorder(BorderFactory.createTitledBorder(null, Translate.get("GUI_MOTORLR"), TitledBorder.CENTER, TitledBorder.TOP, null, Color.BLUE));

		txtMotorL = new JTextField();
		txtMotorR = new JTextField();
		txtMotorLR = new JTextField();

		pnlMotorsTeleoperation = new JPanel();
		pnlMotorsTeleoperation.setLayout(new MigLayout());
		pnlMotorsTeleoperation.setBorder(BorderFactory.createTitledBorder(Translate.get("GUI_MOTORS")));

		pnlMotorsTeleoperation.add(sldMotorL);
		pnlMotorsTeleoperation.add(sldMotorR);
		pnlMotorsTeleoperation.add(sldMotorLR, "gapleft 10, wrap 10");
		pnlMotorsTeleoperation.add(txtMotorL, "gapleft 2, width 34");
		pnlMotorsTeleoperation.add(txtMotorR, "gapleft 2, width 34");
		pnlMotorsTeleoperation.add(txtMotorLR, "gapleft 12, width 34");

		pnlTeleoperation.add(pnlMotorsTeleoperation, "height 200");
		pnlTeleoperation.add(pnlButtonsTeleoperation, "height 200");
		pnlCenter.add(pnlTeleoperation, "width 100%, wrap 10");

		pnlSouth = new JPanel();
		pnlSouth.setLayout(new MigLayout());
		add(pnlSouth, BorderLayout.SOUTH);

		JScrollPane scrollPanel = new JScrollPane();
		pnlSouth.add(scrollPanel, "width 100%, height 100");
		tarConsole = new JTextArea();
		scrollPanel.setViewportView(tarConsole);
	}

	public JMenuItem getMenuItemClose() {
		return menuItemClose;
	}

	public JMenuItem getMenuItemAbout() {
		return menuItemAbout;
	}

	public JMenuItem getMenuItemShowConfig() {
		return menuItemShowConfig;
	}

	public JButton getBtnConnect() {
		return btnConnect;
	}

	public JButton getBtnDisconnect() {
		return btnDisconnect;
	}

	public JTextField getTxtHost() {
		return txtHost;
	}

	public JIntegerField getTxtPort() {
		return txtPort;
	}

	public JTextArea getTarConsole() {
		return tarConsole;
	}

	public JComboBox<ClassW> getCmbArch() {
		return cmbArch;
	}

}
