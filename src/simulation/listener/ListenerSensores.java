package simulation.listener;

import java.util.concurrent.Semaphore;

public class ListenerSensores extends Thread {

	private Semaphore sSensores;
	private boolean value;
	
	public ListenerSensores(Semaphore sSensores) {
		super();
		this.sSensores = sSensores;
		value = true;
	}

	@Override
	public void run() {
		while(true){
			
			revisarSensores();
		}
	}
	
	private void revisarSensores() {
		try {
			sSensores.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//value = revisarSensor();
		sSensores.release();
	}

	public boolean isValue(){
		return value;
	}

}
