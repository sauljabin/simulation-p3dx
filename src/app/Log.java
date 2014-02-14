package app;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import coppelia.remoteApi;
import util.UtilDate;

public class Log {

	private static void print(String type, Class<?> clazz, String msg, Exception e) {
		String string = String.format("%s:\t%s\nTIME:\t%s\n---->\t%s\n", type, clazz.getName(), UtilDate.nowFormat("yyyy-MM-dd HH:mm"), msg);
		System.out.print(string);
		if (e != null)
			e.printStackTrace(System.out);
		System.out.println();

		File folder = new File("log");
		folder.mkdir();
		FileOutputStream fs;
		OutputStreamWriter os;
		BufferedWriter bw;
		try {
			fs = new FileOutputStream(folder.getPath() + "/" + UtilDate.nowString() + ".log", true);
			os = new OutputStreamWriter(fs, "utf-8");
			bw = new BufferedWriter(os);
			bw.write(string);
			bw.flush();
			if (e != null)
				e.printStackTrace(new PrintWriter(os));
			bw.write("\n");
			bw.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public static void info(Class<?> clazz, String msg) {
		print("INFO", clazz, msg, null);
	}

	public static void info(Class<?> clazz, String msg, Exception e) {
		print("INFO", clazz, msg, e);
	}
	
	public static void info(Class<?> clazz, String msg, remoteApi vrep, int clientId) {
		print("INFO", clazz, msg, null);
	}

	public static void info(Class<?> clazz, String msg, Exception e, remoteApi vrep, int clientId) {
		print("INFO", clazz, msg, e);
	}

	public static void error(Class<?> clazz, String msg) {
		print("ERRO", clazz, msg, null);
	}

	public static void error(Class<?> clazz, String msg, Exception e) {
		print("ERRO", clazz, msg, e);
	}

	public static void warning(Class<?> clazz, String msg) {
		print("WARN", clazz, msg, null);
	}

	public static void warning(Class<?> clazz, String msg, Exception e) {
		print("WAR", clazz, msg, e);
	}

}