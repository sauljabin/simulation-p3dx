package test;

import app.Log;

public class TestLog {
	public static void main(String[] args) {
		try {
			throw new Exception();
		} catch (Exception e) {
			Log.info(TestLog.class, "hola",e);
		}
		Log.info(TestLog.class, "hola");
		
		Log.info(TestLog.class, "hola2");
		
		Log.warning(TestLog.class, "Error aqui");
		Log.error(TestLog.class, "Error aqui 2");
	}
}
