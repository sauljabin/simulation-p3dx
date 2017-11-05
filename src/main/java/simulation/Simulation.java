/**
 * Copyright (c) 2014 Saúl Piña <sauljabin@gmail.com>, Jorge Parra <thejorgemylio@gmail.com>.
 * <p>
 * This file is part of SimulationP3DX.
 * <p>
 * SimulationP3DX is licensed under The MIT License.
 * For full copyright and license information please see the LICENSE file.
 */

package simulation;

import app.Log;
import app.Translate;
import app.vrep.Client;
import simulation.interfaces.Architecture;

public class Simulation extends Thread {

    public static final long STANDARD_DELAY = 50l;
    private Architecture architecture;
    private boolean stop;
    private long delay;

    public Simulation(long delay, Architecture architecture) {
        this.architecture = architecture;
        this.delay = delay;
        stop = true;
    }

    public Simulation(Architecture architecture) {
        this(STANDARD_DELAY, architecture);
    }

    public long getDelay() {
        return delay;
    }

    public void setDelay(long delay) {
        this.delay = delay;
    }

    public Architecture getArchitecture() {
        return architecture;
    }

    public void setArchitecture(Architecture architecture) {
        this.architecture = architecture;
    }

    @Override
    public void run() {
        while (Client.isConnect() && !stop) {
            architecture.simulate();
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (!Client.isConnect())
            stopSimulation();
    }

    @Override
    public synchronized void start() {
        Log.info(Client.class, Translate.get("INFO_INITSIMULATION") + " "
                + architecture.getClass().getName());
        init();
        stop = false;
        super.start();
    }

    public void stopSimulation() {
        stop = true;
        Log.info(Client.class, Translate.get("INFO_STOPSIMULATION") + " "
                + architecture.getClass().getName());
    }

    public boolean isRunning() {
        return !stop;
    }

    public void init() {
        architecture.init();
    }

}
