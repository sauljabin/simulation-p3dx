package robotica.architecture;

import coppelia.remoteApi;
import robotica.interfaces.Architecture;

public class Reactive implements Architecture {

	private remoteApi vrep;

	public Reactive(){
		vrep = new remoteApi();
	}
	
	@Override
	public void simulate(int clientId) {
		vrep.simxAddStatusbarMessage(clientId, "hola", remoteApi.simx_opmode_oneshot_wait);
	}

}
