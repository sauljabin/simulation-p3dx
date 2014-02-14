package simulation.interfaces;

import coppelia.remoteApi;

public interface Architecture {
	public void simulate(remoteApi vrep, int clientId);
}
