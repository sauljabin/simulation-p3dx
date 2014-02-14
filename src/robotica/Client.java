package robotica;

import app.Log;
import app.Main;
import robotica.interfaces.Architecture;
import coppelia.remoteApi;

public class Client extends Thread {
	private int clientId;
	private remoteApi vrep;
	private Architecture architecture;
	private String host;
	private int port;

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public remoteApi getVrep() {
		return vrep;
	}

	public void setVrep(remoteApi vrep) {
		this.vrep = vrep;
	}

	public Architecture getArchitecture() {
		return architecture;
	}

	public void setArchitecture(Architecture architecture) {
		this.architecture = architecture;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public Client(String host, int port, Architecture architecture) {
		vrep = new remoteApi();
		clientId = vrep.simxStart(host, 19999, true, true, 5000, 5);
		this.architecture = architecture;
	}

	@Override
	public void run() {
		while (vrep.simxGetConnectionId(clientId) != -1) {
			architecture.simulate(clientId);
		}
		vrep.simxFinish(clientId);
	}

	@Override
	public synchronized void start() {
		Log.info(Main.class, "Iniciando cliente arquitectura: "+architecture.getClass().getName());
		super.start();
	}

}
