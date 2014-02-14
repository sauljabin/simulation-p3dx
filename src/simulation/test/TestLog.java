package simulation.test;

import simulation.app.Log;

public class TestLog {
	public static void main(String[] args) {
		try {
			throw new Exception();
		} catch (Exception e) {
			Log.info(TestLog.class, "hello", e);
		}
		Log.info(TestLog.class, "hello");
	}
}
