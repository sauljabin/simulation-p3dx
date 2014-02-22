package simulation.architecture;

import simulation.behavior.Avoid;
import simulation.behavior.Move;
import simulation.interfaces.Architecture;
import simulation.interfaces.Behavior;

public class Reactive implements Architecture {

	private Behavior avoid;
	private Behavior move;

	@Override
	public void simulate() {
		move.simulate();
		avoid.simulate();
	}

	@Override
	public void init() {
		avoid = new Avoid();
		move = new Move();
		move.init();
		avoid.init();
	}
}
