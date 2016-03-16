/**
 * Copyright (c) 2014 Saúl Piña <sauljabin@gmail.com>, Jorge Parra <thejorgemylio@gmail.com>.
 * 
 * This file is part of SimulationP3DX.
 * 
 * SimulationP3DX is licensed under The MIT License.
 * For full copyright and license information please see the LICENSE file.
 */

package app.vrep;

import app.Log;
import app.Translate;
import coppelia.BoolW;
import coppelia.CharWA;
import coppelia.FloatWA;
import coppelia.IntW;
import coppelia.IntWA;
import coppelia.remoteApi;

public class Client {
	private static int clientId;
	private static remoteApi vrep;
	private static String host;
	private static int port;
	private static Robot robot;

	public int getClientId() {
		return clientId;
	}

	public remoteApi getVrep() {
		return vrep;
	}

	public String getHost() {
		return host;
	}

	public int getPort() {
		return port;
	}

	public static boolean connect(String host, int port) {
		vrep = new remoteApi();
		vrep.simxFinish(-1);
		clientId = vrep.simxStart(host, port, true, true, 5000, 5);
		if (clientId != -1) {
			Log.info(Client.class, Translate.get("INFO_SUCCESSFULCONN"));
			robot = new Robot();
		} else {
			Log.info(Client.class, Translate.get("INFO_UNSUCCESSFULCONN"));
		}
		return clientId != -1;
	}

	public static boolean isConnect() {
		if (vrep == null)
			return false;
		return vrep.simxGetConnectionId(clientId) != -1;
	}

	public static void close() {
		Log.info(Client.class, Translate.get("INFO_CLOSECONN"));
		if (isConnect()) {
			vrep.simxFinish(-1);
			robot = null;
			vrep = null;
		}
	}

	public static void statusBarMessage(String msg) {
		if (isConnect())
			vrep.simxAddStatusbarMessage(clientId, msg, remoteApi.simx_opmode_oneshot);
	}

	public static int getObject(String name) {
		if (!isConnect())
			return 0;
		IntW intW = new IntW(0);
		vrep.simxGetObjectHandle(clientId, name, intW, remoteApi.simx_opmode_oneshot_wait);
		return intW.getValue();
	}

	public static void setMotorSpeed(int object, float speed) {
		if (!isConnect())
			return;
		vrep.simxSetJointTargetVelocity(clientId, object, speed, remoteApi.simx_opmode_oneshot);
	}

	public static boolean getCamImageStreaming(int object, IntWA resolution, CharWA pixels) {
		if (!isConnect())
			return false;
		int errorCode = vrep.simxGetVisionSensorImage(clientId, object, resolution, pixels, remoteApi.sim_object_camera_type, remoteApi.simx_opmode_streaming_split + 4000);
		return errorCode == remoteApi.simx_error_noerror;
	}

	public static boolean getCamImageBuffer(int object, IntWA resolution, CharWA pixels) {
		if (!isConnect())
			return false;
		int errorCode = vrep.simxGetVisionSensorImage(clientId, object, resolution, pixels, remoteApi.sim_object_camera_type, remoteApi.simx_opmode_buffer);
		return errorCode == remoteApi.simx_error_noerror;
	}

	public static boolean readProximitySensorStreaming(int object, BoolW detectionState, FloatWA detectedPoint, IntW detectedObjectHandle, FloatWA detectedSurfaceNormalVector) {
		if (!isConnect())
			return false;
		int errorCode = vrep.simxReadProximitySensor(clientId, object, detectionState, detectedPoint, detectedObjectHandle, detectedSurfaceNormalVector, remoteApi.simx_opmode_streaming_split + 4000);
		return errorCode == remoteApi.simx_error_noerror;
	}

	public static boolean readProximitySensorBuffer(int object, BoolW detectionState, FloatWA detectedPoint, IntW detectedObjectHandle, FloatWA detectedSurfaceNormalVector) {
		if (!isConnect())
			return false;
		int errorCode = vrep.simxReadProximitySensor(clientId, object, detectionState, detectedPoint, detectedObjectHandle, detectedSurfaceNormalVector, remoteApi.simx_opmode_buffer);
		return errorCode == remoteApi.simx_error_noerror;
	}

	public static Robot getRobot() {
		return robot;
	}

}
