package app.vrep;

import coppelia.CharWA;
import coppelia.IntWA;

public class Robot {
	public static final float pi = 3.1415f;
	public static final float stop = 0f;
	public static final float veryLowSpeed = pi * 0.1f;
	public static final float lowSpeed = pi * 0.5f;
	public static final float meanSpeed = pi;
	public static final float highSpeed = pi * 1.5f;
	public static final float maxSpeed = pi * 2f;
	public static final float backVeryLowSpeed = -pi * 0.1f;
	public static final float backLowSpeed = -pi * 0.5f;
	public static final float backMeanSpeed = -pi;
	public static final float backHighSpeed = -pi * 1.5f;
	public static final float backMaxSpeed = -pi * 2f;

	public static final String motorLeft = "p3_leftJoint_";
	public static final String motorRight = "p3_rightJoint_";
	public static final String sensorVision = "Vision_sensor";

	private int motorLeftId;
	private int motorRightId;
	private int sensorVisionId;

	public int getMotorLeftId() {
		return motorLeftId;
	}

	public int getMotorRightId() {
		return motorRightId;
	}

	public int getSensorVisionId() {
		return sensorVisionId;
	}

	public Robot() {
		motorLeftId = Client.getObject(motorLeft);
		motorRightId = Client.getObject(motorRight);
		sensorVisionId = Client.getObject(sensorVision);
	}

	public void setMotorLeftSpeed(float speed) {
		Client.setMotorSpeed(motorLeftId, speed);
	}

	public void setMotorRightSpeed(float speed) {
		Client.setMotorSpeed(motorRightId, speed);
	}

	public boolean getCamImageStrimming(IntWA resolution, CharWA pixels) {
		return Client.getCamImageStrimming(sensorVisionId, resolution, pixels);
	}

	public boolean getCamImageBuffer(IntWA resolution, CharWA pixels) {
		return Client.getCamImageBuffer(sensorVisionId, resolution, pixels);
	}
}
