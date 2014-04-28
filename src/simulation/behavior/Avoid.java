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
