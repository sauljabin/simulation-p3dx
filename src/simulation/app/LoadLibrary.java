package simulation.app;

import java.io.File;
import java.io.FileNotFoundException;

public class LoadLibrary {

	public static void load() throws FileNotFoundException {

		String path = "";
		String extension = "";

		if (LoadLibrary.is32Bit())
			path = Config.get("LIB_PATH_32");

		if (LoadLibrary.is64Bit())
			path = Config.get("LIB_PATH_64");

		if (LoadLibrary.isLinux()) {
			path += Config.get("LIB_LINUX");
			extension += ".so";
		}
		if (LoadLibrary.isWindows()) {
			path += Config.get("LIB_WINDOWS");
			extension += ".dll";
		}

		File file = new File(path + extension);
		if (!file.exists())
			throw new FileNotFoundException("Api v-rep library not found");

		System.loadLibrary(path);
	}

	public static boolean isLinux() {
		return Config.get("OS").toLowerCase().contains("linux");
	}

	public static boolean isWindows() {
		return Config.get("OS").toLowerCase().contains("windows");
	}

	public static boolean is32Bit() {
		return Config.get("ARCH").toLowerCase().contains("x86");
	}

	public static boolean is64Bit() {
		return Config.get("ARCH").toLowerCase().contains("x64");
	}

}
