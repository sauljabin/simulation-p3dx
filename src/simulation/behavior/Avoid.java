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

public abstract class Avoid implements Behavior{

	protected Robot robot;
	protected int speed;
	
	@Override
	public void init() {
		robot = Client.getRobot();
	}
	
	public void setSpeed(int speed){
		this.speed = speed;
	}
	
}
