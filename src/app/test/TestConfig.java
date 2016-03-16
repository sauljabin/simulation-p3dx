/**
 * Copyright (c) 2014 Saúl Piña <sauljabin@gmail.com>, Jorge Parra <thejorgemylio@gmail.com>.
 * 
 * This file is part of SimulationP3DX.
 * 
 * SimulationP3DX is licensed under The MIT License.
 * For full copyright and license information please see the LICENSE file.
 */

package app.test;

import java.io.FileNotFoundException;
import java.io.IOException;

import app.Config;

public class TestConfig {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		Config.load();

		System.out.println(Config.get("OS"));
		System.out.println(Config.get("OS_ARCH"));
		System.out.println(Config.get("APP_NAME"));
		
		Config.save();
	}

}
