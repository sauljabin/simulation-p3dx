/**
 * Copyright (c) 2014 Saúl Piña <sauljabin@gmail.com>, Jorge Parra <thejorgemylio@gmail.com>.
 * <p>
 * This file is part of SimulationP3DX.
 * <p>
 * SimulationP3DX is licensed under The MIT License.
 * For full copyright and license information please see the LICENSE file.
 */

package app.gui;

import app.Config;
import app.Translate;
import app.com.ClassW;
import app.util.JImagePanel;
import app.util.JIntegerField;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.Vector;

public class ViewApp extends JFrame {

    private ControllerViewApp controller;
    private Vector<JMenuItem> menuItems;
    private Vector<JButton> buttons;
    private Vector<JSlider> sliders;
    private Vector<JSpinner> sppiners;
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
    private JButton btnStartSimulation;
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
    private JLabel lblCamDelay;
    private JCheckBox chbCamera;
    private JPanel pnlSettings;
    private JSpinner spnCamDelay;
    private JLabel lblCam;
    private JLabel lblSimDelay;
    private JSpinner spnSimDelay;

    public ViewApp() {
        menuItems = new Vector<JMenuItem>();
        buttons = new Vector<JButton>();
        sliders = new Vector<JSlider>();
        sppiners = new Vector<JSpinner>();
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
            menuItems.get(i).addActionListener(controller);
        }
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).addActionListener(controller);
        }
        for (int i = 0; i < sliders.size(); i++) {
            sliders.get(i).addChangeListener(controller);
        }
        for (int i = 0; i < sppiners.size(); i++) {
            sppiners.get(i).addChangeListener(controller);
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

        pnlConnection = new JPanel();
        pnlConnection.setLayout(new MigLayout());
        pnlConnection.setBorder(BorderFactory.createTitledBorder(Translate.get("GUI_CONNECTION")));

        lblHost = new JLabel(Translate.get("GUI_HOST"));
        lblPort = new JLabel(Translate.get("GUI_PORT"));

        txtHost = new JTextField(Config.get("HOST_SERVER"));
        txtPort = new JIntegerField(new Integer(Config.get("HOST_PORT")));

        btnConnect = new JButton(Translate.get("GUI_CONNECT"));
        btnDisconnect = new JButton(Translate.get("GUI_DISCONNECT"));

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

        btnStartSimulation = new JButton(Translate.get("GUI_STARTSIMULATION"));
        btnStopSimulation = new JButton(Translate.get("GUI_STOPSIMULATION"));

        pnlSimulation.add(lblArch, "width 50%");
        pnlSimulation.add(cmbArch, "width 50%, wrap 10");
        pnlSimulation.add(btnStartSimulation, "grow, height 25");
        pnlSimulation.add(btnStopSimulation, "grow, height 25, wrap");

        pnlSettings = new JPanel();
        pnlSettings.setLayout(new MigLayout());
        pnlSettings.setBorder(BorderFactory.createTitledBorder(Translate.get("GUI_SETTINGS")));

        lblSimDelay = new JLabel(Translate.get("GUI_SIMDELAY"));
        spnSimDelay = new JSpinner();

        lblCamDelay = new JLabel(Translate.get("GUI_CAMDELAY"));
        spnCamDelay = new JSpinner();
        lblCam = new JLabel(Translate.get("GUI_CAM"));
        chbCamera = new JCheckBox();

        pnlSettings.add(lblSimDelay, "width 50%");
        pnlSettings.add(spnSimDelay, "width 50%, wrap");
        pnlSettings.add(lblCamDelay, "width 50%");
        pnlSettings.add(spnCamDelay, "width 50%, wrap");
        pnlSettings.add(lblCam, "width 50%");
        pnlSettings.add(chbCamera, "width 50%, wrap");

        pnlWest = new JPanel();
        pnlWest.setLayout(new MigLayout());
        add(pnlWest, BorderLayout.WEST);
        pnlWest.add(pnlConnection, "width 200, wrap 10");
        pnlWest.add(pnlSimulation, "width 200, wrap 10");
        pnlWest.add(pnlSettings, "width 200, wrap 10");

        pnlCenter = new JPanel();
        pnlCenter.setLayout(new MigLayout());
        add(pnlCenter, BorderLayout.CENTER);

        pnlCam = new JImagePanel();
        pnlCam.setLayout(new MigLayout());
        pnlCam.setBorder(BorderFactory.createTitledBorder(Translate.get("GUI_CAM")));

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
        sldMotorLR.setBorder(BorderFactory.createTitledBorder(null, Translate.get("GUI_MOTORLR"), TitledBorder.CENTER, TitledBorder.TOP, new Font(sldMotorLR.getFont().getName(), sldMotorLR.getFont().getStyle(), 10), Color.BLUE));

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
        pnlCenter.add(pnlCam, "width 200, height 200, wrap 10");
        pnlCenter.add(pnlTeleoperation, "grow, wrap 10");

        pnlSouth = new JPanel();
        pnlSouth.setLayout(new MigLayout());
        add(pnlSouth, BorderLayout.SOUTH);

        JScrollPane scrollPanelConsole = new JScrollPane();
        pnlSouth.add(scrollPanelConsole, "width 100%, height 100");
        tarConsole = new JTextArea();
        scrollPanelConsole.setViewportView(tarConsole);

        menuItems.add(menuItemShowConfig);
        menuItems.add(menuItemClose);
        menuItems.add(menuItemAbout);

        sliders.add(sldMotorL);
        sliders.add(sldMotorR);
        sliders.add(sldMotorLR);

        buttons.add(btnStartSimulation);
        buttons.add(btnStopSimulation);
        buttons.add(btnConnect);
        buttons.add(btnDisconnect);
        buttons.add(btnBackward);
        buttons.add(btnForward);
        buttons.add(btnRotateL);
        buttons.add(btnRotateR);
        buttons.add(btnStop);

        sppiners.add(spnCamDelay);
        sppiners.add(spnSimDelay);
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

    public JImagePanel getPnlCam() {
        return pnlCam;
    }

    public JButton getBtnStartSimulation() {
        return btnStartSimulation;
    }

    public JButton getBtnStopSimulation() {
        return btnStopSimulation;
    }

    public JSlider getSldMotorL() {
        return sldMotorL;
    }

    public JSlider getSldMotorR() {
        return sldMotorR;
    }

    public JSlider getSldMotorLR() {
        return sldMotorLR;
    }

    public JButton getBtnForward() {
        return btnForward;
    }

    public JButton getBtnBackward() {
        return btnBackward;
    }

    public JButton getBtnRotateL() {
        return btnRotateL;
    }

    public JButton getBtnRotateR() {
        return btnRotateR;
    }

    public JButton getBtnStop() {
        return btnStop;
    }

    public JTextField getTxtMotorL() {
        return txtMotorL;
    }

    public JTextField getTxtMotorR() {
        return txtMotorR;
    }

    public JTextField getTxtMotorLR() {
        return txtMotorLR;
    }

    public JCheckBox getChbCamera() {
        return chbCamera;
    }

    public JSpinner getSpnCamDelay() {
        return spnCamDelay;
    }

    public JSpinner getSpnSimDelay() {
        return spnSimDelay;
    }

}
