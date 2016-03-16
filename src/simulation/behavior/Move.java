/**
 * Copyright (c) 2014 Saúl Piña <sauljabin@gmail.com>, Jorge Parra <thejorgemylio@gmail.com>.
 * 
 * This file is part of SimulationP3DX.
 * 
 * SimulationP3DX is licensed under The MIT License.
 * For full copyright and license information please see the LICENSE file.
 */

package simulation.behavior;

import app.vrep.Client;
import app.vrep.Robot;
import simulation.interfaces.Behavior;

public class Move implements Behavior {

	private Robot robot;

	@Override
	public void simulate() {
		robot.setMotorLeftSpeed(Robot.floatMeanSpeed);
		robot.setMotorRightSpeed(Robot.floatMeanSpeed);
	}

	@Override
	public void init() {
		robot = Client.getRobot();
	}

}
