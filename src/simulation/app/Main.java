package simulation.app;

import simulation.architecture.Reactive;
import simulation.connection.Client;
import simulation.interfaces.Architecture;

public class Main {

	public static void main(String[] args) {
		try {
			Config.load();
			LoadLibrary.load();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		Log.info(Main.class, "Librerias y configuracion cargadas");

		Architecture arch = new Reactive();
		Client client = new Client(Config.get("HOST_SERVER"), Integer.parseInt(Config.get("HOST_PORT")), arch);
		client.start();
	}

}
