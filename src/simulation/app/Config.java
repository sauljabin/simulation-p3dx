package simulation.app;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

public class Config {

	private static Properties properties = new Properties();
	public static String configPath = "CONFIG";

	public static void load() throws FileNotFoundException, IOException {
		properties.load(new FileInputStream(configPath));
		properties.put("OS", System.getProperty("os.name"));
		properties.put("ARCH", System.getProperty("os.arch"));
	}

	public static String get(String key) {
		return properties.getProperty(key);
	}

	public static Enumeration<Object> getKeys() {
		return properties.keys();
	}

	public static void set(String key, String value) {
		properties.put(key, value);
	}

	public static void save() throws FileNotFoundException, IOException {
		properties.store(new FileOutputStream(configPath), "CONFIG");
	}
	

}
