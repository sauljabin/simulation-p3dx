/**
 * Copyright (c) 2014 Saúl Piña <sauljabin@gmail.com>, Jorge Parra <thejorgemylio@gmail.com>.
 * 
 * This file is part of SimulationP3DX.
 * 
 * SimulationP3DX is licensed under The MIT License.
 * For full copyright and license information please see the LICENSE file.
 */

package app.vrep;

import coppelia.BoolW;
import coppelia.CharWA;
import coppelia.IntWA;

public class Robot {
	public static final float pi = 3.1415f;

	public static final float floatStop = 0f;

	public static final float floatVeryLowSpeed = pi * 0.1f;
	public static final float floatLowSpeed = pi * 0.5f;
	public static final float floatMeanSpeed = pi;
	public static final float floatHighSpeed = pi * 1.5f;
	public static final float floatMaxSpeed = pi * 2f;

	public static final float floatVeryLowSpeedBack = -floatVeryLowSpeed;
	public static final float floatLowSpeedBack = -floatLowSpeed;
	public static final float floatMeanSpeedBack = -floatMeanSpeed;
	public static final float floatHighSpeedBack = -floatHighSpeed;
	public static final float floatMaxSpeedBack = -floatMaxSpeed;

	public static final int intStop = 0;

	public static final int intVeryLowSpeed = 1;
	public static final int intLowSpeed = 5;
	public static final int intMeanSpeed = 10;
	public static final int intHighSpeed = 15;
	public static final int intMaxSpeed = 20;

	public static final int intVeryLowSpeedBack = -intVeryLowSpeed;
	public static final int intLowSpeedBack = -intLowSpeed;
	public static final int intMeanSpeedBack = -intMeanSpeed;
	public static final int intHighSpeedBack = -intHighSpeed;
	public static final int intMaxSpeedBack = -intMaxSpeed;

	public static final String motorLeft = "p3_leftJoint_";
	public static final String motorRight = "p3_rightJoint_";
	public static final String sensorVision = "Vision_sensor";
	public static final String sensorUltrasonic0 = "p3_ultrasonicSensor_0";
	public static final String sensorUltrasonic1 = "p3_ultrasonicSensor_1";
	public static final String sensorUltrasonic2 = "p3_ultrasonicSensor_2";
	public static final String sensorUltrasonic3 = "p3_ultrasonicSensor_3";
	public static final String sensorUltrasonic4 = "p3_ultrasonicSensor_4";
	public static final String sensorUltrasonic5 = "p3_ultrasonicSensor_5";
	public static final String sensorUltrasonic6 = "p3_ultrasonicSensor_6";
	public static final String sensorUltrasonic7 = "p3_ultrasonicSensor_7";

	private int motorLeftId;
	private int motorRightId;
	private int sensorVisionId;
	private int sensorUltrasonic0Id;
	private int sensorUltrasonic1Id;
	private int sensorUltrasonic2Id;
	private int sensorUltrasonic3Id;
	private int sensorUltrasonic4Id;
	private int sensorUltrasonic5Id;
	private int sensorUltrasonic6Id;
	private int sensorUltrasonic7Id;

	public int getSensorUltrasonic0Id() {
		return sensorUltrasonic0Id;
	}

	public int getSensorUltrasonic1Id() {
		return sensorUltrasonic1Id;
	}

	public int getSensorUltrasonic2Id() {
		return sensorUltrasonic2Id;
	}

	public int getSensorUltrasonic3Id() {
		return sensorUltrasonic3Id;
	}

	public int getSensorUltrasonic4Id() {
		return sensorUltrasonic4Id;
	}

	public int getSensorUltrasonic5Id() {
		return sensorUltrasonic5Id;
	}

	public int getSensorUltrasonic6Id() {
		return sensorUltrasonic6Id;
	}

	public int getSensorUltrasonic7Id() {
		return sensorUltrasonic7Id;
	}

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
		sensorUltrasonic0Id = Client.getObject(sensorUltrasonic0);
		sensorUltrasonic1Id = Client.getObject(sensorUltrasonic1);
		sensorUltrasonic2Id = Client.getObject(sensorUltrasonic2);
		sensorUltrasonic3Id = Client.getObject(sensorUltrasonic3);
		sensorUltrasonic4Id = Client.getObject(sensorUltrasonic4);
		sensorUltrasonic5Id = Client.getObject(sensorUltrasonic5);
		sensorUltrasonic6Id = Client.getObject(sensorUltrasonic6);
		sensorUltrasonic7Id = Client.getObject(sensorUltrasonic7);
	}

	public void setMotorLeftSpeed(float speed) {
		Client.setMotorSpeed(motorLeftId, speed);
	}

	public void setMotorRightSpeed(float speed) {
		Client.setMotorSpeed(motorRightId, speed);
	}

	public void setMotorLeftSpeed(int speed) {
		Client.setMotorSpeed(motorLeftId, pi * speed / 10);
	}

	public void setMotorRightSpeed(int speed) {
		Client.setMotorSpeed(motorRightId, pi * speed / 10);
	}

	public boolean getCamImageStreaming(IntWA resolution, CharWA pixels) {
		return Client.getCamImageStreaming(sensorVisionId, resolution, pixels);
	}

	public boolean getCamImageBuffer(IntWA resolution, CharWA pixels) {
		return Client.getCamImageBuffer(sensorVisionId, resolution, pixels);
	}

	public boolean getDetectionSensorUltrasonicBuffer(int id, BoolW detected) {
		boolean operation = Client.readProximitySensorBuffer(id, detected, null, null, null);
		if (!operation)
			return false;
		return detected.getValue();
	}

	public boolean getDetectionSensorUltrasonicStreaming(int id, BoolW detected) {
		boolean operation = Client.readProximitySensorStreaming(id, detected, null, null, null);
		if (!operation)
			return false;
		return detected.getValue();
	}

}
