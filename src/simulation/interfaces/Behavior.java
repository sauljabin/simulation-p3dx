package simulation.interfaces;

import coppelia.remoteApi;

public interface Behavior {
	public void run(remoteApi vrep, int clientId);
}
