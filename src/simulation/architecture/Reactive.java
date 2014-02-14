package simulation.architecture;

import coppelia.remoteApi;
import simulation.behavior.Avoid;
import simulation.behavior.Escape;
import simulation.behavior.Move;
import simulation.interfaces.Architecture;
import simulation.interfaces.Behavior;

public class Reactive implements Architecture {

	private Behavior avoid;
	private Behavior escape;
	private Behavior move;

	public Reactive() {
		avoid = new Avoid();
		escape = new Escape();
		move = new Move();
	}

	@Override
	public void simulate(remoteApi vrep, int clientId) {
		boolean bumper = true;
		boolean sonar = true;

		if (bumper)
			escape.run(vrep, clientId);
		else if (sonar)
			avoid.run(vrep, clientId);
		else if (!sonar)
			move.run(vrep, clientId);
	}
}
