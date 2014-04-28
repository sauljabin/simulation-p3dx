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

package simulation;

import simulation.interfaces.Architecture;
import app.Log;
import app.Translate;
import app.vrep.Client;

public class Simulation extends Thread {

	private Architecture architecture;
	private boolean stop;
	private long delay;

	public static final long STANDARD_DELAY = 50l;

	public long getDelay() {
		return delay;
	}

	public void setDelay(long delay) {
		this.delay = delay;
	}

	public Architecture getArchitecture() {
		return architecture;
	}

	public void setArchitecture(Architecture architecture) {
		this.architecture = architecture;
	}

	public Simulation(long delay, Architecture architecture) {
		this.architecture = architecture;
		this.delay = delay;
		stop = true;
	}

	public Simulation(Architecture architecture) {
		this(STANDARD_DELAY, architecture);
	}

	@Override
	public void run() {
		while (Client.isConnect() && !stop) {
			architecture.simulate();
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if (!Client.isConnect())
			stopSimulation();
	}

	@Override
	public synchronized void start() {
		Log.info(Client.class, Translate.get("INFO_INITSIMULATION") + " "
				+ architecture.getClass().getName());
		init();
		stop = false;
		super.start();
	}

	public void stopSimulation() {
		stop = true;
		Log.info(Client.class, Translate.get("INFO_STOPSIMULATION") + " "
				+ architecture.getClass().getName());
	}

	public boolean isRunning() {
		return !stop;
	}

	public void init() {
		architecture.init();
	}

}
