package app.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UtilIn {

	public static String readString(String msg) {
		System.out.printf("%s", msg);
		InputStreamReader input = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(input);
		try {
			return br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String readString() {
		return readString("");
	}

	public static Integer readInteger(String msg) {
		System.out.printf("%s", msg);
		InputStreamReader input = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(input);
		try {
			String s = br.readLine();
			if (s.matches("[0-9]+"))
				return new Integer(s);
			else
				return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Integer readInteger() {
		return readInteger("");
	}
}
