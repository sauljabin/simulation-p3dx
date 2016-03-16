/**
 * Copyright (c) 2014 Saúl Piña <sauljabin@gmail.com>, Jorge Parra <thejorgemylio@gmail.com>.
 * 
 * This file is part of SimulationP3DX.
 * 
 * SimulationP3DX is licensed under The MIT License.
 * For full copyright and license information please see the LICENSE file.
 */

package app;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Vector;

public class Library {

	public static void load() throws FileNotFoundException {

		String path = "";

		if (Library.is32Bit())
			path = Config.get("LIB_PATH_32");

		if (Library.is64Bit())
			path = Config.get("LIB_PATH_64");

		if (Library.isLinux())
			path += Config.get("LIB_LINUX");

		if (Library.isWindows())
			path += Config.get("LIB_WINDOWS");

		File file = new File(path);
		if (!file.exists())
			throw new FileNotFoundException("Api v-rep library not found");

		System.load(file.getAbsolutePath());
	}

	public static boolean isLinux() {
		return Config.get("OS").toLowerCase().contains("linux");
	}

	public static boolean isWindows() {
		return Config.get("OS").toLowerCase().contains("windows");
	}

	public static boolean is32Bit() {
		Vector<String> strings = new Vector<String>();
		strings.add("x86");
		strings.add("i386");

		for (String string : strings) {
			if (Config.get("OS_ARCH").toLowerCase().contains(string))
				return true;
		}

		return false;
	}

	public static boolean is64Bit() {
		Vector<String> strings = new Vector<String>();
		strings.add("x64");
		strings.add("amd64");

		for (String string : strings) {
			if (Config.get("OS_ARCH").toLowerCase().contains(string))
				return true;
		}

		return false;
	}

}
