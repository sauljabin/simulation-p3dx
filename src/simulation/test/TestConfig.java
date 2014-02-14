package simulation.test;

import java.io.FileNotFoundException;
import java.io.IOException;

import simulation.app.Config;

public class TestConfig {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		Config.load();

		System.out.println(Config.get("OS"));
		System.out.println(Config.get("ARCH"));
		System.out.println(Config.get("APP_NAME"));
		
		Config.save();
	}

}
