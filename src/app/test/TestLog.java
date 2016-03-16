/**
 * Copyright (c) 2014 Saúl Piña <sauljabin@gmail.com>, Jorge Parra <thejorgemylio@gmail.com>.
 * 
 * This file is part of SimulationP3DX.
 * 
 * SimulationP3DX is licensed under The MIT License.
 * For full copyright and license information please see the LICENSE file.
 */

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
