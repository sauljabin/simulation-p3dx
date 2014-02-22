package simulation.behavior;

import app.vrep.Robot;
import simulation.interfaces.Behavior;

public class Move implements Behavior {

	private Robot robot;

	@Override
	public void simulate() {
		robot.setMotorLeftSpeed(Robot.maxSpeed);
		robot.setMotorRightSpeed(Robot.backMaxSpeed);
	}

	@Override
	public void init() {
		robot = new Robot();
	}

}
