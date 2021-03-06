/**
 * Copyright (c) 2014 Saúl Piña <sauljabin@gmail.com>, Jorge Parra <thejorgemylio@gmail.com>.
 * <p>
 * This file is part of SimulationP3DX.
 * <p>
 * SimulationP3DX is licensed under The MIT License.
 * For full copyright and license information please see the LICENSE file.
 */

package app;

import app.com.ClassW;
import app.gui.ControllerViewApp;
import app.util.UtilClass;
import app.util.UtilIn;
import app.vrep.Client;
import app.vrep.Robot;
import simulation.Simulation;
import simulation.interfaces.Architecture;

import javax.swing.*;
import java.util.Vector;

public class Main {

    public static void main(String[] args) {
        if (args.length > 0) {
            for (String arg : args) {
                switch (arg) {
                    case "-console":
                        commandConsole();
                        break;
                    case "-help":
                    default:
                        commandHelp();
                        break;
                }
            }
        } else {
            initGUI();
        }
    }

    private static void commandConsole() {
        loadFeatures();
        System.out.println(Config.get("APP_NAME"));
        System.out.println(Translate.get("CONSOLE_MSGSELECTARCH"));

        Vector<ClassW> architectures = UtilClass.getSubClass(Architecture.class);

        for (int i = 0; i < architectures.size(); i++) {
            System.out.println(String.format("%d: %s", i + 1, architectures.get(i)));
        }

        Integer select = null;
        do {
            select = UtilIn.readInteger(String.format("%s: ", Translate.get("CONSOLE_MSGSETNUMBER")));
        } while (select == null || select > architectures.size() || select <= 0);
        System.out.println();

        Architecture arch = null;
        try {
            arch = (Architecture) architectures.get(select - 1).getValue().newInstance();
        } catch (Exception e) {
            Log.error(Main.class, Translate.get("ERROR_NOARCHINSTANCE"), e);
            System.exit(0);
        }
        Simulation simulation = null;
        if (Client.connect(Config.get("HOST_SERVER"), Integer.parseInt(Config.get("HOST_PORT")))) {
            simulation = new Simulation(arch);
            simulation.start();
        } else {
            System.exit(0);
        }

        String command = "";

        do {
            command = UtilIn.readString(Translate.get("CONSOLE_MSGQTOEXIT") + "\n");
        } while (!command.equals("q"));

        try {
            simulation.stopSimulation();
            Thread.sleep(500);
            Client.getRobot().setMotorRightSpeed(Robot.intStop);
            Client.getRobot().setMotorLeftSpeed(Robot.intStop);
            Thread.sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Client.close();

    }

    private static void commandHelp() {
        loadFeatures();
        System.out.println(Config.get("APP_NAME"));
        System.out.println(String.format("%10s\t%s", "-help", Translate.get("CONSOLE_MSGSHOWHELP")));
        System.out.println(String.format("%10s\t%s", "-console", Translate.get("CONSOLE_MSGEXECTCONSOLE")));
        System.out.println(String.format("%10s\t%s", "-gui", Translate.get("CONSOLE_MSGEXECTGUI")));
        System.out.println(String.format("%10s\t%s", "", Translate.get("CONSOLE_MSGPARAMEMPTY")));
        System.out.println();
    }

    private static void loadFeatures() {
        try {
            Config.load();
            Translate.load();
        } catch (Exception e) {
            Log.error(Main.class, "loadFeatures()", e);
            System.exit(0);
        }
    }

    private static void loadFeaturesGUI() {
        try {
            Config.load();
            Translate.load();
        } catch (Exception e) {
            Log.error(Main.class, "loadFeaturesGUI()", e);
            JOptionPane.showMessageDialog(null, "loadFeaturesGUI()", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    private static void initGUI() {
        loadFeaturesGUI();
        SwingUtilities.invokeLater(() -> new ControllerViewApp());
    }

}
