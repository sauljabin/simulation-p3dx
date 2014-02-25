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

package simulation.architecture;

import coppelia.BoolW;
import app.vrep.Client;
import app.vrep.Robot;
import simulation.behavior.Avoid;
import simulation.behavior.Move;
import simulation.interfaces.Architecture;
import simulation.interfaces.Behavior;

public class Reactive implements Architecture {

	private Behavior avoid;
	private Behavior move;
	private Robot robot;
	private BoolW detectionSensorUltrasonic3;

	@Override
	public void simulate() {
		robot.getDetectionSensorUltrasonicBuffer(robot.getSensorUltrasonic3Id(), detectionSensorUltrasonic3);
		if (detectionSensorUltrasonic3.getValue()) {
			avoid.simulate();
		} else {
			move.simulate();
		}
	}

	@Override
	public void init() {
		robot = Client.getRobot();
		detectionSensorUltrasonic3 = new BoolW(false);
		robot.getDetectionSensorUltrasonicStreaming(robot.getSensorUltrasonic3Id(), detectionSensorUltrasonic3);

		avoid = new Avoid();
		avoid.init();

		move = new Move();
		move.init();
	}
}
