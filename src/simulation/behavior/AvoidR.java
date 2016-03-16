/**
 * Copyright (c) 2014 Saúl Piña <sauljabin@gmail.com>, Jorge Parra <thejorgemylio@gmail.com>.
 * 
 * This file is part of SimulationP3DX.
 * 
 * SimulationP3DX is licensed under The MIT License.
 * For full copyright and license information please see the LICENSE file.
 */

package simulation.behavior;

import app.vrep.Robot;

public class AvoidR extends Avoid {

	@Override
	public void simulate() {
		float valueLeft = 0;
		float valueRight = 0;
		if (speed == 1) {
			valueLeft = Robot.floatVeryLowSpeed;
			valueRight = Robot.floatVeryLowSpeedBack;
		} else if (speed == 2) {
			valueLeft = Robot.floatLowSpeed;
			valueRight = Robot.floatLowSpeedBack;
		} else if (speed == 3) {
			valueLeft = Robot.floatMeanSpeed;
			valueRight = Robot.floatMeanSpeedBack;
		} else if (speed == 4) {
			valueLeft = Robot.floatHighSpeed;
			valueRight = Robot.floatHighSpeedBack;
		}
		robot.setMotorLeftSpeed(valueLeft);
		robot.setMotorRightSpeed(valueRight);
	}

}
