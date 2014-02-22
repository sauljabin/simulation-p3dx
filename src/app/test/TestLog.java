package app.test;

import app.Log;

public class TestLog {
	public static void main(String[] args) {
		try {
			throw new Exception();
		} catch (Exception e) {
			Log.error(TestLog.class, "Test Log 1", e);
		}
		Log.info(TestLog.class, "Test Log 2");
	}
}
