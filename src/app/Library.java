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

		for (String string : strings) {
			if (Config.get("OS_ARCH").toLowerCase().contains(string))
				return true;
		}

		return false;
	}

}
