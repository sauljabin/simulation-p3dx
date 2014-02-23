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

package app.test;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;

import app.Config;
import app.Library;
import app.util.UtilImage;
import coppelia.CharWA;
import coppelia.IntW;
import coppelia.IntWA;
import coppelia.remoteApi;

public class TestConnect {

	public static void test1() {

		System.out.println("Program started");
		remoteApi vrep = new remoteApi();
		vrep.simxFinish(-1);
		int clientID = vrep.simxStart("127.0.0.1", 20000, true, true, 5000, 5);
		if (clientID != -1) {
			System.out.println("Connected to remote API server");
			IntWA objectHandles = new IntWA(1);
			int ret = vrep.simxGetObjects(clientID, remoteApi.sim_handle_all, objectHandles, remoteApi.simx_opmode_oneshot_wait);
			if (ret == remoteApi.simx_error_noerror)
				System.out.format("Number of objects in the scene: %d\n", objectHandles.getArray().length);
			else
				System.out.format("Remote API function call returned with error code: %d\n", ret);
			vrep.simxFinish(clientID);
		} else
			System.out.println("Failed connecting to remote API server");
		System.out.println("Program ended");
	}

	public static void test2() {
		remoteApi vrep = new remoteApi();
		vrep.simxFinish(-1);
		int clientId = vrep.simxStart("127.0.0.1", 20000, true, true, 5000, 5);
		IntW visionSensor = new IntW(0);
		IntWA resolution = new IntWA(0);
		CharWA image = new CharWA(0);
		vrep.simxGetObjectHandle(clientId, "Vision_sensor", visionSensor, remoteApi.simx_opmode_oneshot_wait);
		vrep.simxGetVisionSensorImage(clientId, visionSensor.getValue(), resolution, image, remoteApi.sim_object_camera_type, remoteApi.simx_opmode_streaming_split + 4000);

		while (vrep.simxGetConnectionId(clientId) != -1) {
			int errorCode = vrep.simxGetVisionSensorImage(clientId, visionSensor.getValue(), resolution, image, remoteApi.sim_object_camera_type, remoteApi.simx_opmode_buffer);

			if (errorCode == remoteApi.simx_error_noerror) {
				char[] chars = image.getArray();
				int x = resolution.getArray()[0];
				int y = resolution.getArray()[1];
				try {
					BufferedImage bi = new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);
					for (int i = 0; i < y; i++) {
						for (int j = 0; j < x; j++) {
							int color = chars[((i * x) + j)];

							bi.setRGB(j, y - 1 - i, new Color(color, color, color).getRGB());
						}
					}

					UtilImage.writeImage(bi, "test.png");

					BufferedImage bi2 = new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);
					for (int i = 0; i < y; i++) {
						for (int j = 0; j < x; j++) {
							int color = chars[((i * x) + j)];
							bi2.setRGB(j, i, color);
						}
					}

					UtilImage.writeImage(bi2, "test2.png");
				} catch (IOException e) {
					e.printStackTrace();
				}
				vrep.simxFinish(clientId);
				System.exit(0);
			}
		}
		vrep.simxFinish(clientId);

	}

	public static void main(String[] args) throws FileNotFoundException, IOException {

		Config.load();
		Library.load();
		test2();

	}
}
