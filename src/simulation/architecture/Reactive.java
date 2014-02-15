package simulation.architecture;

import coppelia.IntW;
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
		move.run(vrep, clientId);
	}
}
