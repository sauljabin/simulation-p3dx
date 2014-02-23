/**
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 * 
 *		SAUL PIÑA - SAULJP07@GMAIL.COM
 *		JORGE PARRA - THEJORGEMYLIO@GMAIL.COM
 *		2014
 */

package app.vrep;

import coppelia.CharWA;
import coppelia.IntWA;

public class Robot {
	public static final float pi = 3.1415f;

	public static final float floatstop = 0f;

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

	public void setMotorLeftSpeed(int speed) {
		Client.setMotorSpeed(motorLeftId, pi * speed / 10);
	}

	public void setMotorRightSpeed(int speed) {
		Client.setMotorSpeed(motorRightId, pi * speed / 10);
	}

	public boolean getCamImageStrimming(IntWA resolution, CharWA pixels) {
		return Client.getCamImageStrimming(sensorVisionId, resolution, pixels);
	}

	public boolean getCamImageBuffer(IntWA resolution, CharWA pixels) {
		return Client.getCamImageBuffer(sensorVisionId, resolution, pixels);
	}
}
