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

import simulation.behavior.Avoid;
import simulation.behavior.AvoidL;
import simulation.behavior.AvoidR;
import simulation.behavior.Move;
import simulation.interfaces.Architecture;
import simulation.interfaces.Behavior;
import app.vrep.Client;
import app.vrep.Robot;
import coppelia.BoolW;

public class Reactive extends Thread implements Architecture {

	private Avoid avoidR;
	private Avoid avoidL;
	private Behavior move;
	private Robot robot;
	private BoolW detectionSensorUltrasonicR3;
	private BoolW detectionSensorUltrasonicR2;
	private BoolW detectionSensorUltrasonicR1;
	private BoolW detectionSensorUltrasonicR0;
	private BoolW detectionSensorUltrasonicL0;
	private BoolW detectionSensorUltrasonicL1;
	private BoolW detectionSensorUltrasonicL2;
	private BoolW detectionSensorUltrasonicL3;
	private boolean avoiding;
	private boolean forwarding;
	private int step;

	@Override
	public void simulate() {
		getDetectionSensorUltrasonicBuffer();
		if (detectionSensorUltrasonicR0.getValue()
				|| detectionSensorUltrasonicR1.getValue()
				|| detectionSensorUltrasonicR2.getValue()
				|| detectionSensorUltrasonicR3.getValue()
				|| detectionSensorUltrasonicL0.getValue()
				|| detectionSensorUltrasonicL1.getValue()
				|| detectionSensorUltrasonicL2.getValue()
				|| detectionSensorUltrasonicL3.getValue()) {
			System.out.println("GIRAR");
			step = (int) (Math.random() * 10.0);
			avoid();
		} else if (avoiding || forwarding) {
			System.out.println("SEGUIR");
			step--;
			System.out.println(step);
			if (step <= 0) {
				avoiding = false;
				forwarding = false;
			}
		} else {
			avoiding = false;
			forwarding = true;
			step = (int) (Math.random() * 10.0);
			move.simulate();
			System.out.println("AVANZAR");
		}
		
	}

	@Override
	public void init() {
		forwarding = false;
		avoiding = false;
		step = 50;
		robot = Client.getRobot();
		initDetectionSensorUltrasonic();
		getDetectionSensorUltrasonicStreaming();

		avoidR = new AvoidR();
		avoidR.init();

		avoidL = new AvoidL();
		avoidL.init();

		move = new Move();
		move.init();
	}

	private void avoid() {
		forwarding = false;
		avoiding = true;
		int v = (int) Math.ceil(Math.random() * 4);
		avoidL.setSpeed(v);
		avoidR.setSpeed(v);
		if (Math.random() < 0.5) {
			if (detectionSensorUltrasonicR0.getValue()
					|| detectionSensorUltrasonicR1.getValue()
					|| detectionSensorUltrasonicR2.getValue()
					|| detectionSensorUltrasonicR3.getValue()) {
				avoidL.simulate();
			} else if (detectionSensorUltrasonicL0.getValue()
					|| detectionSensorUltrasonicL1.getValue()
					|| detectionSensorUltrasonicL2.getValue()
					|| detectionSensorUltrasonicL3.getValue()) {
				avoidR.simulate();
			}
		} else {
			if (detectionSensorUltrasonicL0.getValue()
					|| detectionSensorUltrasonicL1.getValue()
					|| detectionSensorUltrasonicL2.getValue()
					|| detectionSensorUltrasonicL3.getValue()) {
				avoidR.simulate();
			} else if (detectionSensorUltrasonicR0.getValue()
					|| detectionSensorUltrasonicR1.getValue()
					|| detectionSensorUltrasonicR2.getValue()
					|| detectionSensorUltrasonicR3.getValue()) {
				avoidL.simulate();
			}
		}
	}

	private void initDetectionSensorUltrasonic() {
		detectionSensorUltrasonicR0 = new BoolW(false);
		detectionSensorUltrasonicR1 = new BoolW(false);
		detectionSensorUltrasonicR2 = new BoolW(false);
		detectionSensorUltrasonicR3 = new BoolW(false);
		detectionSensorUltrasonicL0 = new BoolW(false);
		detectionSensorUltrasonicL1 = new BoolW(false);
		detectionSensorUltrasonicL2 = new BoolW(false);
		detectionSensorUltrasonicL3 = new BoolW(false);
	}

	private void getDetectionSensorUltrasonicBuffer() {
		System.out.println(robot.getDetectionSensorUltrasonicBuffer(
				robot.getSensorUltrasonic0Id(), detectionSensorUltrasonicR3));
		System.out.println(robot.getDetectionSensorUltrasonicBuffer(
				robot.getSensorUltrasonic1Id(), detectionSensorUltrasonicR2));
		System.out.println(robot.getDetectionSensorUltrasonicBuffer(
				robot.getSensorUltrasonic2Id(), detectionSensorUltrasonicR1));
		System.out.println(robot.getDetectionSensorUltrasonicBuffer(
				robot.getSensorUltrasonic3Id(), detectionSensorUltrasonicR0));
		System.out.println(robot.getDetectionSensorUltrasonicBuffer(
				robot.getSensorUltrasonic4Id(), detectionSensorUltrasonicL0));
		System.out.println(robot.getDetectionSensorUltrasonicBuffer(
				robot.getSensorUltrasonic5Id(), detectionSensorUltrasonicL1));
		System.out.println(robot.getDetectionSensorUltrasonicBuffer(
				robot.getSensorUltrasonic6Id(), detectionSensorUltrasonicL2));
		System.out.println(robot.getDetectionSensorUltrasonicBuffer(
				robot.getSensorUltrasonic7Id(), detectionSensorUltrasonicL3));
	}

	private void getDetectionSensorUltrasonicStreaming() {
		robot.getDetectionSensorUltrasonicStreaming(
				robot.getSensorUltrasonic0Id(), detectionSensorUltrasonicR3);
		robot.getDetectionSensorUltrasonicStreaming(
				robot.getSensorUltrasonic1Id(), detectionSensorUltrasonicR2);
		robot.getDetectionSensorUltrasonicStreaming(
				robot.getSensorUltrasonic2Id(), detectionSensorUltrasonicR1);
		robot.getDetectionSensorUltrasonicStreaming(
				robot.getSensorUltrasonic3Id(), detectionSensorUltrasonicR0);
		robot.getDetectionSensorUltrasonicStreaming(
				robot.getSensorUltrasonic4Id(), detectionSensorUltrasonicL0);
		robot.getDetectionSensorUltrasonicStreaming(
				robot.getSensorUltrasonic5Id(), detectionSensorUltrasonicL1);
		robot.getDetectionSensorUltrasonicStreaming(
				robot.getSensorUltrasonic6Id(), detectionSensorUltrasonicL2);
		robot.getDetectionSensorUltrasonicStreaming(
				robot.getSensorUltrasonic7Id(), detectionSensorUltrasonicL3);
	}
}
