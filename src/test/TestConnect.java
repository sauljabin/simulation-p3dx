package test;

import java.io.FileNotFoundException;
import java.io.IOException;

import app.Config;
import app.LoadLibrary;
import coppelia.IntW;
import coppelia.IntWA;
import coppelia.remoteApi;

public class TestConnect {

	public static void test1() {

		System.out.println("Program started");
		remoteApi vrep = new remoteApi();
		vrep.simxFinish(-1); // just in case, close all opened connections
		int clientID = vrep.simxStart("127.0.0.1", 19999, true, true, 5000, 5);
		if (clientID != -1) {
			System.out.println("Connected to remote API server");
			IntWA objectHandles = new IntWA(1);
			int ret = vrep.simxGetObjects(clientID, vrep.sim_handle_all, objectHandles, vrep.simx_opmode_oneshot_wait);
			if (ret == vrep.simx_error_noerror)
				System.out.format("Number of objects in the scene: %d\n", objectHandles.getArray().length);
			else
				System.out.format("Remote API function call returned with error code: %d\n", ret);
			vrep.simxFinish(clientID);
		} else
			System.out.println("Failed connecting to remote API server");
		System.out.println("Program ended");
	}

	public static void test2() {
	}

	public static void main(String[] args) throws FileNotFoundException, IOException {

		Config.load();
		LoadLibrary.load();
		test2();
		
		
	}
}
