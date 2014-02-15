package simulation.behavior;

import coppelia.IntW;
import coppelia.remoteApi;
import simulation.interfaces.Behavior;
import simulation.interfaces.Const;

public class Move implements Behavior {

	@Override
	public void run(remoteApi vrep, int clientId) {
		IntW leftMotor = new IntW(0);
		IntW rightMotor = new IntW(0);
		vrep.simxGetObjectHandle(clientId, "p3_leftJoint_", leftMotor, remoteApi.simx_opmode_oneshot);
		vrep.simxGetObjectHandle(clientId, "p3_rightJoint_", rightMotor, remoteApi.simx_opmode_oneshot);

		vrep.simxSetJointTargetVelocity(clientId, leftMotor.getValue(), Const.lowSpeed, remoteApi.simx_opmode_oneshot);
		vrep.simxSetJointTargetVelocity(clientId, rightMotor.getValue(), Const.lowSpeed, remoteApi.simx_opmode_oneshot);

	}

}
