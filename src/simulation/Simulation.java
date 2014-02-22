package simulation;

import simulation.interfaces.Architecture;
import app.Log;
import app.Translate;
import app.vrep.Client;

public class Simulation extends Thread {

	private Architecture architecture;

	public Architecture getArchitecture() {
		return architecture;
	}

	public void setArchitecture(Architecture architecture) {
		this.architecture = architecture;
	}

	public Simulation(Architecture architecture) {
		this.architecture = architecture;
	}

	@Override
	public void run() {
		while (Client.isConnect()) {
			architecture.simulate();
		}
	}

	@Override
	public synchronized void start() {
		Log.info(Client.class, Translate.get("INFO_INITSIMULATION") + " " + architecture.getClass().getName());
		init();
		super.start();
	}

	public void init() {
		architecture.init();
	}

}
